filename="E:/Learnings/python projects/sample.txt"

with open(filename) as f:
    line = f.readline()
    while(line):
        print(line)
        line = f.readline()

f.close()