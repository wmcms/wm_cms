<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-button class="filter-item filter-btnNew" type="primary" icon="el-icon-edit" @click="createMeta">New </el-button>
        </el-form-item>
        <el-form-item>
          <el-button class="filter-item filter-btnNew" type="default" icon="el-icon-delete" @click="batchDel">删除所选 </el-button>
        </el-form-item>
        <el-form-item>
          <el-input v-model="queryArgs.keyword" class="filter-item query-keyword" placeholder="名称" @keyup.enter.native="search" />
        </el-form-item>
        <el-form-item>
          <el-select v-model="queryArgs.type" class="filter-item query-type" placeholder="数据类型" clearable>
            <el-option v-for="item in dataTypes" :key="item.key" :label="item.text" :value="item.key" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="search">查询 </el-button>
        </el-form-item>
      </el-form>

    </div>
    <el-table v-loading="isLoading" :data="list" border style="width: 100%" @ @selection-change="selectChange">
      <el-table-column fixed type="selection" align="center" width="50" />
      <el-table-column label="名称" prop="name" min-width="150" />
      <el-table-column label="父级">
        <template slot-scope="scope">
          <span>/</span>
        </template>
      </el-table-column>
      <el-table-column label="类型">
        <template slot-scope="scope">
          <span>{{ scope.row.type|toTypeText }}</span>
        </template>
      </el-table-column>
      <el-table-column label="排序" prop="sort" />
      <el-table-column fixed="right" label="操作" width="120">
        <template slot-scope="{row}">
          <i class="el-icon-edit i-btn" @click="editMeata(row)" />
          <i class="el-icon-delete i-btn" @click="updateStatus(row.id,-1)" />
        </template>
      </el-table-column>

    </el-table>
    <pagination v-show="pageCount>0" :total="pageCount" :page-index.sync="queryArgs.pageIndex" :page-size.sync="queryArgs.pageSize" @pagination="pageLoad" />

    <el-dialog v-el-drag-dialog :title="isNew?'New':'Edit'" :visible.sync="isDialog">
      <el-form ref="dataForm" :rules="rules" class="item-form" :model="item" label-position="right" label-width="70px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="名称" prop="name">
              <el-input v-model="item.name" placeholder="名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="类型" prop="type">
              <el-select v-model="item.type" class="filter-item" placeholder="请选择类型">
                <el-option v-for="item in dataTypes" :key="item.key" :label="item.text" :value="item.key" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="备注">
              <el-input v-model="item.remark" placeholder="备注" maxlength="50" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="排序值">
              <el-input v-model="item.sort" placeholder="排序值" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="isDialog = false">
          取消
        </el-button>
        <el-button type="primary" @click="save()">
          保存
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import waves from '@/directive/waves'
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination'
import elDragDialog from '@/directive/el-drag-dialog'
let typeKeyValue = null
export default {
  components: {
    Pagination
  },
  directives: {
    waves,
    elDragDialog
  },
  filters: {
    toTypeText(key) {
      return typeKeyValue[key]
    }
  },
  data() {
    return {
      list: null, // 数据源列表
      pageCount: 0, // 总页数
      isLoading: true, // 加载图标
      queryArgs: {
        pageIndex: 1,
        pageSize: 20,
        keyword: undefined,
        type: undefined
      },
      selectedIds: [],
      dataTypes: null,
      typeKeyValue: null,
      showReviewer: false,
      iitem: undefined,
      isDialog: false,
      isNew: true,
      rules: {
        type: [{
          required: true,
          message: '请选择数据类型',
          trigger: 'change'
        }],
        name: [{
          required: true,
          message: '名名称不能为空',
          trigger: 'blur'
        }]
      }
    }
  },
  created() {
    this.newItem()
    this.httpGet('/enum/list').then(res => {
      this.dataTypes = res.data
      typeKeyValue = this.dataTypes.reduce((acc, cur) => {
        acc[cur.key] = cur.text
        return acc
      }, {})

      this.pageLoad()
    })
  },
  methods: {
    selectChange(val) {
      this.selectedIds = val
    },
    pageLoad() { // 分页加载
      this.isLoading = false
      this.httpPost('search/meta', this.queryArgs).then(res => {
        const vm = this
        vm.list = res.data.items
        vm.pageCount = res.data.total
        vm.isLoading = false
      })
    },
    search() { // 搜索
      this.queryArgs.pageIndex = 1
      this.pageLoad()
    },
    updateStatus(id, status) { // 更新状态
      this.confirm('确定是否要提交?', () => {
        this.httpPost('/remove/meta', [id]).then(res => {
          this.success('操作成功!')
          this.search()
        })
      })
    },

    batchDel() {
      if (this.selectedIds.length) {
        this.confirm('确定是否要提交?', () => {
          const filterVal = ['id']
          const list = this.selectedIds
          const idList = this.formatJson(filterVal, list)
          this.httpPost('/remove/meta', idList.join().split(',')).then(res => {
            this.success('操作成功!')
            this.search()
          })
        })
      } else {
        this.warn('请先至少选择一条数据')
      }
    },
    newItem() {
      this.item = {
        id: undefined,
        name: undefined,
        parentId: undefined,
        parentPath: undefined,
        type: undefined,
        level: undefined,
        sort: undefined
      }
    },
    createMeta() {
      this.newItem()
      this.isNew = true
      this.isDialog = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    editMeata(row) {
      this.isNew = false
      this.item = Object.assign({}, row) // copy obj
      this.isDialog = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    save() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const item = Object.assign({}, this.item)
          item.createTime = undefined
          item.updateTime = undefined
          this.httpPost('save/meta', item).then(() => {
            this.isDialog = false
            this.success('操作成功!')
            this.search()
          })
        }
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]))
    }

  }
}
</script>
