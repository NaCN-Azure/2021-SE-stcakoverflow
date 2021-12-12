<template>
  <div>
    <el-select v-show="select_year" class="my-el-select" v-model="value_year" placeholder="所有年份" @change="change" style="width: 103px;right: 0;margin-top: 10px;margin-right: 15px;float:right; z-index:5;">
      <el-option
        v-for="item in options_year"
        :key="item.value"
        :label="item.label"
        :value="item.value">
      </el-option>
    </el-select>
    <div class="GraphMapContainer" style="position:relative; z-index:1;"  >
    </div>
  </div>
</template>

<script>
  import {mapMutations, mapActions, mapGetters} from 'vuex'
  import $ from 'jquery'
  import {
    findStackNodesAPI,
    findStackRelationsAPI,
  } from '../../../api/customStackOverflow'
  import * as d3 from 'd3'
  export default {
    name: "Graph",
    data() {
      return {
        options_year: [ {
          value: '2021',
          label: '2021年'
        },{
          value: '2020',
          label: '2020年'
        },{
          value: '2019',
          label: '2019年'
        },{
          value: '2018',
          label: '2018年'
        }, {
          value: '2017',
          label: '2017年'
        }, {
          value: '2016',
          label: '2016年'
        }, {
          value: '2015',
          label: '2015年'
        }, {
          value: '年份',
          label: '所有年份'
        }],
        value_year: '',

        testData1: {
          "nodes":[{"id":17257,"graphId":-1,"name":"Answer 2ToQ17233","label":"Replies","x":0.0,"y":0.0,"shape":"circle","color":"#F4A460","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17284,"graphId":-1,"name":"Answer 1ToQ17252","label":"Replies","x":0.0,"y":0.0,"shape":"circle","color":"#F4A460","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17252,"graphId":-1,"name":"Why is processing a sorted array faster than processing an unsorted array?","label":"Questions","x":0.0,"y":0.0,"shape":"circle","color":"#EE82EE","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17319,"graphId":-1,"name":"undo","label":"Tags","x":0.0,"y":0.0,"shape":"circle","color":"#66ccff","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17320,"graphId":-1,"name":"Answer 3ToQ17233","label":"Replies","x":0.0,"y":0.0,"shape":"circle","color":"#F4A460","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17283,"graphId":-1,"name":"performance","label":"Tags","x":0.0,"y":0.0,"shape":"circle","color":"#66ccff","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17258,"graphId":-1,"name":"git-push","label":"Tags","x":0.0,"y":0.0,"shape":"circle","color":"#66ccff","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17318,"graphId":-1,"name":"git","label":"Tags","x":0.0,"y":0.0,"shape":"circle","color":"#66ccff","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17317,"graphId":-1,"name":"Answer 3ToQ17252","label":"Replies","x":0.0,"y":0.0,"shape":"circle","color":"#F4A460","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17233,"graphId":-1,"name":"How do I undo the most recent local commits in Git?","label":"Questions","x":0.0,"y":0.0,"shape":"circle","color":"#EE82EE","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17236,"graphId":-1,"name":"How do I delete a Git branch locally and remotely?","label":"Questions","x":0.0,"y":0.0,"shape":"circle","color":"#EE82EE","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17234,"graphId":-1,"name":"version-control","label":"Tags","x":0.0,"y":0.0,"shape":"circle","color":"#66ccff","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17259,"graphId":-1,"name":"Answer 2ToQ17236","label":"Replies","x":0.0,"y":0.0,"shape":"circle","color":"#F4A460","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17232,"graphId":-1,"name":"cpu-architecture","label":"Tags","x":0.0,"y":0.0,"shape":"circle","color":"#66ccff","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17253,"graphId":-1,"name":"c++","label":"Tags","x":0.0,"y":0.0,"shape":"circle","color":"#66ccff","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17321,"graphId":-1,"name":"git-remote","label":"Tags","x":0.0,"y":0.0,"shape":"circle","color":"#66ccff","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17237,"graphId":-1,"name":"git-branch","label":"Tags","x":0.0,"y":0.0,"shape":"circle","color":"#66ccff","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17256,"graphId":-1,"name":"git-commit","label":"Tags","x":0.0,"y":0.0,"shape":"circle","color":"#66ccff","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17235,"graphId":-1,"name":"Answer 1ToQ17233","label":"Replies","x":0.0,"y":0.0,"shape":"circle","color":"#F4A460","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17322,"graphId":-1,"name":"Answer 3ToQ17236","label":"Replies","x":0.0,"y":0.0,"shape":"circle","color":"#F4A460","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17238,"graphId":-1,"name":"Answer 1ToQ17236","label":"Replies","x":0.0,"y":0.0,"shape":"circle","color":"#F4A460","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17254,"graphId":-1,"name":"branch-prediction","label":"Tags","x":0.0,"y":0.0,"shape":"circle","color":"#66ccff","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17231,"graphId":-1,"name":"java","label":"Tags","x":0.0,"y":0.0,"shape":"circle","color":"#66ccff","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false},{"id":17255,"graphId":-1,"name":"Answer 2ToQ17252","label":"Replies","x":0.0,"y":0.0,"shape":"circle","color":"#F4A460","node_size":10,"font_size":10,"typesetting_x":0.0,"typesetting_y":0.0,"shown":true,"highlighted":false}],
          "links": [{"id":37857,"source":17236,"target":17318,"graphId":-1,"name":"belongs","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37718,"source":17322,"target":17236,"graphId":-1,"name":"answers","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37808,"source":17320,"target":17233,"graphId":-1,"name":"answers","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37806,"source":17233,"target":17318,"graphId":-1,"name":"belongs","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37805,"source":17317,"target":17252,"graphId":-1,"name":"answers","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37715,"source":17257,"target":17233,"graphId":-1,"name":"answers","label":null,"solid":false,"shown":false,"highlighted":false},{"id":36753,"source":17238,"target":17236,"graphId":-1,"name":"answers","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37804,"source":17252,"target":17283,"graphId":-1,"name":"belongs","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37867,"source":17252,"target":17231,"graphId":-1,"name":"belongs","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37888,"source":17252,"target":17232,"graphId":-1,"name":"belongs","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37710,"source":17252,"target":17253,"graphId":-1,"name":"belongs","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37810,"source":17236,"target":17321,"graphId":-1,"name":"belongs","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37807,"source":17233,"target":17319,"graphId":-1,"name":"belongs","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37809,"source":17236,"target":17234,"graphId":-1,"name":"belongs","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37714,"source":17233,"target":17256,"graphId":-1,"name":"belongs","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37711,"source":17252,"target":17254,"graphId":-1,"name":"belongs","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37858,"source":17236,"target":17237,"graphId":-1,"name":"belongs","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37713,"source":17255,"target":17252,"graphId":-1,"name":"answers","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37712,"source":17284,"target":17252,"graphId":-1,"name":"answers","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37855,"source":17233,"target":17234,"graphId":-1,"name":"belongs","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37716,"source":17236,"target":17258,"graphId":-1,"name":"belongs","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37856,"source":17235,"target":17233,"graphId":-1,"name":"answers","label":null,"solid":false,"shown":false,"highlighted":false},{"id":37717,"source":17259,"target":17236,"graphId":-1,"name":"answers","label":null,"solid":false,"shown":false,"highlighted":false}]
        },
        testData: {
          "nodes":[],
          "links": []
          },
        update: true,
        select_year:true,

        year_show:false,
        flagAddRelation: 'false',
        mousedownNode: '',
        mouseupNode: '',
        moveStop: '',
        DrawLine: '',
        startX: '',
        startY: '',
        Xstart: '',
        Ystart: '',
        zoomOperate: [],
        AddRelationVisible: false,
        input_Relation: '',
        //实虚线，是否显示名字
        lineModal: [{
          isSolid: 'true',
          label: '实线'
        }, {
          isSolid: 'false',
          label: '虚线'
        }],
        isSolid: 'true',
        lineName: [{
          isShow: 'true',
          label: '显示关系名'
        }, {
          isShow: 'false',
          label: '不显示关系名'
        }],
        isShow: 'true',

        // 清空图谱
        confirm_deleteAllVisible: false,

        time: 1,
        tempwidth: 625,
        tempheight: 265,
        startXY: [],

        svgArea: null,
        simulation: null,
        width: 1000,
        height: 370,
        links: [],
        nodes: [],
        rectNodes: [], //方形节点
        triangleUpNodes: [], //三角形节点
        diamondNodes: [], //菱形节点
        nodesName: [],
        linksName: [],
        zoom: '',
      }
    },



    async mounted() {
      await this.getGraph();
    },

    methods: {
      async getGraph() {
        const _this=this;
        await this.$axios.all([
          this.$axios.get('/coinService/api/stackoverflow/findStackNodes'),
          this.$axios.get('/coinService/api/stackoverflow/findStackRelations')
        ])
          .then(this.$axios.spread(function (Resp1, Resp2) {
            _this.testData.nodes=Resp1.data.content;
            _this.testData.links=Resp2.data.content;
          }));
        _this.initGraph(_this.testData);

      },

      initGraph(data) {
        const links = data.links.map(d => Object.create(d));
        const nodes = data.nodes.map(d => Object.create(d));

        this.simulation = d3.forceSimulation(nodes)
          .force("link", d3.forceLink(links).id(d => d.id).distance(180))
          .force("collide", d3.forceCollide().radius(() => 50))
          .force("charge", d3.forceManyBody().strength(-40))
          .force("center", d3.forceCenter(this.width / 2, this.height / 2));

        this.svgArea = d3.select(".GraphMapContainer")
          .append("svg")
          .attr("viewBox", [0, 0, this.width, this.height])
          .call(d3.zoom().on("zoom", function (event) {
            g.attr("transform", event.transform)
          }))

        this.addMarkers();

        const g = this.svgArea.append("g")
          .attr("class", "content");

        this.links = g.append("g")
          .selectAll("path")
          .data(links, function (d) {
            if (typeof (d.source) === 'object') {
              return d.source.id + "_" + d.name + "_" + d.target.id
            } else {
              return d.source + "_" + d.name + "_" + d.target
            }
          })
          .join("path")
          .attr("stroke", "#999")
          .attr("stroke-opacity", 0.8)
          .attr("marker-end", "url(resolved)")
          .attr("fill-opacity", 0)
          .attr("stroke-width", 2)
          .attr("class", "link")
          .attr("id", function (d) {
            if (typeof (d.source) === 'object') {
              return d.source.id + "_" + d.name + "_" + d.target.id
            } else {
              return d.source + "_" + d.name + "_" + d.target
            }
          })
          .on('mouseover', function (d) {
            d3.select(this).attr("stroke", "rgba(45,64,111,0.5)").attr("stroke-width", 4);
          }).on('mouseout', function (d) {
            d3.select(this).attr("stroke", "rgba(45,64,111,0.38)").attr("stroke-width", 2);
          });

        this.linksName = g.append("g")
          .selectAll("text")
          .data(links, function (d) {
            if (typeof (d.source) === 'object') {
              return d.source.id + "_" + d.name + "_" + d.target.id
            } else {
              return d.source + "_" + d.name + "_" + d.target
            }
          })
          .join("text")
          .style('text-anchor', 'middle')
          .style('font-size', '10px')
          .style('font-weight', 'bold')
          .append('textPath')
          .attr('xlink:href', function (d) {
            if (typeof (d.source) === 'object') {
              return "#" + d.source.id + "_" + d.name + "_" + d.target.id
            } else {
              return "#" + d.source + "_" + d.name + "_" + d.target
            }
          })
          .attr('startOffset', '50%')
          .text(d => d.name);

        this.nodes = g.append("g")
          .attr("stroke", "#999")
          .attr("stroke-width", 1)
          .selectAll("circle")
          .data(nodes)
          .join("circle")
          .attr("r", d => d.node_size)
          .attr("class", "node")
          .attr("fill", function (d) {
            return d.color
          })
          .on("click", this.nodeClick)
          .call(this.drag(this.simulation))
          .on('mouseover', function (d) {
            //当鼠标放在节点上时，高亮节点
            d3.select(this)
              .attr("stroke", "rgba(34,39,75,0.77)")
              .attr("stroke-width", 2.5);
          }).on('mouseout', function (d) {
            //当鼠标不再放在节点上时，恢复节点
            d3.select(this)
              .attr("stroke", "rgba(45,64,111,0.5)")
              .attr("stroke-width", 1);
          });

        this.nodesName = g.append("g")
          .selectAll("text")
          .data(nodes)
          .join("text")
          .text(function (d) {
            return d.name
          })
          .attr("dx", -17.5)
          .attr("dy", 45)
          .attr("class", "node-name")
          .attr("fill", function (d) {
            return d.color
          });

        this.simulation.on("tick", () => {
          this.links
            .attr("d", function (d) {
              if (d.source.x < d.target.x) {
                return "M " + d.source.x + " " + d.source.y + " L " + d.target.x + " " + d.target.y
              } else if (d.source.x === d.target.x) {
                return "M" + (d.target.x + 20) + " " + (d.target.y - 10) + " C " + (d.target.x + 120) + " " + (d.target.y - 60)
                  + " " + (d.target.x + 120) + " " + (d.target.y + 60) + "," + (d.target.x + 20) + " " + (d.target.y + 10);
              } else {
                return "M " + d.target.x + " " + d.target.y + " L " + d.source.x + " " + d.source.y
              }
            })
            .attr("marker-end", function (d) {
              if (d.source.x < d.target.x) {
                return "url(#positiveMarker)"
              } else {
                return null
              }
            })
            .attr("marker-start", function (d) {
              if (d.source.x < d.target.x) {
                return null
              } else {
                return "url(#negativeMarker)"
              }
            })

          this.nodes
            .attr("cx", d => d.x)
            .attr("cy", d => d.y);

          this.nodesName
            .attr("x", function (d) {
              if (d.name.length > 1) {
                return d.x + 0.5 * 30 - this.getComputedTextLength() * 0.45
              } else {
                return d.x + 0.40 * 30
              }
            })
            .attr("y", d => d.y + 5);
        });
      },

      addMarkers() {
        const positiveMarker = this.svgArea.append("marker")
          .attr("id", "positiveMarker")
          .attr("orient", "auto")
          .attr("stroke-width", 2)
          .attr("markerUnits", "strokeWidth")
          .attr("markerUnits", "userSpaceOnUse")
          .attr("viewBox", "0 -5 10 10")
          .attr("refX", 15)
          .attr("refY", 0)
          .attr("markerWidth", 12)
          .attr("markerHeight", 12)
          .append("path")
          .attr("d", "M 0 -5 L 10 0 L 0 5")
          .attr('fill', '#999')
          .attr("stroke-opacity", 0.6);
        const negativeMarker = this.svgArea.append("marker")
          .attr("id", "negativeMarker")
          .attr("orient", "auto")
          .attr("stroke-width", 2)
          .attr("markerUnits", "strokeWidth")
          .attr("markerUnits", "userSpaceOnUse")
          .attr("viewBox", "0 -5 10 10")
          .attr("refX", -5)
          .attr("refY", 0)
          .attr("markerWidth", 12)
          .attr("markerHeight", 12)
          .append("path")
          .attr("d", "M 10 -5 L 0 0 L 10 5")
          .attr('fill', '#999')
          .attr("stroke-opacity", 0.6);
      },

      drag(simulation) {
        function dragstarted(event) {
          if (!event.active) simulation.alphaTarget(0.3).restart();
          event.subject.fx = event.subject.x;
          event.subject.fy = event.subject.y;
        }

        function dragged(event) {
          event.subject.fx = event.x;
          event.subject.fy = event.y;
        }

        function dragended(event) {
          if (!event.active) simulation.alphaTarget(0);
          event.subject.fx = null;
          event.subject.fy = null;
        }

        return d3.drag()
          .on("start", dragstarted)
          .on("drag", dragged)
          .on("end", dragended);
      },

      resetGraph(index) {
        if(localStorage.getItem('currentId')!==index){
          localStorage.setItem('currentId',index)
          this.refreshSelf()
        }
      },
      removeSvg() {
        // console.log('remove!')
        d3.selectAll(".GraphMapContainer > *").remove();
      },


      async change(value){
        console.log(value);
        this.removeSvg();
        const _this=this;
        if(value==="年份"){
          await this.$axios.all([
            this.$axios.get('/coinService/api/stackoverflow/findStackNodes'),
            this.$axios.get('/coinService/api/stackoverflow/findStackRelations')
          ])
            .then(this.$axios.spread(function (Resp1, Resp2) {
              _this.testData.nodes=Resp1.data.content;
              _this.testData.links=Resp2.data.content;
            }));
          _this.initGraph(_this.testData);
          return;
        }

        await this.$axios.all([
          this.$axios.get('/coinService/api/stackoverflow/findStackNodesYear/'+value),
          this.$axios.get('/coinService/api/stackoverflow/findStackRelationsYear/'+value)
        ])
          .then(this.$axios.spread(function (Resp1, Resp2) {
            _this.testData.nodes=Resp1.data.content;
            _this.testData.links=Resp2.data.content;
          }));
        _this.initGraph(_this.testData);
      },

      changeSize(){
        if(this.width>900) {
          this.width = this.width / 1.7;
        }
        this.removeSvg();
        this.select_year=false;
      },

      async ResetSize(){
        this.width=this.width*1.7;
        this.removeSvg();

        const _this=this;
        await this.$axios.all([
          this.$axios.get('/coinService/api/stackoverflow/findStackNodes'),
          this.$axios.get('/coinService/api/stackoverflow/findStackRelations')
        ])
          .then(this.$axios.spread(function (Resp1, Resp2) {
            _this.testData.nodes=Resp1.data.content;
            _this.testData.links=Resp2.data.content;
          }));
        _this.initGraph(_this.testData);
        this.select_year=true;
      },

      //点击节点
      nodeClick(d, i) {
        this.$parent.setSearchContent(i.name);
        this.$parent.searchGraph();
      },
    },
  }
</script>

<style scoped>
  .my-el-select /deep/ .el-input .el-input__inner {
    border-color: #DCDFE6  !important;
    background: transparent;
    border: 0;
  }
</style>

