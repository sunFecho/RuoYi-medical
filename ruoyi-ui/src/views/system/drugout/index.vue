<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="出库类型" prop="outType">
        <el-select
          v-model="queryParams.outType"
          placeholder="请选择药品类别"
          clearable
        >
          <el-option
            v-for="dict in dict.type.out_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="入库状态" prop="outStatus">
        <el-select
          v-model="queryParams.outStatus"
          placeholder="请选择药品类别"
          clearable
        >
          <el-option
            v-for="dict in dict.type.out_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>


      <el-form-item label="出库单号" prop="outOrderid">
        <el-input
          v-model="queryParams.outOrderid"
          placeholder="请输入出库单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出库日期">
        <el-date-picker
          v-model="daterangeOutDate"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="药品名称" prop="drugName">
        <el-input
          v-model="queryParams.drugName"
          placeholder="请输入药品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:drugout:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:drugout:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:drugout:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:drugout:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="totallist" @selection-change="handleSelectionChange" show-summary :summary-method="getSummaries">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="id" align="center" prop="id" />-->
      <el-table-column label="出库状态" align="center" prop="outStatus" />
      <el-table-column label="出库类型" align="center" prop="outType" />
      <el-table-column label="出库单号" align="center" prop="outOrderid" />
      <el-table-column label="出库日期" align="center" prop="outDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.outDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="药品名称" align="center" prop="drugName"></el-table-column>
      <el-table-column label="包装规格" align="center" prop="drugPackage" />
      <el-table-column label="数量" align="center" prop="drugCount" />
      <el-table-column label="单位" align="center" prop="drugPackingunit" />
      <el-table-column label="单价" align="center" prop="drugPrice" />
      <el-table-column label="总价" align="center" prop="total" />
      <el-table-column label="批次号" align="center" prop="drugOrder" />

      <el-table-column label="有效期" align="center" prop="drugValiddate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.drugValiddate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['system:drugout:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['system:drugout:remove']"-->
<!--          >删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />




    <!-- 添加或修改药品出库对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="8">
        <el-form-item label="出库单号" prop="outOrderid">
          <el-input v-model="form.outOrderid" placeholder="请输入出库单号" :disabled="true"/>
        </el-form-item>
          </el-col>
          <el-col :span="8">
        <el-form-item label="出库日期" prop="outDate">
          <el-date-picker clearable
            v-model="form.outDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择出库日期">
          </el-date-picker>
        </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出库类型" prop="outType">
            <el-select v-model="form.outType" placeholder="出库类型" clearable >
              <el-option
                v-for="item in dict.type.out_type"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" type="textarea" />
        </el-form-item>
        <el-divider content-position="center">出库详情信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddDrugoutdetail">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteDrugoutdetail">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="drugoutdetailList" :row-class-name="rowDrugoutdetailIndex" @selection-change="handleDrugoutdetailSelectionChange" ref="drugoutdetail">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column align="center" prop="index" width="30"/>
          <el-table-column label="药品编号" prop="drugId" width="150">
            <template slot-scope="scope">
              {{scope.row.drugId}}
            </template>
          </el-table-column>
          <el-table-column label="药品名称" prop="drugName" width="150">
            <template slot-scope="scope">
<!--              <el-input v-model="scope.row.drugName" placeholder="请输入药品名称" />-->
              {{scope.row.drugName}}
            </template>
          </el-table-column>
          <el-table-column label="包装规格" prop="drugPackage" width="150">
            <template slot-scope="scope">
              {{scope.row.drugPackage}}
<!--              <el-input v-model="scope.row.drugPackage" placeholder="请输入包装规格" />-->
            </template>
          </el-table-column>
          <el-table-column label="数量" prop="drugCount" width="150">
            <template slot-scope="scope">
<!--              {{scope.row.maxCount}}-->
              <el-input-number :controls="false" v-model="scope.row.drugCount" :style="{width:'100px'}" :max="scope.row.maxCount"/>
            </template>
          </el-table-column>
          <el-table-column label="单价" prop="drugPrice" width="150">
            <template slot-scope="scope">
              {{scope.row.drugPrice}}
<!--              <el-input v-model="scope.row.drugPrice" placeholder="请输入单价" />-->
            </template>
          </el-table-column>
          <el-table-column label="药品类型" prop="drugType" width="150">
            <template slot-scope="scope">
              {{scope.row.drugType}}
            </template>
          </el-table-column>
          <el-table-column label="单位" prop="drugPackingunit" width="150">
            <template slot-scope="scope">
              {{scope.row.drugPackingunit}}
            </template>
          </el-table-column>
          <el-table-column label="批次号" prop="drugOrder" width="150">
            <template slot-scope="scope">
              {{scope.row.drugOrder}}
              <!--              <el-input v-model="scope.row.drugOrder" placeholder="请输入批次号" />-->
            </template>
          </el-table-column>
          <el-table-column label="有效期" prop="drugValiddate" width="150">
            <template slot-scope="scope">
              <span>{{parseTime(scope.row.drugValiddate, '{y}-{m}-{d}')}}</span>
              <!--              <el-date-picker clearable v-model="scope.row.drugValiddate" type="date" value-format="yyyy-MM-dd" placeholder="请选择有效期" />-->
            </template>
          </el-table-column>
          <el-table-column label="备注" prop="remark" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.remark" placeholder="请输入备注" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">保 存</el-button>
        <el-button type="primary" @click="submitForm1">保存并出库</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="open1" width="1600px" height="500px" append-to-body :before-close="dialogclose" title="添加库存">
      <el-button @click="clickbtn">添 加</el-button>
      <el-button @click="cancel1">取 消</el-button>
      <div class="table-box">
        <div class="table-container">
          <el-table ref="leftTab" :data="leftlists" @selection-change="selectionChange" height="500px" width="700px" :row-key="getRowKey">
            <el-table-column
              type="selection"
              :reserve-selection="true"
              disabled="true"
              width="40"
            ></el-table-column>
            <el-table-column prop="drugId" label="药品编号" align="center"></el-table-column>
            <el-table-column prop="drugType" label="药品类别" align="center"></el-table-column>
            <el-table-column prop="drugName" label="药品名称" align="center"></el-table-column>
            <el-table-column prop="drugPackage" label="药品规格" align="center"></el-table-column>
            <el-table-column prop="drugCount" label="库存数量" align="center"></el-table-column>
            <el-table-column prop="drugPrice" label="单价" align="center"></el-table-column>
            <el-table-column prop="drugPackingunit" label="药品单位" align="center"></el-table-column>
            <el-table-column prop="drugOrder" label="批次号" align="center"></el-table-column>
            <el-table-column prop="drugValiddate" label="有效期" align="center"></el-table-column>
          </el-table>
        </div>
        <div class="table-container">
          <el-table ref="rightTab" :data="rightlists" width="700px" height="500px">
            <el-table-column prop="drugId" label="药品编号" align="center"></el-table-column>
            <el-table-column prop="drugType" label="药品类别" align="center"></el-table-column>
            <el-table-column prop="drugName" label="药品名称" align="center"></el-table-column>
            <el-table-column prop="drugPackage" label="药品规格" align="center"></el-table-column>
            <el-table-column prop="drugCount" label="库存数量" align="center"></el-table-column>
            <el-table-column prop="drugPrice" label="单价" align="center"></el-table-column>
            <el-table-column prop="drugPackingunit" label="药品单位" align="center"></el-table-column>
            <el-table-column prop="drugOrder" label="批次号" align="center"></el-table-column>
            <el-table-column prop="drugValiddate" label="有效期" align="center"></el-table-column>
          </el-table>
        </div>
      </div>

    </el-dialog>

  </div>
</template>

<script>
import {
  listDrugout,
  getDrugout,
  delDrugout,
  addDrugout,
  updateDrugout,
  listDrugoutTotal,
  listDrugoutSum
} from "@/api/system/drugout";
import {listDruginfo} from "@/api/system/druginfo";
import {listDrugstock, listDrugstocklimit, updateDrugstock} from "@/api/system/drugstock";
import {listDrugin} from "@/api/system/drugin";

export default {
  name: "Drugout",
  dicts: ['drug_type', 'drug_toxicology','drug_formulation','drug_packingunit','drug_numunit','drug_use','drug_doseunit','in_type','in_status','out_type','out_status'],

  data() {
    return {
      leftlists: [], //左边表格数据
      rightlists: [], //右边所选择的表格数据
      sumlist:[],
      open1:false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedDrugoutdetail: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 药品出库表格数据
      drugoutList: [],
      // 出库详情表格数据
      drugoutdetailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 单位时间范围
      daterangeOutDate: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        outStatus: null,
        outType: null,
        outOrderid: null,
        outDate: null,
        isvalid: null,
      },
      sockCount:null,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        outOrderid:[{
          required: true, message: '不能为空', trigger: 'blur'
        }],
        outType:[{
          required: true, message: '不能为空', trigger: 'blur'
        }],
      },
      totallist:[],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询药品出库列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeOutDate && '' != this.daterangeOutDate) {
        this.queryParams.params["beginOutDate"] = this.daterangeOutDate[0];
        this.queryParams.params["endOutDate"] = this.daterangeOutDate[1];
      }
      listDrugout(this.queryParams).then(response => {
        this.drugoutList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      listDrugoutTotal(this.queryParams).then(res =>{
        this.totallist = res.rows;
      })
      listDrugoutSum(this.queryParams).then(res =>{
        // console.log(res.rows[0])
        this.sumlist = res.rows[0]
      })
    },
    clickbtn(){
      console.log(this.rightlists)
      for (let i=0;i<this.rightlists.length;i++){
        let obj = {};
        obj.drugType =this.rightlists[i].drugType;
        obj.drugName = this.rightlists[i].drugName;
        obj.drugPackage = this.rightlists[i].drugPackage;
        obj.drugCount = "";
        obj.drugId = this.rightlists[i].drugId;
        obj.drugPackingunit=this.rightlists[i].drugPackingunit;
        obj.drugPrice = this.rightlists[i].drugPrice;
        obj.drugOrder = this.rightlists[i].drugOrder;
        obj.drugValiddate=this.rightlists[i].drugValiddate;
        obj.remark = "";
        obj.maxCount= this.rightlists[i].drugCount;
        this.drugoutdetailList.push(obj);

      }
      this.open1 = false;
      this.$refs.leftTab.clearSelection();
    },
    selectionChange(val) {
      this.rightlists=[];
      // val是传过来当前所有选中列表值，但是已经存在的我们就不需要添加了，所以要判断当前已选择的列表长度
      for (var i = 0; i < val.length; i++) {
        this.rightlists.push({
          drugName: val[i].drugName,
          drugType: val[i].drugType,
          drugPackage: val[i].drugPackage,
          drugId: val[i].drugId,
          drugCount:val[i].drugCount,
          drugPrice:val[i].drugPrice,
          drugPackingunit:val[i].drugPackingunit,
          drugOrder:val[i].drugOrder,
          drugValiddate:val[i].drugValiddate,
        })
      }
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    dialogclose(){
      this.$refs.leftTab.clearSelection();
      this.open1 = false;
      this.leftlists=[];
      this.rightlists=[];

    },
    getRowKey(row){
      return row.id;
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        outStatus: null,
        outType: null,
        outOrderid: null,
        outDate: null,
        createBy: null,
        createTime: null,
        updateTime: null,
        updateBy: null,
        isvalid: null,
        remark: null
      };
      this.drugoutdetailList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeOutDate = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      let nowDate = new Date();
      let date = {
        year: nowDate.getFullYear(),
        month: nowDate.getMonth() + 1,
        date: nowDate.getDate(),
      };
      let newmonth = date.month > 10 ? date.month : "0" + date.month;
      let newday = date.date > 10 ? date.date : "0" + date.date;
      var today= date.year+newmonth+newday
      var today_num = today-0;
      this.form.outOrderid=today_num*10000+parseInt(Math.random()*10000)
      let params1={
        outOrderid:this.form.outOrderid,
      }
      listDrugout(params1).then(res=> {
        // console.log(res.rows)
        if (res.rows.length == 0) {
          this.open = true;
          this.title = "添加药品出库";
        } else {
          this.form.outOrderid = today_num * 10000 + parseInt(Math.random() * 10000)
          this.open = true;
          this.title = "添加药品出库";
        }
      })
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDrugout(id).then(response => {
        this.form = response.data;
        this.drugoutdetailList = response.data.drugoutdetailList;
        this.open = true;
        this.title = "修改药品出库";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.drugoutdetailList = this.drugoutdetailList;
          if (this.form.id != null) {
            updateDrugout(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.outStatus = "未出库"
            addDrugout(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    submitForm1(){
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.drugoutdetailList = this.drugoutdetailList;
          if (this.form.id != null) {
            updateDrugout(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.outStatus = "未出库"
            if (this.drugoutdetailList.length>0){
              console.log("notAdd")
              for (let i=0;i<this.drugoutdetailList.length;i++) {
                // console.log(this.drugoutdetailList[i].drugId)
                console.log(this.drugoutdetailList[i])
                  let paramssearch={
                    drugId:this.drugoutdetailList[i].drugId,
                    drugName:this.drugoutdetailList[i].drugName,
                    drugPrice:this.drugoutdetailList[i].drugPrice,
                    drugOrder:this.drugoutdetailList[i].drugOrder,
                    drugValiddate:this.drugoutdetailList[i].drugValiddate,
                  }
                listDrugstock(paramssearch).then(res =>{
                  console.log(res.rows[0].drugCount)
                  console.log(this.drugoutdetailList[i].drugCount)
                  let changeParams = {
                    id:res.rows[0].id,
                    drugCount:res.rows[0].drugCount - this.drugoutdetailList[i].drugCount
                  }
                  updateDrugstock(changeParams);
                    this.$modal.msgSuccess("出库成功");
                    this.open = false;
                    this.getList();
                })
              }
            }else{
                this.$modal.msgSuccess("无效数据");
            }

          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除药品出库编号为"' + ids + '"的数据项？').then(function() {
        return delDrugout(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 出库详情序号 */
    rowDrugoutdetailIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 出库详情添加按钮操作 */
    handleAddDrugoutdetail() {
      this.rightlists=[];
      this.open1=true;
      const queryParams1 = {
        pageNum : 1,
        pageSize : 1000000
      };
      listDrugstocklimit(queryParams1).then(res =>{
        this.leftlists =res.rows
        console.log(this.leftlists)
      })

      // let obj = {};
      // obj.drugName = "";
      // obj.drugPackage = "";
      // obj.drugCount = "";
      // obj.drugPrice = "";
      // obj.drugOrder = "";
      // obj.drugValiddate = "";
      // obj.remark = "";
      // obj.drugType = "";
      // obj.drugId = "";
      // obj.drugPackingunit = "";
      // this.drugoutdetailList.push(obj);
    },
    /** 出库详情删除按钮操作 */
    handleDeleteDrugoutdetail() {
      if (this.checkedDrugoutdetail.length == 0) {
        this.$modal.msgError("请先选择要删除的出库详情数据");
      } else {
        const drugoutdetailList = this.drugoutdetailList;
        const checkedDrugoutdetail = this.checkedDrugoutdetail;
        this.drugoutdetailList = drugoutdetailList.filter(function(item) {
          return checkedDrugoutdetail.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleDrugoutdetailSelectionChange(selection) {
      this.checkedDrugoutdetail = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/drugout/export', {
        ...this.queryParams
      }, `drugout_${new Date().getTime()}.xlsx`)
    },
    cancel1(){
      this.$refs.leftTab.clearSelection();
      this.open1 = false;
      this.leftlists=[];
      this.rightlists=[];

    },

    getSummaries(param) {
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index === 7) {
          sums[index] = this.sumlist.sumcount;
          return;
        }else if(index === 10){
          sums[index] = this.sumlist.sumall;
          return;
        }else if(index === 6){
          sums[index] = '合计';
          return;
        }else{
          sums[index] = '';
          return;
        }
      });
      return sums;
    },

  }
};
</script>
<style>
.table-box{
  width: 1300px;
  height: 500px;
  display: flex;
}
.table-container{
  width: 700px;
  margin-left: 50px;
  margin-top: 5px;
}
</style>
