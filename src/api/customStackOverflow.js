import axios from "axios";

const api = {
  customStackOverflowPre: '/coinService/api/stackoverflow'
}

export function findStackNodesAPI() {
  return axios({
    url: `${api.customStackOverflowPre}/findStackNodes`,
    method: 'GET'
  })
}

export function findStackRelationsAPI() {
  return axios({
    url: `${api.customStackOverflowPre}/findStackRelations`,
    method: 'GET',
  })
}

export function findTargetNodesChartAPI(name) {
  return axios({
    url: `${api.customStackOverflowPre}/findTargetNodesChart/${name}`,
    method: 'GET',
  })
}

