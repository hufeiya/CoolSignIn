# encoding:utf-8
#!/usr/bin/python
#-*-coding:utf-8-*-
import MySQLdb
db = MySQLdb.connect(host="localhost",user="root",passwd="4242",\
        db="coolSignIn",charset="utf8",use_unicode=True)
cursor = db.cursor()
data = [203,1,0]
length = 20
for i in xrange(length):
    data[1] +=  1
    if i > 9:
        data[0] = 204
    sql ="insert into SignInfo(cid,sid,signTimes)\
            values('%d','%d','%d')" % tuple(data)
    try:
        cursor.execute(sql)
        db.commit()
    except Exception,e:
        db.rollback()
        print e
db.close()
