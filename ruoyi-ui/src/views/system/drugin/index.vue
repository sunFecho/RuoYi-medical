<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="入库类型" prop="inType">
        <el-select
          v-model="queryParams.inType"
          placeholder="请选择药品类别"
          clearable
        >
          <el-option
            v-for="dict in dict.type.in_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="入库状态" prop="inStatus">
        <el-select
          v-model="queryParams.inStatus"
          placeholder="请选择药品类别"
          clearable
        >
          <el-option
            v-for="dict in dict.type.in_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>


      <el-form-item label="入库单号" prop="inOrderid">
        <el-input
          v-model="queryParams.inOrderid"
          placeholder="请输入入库单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="入库日期" prop="inDate">-->
<!--        <el-date-picker clearable-->
<!--                        v-model="queryParams.inDate"-->
<!--                        type="date"-->
<!--                        value-format="yyyy-MM-dd"-->
<!--                        placeholder="请选择入库日期">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->

      <el-form-item label="入库日期">
        <el-date-picker
          v-model="daterangeInDate"
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
          v-hasPermi="['system:drugin:add']"
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
          v-hasPermi="['system:drugin:edit']"
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
          v-hasPermi="['system:drugin:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:drugin:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="totallist" @selection-change="handleSelectionChange" show-summary :summary-method="getSummaries">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="入库状态" align="center" prop="inStatus" />
      <el-table-column label="入库类型" align="center" prop="inType" />
      <el-table-column label="入库单号" align="center" prop="inOrderid" />
      <el-table-column label="入库日期" align="center" prop="inDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.inDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="药品名称" align="center" prop="drugName" />
      <el-table-column label="包装规格" align="center" prop="drugPackage" />
      <el-table-column label="数量" align="center" prop="drugCount" />
      <el-table-column label="单价" align="center" prop="drugPrice" />
      <el-table-column label="总价" align="center" prop="total" />
      <el-table-column label="单位" align="center" prop="drugPackingunit" />
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
<!--            v-hasPermi="['system:drugin:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['system:drugin:remove']"-->
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

    <!-- 添加或修改药品入库对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1100px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="8">
        <el-form-item label="入库单号" prop="inOrderid">
          <el-input v-model="form.inOrderid" placeholder="请输入入库单号" disabled />
        </el-form-item>
        </el-col>
          <el-col :span="8">
        <el-form-item label="入库日期" prop="inDate">
          <el-date-picker clearable
                          :style="{width:'273px'}"
                          v-model="form.inDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择入库日期">
          </el-date-picker>
        </el-form-item>
          </el-col>

          <el-col :span="8">
        <el-form-item label="入库类型" prop="inType">
          <el-select v-model="form.inType" placeholder="入库类型" clearable :style="{width:'273px'}" >
            <el-option
              v-for="item in dict.type.in_type"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入备注" />
        </el-form-item>
        <el-divider content-position="center">入库详情信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddDrugindetail">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteDrugindetail">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="drugindetailList" @selection-change="handleDrugindetailSelectionChange" :row-class-name="rowDrugindetailIndex" ref="drugindetail" border>
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="药品类型" prop="drugType" width="120">
            <template slot-scope="scope">
              {{scope.row.drugType}}
<!--              <el-input v-model="scope.row.drugType" placeholder="请输入药品名称" />-->
            </template>
          </el-table-column>
          <el-table-column label="药品名称" prop="drugName" width="150">
            <template slot-scope="scope">
              {{scope.row.drugName}}
<!--              <el-input v-model="scope.row.drugName" placeholder="请输入药品名称" />-->
            </template>
          </el-table-column>
          <el-table-column label="包装规格" prop="drugPackage" width="150">
            <template slot-scope="scope">
              {{scope.row.drugPackage}}
<!--              <el-input v-model="scope.row.drugPackage" placeholder="请输入包装规格" />-->
            </template>
          </el-table-column>
          <el-table-column label="数量*" prop="drugCount" width="130" >
            <template slot-scope="scope">
<!--              <el-form ref="form" :model="form">-->
<!--              <el-form-item prop="drugCount" :rules="rules1" label-width="0"  style="margin-bottom: 0;">-->
              <el-input-number :controls="false" v-model="scope.row.drugCount" placeholder="请输入数量" :style="{width:'100px'}"/>
<!--              </el-form-item>-->
<!--              </el-form>-->
            </template>


<!--            <el-form-item :prop="'drugindetailList.' + scope.$index +'.drugCount'" :rules="rules.brebroodsList.malenum" label-width="0"  style="margin-bottom: 0;">-->
<!--              <el-input-number :controls="false" placeholder="请输入公鸡数" v-model="scope.row.malenum"  />-->
<!--            </el-form-item>-->


          </el-table-column>
          <el-table-column label="单位" prop="drugPackingunit" width="50">
            <template slot-scope="scope">
              {{scope.row.drugPackingunit}}
<!--              <el-input v-model="scope.row.drugPackingunit" placeholder="请输入单位" />-->
            </template>
          </el-table-column>
          <el-table-column label="单价*" prop="drugPrice" width="90" >
            <template slot-scope="scope">
<!--              <el-form ref="form">-->
<!--                <el-form-item prop="drugPrice" :rules="[{required: true, message: '不能为空', trigger: 'blur' }]">-->
              <el-input-number :controls="false" :precision="2" v-model="scope.row.drugPrice" placeholder="请输入单价" :style="{width:'70px'}"/>
<!--                </el-form-item>-->
<!--              </el-form>-->
            </template>
          </el-table-column>
          <el-table-column label="批次号*" prop="drugOrder" width="150">
            <template slot-scope="scope">
<!--              <el-form >-->
<!--                <el-form-item prop="drugOrder" :rules="[{required: true, message: '不能为空', trigger: 'blur' }]">-->
              <el-input v-model="scope.row.drugOrder" placeholder="请输入批次号" />
<!--                </el-form-item>-->
<!--              </el-form>-->
            </template>
          </el-table-column>
          <el-table-column label="有效期*" prop="drugValiddate" width="240">
            <template slot-scope="scope">
<!--              <el-form>-->
<!--                <el-form-item prop="drugValiddate" :rules="[{required: true, message: '不能为空', trigger: 'blur' }]">-->
              <el-date-picker clearable v-model="scope.row.drugValiddate" type="date" value-format="yyyy-MM-dd" placeholder="请选择有效期" />
<!--                </el-form-item>-->
<!--              </el-form>-->
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
        <el-button type="primary" @click="submitForm1">保存并入库</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="open1" width="1500px" height="500px" append-to-body :before-close="dialogclose" title="添加库存">
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
            <el-table-column prop="drugId" label="药品编号"></el-table-column>
            <el-table-column prop="drugType" label="药品类别"></el-table-column>
            <el-table-column prop="drugName" label="药品名称"></el-table-column>
            <el-table-column prop="drugPackage" label="药品规格"></el-table-column>
            <el-table-column prop="drugPackingunit" label="药品单位"></el-table-column>
          </el-table>
        </div>
        <div class="table-container">
          <el-table ref="rightTab" :data="rightlists" width="700px" height="500px">
            <el-table-column prop="drugId" label="药品编号"></el-table-column>
            <el-table-column prop="drugType" label="药品类别"></el-table-column>
            <el-table-column prop="drugName" label="药品名称"></el-table-column>
            <el-table-column prop="drugPackage" label="药品规格"></el-table-column>
            <el-table-column prop="drugPackingunit" label="药品单位"></el-table-column>
          </el-table>
        </div>
      </div>

    </el-dialog>

  </div>
</template>

<script>
import {
  listDrugin,
  getDrugin,
  delDrugin,
  addDrugin,
  updateDrugin,
  listDruginTotal,
  listDruginSum
} from "@/api/system/drugin";
import {getDruginfo, listDruginfo} from "@/api/system/druginfo";
import {addDrugstock, listDrugstock, updateDrugstock} from "@/api/system/drugstock";

export default {
  name: "Drugin",
  dicts: ['drug_type', 'drug_toxicology','drug_formulation','drug_packingunit','drug_numunit','drug_use','drug_doseunit','in_type','in_status'],
  data() {
    return {
      // 备注时间范围
      daterangeInDate: [],
      leftlists: [], //左边表格数据
      rightlists: [], //右边所选择的表格数据
      druginfoList: [],
      open1 : false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedDrugindetail: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      isdisabled:false,
      // 药品入库表格数据
      druginList: [],
      // 入库详情表格数据
      drugindetailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      sumlist:[{}],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        inStatus: null,
        inType: null,
        inOrderid: null,
        inDate: null,
        isvalid: null,
        drugName:null
      },
      sumall:null,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        inOrderid:[{
          required: true, message: '不能为空', trigger: 'blur'
        }],
        inType:[{
          required: true, message: '不能为空', trigger: 'blur'
        }],
      },
      rules1:{
        drugCount: [{
          required: true,
          message: '数量不能为空',
          trigger: 'blur'
        }],
      },
      totallist:[],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    dialogclose(){
      this.$refs.leftTab.clearSelection();
      this.open1 = false;
      this.leftlists=[];
      this.rightlists=[];

    },
    clickbtn(){
      for (let i=0;i<this.rightlists.length;i++){
        let obj = {};
        obj.drugType =this.rightlists[i].drugType;
        obj.drugName = this.rightlists[i].drugName;
        obj.drugPackage = this.rightlists[i].drugPackage;
        obj.drugCount = "";
        obj.drugId = this.rightlists[i].drugId;
        obj.drugPackingunit=this.rightlists[i].drugPackingunit;
        obj.drugPrice = "";
        obj.drugOrder = "";
        obj.drugValiddate="";
        obj.remark = "";
        this.drugindetailList.push(obj);
      }
      this.open1 = false;
      this.$refs.leftTab.clearSelection();
      // console.log('print')
      // console.log(this.rightlists)
    },
    cancel1(){
      this.$refs.leftTab.clearSelection();
      this.open1 = false;
      this.leftlists=[];
      this.rightlists=[];

    },
    getRowKey(row){
      return row.id;
    },
    selectionChange(val) {
      this.rightlists=[];
      // val是传过来当前所有选中列表值，但是已经存在的我们就不需要添加了，所以要判断当前已选择的列表长度
      for (var i = 0; i < val.length; i++) {
        this.rightlists.push({
          drugName: val[i].drugName,
          drugType: val[i].drugType,
          drugPackage: val[i].drugPackage,
          drugPackingunit: val[i].drugPackingunit,
          drugId: val[i].drugId,
        })
      }
    },

    /** 查询药品入库列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeInDate && '' != this.daterangeInDate) {
        this.queryParams.params["beginInDate"] = this.daterangeInDate[0];
        this.queryParams.params["endInDate"] = this.daterangeInDate[1];
      }
      this.listLoading = false;
      listDruginTotal(this.queryParams).then(response => {
        this.totallist = response.rows;
        this.total = response.total;
        this.loading = false;
        // console.log(this.totallist)
      });
      listDruginSum().then(res=>{
        this.sumlist=res.rows
        // console.log(this.sumlist)
      })

    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        inStatus: null,
        inType: null,
        inOrderid: null,
        inDate: null,
        createBy: null,
        createTime: null,
        updateTime: null,
        updateBy: null,
        isvalid: null,
        remark: null
      };
      this.drugindetailList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeInDate = [];
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
      this.form.inOrderid=today_num*10000+parseInt(Math.random()*10000)
      let params1={
            inOrderid:this.form.inOrderid,
          }
          listDrugin(params1).then(res=> {
            // console.log(res.rows)
            if (res.rows.length == 0) {
              this.open = true;
              this.title = "添加药品入库";
            } else {
              this.form.inOrderid = today_num * 10000 + parseInt(Math.random() * 10000)
              this.open = true;
              this.title = "添加药品出库";
            }
          })
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      // this.isdisabled = true;
      this.reset();
      const id = row.id || this.ids
      getDrugin(id).then(response => {
        this.form = response.data;
        this.drugindetailList = response.data.drugindetailList;
        this.open = true;
        this.title = "修改药品入库";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
              this.form.drugindetailList = this.drugindetailList;
              // console.log(this.form.drugindetailList)
          this.form.inStatus="未入库";
              if (this.form.id != null) {
                updateDrugin(this.form).then(response => {
                  this.$modal.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                });
              } else {
                addDrugin(this.form).then(response => {
                  this.$modal.msgSuccess("新增成功");
                  this.open = false;
                  this.getList();
                });
              }
        }
      });
    },
    submitForm1() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.drugindetailList = this.drugindetailList;
          if (this.form.id != null) {
            updateDrugin(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            console.log(this.form.drugindetailList)
            this.form.inStatus="已入库";
            // console.log(this.form)
            // console.log(this.drugindetailList)
            console.log(this.drugindetailList.length)
            if (this.drugindetailList.length>0){
            for (let i=0;i<this.drugindetailList.length;i++){
              console.log(this.drugindetailList[i].drugId)
              let params2={
                drugId: this.drugindetailList[i].drugId,
              }
              listDruginfo(params2).then(res =>{
                let paramssave={
                     drugId:this.drugindetailList[i].drugId,
                     drugName:res.rows[0].drugName,
                     drugEngname:res.rows[0].drugEngname,
                     drugType:res.rows[0].drugType,
                     drugPackage:res.rows[0].drugPackage,
                     drugPrice:this.drugindetailList[i].drugPrice,
                     drugPackingunit:this.drugindetailList[i].drugPackingunit,
                     drugOrder:this.drugindetailList[i].drugOrder,
                     drugValiddate:this.drugindetailList[i].drugValiddate,
                     drugFactory:res.rows[0].drugFactory
                }
                let count =this.drugindetailList[i].drugCount
                listDrugstock(paramssave).then(response=>{
                  //判断数据库是否存在这条相似的数据
                  if(response.rows.length==0){
                    //没有相似数据 存入数据
                    paramssave.drugCount = count
                    addDrugstock(paramssave).then(res1 =>{
                      addDrugin(this.form).then(response => {
                        this.$modal.msgSuccess("新增成功");
                        this.open = false;
                        this.getList();
                      });
                    })
                  }else{
                    //有相似数据 修改数据
                    let num=response.rows[0].drugCount - 0
                    let change1={
                      id:response.rows[0].id,
                      drugCount:count+num
                    }
                    updateDrugstock(change1)
                    addDrugin(this.form).then(response => {
                      this.$modal.msgSuccess("新增成功");
                      this.open = false;
                      this.getList();
                    });
                  }
                })

              })

            }
            }else {
                this.$modal.msgSuccess("无效数据");
            }

          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除药品入库编号为"' + ids + '"的数据项？').then(function() {
        return delDrugin(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },

    /** 入库详情添加按钮操作 */
    handleAddDrugindetail() {
      this.rightlists=[];
      this.open1=true;
     const queryParams1 = {
        pageNum : 1,
        pageSize : 1000000
      };
      listDruginfo(queryParams1).then(res =>{
        this.leftlists =res.rows
      })

    },
    /** 入库详情删除按钮操作 */
    handleDeleteDrugindetail() {
      if (this.checkedDrugindetail.length == 0) {
        this.$modal.msgError("请先选择要删除的入库详情数据");
      } else {
        const drugindetailList = this.drugindetailList;
        const checkedDrugindetail = this.checkedDrugindetail;
        this.drugindetailList = drugindetailList.filter(function(item) {
          return checkedDrugindetail.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleDrugindetailSelectionChange(selection) {
      this.checkedDrugindetail = selection.map(item => item.index)
    },
    rowDrugindetailIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/drugin/export', {
        ...this.queryParams
      }, `drugin_${new Date().getTime()}.xlsx`)
    },
    getSummaries(param) {
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index === 7) {
          sums[index] = '总计';
          return;
        }else if(index === 8){
          sums[index] = this.sumlist[0].sumcount;
          return;
        } else if(index === 10){
          sums[index] = this.sumlist[0].sumall;
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
