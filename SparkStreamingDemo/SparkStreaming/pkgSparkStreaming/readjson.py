from __future__ import print_function
import random
import sys

from pyspark.sql.context import SQLContext
from pyspark import SparkConf, SparkContext
from pyspark.streaming import StreamingContext
from pyspark.streaming.kafka import KafkaUtils
from kafka import KafkaProducer
from gremlin_python.driver import client, serializer

producer = KafkaProducer(bootstrap_servers='localhost:9092')
client = client.Client('wss://sparkkafka.gremlin.cosmosdb.azure.com:443/', 'g',
                       username="/dbs/sparkkafka_db/colls/sparkkafka_graph",
                       password="zRY7IuiIF4mBXdKAiKQlZpdKQvT7v0La8HyGE6DtcQT9wWKHLUWxSniCP4AkiYEbHeeNTIe2c5C92ivleLjbUQ==",
                       message_serializer=serializer.GraphSONSerializersV2d0()
                       )

_gremlin_insert_vertices = []


def handler(message):
    _gremlin_insert_vertices = []

    records = message.collect()

    guidfound = False
    key1 = key2 = value1 = value2 = guidvalue = query = ""
    jsonobj = "{\n"
    outputtopic ={}
    recordpos = 1
    for record in records:
        recordsplit = record.split(":")
        print(recordsplit[0])

        if len(recordsplit) > 1:
            print(recordsplit[1])

            firstpart = recordsplit[0].replace('"', '')
            secondpart = recordsplit[1].strip()
            secondpart = secondpart.replace('"', "")
            if (secondpart.endswith(",")):
                secondpart = secondpart[:-1]
            if(firstpart.strip() == "timestamp"):
                jsonobj = jsonobj + recordsplit[0] + ":" + \
                          (recordsplit[1][:-1] if recordsplit[1].endswith(",") else recordsplit[1]) + ",\n"
            if (firstpart.strip() == "keys"):
                key1 = (secondpart.split(",")[0]).replace("[", "")
                key2 = (secondpart.split(",")[1]).replace("]", "")
            if (firstpart.strip() == "values"):
                value1 = (secondpart.split(",")[0]).replace("[", "")
                value2 = (secondpart.split(",")[1]).replace("]", "")
            if (firstpart.strip() == "flguid"):
                guidfound = True
                guidvalue = secondpart[1].strip()
                jsonobj = jsonobj + recordsplit[0] + ":" + \
                          (recordsplit[1][:-1] if recordsplit[1].endswith(",") else recordsplit[1]) + ",\n"
            if(firstpart.strip() == "data"):
                query = "g.addV('Customer').property('" + str(key1.strip()) + "', '" + str(value1) + "')"
                query = query + ".property('" + str(key2.strip()) + "'," + str(value2) + ")"
                if (guidfound):
                    guidfound = False
                    query = query + ".property('flguid'," + guidvalue + ")"
                else:
                    guid = str(generateguid())
                    query = query + ".property('flguid'," + guid + ")"
                    jsonobj = jsonobj + '"flguid":' + guid + ",\n"
                jsonobj = jsonobj + recordsplit[0] + ":" + \
                          (recordsplit[1][:-1] if recordsplit[1].endswith(",") else recordsplit[1]) + "\n}"
                _gremlin_insert_vertices.append(query)
                print(query)
                outputtopic["Record" + str(recordpos)] = jsonobj
                recordpos += 1


                jsonobj ="{\n"

    recordpos = 1
    if (len(_gremlin_insert_vertices)>0):
        for qry in _gremlin_insert_vertices:
            print("Record being inserted for query")
            print(qry)
            callback = client.submitAsync(qry)
            if callback.result() is not None:
                print("Record Inserted")
            else:
                print("Something went wrong")

            # Write to different topic
            WritetoTopic(outputtopic["Record" + str(recordpos)])
            recordpos += 1


def WritetoTopic(jsobj):
    producer.send('test-output', str(jsobj))
    producer.flush()


def generateguid():
    random_test = random.randint(100000000000, 999999999999)
    return random_test


def main():
    sc = SparkContext("local", "kafka-spark-streaming")
    sc.setLogLevel(logLevel="OFF")
    ssc = StreamingContext(sc, 20)

    brokers, topic = sys.argv[1:]
    # kvs = KafkaUtils.createDirectStream(ssc, ["events.noflguid"], {"metadata.broker.list": "localhost:9092"})
    kvs = KafkaUtils.createDirectStream(ssc, [topic], {"metadata.broker.list": brokers})

    lines = kvs.map(lambda x: x[1])

    lines.foreachRDD(handler)

    ssc.start()
    ssc.awaitTermination()


if __name__ == "__main__":
    main()