import sys
from matrices import *

def eqf(x, y):
    if x - y < sys.float_info.epsilon:
        return 1
    else:
        return 0

#Поиск позиции максиматьного элемента в столбце
def maxposincol(matrix, i, j):
    m = abs(matrix[i][j])
    pos = 0
    for i in range(i, len(matrix)):
        if abs(matrix[i][j]) > m:
            m = abs(matrix[i][j])
            pos = i
    return pos

#Поиск позиции максиматьного элемента в строке
def maxposinline(matrix, i):
    m = matrix[i][0]
    pos = 0
    for j in range(i, len(matrix[i])):
        if matrix[i][j] > m:
            m = matrix[i][j]
            pos = j
    return pos

#Преобразование матрицы к треугольному виду
def totrianglematrix(matrix):
    f = open("ttt.txt", "w")
    mlen = 0
    if len(matrix) < len(matrix[0]):
        mlen = len(matrix)
    else:
        mlen = len(matrix[0])
    for i in range(mlen - 1):
        k = maxposincol(matrix, i, i)
        swaplines(matrix, i, k)
        if eqf(matrix[i][i], 0):
            return 0
        for j in range(i + 1,len(matrix)):
            f.write(matrixtostring(matrix))
            mulline(matrix, matrix[i][i]/matrix[j][i], j)
            sublines(matrix, j, i)
    f.write(matrixtostring(matrix))
