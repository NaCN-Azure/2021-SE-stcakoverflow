
# public class Node {
#     private Long id;
#     private Long graphId;
#     private String name;
#     private NodeShape shape;
#     private String color;
#     private int node_size;
#     private int font_size;
#     private String label;
#     private boolean isHighlighted;
#     private float x;
#     private float y;
#     //排版模式坐标
#     private float typesetting_x;
#     private float typesetting_y;
#     private boolean isShown;
# }

# public class RelationVO {
#     private Long id;
#
#     //必填
#     private Long source;
#
#     private Long target;
#
#     private Long graphId;
#
#     private String name;
#
#     //可选
#     private boolean isSolid;
#
#     private String label;
#
#     //不可初始化
#     private boolean isHighlighted;
#
#     private boolean isShown;
# }

from py2neo import Graph, Node, Relationship, NodeMatcher, Subgraph
from pymongo import MongoClient
graph=Graph("http://106.15.93.81:7474", username="neo4j",password="123456")
matcher=NodeMatcher(graph)
conn = MongoClient('mongodb://scrapy:testname123456@106.15.93.81:27017/test?authSource=scrapy&readPreference=primary&appname=MongoDB%20Compass&directConnection=true&ssl=false')
db = conn.scrapy
# tags = db.stackoverflow_1w
# contents = db.contents_1w
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

# def addNode(type,name,description):
#     if(type==0):
#         data_check = matcher.match().where(f"_.name='{name}'").first()
#         if(data_check):
#             return data_check
#     labels=""
#     Nodecolor=""
#     if(type==0):
#         labels="tags"
#         Nodecolor=Default.color_tags
#     elif(type==1):
#         labels = "questions"
#         Nodecolor = Default.color_ques
#     elif(type==2):
#         labels = "answers"
#         Nodecolor = Default.color_answ
#     node = Node(labels, name=name, color=Nodecolor, font_size=Default.default_fontsize,
#                 isHighlighted=Default.default_High,
#                 isShown=Default.default_isShown, node_size=Default.default_nodesize,
#                 graphId=Default.default_graphID,shape=Default.default_shape,description=description)
#     graph.create(node)
#     return node

def addLink(Node1,relation,Node2):
    r = Relationship(Node1,relation,Node2)
    r["graphId"]=Default.default_graphID
    r["from_id"]=Node1["id"]
    r["to_id"]=Node2["id"]
    r["name"]=relation
    return r

# def addTagsRelation(Node1,relation,Node2,count):
#     r = Relationship(Node1,relation,Node2)
#     r["graphId"]=Default.default_graphID
#     r["from_id"]=Node1.identity
#     r["to_id"]=Node2.identity
#     r["name"]=relation
#     r["count"]=count
#     cql=f"match (n)-[r]->(m) where id(n)={Node1.identity} and id(m)={Node2.identity} return r"
#     check = graph.run(cql).data()
#     if(check):
#         return None
#     return r

def updateQuestion(create_date,votes,answers,views,Node):
    Node["create_date"]=create_date
    Node["votes"]=votes
    Node["answers"]=answers
    Node["views"]=views

    return Node

def findAndAddTagsNode(tagName):
    data_check = matcher.match().where(f"_.name='{tagName}'").first()
    if(data_check):
        return data_check
    else:
        node = Node("Tags", name=tagName, color=Default.color_tags, font_size=Default.default_fontsize,
                     node_size=Default.default_nodesize,graphId=Default.default_graphID)
        graph.create(node)
        return node

def checkExistRelation(from_id,to_id):
    cql = f"match (n)-[r]->(m) where id(n)={from_id} and id(m)={to_id} return r"
    r = graph.run(cql).data()
    if(r):
        return True
    else:
        return False
# def dealtags():
#     i=1
#     for messages in tags.find({},{"_id":0,"questions":1,"tags":2}):
#         answer_des=3
#         question = messages["questions"][0]
#         # for u in contents.find({"_id":question}):
#         #     # question_des= u["question_contents"][0]
#         #     answer_des = len(u["answer_contents"])
#         question=question.replace('\'','丨').replace('\"','丨').replace('‘','丨').replace('“','丨').replace('\\','亖') # 丨 这个东西是汉字丨（gun）
#         questiontags = messages["tags"]
#         print("now["+str(i)+"]: "+question)
#         i=i+1
#         QuestionNode = addNode(1,question,"") # 可能考虑
#         for questiontag in questiontags:
#             TagNode = addNode(0,questiontag,"")
#             addLink(QuestionNode,"belongs",TagNode)
#         for k in range(answer_des):
#             AnswerNode = addNode(2,"Answer "+str(k+1)+"ToQ"+str(QuestionNode.identity),str(k)) # 可能考虑
#             addLink(AnswerNode,"answers",QuestionNode)

# def tagToRelate():
#     i = 30000
#     for messages in tag2Relate.find({},{"TagName":1,"TagRelated":1}).skip(30000):
#         relationsList=[]
#         TagName = messages["TagName"]
#         print("No."+str(i)+": "+TagName)
#         i=i+1
#         TagRelated = messages["TagRelated"]
#         FatherTag = findAndAddTagsNode(TagName)
#         for object in TagRelated:
#             sonTagName = object["tagName"]
#             sonTag = findAndAddTagsNode(sonTagName)
#             r=addTagsRelation(FatherTag,"contains",sonTag,object["count"])
#             if(not(r is None)):
#                 relationsList.append(r)
#         graph.push(relationsList)

# def tagInfo():
#     i = 43150
#     for messages in tagsInfo.find({}).skip(43150):
#         TagName = messages["TagName"]
#         print("No."+str(i)+" Tag:"+TagName)
#         i = i + 1
#         TagNode = findAndAddTagsNode(TagName,messages["QuestionCount"])
#         TagNode["count_2021"] = messages["Year2021"]
#         TagNode["count_2020"] = messages["Year2020"]
#         TagNode["count_2019"] = messages["Year2019"]
#         TagNode["count_2018"] = messages["Year2018"]
#         TagNode["count_2017"] = messages["Year2017"]
#         TagNode["count_2016"] = messages["Year2016"]
#         TagNode["count_2015"] = messages["Year2015"]
#         TagNode["excerpt"]=messages["Excerpt"]
#         graph.push(TagNode)

def tagtoQues():
    i=265
    for message in tag2Question.find({}).skip(265).limit(200):
        TagName = message["TagName"]
        print("No." + str(i) + ": " + TagName)
        i = i + 1
        TagNode = matcher.match().where(f"_.name='{TagName}'").first()
        if(not (TagNode)):
            continue # 如果找不到node跳过之
        Questions = message["Questions"]
        for ques in Questions:
            questionName = ques["questions"]
            questionName=questionName.replace('\'','丨').replace('\"','丨').replace('‘','丨').replace('“','丨').replace('\\','亖')
            questionNode = matcher.match().where(f"_.name='{questionName}'").first()
            # subTags = ques["tags"]
            links=[]
            if(questionNode):
                newQuestionNode=updateQuestion(ques["create_date"],ques["votes"],ques["answers"],ques["views"],questionNode)
                if(checkExistRelation(newQuestionNode.identity,TagNode.identity)):
                    pass
                else:
                    links.append(addLink(newQuestionNode,"belongs",TagNode))
                # iterTags = iter(subTags)
                # next(iterTags)
                # for subTagName in iterTags:
                #     x = matcher.match().where(f"_.name='{subTagName}'").first()
                #     if(checkExistRelation(newQuestionNode.identity,x.identity)):
                #         pass
                #     else:
                #         links.append(addLink(newQuestionNode, "belongs", x))
            else:
                node = Node("Tags", name=questionName, color=Default.color_ques, font_size=Default.default_fontsize,
                            node_size=Default.default_nodesize, graphId=Default.default_graphID)
                graph.create(node)
                newQuestionNode=updateQuestion(ques["create_date"],ques["votes"],ques["answers"],ques["views"],node)
                links.append(addLink(newQuestionNode,"belongs",TagNode))
                # iterTags = iter(subTags)
                # next(iterTags)
                # for subTagName in iterTags:
                #     x = matcher.match().where(f"_.name='{subTagName}'").first()
                #     links.append(addLink(newQuestionNode, "belongs", x))
            graph.push(Subgraph(nodes=[newQuestionNode],relationships=links))
            links.clear()



    # for messages in contents.find({"answer_contents":{"$size":1}}):
    #     question = messages["_id"]
    #     question=question.replace('\'','丨').replace('\"','丨').replace('‘','丨').replace('“','丨').replace('\\','亖')
    #     print("fix :" + question)
    #     cql = f"match (n)-[r:answers]-(m) where n.description = '2' and m.name = '{question}' delete n,r"
    #     cql2 = f"match (n)-[r:answers]-(m) where n.description = '1' and m.name = '{question}' delete n,r"
    #     graph.run(cql)
    #     graph.run(cql2)

def subGraphTest():
    a= matcher.match("test",name="a").first()
    a["name"]="aa"
    b = matcher.match("test",name="b").first()
    b["name"] = "bb"
    cql = f"match (n)-[r]->(m) where id(n)={a.identity} and id(m)={b.identity} return r"
    r = graph.run(cql).data()[0]['r']
    A=Subgraph(nodes=[a,b],relationships=[r])
    graph.push(A)

# def dealcontents():
#     target_tag=""
#     k=0
#     for messages in contents.find({},{"question_contents":1,"answer_contents":2}):
#         question = messages["_id"]
#         for u in tags.find({"questions":question}):
#             target_tag = u["tags"][0]
#         question = question.replace('\'', '丨').replace('\"', '丨').replace('‘', '丨').replace('“', '丨').replace('\\','亖')  # 丨 这个东西是汉字丨（gun）
#         question_des = messages["question_contents"][0]
#         answer_des = messages["answer_contents"]
#         k=k+1
#         print("now-contents["+str(k)+"]: "+question)
#         QuestionNode = updatedes(target_tag,question,question_des)
#         for i in range(len(answer_des)):
#             AnswerNode = addNode(2,"Answer "+str(i+1)+"ToQ"+str(QuestionNode.identity),answer_des[i])
#             addLink(AnswerNode,"answers",QuestionNode)


if __name__ =="__main__":
    print("-----------------------------------")
    tagtoQues()
    # subGraphTest()
    print("-----------------------------------")
    # dealcontents()
    # question = "Why is processing a sorted array faster than processing an unsorted array?"
    # for u in contents.find({"_id": question}):
    #     question_des = u["question_contents"][0]
    #     print(question_des[0:8])
    #     answer_des = u["answer_contents"]
    #     for i in answer_des:
    #         print(answer_des[i][0:8])



