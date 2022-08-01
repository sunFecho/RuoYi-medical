<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="拼音简写" prop="drugEngname">
        <el-input
          v-model="queryParams.drugEngname"
          placeholder="请输入拼音简写"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="药品名称" prop="drugName">
        <el-input
          v-model="queryParams.drugName"
          placeholder="请输入药品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="药品类别" prop="drugType">
        <el-select
          v-model="queryParams.drugType"
          placeholder="请选择药品类别"
          clearable
        >
          <el-option
            v-for="dict in dict.type.drug_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['system:druginfo:add']"
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
          v-hasPermi="['system:druginfo:edit']"
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
          v-hasPermi="['system:druginfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:druginfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="druginfoList" @selection-change="handleSelectionChange" border>
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="编号" align="center" prop="id" />-->
      <el-table-column label="药品编号" align="center" prop="drugId" />
      <el-table-column label="药品名称" align="center" prop="drugName" />
      <el-table-column label="拼音简写" align="center" prop="drugEngname" />
      <el-table-column label="毒理分类" align="center" prop="drugToxicology" />
      <el-table-column label="药品类别" align="center" prop="drugType" />
      <el-table-column label="制剂类型" align="center" prop="drugFormulation" />
      <el-table-column label="包装规格" align="center" prop="drugPackage" />
      <el-table-column label="用法" align="center" prop="drugUse" />
      <el-table-column label="生产企业" align="center" prop="drugFactory" />
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['system:druginfo:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['system:druginfo:remove']"-->
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

    <!-- 添加药品信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body :close-on-click-modal="false">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="药品名称" prop="drugName">
          <el-input v-model="form.drugName" placeholder="请输入药品名称" />
        </el-form-item>
        <el-row>
          <el-col :span="12">
        <el-form-item label="药品编号" prop="drugId">
          <el-input v-model="form.drugId" placeholder="请输入药品编号"  />
        </el-form-item>
          </el-col>
          <el-col :span="12">
        <el-form-item label="拼音简写" prop="drugEngname" >
          <el-input v-model="form.drugEngname" placeholder="请输入拼音简写"/>
        </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
        <el-form-item label="药品类别" prop="drugType" >
<!--          <el-input v-model="form.drugType" placeholder="请输入药品类别" />-->
          <el-select v-model="form.drugType" placeholder="请选择药品类别" clearable :style="{width:'400px'}" >
            <el-option
              v-for="item in dict.type.drug_type"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
          </el-col>
          <el-col :span="12">
        <el-form-item label="毒理分类" prop="drugToxicology">
<!--        <el-input v-model="form.drugToxicology" placeholder="请输入毒理分类" />-->
          <el-select v-model="form.drugToxicology" placeholder="请选择毒理分类" clearable :style="{width:'400px'}">
            <el-option
              v-for="item in dict.type.drug_toxicology"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
      </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
        <el-form-item label="制剂类型" prop="drugFormulation" >
<!--          <el-input v-model="form.drugFormulation" placeholder="" />-->
          <el-select v-model="form.drugFormulation" placeholder="请选择制剂类型" clearable :style="{width:'400px'}">
            <el-option
              v-for="item in dict.type.drug_formulation"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        </el-col>
        <el-col :span="12">
        <el-form-item label="包装单位" prop="drugPackingunit" >
<!--          <el-input v-model="form.drugPackingunit" placeholder="请输入包装单位" />-->
          <el-select v-model="form.drugPackingunit" placeholder="请选择包装单位" clearable :style="{width:'400px'}" :disabled="isdisabled">
            <el-option
              v-for="item in dict.type.drug_packingunit"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
        <el-form-item label="基本剂量" prop="drugDose">
          <el-input-number v-model="form.drugDose" controls-position="right" :precision="2" :min="0" :style="{width:'400px'}" :disabled="isdisabled"></el-input-number>
        </el-form-item>
          </el-col>
          <el-col :span="12">
        <el-form-item label="剂量单位" prop="drugDoseunit" >
<!--          <el-input v-model="form.drugDoseunit" placeholder="请输入" />-->
          <el-select v-model="form.drugDoseunit" placeholder="请选择剂量单位" clearable :style="{width:'400px'}" :disabled="isdisabled">
            <el-option
              v-for="item in dict.type.drug_doseunit"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
        <el-form-item label="基本数量" prop="drugNum" >
          <el-input-number v-model="form.drugNum" controls-position="right":min="0" :style="{width:'400px'}" :disabled="isdisabled"></el-input-number>

        </el-form-item>
          </el-col>
          <el-col :span="12">
        <el-form-item label="数量单位" prop="drugNumunit" >
<!--          <el-input v-model="form.drugNumunit" placeholder="请输入数量单位" />-->
          <el-select v-model="form.drugNumunit" placeholder="请选择数量单位" clearable :style="{width:'400px'}" :disabled="isdisabled">
            <el-option
              v-for="item in dict.type.drug_numunit"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
        <el-form-item label="包装规格" prop="drugPackage" >
          <el-input v-model="form.drugPackage" placeholder="请输入包装规格" :disabled="isdisabled"/>
        </el-form-item>
          </el-col>
          <el-col :span="12">
        <el-form-item label="用法" prop="drugUse" >
<!--          <el-input v-model="form.drugUse" placeholder="请输入用法" />-->
          <el-select v-model="form.drugUse" placeholder="请选择用法" clearable :style="{width:'400px'}">
            <el-option
              v-for="item in dict.type.drug_use"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
        <el-form-item label="库存下限" prop="drugDown" >
          <el-input-number v-model="form.drugDown" controls-position="right":min="0" :style="{width:'400px'}" :disabled="isdisabled"></el-input-number>

        </el-form-item>
        </el-col>
        <el-col :span="12">
        <el-form-item label="库存上限" prop="drugUp" >
          <el-input-number v-model="form.drugUp" controls-position="right":min="0" :style="{width:'400px'}" :disabled="isdisabled"></el-input-number>
        </el-form-item>
        </el-col>
        </el-row>

        <el-form-item label="生产厂家" prop="drugFactory" >
          <el-input v-model="form.drugFactory" placeholder="请输入生产厂家" />
        </el-form-item>

        <el-form-item label="排序" prop="sort">
<!--          <el-input v-model="form.sort" placeholder="请输入排序">-->
            <el-input-number v-model="form.sort" controls-position="right":min="0" :style="{width:'400px'}"></el-input-number>
            数字越小越靠前
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 修改药品信息对话框 -->
    <el-dialog :title="title" :visible.sync="open1" width="1000px" append-to-body :close-on-click-modal="false">
      <el-form ref="form" :model="form" :rules="rules1" label-width="80px">
        <el-form-item label="药品名称" prop="drugName">
          <el-input v-model="form.drugName" placeholder="请输入药品名称" />
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="药品编号" prop="drugId">
              <el-input v-model="form.drugId" placeholder="请输入药品编号" :disabled="isdisabled"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="拼音简写" prop="drugEngname" >
              <el-input v-model="form.drugEngname" placeholder="请输入拼音简写"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="药品类别" prop="drugType" >
              <!--          <el-input v-model="form.drugType" placeholder="请输入药品类别" />-->
              <el-select v-model="form.drugType" placeholder="请选择药品类别" clearable :style="{width:'400px'}" >
                <el-option
                  v-for="item in dict.type.drug_type"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="毒理分类" prop="drugToxicology">
              <!--        <el-input v-model="form.drugToxicology" placeholder="请输入毒理分类" />-->
              <el-select v-model="form.drugToxicology" placeholder="请选择毒理分类" clearable :style="{width:'400px'}">
                <el-option
                  v-for="item in dict.type.drug_toxicology"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="制剂类型" prop="drugFormulation" >
              <!--          <el-input v-model="form.drugFormulation" placeholder="" />-->
              <el-select v-model="form.drugFormulation" placeholder="请选择制剂类型" clearable :style="{width:'400px'}">
                <el-option
                  v-for="item in dict.type.drug_formulation"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="包装单位" prop="drugPackingunit" >
              <!--          <el-input v-model="form.drugPackingunit" placeholder="请输入包装单位" />-->
              <el-select v-model="form.drugPackingunit" placeholder="请选择包装单位" clearable :style="{width:'400px'}" :disabled="isdisabled">
                <el-option
                  v-for="item in dict.type.drug_packingunit"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="基本剂量" prop="drugDose">
              <el-input-number v-model="form.drugDose" controls-position="right" :precision="2" :min="0" :style="{width:'400px'}" :disabled="isdisabled"></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="剂量单位" prop="drugDoseunit" >
              <!--          <el-input v-model="form.drugDoseunit" placeholder="请输入" />-->
              <el-select v-model="form.drugDoseunit" placeholder="请选择剂量单位" clearable :style="{width:'400px'}" :disabled="isdisabled">
                <el-option
                  v-for="item in dict.type.drug_doseunit"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="基本数量" prop="drugNum" >
              <el-input-number v-model="form.drugNum" controls-position="right":min="0" :style="{width:'400px'}" :disabled="isdisabled"></el-input-number>

            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="数量单位" prop="drugNumunit" >
              <!--          <el-input v-model="form.drugNumunit" placeholder="请输入数量单位" />-->
              <el-select v-model="form.drugNumunit" placeholder="请选择数量单位" clearable :style="{width:'400px'}" :disabled="isdisabled">
                <el-option
                  v-for="item in dict.type.drug_numunit"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="包装规格" prop="drugPackage" >
              <el-input v-model="form.drugPackage" placeholder="请输入包装规格" :disabled="isdisabled"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="用法" prop="drugUse" >
              <!--          <el-input v-model="form.drugUse" placeholder="请输入用法" />-->
              <el-select v-model="form.drugUse" placeholder="请选择用法" clearable :style="{width:'400px'}">
                <el-option
                  v-for="item in dict.type.drug_use"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="库存下限" prop="drugDown" >
              <el-input-number v-model="form.drugDown" controls-position="right":min="0" :style="{width:'400px'}" :disabled="isdisabled"></el-input-number>

            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="库存上限" prop="drugUp" >
              <el-input-number v-model="form.drugUp" controls-position="right":min="0" :style="{width:'400px'}" :disabled="isdisabled"></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="生产厂家" prop="drugFactory" >
          <el-input v-model="form.drugFactory" placeholder="请输入生产厂家" />
        </el-form-item>

        <el-form-item label="排序" prop="sort">
          <!--          <el-input v-model="form.sort" placeholder="请输入排序">-->
          <el-input-number v-model="form.sort" controls-position="right":min="0" :style="{width:'400px'}"></el-input-number>
          数字越小越靠前
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { listDruginfo, getDruginfo, delDruginfo, addDruginfo, updateDruginfo } from "@/api/system/druginfo";

export default {
  name: "Druginfo",
  dicts: ['drug_type', 'drug_toxicology','drug_formulation','drug_packingunit','drug_numunit','drug_use','drug_doseunit'],
  data() {
    let validate = (rule, value, callback) => {
      this.checkdrugId(value,callback);
    };
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 药品信息表格数据
      druginfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      open1:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        drugId: null,
        drugName: null,
        drugEngname: null,
        drugType: null,
        drugToxicology: null,
        drugFormulation: null,
        drugPackingunit: null,
        drugDose: null,
        drugDoseunit: null,
        drugNum: null,
        drugNumunit: null,
        drugPackage: null,
        drugUse: null,
        drugDown: null,
        drugUp: null,
        drugFactory: null,
        sort: null,
        isvalid: null,
        createDate: null,
        updateDate: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        drugName: [
          { required: true, message: "药品名称不能为空", trigger: "blur" }
        ],
        drugDown:[
          { required: true, message: "库存下限不能为空", trigger: "blur" }
        ],
        drugUp: [
          { required: true, message: "库存上限不能为空", trigger: "blur" }
        ],
        drugPackage: [
          { required: true, message: "包装规格不能为空", trigger: "blur" }
        ],
        drugId:[
          {validator: validate , trigger: 'blur'}
        ]
      },
      rules1: {
        drugName: [
          { required: true, message: "药品名称不能为空", trigger: "blur" }
        ],
        drugDown:[
          { required: true, message: "库存下限不能为空", trigger: "blur" }
        ],
        drugUp: [
          { required: true, message: "库存上限不能为空", trigger: "blur" }
        ],
        drugPackage: [
          { required: true, message: "包装规格不能为空", trigger: "blur" }
        ],
      },
      isdisabled:false,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    async checkdrugId(value,callback){
      let flag = 0
      listDruginfo().then(res =>{
        // console.log(res.rows.length);
        for(let i=0;i<res.rows.length;i++){
          if(value == res.rows[i].drugId){
            flag = flag + 1;
          }
        }
        if(flag != 0 ){
          return callback("编码已存在");
        }else{
          return callback()
        }

      })
    },
    /** 查询药品信息列表 */
    getList() {
      this.loading = true;
      listDruginfo(this.queryParams).then(response => {
        this.druginfoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.isdisabled = false;
      this.open = false;
      this.open1 = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        drugId: null,
        drugName: null,
        drugEngname: null,
        drugType: null,
        drugToxicology: null,
        drugFormulation: null,
        drugPackingunit: null,
        drugDose: null,
        drugDoseunit: null,
        drugNum: null,
        drugNumunit: null,
        drugPackage: null,
        drugUse: null,
        drugDown: null,
        drugUp: null,
        drugFactory: null,
        sort: null,
        isvalid: null,
        createBy: null,
        createDate: null,
        updateBy: null,
        updateDate: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
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
      this.isdisabled = false;
      this.open = true;
      this.title = "添加药品信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      this.isdisabled = true;
      getDruginfo(id).then(response => {
        this.form = response.data;
        this.open1 = true;
        this.title = "修改药品信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDruginfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open1 = false;
              this.isdisabled = false;
              this.getList();
            });
          } else {

            this.form.isvalid = 1;
            addDruginfo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();

            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除药品信息编号为"' + ids + '"的数据项？').then(function() {
        return delDruginfo(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/druginfo/export', {
        ...this.queryParams
      }, `druginfo_${new Date().getTime()}.xlsx`)
    },

  }
};
</script>
