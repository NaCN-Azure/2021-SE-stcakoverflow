# MicroService-testname

后端微服务

## 2021-11-29
- 增设的如下接口
  - 1.原先的findStackNode和findStackRelation不变，这个不用动
  - 2.新设findStackNodesYears和findStackRelationsYears，和上面类似，用作图谱展示的，但是是加了个2015-2021年份的查询
  - 3.新设findTargetNodesInfo和findTargetNodesRelated，也是用作图谱展示的，但是是按照tagName查询的，而且返回的是该tag的全部（我搜过，一个节点不多）
  - 4.新设findTargetNodesDescription，返回该tagName的节点详细信息，就是po的Tags，**前端可能得创建个这玩意**，后面点击事件或者方便你们对单独节点操作
- **建议**
  - 1.跑的时候**4个yml全部改成dev**，我这个传的版本四个都是**prod**,测的时候记得改，不然**zrg**会揍你
  - 2.前端也许需要加界面，一个是那个能挑选时间的下边栏，还有对4中方法详细信息的右边栏

## 2021-12-5
- 看一下StackOverflowDAOImpl的findTargetNodesChart方法和配置文件yml，我mongo接不上我不会嘤(`皿´)，I need help
