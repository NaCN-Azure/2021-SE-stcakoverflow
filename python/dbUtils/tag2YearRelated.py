# -*- coding:utf-8 -*-
import pymysql
import json
import platform
from multiprocessing import Pool as mPool
from multiprocessing.dummy import Pool as dPool
import time
import os
import pymongo


def get_sqlcursor_mgcollection():
    sqldb = pymysql.connect(
        host='172.29.7.234',
        port=3306,
        user='root',
        passwd='123456',
        db='testname',
        charset='utf8'
    )
    sqlcursor = sqldb.cursor()

    mgconnection = pymongo.MongoClient(
        'mongodb://scrapy:testname123456@106.15.93.81:27017/scrapy?authSource=scrapy&authMechanism=SCRAM-SHA-1')
    mgdb = mgconnection.scrapy
    mgTag2Related=mgdb.tag2Related
    mgTag2YearRelated=mgdb.tag2YearRelated
    return sqldb,sqlcursor,mgTag2Related,mgTag2YearRelated


def get_tagYearRelated(skip,count=0):
    # 连接数据库
    sqldb,sqlcursor,mgTag2Related,mgTag2YearRelated=get_sqlcursor_mgcollection()

    try:
        for message in mgTag2Related.find({}).skip(skip).limit(300-count).batch_size(4):
            starttime = time.time()
            count = count + 1
            tagId=message['_id']
            tagName=message['TagName']
            print('tag:', skip+count, tagName)
            tagdict = {}
            tagdict['_id'] = tagId
            tagdict['TagName'] = tagName
            tagdict['AllYear'] = {}
            allyear={}
            for relatedtag in message['TagRelated'][0:10]:
                allyear[relatedtag['tagName']]=relatedtag['count']
            tagdict['AllYear']['sortedList']=list(allyear.keys())
            tagdict['AllYear']['dict'] = allyear
            years=[{} for i in range(0,7)]
            # retagcount=0
            for relatedtag in message['TagRelated'][0:10]:
                # retagcount+=1

                reTagId=relatedtag['_id']
                reTagName=relatedtag['tagName']
                # print('reTag:',retagcount,reTagName)
                currentfetch=mgTag2YearRelated.find({'_id':reTagId})
                if currentfetch.count()!=0:
                    fetch={}
                    for f in currentfetch:
                        fetch=f
                        break
                    isFetch=False
                    if tagName in fetch['AllYear']['sortedList']:
                        isFetch=True
                    if isFetch:
                        print("message exists!")
                        if tagName in fetch['Year2021']['sortedList']:
                            years[0][reTagName]=fetch['Year2021']['dict'][tagName]
                        if tagName in fetch['Year2020']['sortedList']:
                            years[1][reTagName]=fetch['Year2020']['dict'][tagName]
                        if tagName in fetch['Year2019']['sortedList']:
                            years[2][reTagName]=fetch['Year2019']['dict'][tagName]
                        if tagName in fetch['Year2018']['sortedList']:
                            years[3][reTagName]=fetch['Year2018']['dict'][tagName]
                        if tagName in fetch['Year2017']['sortedList']:
                            years[4][reTagName]=fetch['Year2017']['dict'][tagName]
                        if tagName in fetch['Year2016']['sortedList']:
                            years[5][reTagName]=fetch['Year2016']['dict'][tagName]
                        if tagName in fetch['Year2015']['sortedList']:
                            years[6][reTagName]=fetch['Year2015']['dict'][tagName]
                        continue

                reTagAllCount=relatedtag['count']


                sql = "SELECT  count(case when YEAR(CreationDate)= 2021 then Id else null end) as 'Year2021' , \
                                        count(case when YEAR(CreationDate)= 2020 then Id else null end) as 'Year2020', \
                                        count(case when YEAR(CreationDate)= 2019 then Id else null end) as 'Year2019', \
                                      count(case when YEAR(CreationDate)= 2018 then Id else null end) as 'Year2018', \
                                      count(case when YEAR(CreationDate)= 2017 then Id else null end) as 'Year2017', \
                                      count(case when YEAR(CreationDate)= 2016 then Id else null end) as 'Year2016', \
                                      count(case when YEAR(CreationDate)= 2015 then Id else null end) as 'Year2015'\
                                        from miniposts \
                                        where Tags like '%<{0}>%' and Tags like '%<{1}>%'".format(tagName, reTagName)
                sqlcursor.execute(sql)
                result = sqlcursor.fetchall()
                for row in result:
                    for i in range(0,7):
                        years[i][reTagName]=row[i]
                # trendArray.append(tagdict)


            tagdict['Year2021'] = {'sortedList':list(sorted(years[0].keys(),key=(lambda x:years[0][x]),reverse=True)),'dict':years[0]}
            tagdict['Year2020'] =  {'sortedList':list(sorted(years[1].keys(),key=(lambda x:years[1][x]),reverse=True)),'dict':years[1]}
            tagdict['Year2019'] =  {'sortedList':list(sorted(years[2].keys(),key=(lambda x:years[2][x]),reverse=True)),'dict':years[2]}
            tagdict['Year2018'] =  {'sortedList':list(sorted(years[3].keys(),key=(lambda x:years[3][x]),reverse=True)),'dict':years[3]}
            tagdict['Year2017'] = {'sortedList':list(sorted(years[4].keys(),key=(lambda x:years[4][x]),reverse=True)),'dict':years[4]}
            tagdict['Year2016'] =  {'sortedList':list(sorted(years[5].keys(),key=(lambda x:years[5][x]),reverse=True)),'dict':years[5]}
            tagdict['Year2015'] = {'sortedList':list(sorted(years[6].keys(),key=(lambda x:years[6][x]),reverse=True)),'dict':years[6]}
            # print(tagdict)
            mgTag2YearRelated.save(tagdict)
            endtime = time.time()
            print(endtime - starttime)

    except Exception as e:
        print(e)
        os.system('curl "http://p.nju.edu.cn/portal_io/login" --data "username=181250198&password=zrg13592862039"')
        get_tagYearRelated(skip+count-1,count=count-1)

    sqlcursor.close()
    sqldb.close()



# def get_json():
#     path = ''
#     if platform.system().lower() == 'windows':
#         path = 'D:\\Custom_Files\\大四上\\软工四\\gitlab\\python\\tag2Id.json'
#     elif platform.system().lower() == 'linux':
#         path = '/home/zhengronggui/test/python_so/tag2Id.json'
#     with open(path, encoding='utf8', mode='r') as fp:
#         data = json.load(fp)
#     return data


def main(mstart):
    dpool=dPool(processes=10)
    dpool.map(get_tagYearRelated, [mstart+i * 300 for i in range(0, 10)])


if __name__ == '__main__':
    mpool = mPool(processes=10)
    mpool.map(main, [i*3000 for i in range(0, 10)])
