# encoding:utf-8
#!/usr/bin/python
#-*-coding:utf-8-*-
import MySQLdb
db = MySQLdb.connect(host="localhost",user="root",passwd="4242",\
        db="coolSignIn",charset="utf8",use_unicode=True)
cursor = db.cursor()
data = ["用户","e10adc3949ba59abbe56e057f20f883e","201226630205","18258254022",1]
length = 20
for i in xrange(length):
    data[0] = data[0] + str(i)
    data[2] = str(int(data[2]) + 1)
    data[3] = str(int(data[3]) + 1)
    sql ="insert into User(username,pass,userNo,phone,userType)\
            values('%s','%s','%s','%s','%d')" % tuple(data)
    try:
        cursor.execute(sql)
        db.commit()
    except:
        db.rollback()
    data[0] = "用户"
db.close()
