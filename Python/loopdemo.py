computer = ["Toshiba","Asus","Apple","Samsung"]

i = 0
for item in computer:
    if(computer[i] == "Asus"):
        print("Contains")
        break;
    i = i + 1

if(i == len(computer)):
    print("Does not contain")


number =[10,3,4,38,39]
sum = 0

for no in number:
    sum = sum + no

print("Total = " + str(sum))