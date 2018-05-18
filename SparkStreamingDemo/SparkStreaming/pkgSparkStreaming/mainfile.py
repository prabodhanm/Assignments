import file1
import file2
from subprocess import call


def main():
    print("This is main")
    call(["python", "file1.py"])
    call(["python", "file2.py"])

if __name__ == "__main__":
    main()