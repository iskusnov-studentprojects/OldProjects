tablePath = 'd:\\table.dat'

def swap(mass, i, j):
    tmp = mass[i]
    mass[i] = mass[j]
    mass[j] = tmp

def sortTable(table, password):
    copypass = password.copy()
    copypass.sort()
    lst = [password.index(i) for i in copypass]
    newtable = [table[i] for i in lst]
    return newtable

def changesymbol(sym, table):
    lst = ['A','D','F','G','V','X']
    for i in range(6):
        for j in range(6):
            if sym == table[i][j]:
                return lst[i] + lst[j]
    return sym

def restoresymbol(code,table):
    string = 'ADFGVX'
    return table[string.index(code[0])][string.index(code[1])]
    

def loadtable(path):
    file = open(path, 'r')
    table = []
    for line in file:
        strings = line.split(' ')
        for i in range(len(len(strings)):
            if strings[i] == 'space':
                srings[i] = ' '
        table.append(strings)
    file.close()
    return table

def incription(infPath, password):
    file = open(infPath, 'r')
    inf = (file.read()).lower()
    file.close()
    if len(password) > 6:
        password = password[:6]
    if len(password) < 6:
        password = password*6
        password = password[:6]
    table = sortTable(loadtable(tablePath), password)
    newinf = ''
    for i in range(len(inf)):
        newinf += changesymbol(inf[i],table)
    file = open(infPath, 'w')
    file.write(newinf)
    file.close()

def decription(infPath, password):
    file = open(infPath, 'r')
    inf = (file.read()).lower()
    file.close()
    if len(password) > 6:
        password = password[:6]
    if len(password) < 6:
        password = password*6
        password = password[:6]
    table = sortTable(loadtable(tablePath), password)
    newinf = ''
    for i in range(len(inf)):
        if inf[i] in 'ADFGVG':
            newinf += restoresymbol(inf[i] + inf[i+1], table)
            i += 1
        else:
            newinf += inf[i]
    file = open(infPath, 'w')
    file.write(newinf)
    file.close()
