filename="E:/Learnings/python projects/sample.txt"
filewrite="E:/Learnings/python projects/samplenew.txt"


with open(filewrite,'w') as fw:
    with open(filename) as fr:
        line = fr.readline()
        while(line):
            fw.write(line)
            line = fr.readline()


fr.close()
fw.close()