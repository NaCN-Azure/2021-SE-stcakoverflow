const getters = {
  //customNode

  customNodeParams: state => state.customNode.customNodeParams,
  currentNodeId: state => state.customNode.currentNodeId,
  subNodeList: state => state.customNode.subNodeList,

  //customRelation

  customRelationParams: state => state.customRelation.customRelationParams,
  currentLinkId: state => state.customRelation.currentLinkId,

  //customGraph

  graphList: state => state.customGraph.graphList,
  // 存放当前图谱数据信息
  customGraphRes: state => state.customGraph.customGraphRes,
  // 存放当前图谱基本信息
  customGraphParams: state => state.customGraph.customGraphParams,
  currentGraphId: state => state.customGraph.currentGraphId,
  currentGraphName: state => state.customGraph.currentGraphName,
  file: state => state.customGraph.file,
  fuzzyMatchingOutComeList: state => state.customGraph.fuzzyMatchingOutComeList,
  showModel: state => state.customGraph.showModel,
  searchString: state => state.customGraph.searchString,
  graphMovable: state => state.customGraph.graphMovable,
  searchBoardVisible: state => state.customGraph.searchBoardVisible,
  numericalVisible: state => state.customGraph.numericalVisible,
  nodeClickVisible: state => state.customGraph.nodeClickVisible,
  relationClickVisible: state => state.customGraph.relationClickVisible,
  stockCardVisible: state => state.customGraph.stockCardVisible,

  //customUser
  userParams: state => state.customUser.userParams,
  loginInSuccess: state => state.customUser.loginInSuccess,
  errorMessage: state => state.customUser.errorMessage,
  userGraphList: state => state.customUser.userGraphList,
  recommendList: state => state.customUser.recommendList,
  favourStockName: state => state.customUser.favourStockName,
  chatBotAnswer: state => state.customUser.chatBotAnswer,
  chatBotRequest: state => state.customUser.chatBotRequest,

  //stackGraph

  stackgraphList: state => state.stackGraph.graphList,
  // 存放当前图谱数据信息
  stackGraphRes: state => state.stackGraph.stackGraphRes,
  // 存放当前图谱基本信息
  stackGraphParams: state => state.stackGraph.stackGraphParams,
  stackcurrentGraphId: state => state.stackGraph.currentGraphId,
  stackcurrentGraphName: state => state.stackGraph.currentGraphName,
  stackfile: state => state.stackGraph.file,
  stackfuzzyMatchingOutComeList: state => state.stackGraph.fuzzyMatchingOutComeList,
  stackshowModel: state => state.stackGraph.showModel,
  stacksearchString: state => state.stackGraph.searchString,
  stackgraphMovable: state => state.stackGraph.graphMovable,
  stacksearchBoardVisible: state => state.stackGraph.searchBoardVisible,
  stacknumericalVisible: state => state.stackGraph.numericalVisible,
  stacknodeClickVisible: state => state.stackGraph.nodeClickVisible,
  stackrelationClickVisible: state => state.stackGraph.relationClickVisible,
  stackstockCardVisible: state => state.stackGraph.stockCardVisible,
  tagChart: state => state.stackGraph.tagChart,
}

export default getters
