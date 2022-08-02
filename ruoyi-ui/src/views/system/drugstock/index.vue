<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="药品名称" prop="drugName">
        <el-input
          v-model="queryParams.drugName"
          placeholder="请输入药品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="拼音简写" prop="drugEngname">
        <el-input
          v-model="queryParams.drugEngname"
          placeholder="请输入拼音简写"
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
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:drugstock:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="drugstockList" show-summary :summary-method="getSummaries">
      <el-table-column
        type="index"
        width="50">
      </el-table-column>
<!--      <el-table-column type="selection" width="55" align="center" />-->
<!--      <el-table-column label="id" align="center" prop="id" />-->
      <el-table-column label="药品编码" align="center" prop="drugId" />
      <el-table-column label="药品名称" align="center" prop="drugName" />
      <el-table-column label="拼音简写" align="center" prop="drugEngname" />
      <el-table-column label="药品类别" align="center" prop="drugType" />
      <el-table-column label="包装规格" align="center" prop="drugPackage" />
      <el-table-column label="当前库存" align="center" prop="drugCount" />
      <el-table-column label="单价" align="center" prop="drugPrice" />
      <el-table-column label="包装单位" align="center" prop="drugPackingunit" />
      <el-table-column label="总价" align="center" prop="total" >
        <template slot-scope="scope">
        {{scope.row.drugCount * scope.row.drugPrice}}
        </template>
      </el-table-column>
      <el-table-column label="批次号" align="center" prop="drugOrder" />
      <el-table-column label="有效期" align="center" prop="drugValiddate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.drugValiddate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="生产企业" align="center" prop="drugFactory" />
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['system:drugstock:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['system:drugstock:remove']"-->
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

    <!-- 添加或修改药品库存对话框 -->
<!--    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>-->
<!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
<!--        <el-form-item label="药品编码" prop="drugId">-->
<!--          <el-input v-model="form.drugId" placeholder="请输入药品编码" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="药品名称" prop="drugName">-->
<!--          <el-input v-model="form.drugName" placeholder="请输入药品名称" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="拼音简写" prop="drugEngname">-->
<!--          <el-input v-model="form.drugEngname" placeholder="请输入拼音简写" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="包装规格" prop="drugPackage">-->
<!--          <el-input v-model="form.drugPackage" placeholder="请输入包装规格" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="当前库存" prop="drugCount">-->
<!--          <el-input v-model="form.drugCount" placeholder="请输入当前库存" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="单价" prop="drugPrice">-->
<!--          <el-input v-model="form.drugPrice" placeholder="请输入单价" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="包装单位" prop="drugPackingunit">-->
<!--          <el-input v-model="form.drugPackingunit" placeholder="请输入包装单位" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="批次号" prop="drugOrder">-->
<!--          <el-input v-model="form.drugOrder" placeholder="请输入批次号" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="有效期" prop="drugValiddate">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.drugValiddate"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择有效期">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="生产企业" prop="drugFactory">-->
<!--          <el-input v-model="form.drugFactory" placeholder="请输入生产企业" />-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--        <el-button @click="cancel">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->
  </div>
</template>

<script>
import {
  listDrugstock,
  getDrugstock,
  delDrugstock,
  addDrugstock,
  updateDrugstock,
  listStockSum
} from "@/api/system/drugstock";

export default {
  name: "Drugstock",
  data() {
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
      // 药品库存表格数据
      drugstockList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        drugId: null,
        drugName: null,
        drugEngname: null,
        drugType: null,
        drugPackage: null,
        drugCount: null,
        drugPrice: null,
        drugPackingunit: null,
        drugOrder: null,
        drugValiddate: null,
        drugFactory: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      sumlist:[],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询药品库存列表 */
    getList() {
      this.loading = true;
      listDrugstock(this.queryParams).then(response => {
        this.drugstockList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      listStockSum().then(res => {
        this.sumlist = res.rows[0]
      });
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
        drugId: null,
        drugName: null,
        drugEngname: null,
        drugType: null,
        drugPackage: null,
        drugCount: null,
        drugPrice: null,
        drugPackingunit: null,
        drugOrder: null,
        drugValiddate: null,
        drugFactory: null
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
      this.open = true;
      this.title = "添加药品库存";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDrugstock(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改药品库存";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDrugstock(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDrugstock(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除药品库存编号为"' + ids + '"的数据项？').then(function() {
        return delDrugstock(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/drugstock/export', {
        ...this.queryParams
      }, `drugstock_${new Date().getTime()}.xlsx`)
    },
    getSummaries(param){
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index === 6) {
          sums[index] = this.sumlist.sumcount;
          return;
        }else if(index === 9){
          sums[index] = this.sumlist.sumall;
          return;
        }else if(index === 5){
          sums[index] = '合计';
          return;
        }else{
          sums[index] = '';
          return;
        }
      });
      return sums;
    }
  }
};
</script>
