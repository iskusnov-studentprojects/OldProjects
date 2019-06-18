#Сумма матриц
def madd(matrix1, matrix2):
	if len(matrix1) != len(matrix2) or len(matrix1[0]) != len(matrix2[0]):
		raise Exception("Размеры матриц не соответствуют операции")
	res = createnullmatrix(len(matrix1), len(matrix1[0]))
	for i in res:
		for j in i:
			j = matrix1[i][j] + matrix2[i][j]
	return res

#Разница матриц
def msub(matrix1, matrix2):
	if len(matrix1) != len(matrix2) or len(matrix1[0]) != len(matrix2[0]):
		raise Exception("Размеры матриц не соответствуют операции")
	res = createnullmatrix(len(matrix1), len(matrix[0]))
	for i in res:
		for j in i:
			j = matrix1.mass[i][j] = matrix2.mass[i][j]
	return res

#Умножение матриц
def mmul(matrix1, matrix2):
        if(len(matrix1[0]) != len(matrix2)):
           raise Exception("Размеры матриц не соответствуют операции")
        res = createnullmatrix(len(matrix1), len(matrix2[0]))
        for i in range(len(res)):
           for j in range(len(res[0])):
                for k in range(len(matrix2)):
                        res[i][j] += matrix1[i][k] * matrix2[k][j]
        return res

#Преобразовать в строку
def matrixtostring(matrix):
	string = ""
	for i in matrix:
		for j in i:
			string += str(j) + ' '
		string += "\n"
	return string

#Создание нулевай матрицы
def createnullmatrix(N, M):
	matrix = [[0 for j in range(M)] for i in range(N)]
	return matrix

#Умножить матрицу на константу
def mmul(matrix, c):
	for i in range(len(matrix)):
		for j in range(len(matrix[i])):
			matrix[i][j] *= c

#Умножить строку матрицы на константу
def mulline(matrix, c, i):
	for j in range(len(matrix[i])):
		matrix[i][j] *= c

#Отнять от k-ой строки m-ую строку
def sublines(matrix, k, m):
	for j in range(len(matrix[k])):
		matrix[k][j] -= matrix[m][j]

#Прибавить к k-ой строке m-ую строку
def addlines(matrix, k, m):
	for j in range(len(matrix[k])):
		matrix[k][j] += matrix[m][j]

#Поменять строки местами
def swaplines(matrix, k, m):
	for i in range(len(matrix[0])):
		matrix[k][i] += matrix[m][i]
		matrix[m][i] = matrix[k][i] - matrix[m][i]
		matrix[k][i] -= matrix[m][i]

#Поменять столбцы местами
def swapcolumns(matrix, k, m):
	for i in range(len(matrix)):
		matrix[i][k] += matrix[i][m]
		matrix[i][m] = matrix[i][k] - matrix[i][m]
		matrix[i][k] -= matrix[i][m]
