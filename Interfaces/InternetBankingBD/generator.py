path = "inputdata.dat"

def loadData(path):
    f = open(path,"r")
    list = [(line[:-1]).split('\t') for line in f]
    f.close()
    return list
def genQuery(data):
    query = "insert into RealCard (number,firstName,lastName,validDate,securePassword,cash,idCardType) values ("
    query = query + "\"" + data[0] + "\", \"" + data[1] + "\", \"" + data[2] + "\", str_to_date(\"" + data[3] + "\", \"%d/%m/%Y\"), \"" + data[4] + "\", " + data[5] + ", " + data[6] + ");"
    return query
    
file = open("tmpsql.sql", "w")
allData = loadData(path)
for i in allData:
    file.write(genQuery(i) + "\n")
file.close()
