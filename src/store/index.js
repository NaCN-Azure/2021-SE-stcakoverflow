import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import customNode from "./modules/customNode";
import customRelation from "./modules/customRelation";
import customGraph from "./modules/customGraph";
import customUser from "./modules/customUser";
import customStackOverflow from "./modules/customStackOverflow";

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    customNode,
    customRelation,
    customGraph,
    customUser,
    customStackOverflow
  },
  state: {},
  mutations: {},
  actions: {},
  getters
})
