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

