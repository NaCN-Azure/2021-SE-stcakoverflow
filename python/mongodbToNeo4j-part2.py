from py2neo import Graph, Node, Relationship, NodeMatcher, Subgraph
from pymongo import MongoClient
graph=Graph("http://106.15.93.81:7474", username="neo4j",password="123456")
matcher=NodeMatcher(graph)
conn = MongoClient('mongodb://scrapy:testname123456@106.15.93.81:27017/test?authSource=scrapy&readPreference=primary&appname=MongoDB%20Compass&directConnection=true&ssl=false')
db = conn.scrapy
tag2YearRelated=db.tag2YearRelated
tag2Question = db.tag2Question
tag2Relate = db.tag2Related
tagsInfo = db.tagsInfo

class Default:
    default_shape = "CIRCLE"
    default_High =  False
    default_isShown = True
    default_fontsize = 10
    default_nodesize = 40
    color_ques = "#EE82EE"
    color_answ = "#F4A460"
    color_tags="#66ccff"
    default_graphID=-1
    default_label =""
    default_isSolid=True

def addLink(Node1,relation,Node2):
    r = Relationship(Node1,relation,Node2)
    r["graphId"]=Default.default_graphID
    r["from_id"]=Node1.identity
    r["to_id"]=Node2.identity
    r["name"]=relation
    graph.create(r)
    return r

def dealwithYear(ALLYear,Year2021,Year2020,Year2019,Year2018,Year2017,Year2016,Year2015):
    r=list(set(ALLYear["sortedList"]).union(Year2021["sortedList"],Year2020["sortedList"],Year2019["sortedList"],Year2018["sortedList"],
                                            Year2017["sortedList"],Year2016["sortedList"],Year2015["sortedList"]))
    return r

def sendYearCount(Year,name):
    if(name in Year.keys()):
        return Year[name]
    else:
        return "0"

def DoYears(AllYearTags,Node,AllYear,Year2021,Year2020,Year2019,Year2018,Year2017,Year2016,Year2015):
    fatherName = Node["name"]
    for j in range(0,len(AllYearTags)):
        sonName = AllYearTags[j]
        # addAndCheckNewTag(sonName)
        count = sendYearCount(AllYear["dict"],sonName)
        count_2021 = sendYearCount(Year2021["dict"], sonName)
        count_2020 = sendYearCount(Year2020["dict"], sonName)
        count_2019 = sendYearCount(Year2019["dict"], sonName)
        count_2018 = sendYearCount(Year2018["dict"], sonName)
        count_2017 = sendYearCount(Year2017["dict"], sonName)
        count_2016 = sendYearCount(Year2016["dict"], sonName)
        count_2015 = sendYearCount(Year2015["dict"], sonName)
        long_cql = f"match (n:Tags)-[r:contains]->(m:Tags) where n.name='{fatherName}' and m.name='{sonName}' " \
                   f" set r.count = '{count}' " \
                   f" set r.count_2021 = '{count_2021}'" \
                   f" set r.count_2020 = '{count_2020}' " \
                   f" set r.count_2019 = '{count_2019}'" \
                   f" set r.count_2018 = '{count_2018}'"\
                   f" set r.count_2017 = '{count_2017}'" \
                   f" set r.count_2016 = '{count_2016}'" \
                   f" set r.count_2015 = '{count_2015}'" \
                   f" return r"
        addAndCheckNewRelation(Node,sonName)
        graph.run(long_cql)

def Trans(start,length):
    i=start
    # for messages in tag2YearRelated.find({}).skip(start).limit(length):
    for messages in tag2YearRelated.find({"TagName":length}):
        TagName = messages["TagName"]
        print("No." + str(i) + ": " + TagName)
        i = i + 1
        Year2021 = messages["Year2021"]
        Year2020 = messages["Year2020"]
        Year2019 = messages["Year2019"]
        Year2018 = messages["Year2018"]
        Year2017 = messages["Year2017"]
        Year2016 = messages["Year2016"]
        Year2015 = messages["Year2015"]
        AllYear = messages["AllYear"]
        AllYearTags=dealwithYear(AllYear,Year2021,Year2020,Year2019,Year2018,Year2017,Year2016,Year2015)
        TagNode = matcher.match().where(f"_.name='{TagName}'").first()
        DoYears(AllYearTags,TagNode,AllYear,Year2021,Year2020,Year2019,Year2018,Year2017,Year2016,Year2015)

# def addAndCheckNewTag(name):
#     cql = f"match (n) where n.name='{name}' return n"
#     check = graph.run(cql).data()
#     if(check):
#         return
#     else:
#         for info in tagsInfo.find({"TagName":name}):
#
#             TagNode = Node("Tags", name=name, color=Default.color_tags, font_size=Default.default_fontsize,
#                          node_size=Default.default_nodesize,
#                          graphId=Default.default_graphID,shape=Default.default_shape)
#             TagNode["count"] = info["QuestionCount"]
#             TagNode["count_2021"] = info["Year2021"]
#             TagNode["count_2020"] = info["Year2020"]
#             TagNode["count_2019"] = info["Year2019"]
#             TagNode["count_2018"] = info["Year2018"]
#             TagNode["count_2017"] = info["Year2017"]
#             TagNode["count_2016"] = info["Year2016"]
#             TagNode["count_2015"] = info["Year2015"]
#             TagNode["excerpt"]=info["Excerpt"]
#             graph.create(TagNode)
#
def addAndCheckNewRelation(Node,sonName):
    fatherName = Node['name']
    cql = f"match (n:Tags)-[r:contains]->(m:Tags) where n.name='{fatherName}' and m.name='{sonName}' return r"
    check = graph.run(cql).data()
    if(check):
        return
    else:
        cql2 = f"match (n) where n.name='{sonName}' return n"
        son=graph.run(cql2).data()[0]['n']
        addLink(Node,"contains",son)

def fixNodes():
    victims2=["jquery","html","css","node.js","reactjs","php","angularjs","ajax","arrays","json"] # css没解决
    victims = ["json"]
    for v in victims:
        print(v)
        for info in tagsInfo.find({"TagName": v}):
            TagNode = Node("Tags", name=v, color=Default.color_tags, font_size=Default.default_fontsize,
                                 node_size=Default.default_nodesize,
                                 graphId=Default.default_graphID,shape=Default.default_shape)
            TagNode["count"] = info["QuestionCount"]
            TagNode["count_2021"] = info["Year2021"]
            TagNode["count_2020"] = info["Year2020"]
            TagNode["count_2019"] = info["Year2019"]
            TagNode["count_2018"] = info["Year2018"]
            TagNode["count_2017"] = info["Year2017"]
            TagNode["count_2016"] = info["Year2016"]
            TagNode["count_2015"] = info["Year2015"]
            TagNode["excerpt"]=info["Excerpt"]
            graph.create(TagNode)
        for message in tag2Question.find({"TagName":v}):
            Questions=message["Questions"]
            for ques in Questions:
                questionName = ques["questions"]
                questionName=questionName.replace('\'','丨').replace('\"','丨').replace('‘','丨').replace('“','丨').replace('\\','亖')
                questionNode = matcher.match().where(f"_.name='{questionName}'").first()
                NowNode = matcher.match().where(f"_.name='{v}'").first()
                r = Relationship(questionNode, "belongs", NowNode)
                r["graphId"] = Default.default_graphID
                r["from_id"] = questionNode.identity
                r["to_id"] = NowNode.identity
                r["name"] = "belongs"
                graph.create(r)
    # for v in victims:
    #     for infos in tagsInfo.find({"TagName":v}):
    #         print(infos)
    #         addAndCheckNewTag(infos)
    #     for messages in tag2Question.find({"TagName":v}):
    #         print(v)
    #         Related=messages["Questions"]
    #         son = matcher.match().where(f"_.name='{v}'").first()
    #         for i in Related:
    #             fatherName = i["questions"]
    #             fatherName = fatherName.replace('\'','丨').replace('\"','丨').replace('‘','丨').replace('“','丨').replace('\\','亖')
    #             father = matcher.match().where(f"_.name='{fatherName}'").first()
    #             r = Relationship(father, "belongs", son)
    #             r["graphId"] = Default.default_graphID
    #             r["from_id"] = father.identity
    #             r["to_id"] = son.identity
    #             r["name"] = "belongs"
    #             graph.create(r)

def C_jing():
    checkName = "c#"
    for message in tag2Question.find({"TagName": ".net"}):
        Questions = message["Questions"]
        for i in range(0,50):
            if(i==44 or i==37 or i==34 or i==17):
                continue
            ques=Questions[i]
            questionName = ques["questions"]
            questionName = questionName.replace('\'', '丨').replace('\"', '丨').replace('‘', '丨').replace('“','丨').replace('\\', '亖')
            questionNode = matcher.match().where(f"_.name='{questionName}'").first()
            NowNode = matcher.match().where(f"_.name='{checkName}'").first()
            r = Relationship(questionNode, "belongs", NowNode)
            r["graphId"] = Default.default_graphID
            r["from_id"] = questionNode.identity
            r["to_id"] = NowNode.identity
            r["name"] = "belongs"
            graph.create(r)

if __name__ =="__main__":
    print("-----------------------------------")
    # fixNodes()
    # victims2 = [ "c#"]
    # for v in victims2:
    #     Trans(0,v) # 表示从40号开始，开始跑9960个
    #              # 我建议你这样 40-10000 即（40,9960）
    #              # 然后(10000,10000) 然后等等跑到结束
    #              # 然后(20000,10000)
    #              # 然后(30000,10000)
    #             # 然后(40000,10000)
    C_jing()
    print("-----------------------------------")



