# encoding:utf-8
#!/usr/bin/python
#-*-coding:utf-8-*-
import MySQLdb
db = MySQLdb.connect(host="localhost",user="root",passwd="4242",\
        db="coolSignIn",charset="utf8",use_unicode=True)
cursor = db.cursor()
data = [82,"软工10"]
length = 20
for i in xrange(length):
    data[0] += i
    data[1] = data[1] + str(i)
    sql ="insert into StudentSheet(uid,sheetName)\
            values('%d','%s')" % tuple(data)
    try:
        cursor.execute(sql)
        db.commit()
    except:
        db.rollback()
    data[0] = 82
    data[1] = "软工10"
db.close()
