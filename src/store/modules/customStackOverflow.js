import {
  findStackNodesAPI,
  findStackRelationsAPI,
} from '../../api/customStackOverflow'

const stackGraph = {
  state: {
    graphList: [],
    // 存放当前图谱数据信息
    stackGraphRes: {
      nodes: [],
      links: [],
    },
    // 存放当前图谱基本信息
    stackGraphParams: {},
    currentGraphId: -1,
    currentGraphName: '',
    file: null,
    searchString: '',
    fuzzyMatchingOutComeList: [],
    showModel: '',
    graphMovable: true,
    searchBoardVisible: false,
    numericalVisible: false,
    relationClickVisible: false,
    nodeClickVisible: false,
    stockCardVisible: false,
  },
  mutations: {
    set_graphList: function (state, data) {
      state.graphList = data
    },
    set_stackGraphRes: function (state, data) {
      state.stackGraphRes.nodes = data.nodes
      state.stackGraphRes.links = data.links
    },
    set_stackGraphParams: function (state, data) {
      state.stackGraphParams = {
        ...state.stackGraphParams,
        ...data,
      }
    },
    set_stackGraphParamsClear: function (state, data){
      state.stackGraphParams = data
    },
    set_currentGraphId: function (state, data) {
      state.currentGraphId = data
    },
    set_currentGraphName: function (state, data) {
      state.currentGraphName = data
    },
    set_loadFile: function (state, data) {
      state.file = data
    },
    set_searchString: function (state, data) {
      state.searchString = data
    },
    set_filterList: function (state, data) {
      state.filterList = data
    },
    set_fuzzyMatchingOutComeList: function (state, data) {
      state.fuzzyMatchingOutComeList = data
    },
    set_showModel: function (state, data) {
      state.showModel = data
    },
    set_graphMovable: function (state, data) {
      state.graphMovable = data
    },
    set_searchBoardVisible: function (state, data) {
      if (data) {
        state.searchBoardVisible = true
        state.numericalVisible = false
        state.nodeClickVisible = false
        state.relationClickVisible = false
      } else {
        state.searchBoardVisible = false
      }
    },
    set_numericalVisible: function (state, data) {
      if (data) {
        state.numericalVisible = true
        state.searchBoardVisible = false
        state.nodeClickVisible = false
        state.relationClickVisible = false
      } else {
        state.numericalVisible = false
      }
    },
    set_relationClickVisible: function (state, data) {
      console.log(data)
      if (data) {
        state.relationClickVisible = true
        state.nodeClickVisible = false
        state.searchBoardVisible = false
        state.numericalVisible = false
      } else {
        state.relationClickVisible = false
      }
    },
    set_nodeClickVisible: function (state, data) {
      if (data) {
        state.nodeClickVisible = true
        state.relationClickVisible = false
        state.numericalVisible = false
        state.searchBoardVisible = false
      } else {
        state.nodeClickVisible = false
      }
    },
    set_stockCardVisible: function (state, data) {
      state.stockCardVisible = data
    }
  },
  actions: {
    // 传入当前新增graph名称，将返回的graphId回传到currentGraphId
    getstackGraph: async ({commit, state}) => {
      const nodeRes = await findStackNodesAPI()
      const linkRes = await findStackRelationsAPI()
      if (nodeRes && linkRes) {
        commit('set_stackGraphRes', {
          nodes: nodeRes.data.content,
          links: linkRes.data.content,
        })
      }
    },
  },
}

export default stackGraph
