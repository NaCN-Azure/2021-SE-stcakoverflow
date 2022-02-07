# -*- coding:utf-8 -*-
import pymysql
import json
import platform
from multiprocessing import Pool as mPool
from multiprocessing.dummy import Pool as dPool
import time

import os
import pymongo



def get_ymtrend(data):
    # 连接数据库
    sqldb = pymysql.connect(
        host='localhost',
        port=3306,
        user='root',
        passwd='111111',
        db='testname',
        charset='utf8'
    )
    sqlcursor = sqldb.cursor()

    mgconnection = pymongo.MongoClient(
        'mongodb://scrapy:testname123456@106.15.93.81:27017/scrapy?authSource=scrapy&authMechanism=SCRAM-SHA-1')
    mgdb = mgconnection.scrapy
    mgcollection =mgdb.tagTrend
    count=56
    trendArray=[]

    for tag in data.keys():
        starttime = time.time()
        trenddict = {}
        try:
            sql = "SELECT YEAR(CreationDate) tagYear,\
            			count(case when MONTH(CreationDate)= 1 then Id else null end) as 'month1',\
            			count(case when MONTH(CreationDate)= 2 then Id else null end) as 'month2',\
            			count(case when MONTH(CreationDate)= 3 then Id else null end) as 'month3',\
            			count(case when MONTH(CreationDate)= 4 then Id else null end) as 'month4',\
            			count(case when MONTH(CreationDate)= 5 then Id else null end) as 'month5',\
            			count(case when MONTH(CreationDate)= 6 then Id else null end) as 'month6',\
            			count(case when MONTH(CreationDate)= 7 then Id else null end) as 'month7',\
            			count(case when MONTH(CreationDate)= 8 then Id else null end) as 'month8',\
            			count(case when MONTH(CreationDate)= 9 then Id else null end) as 'month9',\
            			count(case when MONTH(CreationDate)= 10 then Id else null end) as 'month10',\
            			count(case when MONTH(CreationDate)= 11 then Id else null end) as 'month11',\
            			count(case when MONTH(CreationDate)= 12 then Id else null end) as 'month12'\
                        FROM posts \
                        WHERE Tags like '%<{0}>%' \
                        GROUP BY tagYear".format(tag)
            sqlcursor.execute(sql)
            result = sqlcursor.fetchall()
            trenddict['_id']=int(data[tag])
            trenddict['TagName'] = tag
            for row in result:
                year=str(row[0])
                trenddict[year]=[]
                for month in range(1,13):
                    trenddict[year].append(row[month])
            print(trenddict)
            # trendArray.append(trenddict)
            # mgcollection.save(trenddict)
            endtime = time.time()
            count=count+1
            print(count)
            print(endtime - starttime)
        except Exception as e:
            raise e
    sqlcursor.close()
    sqldb.close()

# def write_mongodb(data):
#
#     # for item in data:


def get_json():
    path = ''
    if platform.system().lower() == 'windows':
        path = 'D:\\Custom_Files\\大四上\\软工四\\gitlab\\python\\tag2Id.json'
    elif platform.system().lower() == 'linux':
        path = '/home/zhengronggui/test/python_so/tag2Id.json'
    with open(path, encoding='utf8', mode='r') as fp:
        data = json.load(fp)
    return data

def get_data_slice(data,interval=10000):
    datalist=[]
    leng=len(data)
    alltags=list(data.keys())
    if leng==50000:
        alltags=alltags[30000:40000]
        leng=10000
    bias=0
    if(interval==500):
        bias=56
    for i in range(0,leng//interval):
        dic={}
        tagnames=alltags[i*interval+bias:(i+1)*interval]
        for tagname in tagnames:
            dic[tagname] = data[tagname]
        datalist.append(dic)
    return datalist

def main(bigdata):
    dataslice=get_data_slice(bigdata,interval=500)
    dpool = dPool(processes=4)
    dpool.map(get_ymtrend, [dataslice[i] for i in range(0, 4)])



if __name__ == '__main__':

    bigdata=get_json()
    datalist=get_data_slice(bigdata,interval=2000)
    mpool = mPool(processes=5)
    mpool.map(main,[datalist[i] for i in range(0,5)])
    # get_ymtrend(bigdata)
