# encoding:utf-8
#!/usr/bin/python
#-*-coding:utf-8-*-
import MySQLdb
db = MySQLdb.connect(host="localhost",user="root",passwd="4242",\
        db="coolSignIn",charset="utf8",use_unicode=True)
cursor = db.cursor()
data = ["学生","201226630205",1]
length = 20
for i in xrange(length):
    data[0] = data[0] + str(i)
    data[1] = str(int(data[1]) + 1)
    sql ="insert into Student(studentName,studentNO,sheetID)\
            values('%s','%s','%d')" % tuple(data)
    try:
        cursor.execute(sql)
        db.commit()
    except:
        db.rollback()
    data[0] = "学生"
db.close()
