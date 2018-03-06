

# file = open(filepath)
#
# # for line in file:
# #     print (line)
#
#
# lineobj = [line for line in open(filepath)]
#
# print(lineobj[0])
# print(lineobj[1].strip())
# print(lineobj[2].strip().split(","))
#
# dataobj = [line.strip().split(",") for line in open(filepath)]
#
# print(dataobj[1])

import csv
from datetime import datetime


filepath = "E:/Projects/Data/google_stock_data.csv"

#print(dir(csv))

data = []
with open(filepath,'r') as fr:
    reader = csv.reader(fr)
    header = next(reader)
    print(header)
    #data.append(header)
    for row in reader:
        date = datetime.strptime(row[0],"%m/%d/%Y")
        Open = float(row[1])
        High = float(row[2])
        Low = float(row[3])
        Close = float(row[4])
        Volume = int(row[5])
        Adj_Close = float(row[6])

        data.append([date,Open,High,Low,Close,Volume,Adj_Close])

filewrite="E:/Projects/Data/filereturn.csv"

with open(filewrite,'w') as fw:
    writer = csv.writer(fw,delimiter = ' ')
    writer.writerow(['Date','Open'])

    for i in range(len(data)-1):
        currow = data[i]
        writer.writerow([currow[0].strftime("%m/%d/%Y"),currow[1]])


