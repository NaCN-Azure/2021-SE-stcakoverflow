<template>
  <div>
    <el-drawer class="drawer" title="推荐列表" :visible.sync="stockCardVisible" size="40%" :before-close="handleStock">
      <el-popover
        title="设置刷新条件"
        placement="bottom-start"
        width="210"
        trigger="click"
        v-model="StockRefresh_Visible"
      >
        <div>
          <el-input v-model="StockExpectInput" placeholder="您的预期最低收益率:%" size="small"
                    style="width: 203px"></el-input>
        </div>
        <div>
          <el-input v-model="Stock_radio" placeholder="您的预期亏损容忍率:%" size="small" style="width: 203px"></el-input>
        </div>
        <div>
          <el-radio-group v-model="userAcceptST" size="small">
            <el-radio-button label="是否接受ST" :disabled=true></el-radio-button>
            <el-radio-button label="接受"></el-radio-button>
            <el-radio-button label="拒绝"></el-radio-button>
          </el-radio-group>
        </div>
        <div style="float: right;margin-top: 10px">
          <el-button size="mini" type="text" @click="StockRefresh_Visible = false">取消</el-button>
          <el-button type="primary" size="mini" @click.native="RefreshStock">确定</el-button>
        </div>
        <el-button slot="reference"
                   class="reRecommend_button"
                   icon="el-icon-refresh-left" circle></el-button>
      </el-popover>
      <div class="scroll_bar">
        <el-card v-for="(o,index) in this.recommendList.length" :key="o" class="cards">
          <div slot="header" class="clearFix">
            <span style="margin-right: 20%; font-size: 30px; font-family: STKaiti; margin-top: 4px">{{recommendList[index][1]}}</span>
            <el-button class="el-icon-magic-stick" @click="favourStock(index)"></el-button>
          </div>
          <div class="textBox">
            <div>股票编号:  {{recommendList[index][0]}}</div>
            <div>较预期收益高:  {{Number(recommendList[index][2]).toFixed(2)}}  %</div>
            <div>较预估风险低:  {{Number(recommendList[index][3]).toFixed(2)}}  %</div>
          </div>
        </el-card>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import {mapActions, mapMutations, mapGetters} from 'vuex'

export default {
  name: "stockCard",
  async mounted() {
    await this.getRecommendList()
    console.log(this.recommendList)
  },
  data() {
    return {
      currentGraph: 0,
      StockRefresh_Visible: false,
      StockExpectInput: '',
      Stock_radio: '',
      userAcceptST: '',
      // recommendList: [['123123123','12312313','12.3313','12.312313'],['123123123','12312313','123313','12312313'],
      //   ['123123123','12312313','123313','12312313'],['123123123','12312313','123313','12312313'],
      //   ['123123123','12312313','123313','12312313'],['123123123','12312313','123313','12312313'],
      //   ['123123123','12312313','123313','12312313'],['123123123','12312313','123313','12312313'],
      //   ['123123123','12312313','123313','12312313'],['123123123','12312313','123313','12312313'],
      //   ['123123123','12312313','123313','12312313'],['123123123','12312313','123313','12312313'],
      //   ['123123123','12312313','123313','12312313'],['123123123','12312313','123313','12312313'],
      //   ['123123123','12312313','123313','12312313'],['123123123','12312313','123313','12312313'],
      //   ['123123123','12312313','123313','12312313'],['123123123','12312313','123313','12312313'],
      //   ['123123123','12312313','123313','12312313'],['123123123','12312313','123313','12312313'],['123123123','12312313','123313','12312313'],['123123123','12312313','123313','12312313'],
      //   ['123123123','12312313','123313','12312313'],['123123123','12312313','123313','12312313'],
      //   ['123123123','12312313','123313','12312313'],['123123123','12312313','123313','12312313'],
      //   ['123123123','12312313','123313','12312313'],['123123123','12312313','123313','12312313'],
      //   ['123123123','12312313','123313','12312313'],['123123123','12312313','123313','12312313']]
    }
  },
  computed: {
    ...mapGetters([
      'recommendList',
      'favourStockName',
      'stockCardVisible',
      'userParams',
    ])
  },
  props:{
    refreshSelf: {
      refreshSelf : {
        type: Function,
        default: null,
      }
    }
  },
  methods: {
    ...mapMutations([
      'set_favourStockName',
      'set_stockCardVisible',
      'set_userParams',
      'set_recommendList',
    ]),

    ...mapActions([
      'getRecommendList',
      'postUserFavourStock',
      'editUserParams',
      'findUserParams',
    ]),


    favourStock(index) {
      this.$message.success("会在之后的推荐中增加" + this.recommendList[index][1] + "的权重")
      this.set_favourStockName(this.recommendList[index][1])
      this.postUserFavourStock()
    },

    handleStock(){
      this.set_stockCardVisible(false)
    },

    refreshStockCard() {
      this.stock_card_update = false;
      this.$nextTick(() => {
        this.stock_card_update = true
      })
    },

    async RefreshStock() {
      this.set_userParams({id: localStorage.getItem('userId')})
      await this.findUserParams()
      this.StockRefresh_Visible = false;
      let acceptSt = '';
      let flag = true;
      if(this.userAcceptST==='接受'){
        acceptSt = true;
      }else if(this.userAcceptST==='拒绝'){
        acceptSt = false;
      }else{
        flag = false
        this.$message.error('错误的输入')
      }
      if(flag){
        this.set_userParams({
          risk: this.Stock_radio,
          balance: this.StockExpectInput,
          acceptSt: acceptSt
        })
        await this.editUserParams()
        console.log(this.userParams)
        this.set_recommendList([])
        await this.refreshSelf()
      }
    }

  },
}
</script>

<style scoped>

.cards {
  margin-left: 10%;
  margin-right: 10%;
  margin-bottom: 10px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
  font-size: 18px;
}

.cards:hover {
  opacity: 0.8;
  border-bottom: 1px solid #000;
}
.drawer >>> .el-drawer__body {
  height: calc(100% - 77px) !important;
}
.scroll_bar {
  overflow-y: auto;
  height: 100%;
}

.reRecommend_button {
  position: absolute;
  margin-top: 10px;
  margin-left: 39%;
}

.textBox {
  font-family: STHeiti;
  text-align: left;
  margin-left: 10%;
  font-size: 20px;
}


</style>
