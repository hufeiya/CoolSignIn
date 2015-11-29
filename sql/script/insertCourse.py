# encoding:utf-8
#!/usr/bin/python
#-*-coding:utf-8-*-
import MySQLdb
db = MySQLdb.connect(host="localhost",user="root",passwd="4242",\
        db="coolSignIn",charset="utf8",use_unicode=True)
cursor = db.cursor()
data = [82,"课程","1449021300,1449107700",16]
length = 40
for j in xrange(length):
    i = j/2
    data[0] = data[0] + i
    data[1] = data[1] + str(j)
    dateList = data[2].split(',')
    temp = str(int(dateList[0])+1800)+','+str(int(dateList[1])+1800)
    data[2] = temp
    sql ="insert into Course(uid,courseName,startDates,numberOfWeeks)\
            values('%d','%s','%s','%d')" % tuple(data)
    try:
        cursor.execute(sql)
        db.commit()
    except Exception,e:
        print e
        db.rollback()
    data[1] = "课程"
    data[0] = 82
db.close()
