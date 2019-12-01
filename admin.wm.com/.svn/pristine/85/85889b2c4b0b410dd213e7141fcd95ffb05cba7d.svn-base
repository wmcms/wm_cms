<template>
  <div class="app-container">
    <div class="filter-container">
      <el-row>
        <el-col :span="2">
          <el-button class="filter-item filter-btnNew" type="primary" icon="el-icon-edit" @click="createAD">New </el-button>
        </el-col>
        <el-col :span="6">
          <el-select v-model="queryArgs.metaId" class="filter-item query-type" placeholder="广告分类" clearable>
            <el-option v-for="item in categories" :key="item.key" :label="item.text" :value="item.key" />
          </el-select>
        </el-col>
        <el-col :span="8">
          <el-input v-model="queryArgs.keyword" class="filter-item query-keyword" placeholder="名称" @keyup.enter.native="search" />
        </el-col>
        <el-col :span="4" class="ml10">
          <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="search">查询 </el-button>
        </el-col>
      </el-row>
    </div>
    <el-table v-loading="isLoading" :data="list" style="width: 100%" max-height="250" border>
      <el-table-column fixed label="名称" min-width="300">
        <template slot-scope="scope">
          <a :href="scope.row.url" target="_blank">
            {{ scope.row.name }}
          </a>
        </template>
      </el-table-column>
      <el-table-column label="广告分类" width="120">
        <template slot-scope="scope">
          <span>{{ scope.row.metaId | tocategoryText }}</span>
        </template>
      </el-table-column>
      <el-table-column label="类型" width="120">
        <template slot-scope="scope">
          <span>图片</span>
        </template>
      </el-table-column>
      <el-table-column label="广告" width="150">
        <template slot-scope="scope">
          <a :href="scope.row.resUrl" target="_blank">
            {{ scope.row.resUrl }}
          </a>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="100">
        <template slot-scope="{row}">
          <i class="el-icon-edit i-btn " @click="editMeata(row)" />
          <i class="el-icon-delete i-btn" @click="updateStatus(row.id,-1)" />
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="pageCount>0" :total="pageCount" :page-index.sync="queryArgs.pageIndex" :page-size.sync="queryArgs.pageSize" @pagination="pageLoad" />
    <el-dialog v-el-drag-dialog :title="isNew?'New':'Edit'" :visible.sync="isDialog">
      <el-form ref="dataForm" :rules="rules" class="item-form" :model="item" label-position="right" label-width="70px">
        <el-row>
          <el-col :span="20">
            <el-form-item label="名称" prop="name">
              <el-input v-model="item.name" placeholder="名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="20">
            <el-form-item label="分类" prop="metaId">
              <el-select v-model="item.metaId" class="filter-item" placeholder="请选择分类">
                <el-option v-for="item in categories" :key="item.key" :label="item.text" :value="item.key" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="20">
            <el-form-item label="链接">
              <el-input v-model="item.url" placeholder="http://www.xxx.xx" maxlength="150" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="17">
            <el-form-item label="资源">
              <el-input v-model="item.resUrl" placeholder="图片资源路径" readonly="readonly" maxlength="150" />
            </el-form-item>
          </el-col>
          <el-col :span="2" :offset="1">
            <el-button type="primary" @click="imageSelectorVisible=true"><i class="el-icon-upload" />上传</el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="15" :offset="3">
            <img :src.trim="item.resUrl" width="200px;" height="50px;">
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="save()">
          保存
        </el-button>
      </div>
    </el-dialog>
    <img-selector :is-show.sync="imageSelectorVisible" :res-url.sync="item.resUrl" />
  </div>
</template>

<script>
import waves from '@/directive/waves'
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination'
import elDragDialog from '@/directive/el-drag-dialog'
import ImgSelector from './image-selector'
const typeKeyValue = null
let categoryKeyValue = null
export default {
  components: {
    Pagination,
    ImgSelector
  },
  directives: {
    waves,
    elDragDialog
  },
  filters: {
    toTypeText(key) {
      return typeKeyValue[key]
    },
    tocategoryText(key) {
      return categoryKeyValue[key]
    }
  },
  data() {
    return {
      list: null, // 数据源列表
      pageCount: 0, // 总页数
      isLoading: true, // 加载图标
      categories: undefined,
      queryArgs: {
        pageIndex: 1,
        pageSize: 20,
        keyword: undefined,
        type: undefined
      },
      selectedIds: [],
      dataTypes: null,
      typeKeyValue: null,
      item: undefined,
      isDialog: false,
      imageSelectorVisible: false,
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
    this.getCategories()
  },
  methods: {
    getCategories() {
      this.httpGet('enum/adype').then(res => {
        this.categories = res.data
        categoryKeyValue = this.categories.reduce((acc, cur) => {
          acc[cur.key] = cur.text
          return acc
        }, {})

        this.pageLoad()
      })
    },

    selectChange(val) {
      this.selectedIds = val
    },
    pageLoad() { // 分页加载
      this.isLoading = false
      this.httpPost('search/ad', this.queryArgs).then(res => {
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
        this.httpPost('save/ad', {
          id: id,
          status: status
        }).then(res => {
          this.success('操作成功!')
          this.search()
        })
      })
    },
    createAD() {
      this.isNew = true
      this.newItem()
      this.isDialog = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    newItem() {
    	this.item = {
        id: undefined,
        name: undefined,
        parentId: undefined,
        type: undefined,
        parentPath: undefined,
        level: undefined,
        sort: undefined
      }
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
          this.httpPost('save/ad', item).then(() => {
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
