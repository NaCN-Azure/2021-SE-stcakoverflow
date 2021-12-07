<template>
  <div class="box">
    <div class="header">
      <img class="icon" src="https://lililizi.oss-cn-beijing.aliyuncs.com/3678be9a7b6d4697cc40a9b5428f738%28%E5%B7%B2%E5%8E%BB%E5%BA%95%29%20%281%29.jpg" height="30"
           width="30"/>
      <p class="title"><strong>Who Am I ？</strong></p>
      <el-input
        class="inline-input"
        v-model="searchInput"
        placeholder="搜索"
        clearable
        @clear="clearSearch1">
        <el-button class="search_btn" slot="suffix" icon="el-icon-search" @click="searchGraph"></el-button>
      </el-input>
      <Avatar></Avatar>
    </div>

    <el-dialog
      title="创建新图谱"
      :visible.sync="CreateChartVisible"
      width="30%">
               <span>
                 <el-input v-model="input_chart" placeholder="请输入图谱名"></el-input>
               </span>
      <span>

             </span>
      <span slot="footer" class="dialog-footer">
               <el-button @click="CreateChartVisible = false">取 消</el-button>
               <el-button type="primary" @click="handleCreateChartClose">确 定</el-button>
               </span>
    </el-dialog>


    <div>
      <div class="buttonHolder">
        <el-button id="btn_P" class="btn_PublicGraph" @click="PublicGraph"></el-button>
        <div id="Name_P" style="float: left; font-size: 10px;height: 15px;margin-left: 5px;color: #000000;"
             v-model="bolPublicGraph"><strong>知识图谱</strong></div>
        <el-button id="btn_W" class="btn_Warehouse" @click="Warehouse"></el-button>
        <div id="Name_W" style="float: left; font-size: 10px;height: 15px;margin-left: 5px" v-model="bolPublicGraph">
          <strong class="PersonalHouse">个人仓库</strong></div>
        <!--        <a href="#" class="button1 tick" style="margin-top: 20px"></a>-->

        <a href="#" class="button1 tick1" style="margin-top: 20px" v-show="this.Modal==='Warehouse'"
           @click="CreateChartVisible=true"></a>
<!--        <a href="#" class="button1 tick2" style="margin-top: 20px" v-show="this.Modal==='publicGraph'"-->
<!--           @click="set_stockCardVisible(true)"></a>-->
      </div>

      <div class="leftSidePart">
<!--        <div class="PublicMode" v-show="this.Modal==='publicGraph'">-->
<!--          <div>-->
<!--            <div class="PublicGraph">-->
<!--              <FourGraphs v-if="updateFour" :refreshSelf="refreshFour"></FourGraphs>-->
<!--            </div>-->
<!--          </div>-->
<!--          <div style="margin-left: 940px">-->
<!--            <div class="recommend">-->
<!--              <stock-card v-if="stock_card_update" class="recommend_items" :refreshSelf="refreshStockCard"></stock-card>-->
<!--            </div>-->
<!--            <div class="chatBot">-->
<!--              <ChatLog></ChatLog>-->
<!--            </div>-->
<!--          </div>-->
<!--        </div>-->

        <div class="body_publicGraph" id="public_Graph" v-show="this.Modal==='publicGraph'">
          <Graph ref="publicGraph"></Graph>
        </div>

        <div class="RightBlock" v-show="rightBlock">
          <strong>{{SearchRes.name}}</strong>
          <p>{{SearchRes.excerpt}}</p>
        </div>

        <div class="body" v-show="this.Modal==='Warehouse'">
          <GraphCard v-if="update" style="margin-top: 15px;margin-bottom: 20px;" ref="GraphCard"></GraphCard>
        </div>
      </div>
    </div>


  </div>
</template>

<script>
import {mapGetters, mapMutations, mapActions} from 'vuex'
import Avatar from '../Graphs/Components/Avatar'
import GraphCard from './graphCard'
import Graph from './Component/Graph.vue'

export default {
  name: "home",
  components: {
    Avatar,
    GraphCard,
    Graph
  },
  computed: {
    ...mapGetters([
      'userParams',
      'userGraphList',
    ])
  },
  async mounted() {
    this.changePic();
    this.set_userParams({id: localStorage.getItem('userId')})
    await this.findUserParams()
  },

  data() {
    return {
      rightBlock:false,
      bolWarehouseBtn: false,
      bolPublicGraph: true,
      searchInput: '',
      clearSearch: '',
      Modal: 'publicGraph',

      CreateChartVisible: false,
      // 图谱名
      input_chart: '',

      update: true,
      updateFour: true,

      SearchData: {
        "nodes":[],
        "links": []
      },
      SearchRes:{},
    }
  },
  methods: {
    ...mapMutations([
      'set_userParams',
      'set_customGraphParams',
      'set_customGraphParamsClear',
    ]),

    ...mapActions([
      'getUserGraphList',
      'addGraph',
      'editUserParams',
      'findUserParams',
    ]),


    PublicGraph() {
      if (this.bolPublicGraph === 'true') return;
      this.Modal = 'publicGraph';
      this.bolPublicGraph = true;
      this.bolWarehouseBtn = false;
      var obj1 = document.getElementById("btn_P");
      obj1.style.cssText = 'background-image: url("https://lililizi.oss-cn-beijing.aliyuncs.com/a_%E8%BD%AF%E5%B7%A53/%E5%9B%BE%E7%89%8712.png") ;background-size:55px;margin-left: 0;margin-top:4px;height:65px;width:65px';
      var obj2 = document.getElementById("btn_W");
      obj2.style.cssText = 'background-image: url("https://lililizi.oss-cn-beijing.aliyuncs.com/a_%E8%BD%AF%E5%B7%A53/%E6%88%BF%E5%AD%90%20%282%29.png") ;background-size:55px;margin-left: 0;margin-top:4px;height:65px;width:65px';
      var obj3 = document.getElementById("Name_W");
      obj3.style.cssText = 'float: left; font-size: 10px;height: 15px;margin-left: 5px;color: #f3f6fd;';
      var obj4 = document.getElementById("Name_P");
      obj4.style.cssText = 'float: left; font-size: 10px;height: 15px;margin-left: 5px;color: #000000;';
    },
    Warehouse() {
      if (this.bolWarehouseBtn === 'true') return;
      this.Modal = "Warehouse";
      this.bolWarehouseBtn = true;
      this.bolPublicGraph = false;
      var obj1 = document.getElementById("btn_W");
      obj1.style.cssText = 'background-image: url("https://lililizi.oss-cn-beijing.aliyuncs.com/a_%E8%BD%AF%E5%B7%A53/%E5%9B%BE%E7%89%8711.png") ;background-size:55px;margin-left: 0;margin-top:4px;height:65px;width:65px';
      var obj2 = document.getElementById("btn_P");
      obj2.style.cssText = 'background-image: url("https://lililizi.oss-cn-beijing.aliyuncs.com/a_%E8%BD%AF%E5%B7%A53/%E6%95%B0%E6%8D%AE%E5%9B%BE%E8%B0%B1.png") ;background-size:55px;margin-left: 0;margin-top:4px;height:65px;width:65px';
      var obj3 = document.getElementById("Name_P");
      obj3.style.cssText = 'float: left; font-size: 10px;height: 15px;margin-left: 5px;color: #f3f6fd;';
      var obj4 = document.getElementById("Name_W");
      obj4.style.cssText = 'float: left; font-size: 10px;height: 15px;margin-left: 5px;color: #000000;';
      if(this.rightBlock===true){
        this.rightBlock=false;
        var obj = document.getElementById("public_Graph");
        obj.style.cssText = 'float: left;width: 1400px;margin-top: 20px;background: white; border-radius: 20px; height:580px;';
        this.$refs.publicGraph.ResetSize();
      }

    },

    //todo 搜索按钮
    async searchGraph() {
      if(this.Modal==='Warehouse') {
        if (this.searchInput != '') {
          this.$refs.GraphCard.resetGraphList();
          this.$refs.GraphCard.resetbolSearch();
          this.$refs.GraphCard.search(this.searchInput);
        }
      }
      else{
        if (this.searchInput != '') {
          var obj = document.getElementById("public_Graph");
          obj.style.cssText = 'float: left;width: 900px;margin-top: 20px;background: white; border-radius: 20px; height:580px;';
          this.$refs.publicGraph.changeSize();
          this.rightBlock = true;

          const _this=this;
          await this.$axios.all([
            this.$axios.get('/coinService/api/stackoverflow/findTargetNodesInfo/'+this.searchInput),
            this.$axios.get('/coinService/api/stackoverflow/findTargetNodesRelated/'+this.searchInput),
            this.$axios.get('/coinService/api/stackoverflow/findTargetNodesDescription/'+this.searchInput)
          ])
            .then(this.$axios.spread(function (Resp1, Resp2,Resp3) {
              _this.SearchData.nodes=Resp1.data.content;
              _this.SearchData.links=Resp2.data.content;
              _this.SearchRes=Resp3.data.content;
            }));
          this.$refs.publicGraph.initGraph(_this.SearchData);
        }
      }
    },
    clearSearch1() {
      this.searchInput = '';
      if(this.Modal==='Warehouse') {
        this.$refs.GraphCard.resetGraphList();
        this.$refs.GraphCard.resetbolSearch();
      }
      else{
        this.rightBlock=false;
        var obj = document.getElementById("public_Graph");
        obj.style.cssText = 'float: left;width: 1400px;margin-top: 20px;background: white; border-radius: 20px; height:580px;';
        this.$refs.publicGraph.ResetSize();
      }
    },

    changePic() {
      for (let i = 0; i < this.userGraphList.length; i++) {
        if (this.userGraphList[i].id === this.$route.params.GraphId) {
          let tempPic = this.userGraphList[i];
          tempPic.url = this.$route.params.dataUrl;
          tempPic.time = this.$route.params.dataTime;
          this.$set(this.userGraphList, i, tempPic);
          break;
        }
      }
    },

    async handleCreateChartClose() {
      this.set_customGraphParamsClear({
        name: this.input_chart,
        url: "https://lililizi.oss-cn-beijing.aliyuncs.com/%E5%9B%BE%E7%89%871.png",
        time: new Date().toLocaleDateString()
      });
      await this.addGraph();

      this.update = false;
      this.input_chart = '';
      this.CreateChartVisible = false;
      this.$nextTick(() => {
        this.update = true
      })
    },

    refreshGraph() {
      this.update = false;
      this.$nextTick(() => {
        this.update = true
      })
    },


    refreshFour() {
      console.log("refresh")
      this.updateFour = false;
      this.$nextTick(() => {
        this.updateFour = true
      })
    },
  },
}
</script>

<style scoped>
* {
  font-family: "Roboto", Arial, sans-serif;
}

.header {
  height: 68px;
}

.icon {
  float: left;
  margin-top: 16px;
  margin-left: 32px;
}

.title {
  float: left;
  font-size: 20px;
  margin-left: 5px;
}

.leftSidePart {
  margin-left: 90px;
}

.inline-input {
  float: left;
  margin-left: 23px;
  margin-top: 10px;
  width: 31%;
  border-radius: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.06), 0 0 6px rgba(0, 0, 0, .04)
}

.inline-input >>> .el-input__inner {
  border-radius: 16px;
}

.search_btn {
  margin-top: 2px;
  background-color: Transparent;
  border-style: none;
}

.body {
  float: left;
  width: 97%;
  margin-top: 20px;

  background: white;
  border-radius: 20px;
}

.body_publicGraph{
  float: left;
  width:1400px;
  margin-top: 20px;
  background: white;
  border-radius: 20px;
  height:575px;
}

.recommend {
  position: absolute;
}

.recommend_items {
  width: 420px;
  margin-top: 10px;
  float: left
}

.reRecommend_button {
  float: left;
  width: 40px;
  height: 40px;
  font-size: 35px;
  background-repeat: no-repeat;
  background-color: transparent;
  border: 0 none;
}

/*.chatBot {*/
/*  position: absolute;*/
/*  margin-top: 160px;*/
/*  margin-left: 16%;*/
/*  width: 400px;*/
/*  background-color: white;*/
/*  border-radius: 15px;*/
/*}*/

/*.PublicMode {*/
/*  width: 1550px;*/
/*  height: 700px;*/
/*  float: left;*/
/*}*/

/*.PublicGraph {*/
/*  margin-top: 10px;*/
/*  float: left;*/
/*  width: 110%;*/
/*  height: 800px;*/
/*  background: white;*/
/*  border-radius: 35px;*/
/*  margin-left: 10px;*/
/*}*/

.buttonHolder {
  float: left;
  width: 70px;
  margin-left: 20px;
  margin-top: 25px;
}

/*.button2 {*/
/*  background-image: -webkit-linear-gradient(top, #f4f1ee, #fff);*/
/*  background-image: linear-gradient(top, #f4f1ee, #fff);*/
/*  border-radius: 50%;*/
/*  box-shadow: 0px 8px 10px 0px rgba(109, 109, 109, 0.3), inset 0px 4px 1px 1px white, inset 0px -3px 1px 1px rgba(204, 198, 197, .5);*/
/*  float: left;*/
/*  height: 50px;*/
/*  margin: 0 30px 30px 0;*/
/*  position: relative;*/
/*  width: 50px;*/
/*  -webkit-transition: all .1s linear;*/
/*  transition: all .1s linear;*/
/*}*/

.button1 {
  background-image: -webkit-linear-gradient(top, #f4f1ee, rgba(255, 255, 255, 0.7));
  background-image: linear-gradient(top, #f4f1ee, #fff);
  border-radius: 15%;
  box-shadow: 0px 8px 10px 0px rgba(109, 109, 109, 0.07), inset 0px 4px 1px 1px white, inset 0px -3px 1px 1px rgba(204, 198, 197, 0.31);
  float: left;
  height: 30px;
  margin-left: 2px;
  position: relative;
  width: 50px;
  -webkit-transition: all .1s linear;
  transition: all .1s linear;
}

.button1:after {
  color: #e9e6e4;
  content: "";
  display: block;
  font-size: 30px;
  height: 30px;
  text-decoration: none;
  text-shadow: 0px -1px 1px #bdb5b4, 1px 1px 1px white;
  position: absolute;
  width: 30px;
}

.tick1:after {
  content: "＋";
  left: 11px;
  bottom: 7px;
}

/*.tick2:after {*/
/*  content: "o";*/
/*  left: 11px;*/
/*  top: 4px;*/
/*}*/


.button1:hover {
  background-image: -webkit-linear-gradient(top, #fff, #f4f1ee);
  background-image: linear-gradient(top, #fff, #f4f1ee);
  color: #0088cc;
}

.tick1:hover:after {
  color: rgba(0, 0, 0, 0.2);
  text-shadow: 0px 0px 6px rgba(0, 0, 0, 0.21);
}

/*.tick2:hover:after {*/
/*  color: rgba(0, 0, 0, 0.57);*/
/*  text-shadow: 0px 0px 6px rgba(0, 0, 0, 0.56);*/
/*}*/


.button1:active {
  background-image: -webkit-linear-gradient(top, #efedec, #f7f4f4);
  background-image: linear-gradient(top, #efedec, #f7f4f4);
  box-shadow: 0 3px 5px 0 rgba(0, 0, 0, .4), inset 0px -3px 1px 1px rgba(204, 198, 197, .5);
}

.button1:active:after {
  color: #dbd2d2;
  text-shadow: 0px -1px 1px #bdb5b4, 0px 1px 1px white;
}


.btn_PublicGraph {
  float: left;
  background-repeat: no-repeat;
  border: 0;
  background-color: transparent;
  background-image: url("https://lililizi.oss-cn-beijing.aliyuncs.com/a_%E8%BD%AF%E5%B7%A53/%E5%9B%BE%E7%89%8712.png");
  border-style: none;
  background-size: 55px;
  margin-left: 0;
  margin-top: 4px;
  height: 65px;
  width: 65px;
}

.btn_Warehouse {
  float: left;
  background-repeat: no-repeat;
  border: 0;
  background-color: transparent;
  border-style: none;
  background-image: url("https://lililizi.oss-cn-beijing.aliyuncs.com/a_%E8%BD%AF%E5%B7%A53/%E6%88%BF%E5%AD%90%20%282%29.png");
  background-size: 55px;
  margin-left: 0;
  margin-top: 4px;
  height: 65px;
  width: 65px;
}
  .PersonalHouse{
    color: #ffffff;
  }

  .RightBlock{
    margin-top: 50px;
    margin-left: 50px;
    float: left;
    width: 400px;
    height: 500px;
    background: white;
    border-radius: 15px;
  }
</style>
