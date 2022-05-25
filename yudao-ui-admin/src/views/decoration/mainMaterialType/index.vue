<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="主材类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择主材类型" clearable size="small">
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.MAIN_MATERIAL_TYPE)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="名称" prop="typeName">
        <el-input v-model="queryParams.typeName" placeholder="请输入名称" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="单位" prop="unit">
        <el-input v-model="queryParams.unit" placeholder="请输入单位" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="菜单状态" clearable>
          <el-option v-for="dict in statusDictDatas" :key="parseInt(dict.value)" :label="dict.label" :value="parseInt(dict.value)"/>
        </el-select>
      </el-form-item>
      <!--<el-form-item label="备注" prop="remark">
        <el-input v-model="queryParams.remark" placeholder="请输入备注" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
       <el-form-item label="备用字段1" prop="reason1">
        <el-input v-model="queryParams.reason1" placeholder="请输入备用字段1" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="备用字段2" prop="reason2">
        <el-input v-model="queryParams.reason2" placeholder="请输入备用字段2" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item> -->
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRangeCreateTime" style="width: 240px" value-format="yyyy-MM-dd"
                        type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['decoration:main-material-type:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['decoration:main-material-type:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <!-- <el-table-column label="主键ID" align="center" prop="id" /> -->
      <el-table-column label="主材类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.MAIN_MATERIAL_TYPE" :value="scope.row.type" />
        </template>
      </el-table-column>
      <el-table-column label="主材名称" align="center" prop="typeName"/>
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column prop="status" label="状态" width="80">
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.COMMON_STATUS" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <!-- <el-table-column label="备用字段1" align="center" prop="reason1" />
      <el-table-column label="备用字段2" align="center" prop="reason2" /> -->
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['decoration:main-material-type:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['decoration:main-material-type:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="主材类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择主材类型">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.MAIN_MATERIAL_TYPE)"
                       :key="dict.value" :label="dict.label" :value="dict.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="名称" prop="typeName">
        <el-input v-model="form.typeName" placeholder="请输入名称" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
        <el-form-item label="单位" prop="unit">
          <el-input v-model="form.unit" placeholder="请输入单位" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <span slot="label">
            <el-tooltip content="选择停用时，不能被访问" placement="top">
              <i class="el-icon-question" />
            </el-tooltip>
            状态
          </span>
          <el-radio-group v-model="form.status">
            <el-radio v-for="dict in this.getDictDatas(DICT_TYPE.COMMON_STATUS)"
                      :key="dict.value" :label="parseInt(dict.value)">{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <!-- <el-form-item label="备用字段1" prop="reason1">
          <el-input v-model="form.reason1" placeholder="请输入备用字段1" />
        </el-form-item>
        <el-form-item label="备用字段2" prop="reason2">
          <el-input v-model="form.reason2" placeholder="请输入备用字段2" />
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { createMainMaterialType, updateMainMaterialType, deleteMainMaterialType, getMainMaterialType, getMainMaterialTypePage, exportMainMaterialTypeExcel } from "@/api/decoration/mainMaterialType";
import {DICT_TYPE, getDictDatas} from "@/utils/dict";
import { CommonStatusEnum } from '@/utils/constants'

export default {
  name: "MainMaterialType",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 装修主材类型列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      dateRangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        type: null,
        unit: null,
        remark: null,
        reason1: null,
        reason2: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        type: [{ required: true, message: "主材类型(字典值)不能为空", trigger: "change" }],
        unit: [{ required: true, message: "单位不能为空", trigger: "blur" }],
      },
      statusDictDatas: getDictDatas(DICT_TYPE.COMMON_STATUS),
      CommonStatusEnum: CommonStatusEnum,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 处理查询参数
      let params = {...this.queryParams};
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行查询
      getMainMaterialTypePage(params).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        type: undefined,
        typeName: undefined,
        status: CommonStatusEnum.ENABLE,
        unit: undefined,
        remark: undefined,
        reason1: undefined,
        reason2: undefined,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加装修主材类型";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getMainMaterialType(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改装修主材类型";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateMainMaterialType(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createMainMaterialType(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除装修主材类型编号为"' + id + '"的数据项?').then(function() {
          return deleteMainMaterialType(id);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行导出
      this.$modal.confirm('是否确认导出所有装修主材类型数据项?').then(() => {
          this.exportLoading = true;
          return exportMainMaterialTypeExcel(params);
        }).then(response => {
          this.$download.excel(response, '装修主材类型.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
