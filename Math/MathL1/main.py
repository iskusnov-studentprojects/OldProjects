from linearsystem import *
def run():
    matr = []
    file = open("d:\mathl1\input.dat", 'r')
    for line in file:
        strings = line.split(' ')
        matr.append([int(i) for i in strings])
    totrianglematrix(matr)
    file.close()
    file = open("d:\mathl1\output.dat", 'w')
    file.write(matrixtostring(matr))
    file.close()
