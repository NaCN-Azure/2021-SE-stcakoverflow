<template>
  <div>
    <div class="GraphMapContainer"></div>
  </div>
</template>

<script>
  import {mapMutations, mapActions, mapGetters} from 'vuex'
  import * as d3 from 'd3'
  export default {
    name: "Graph",
    data() {
      return {
        testData1: {
          "nodes": [
            {
              "id": "1",
              "name": "互联网相关服务",
              "color": "#FF8000",
            }, {
              "id": "2",
              "name": "湖北盛天网络技术股份有限公司",
              "color": "#A52A2A",
            },
          ],
          "links": [
            {
              "id": "3",
              "name": "行业二级分类",
              "source": "1",
              "target": "2"
            }]
        },
        update: true,

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
        width: 1250,
        height: 520,
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

    computed: {
      ...mapGetters([
      ])
    },
    async mounted() {
      await this.initGraph(this.testData1)
    },
    methods: {
      ...mapMutations([
      ]),

      ...mapActions([
      ]),
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
          .attr("stroke-width", 2)
          .selectAll("circle")
          .data(nodes)
          .join("circle")
          .attr("r", 30)
          .attr("class", "node")
          .attr("fill", function (d) {
            return d.color
          })
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
          .attr("refX", 35)
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
          .attr("refX", -25)
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

      removeSvg() {
        console.log('remove!')
        d3.selectAll(".bodyGraphContainer > *").remove();
      },

      resetGraph(index) {
        if(localStorage.getItem('currentId')!==index){
          localStorage.setItem('currentId',index)
          this.refreshSelf()
        }
      }
    }
  }
</script>

<style scoped>

</style>

