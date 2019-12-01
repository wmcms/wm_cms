<template>
  <div class="app-container">
    <el-table v-loading="loading" :data="list" border fit highlight-current-row style="width: 100%">
      <el-table-column fixed prop="title" label="标题" width="300px" />
      <el-table-column prop="author" label="作者" width="100px" />
      <el-table-column prop="tags" label="标签" width="200px" />
      <el-table-column label="置顶?" width="80px">
        <template slot-scope="scope">
          <span>{{ scope.row.top?"是":"否" }}</span>
        </template>
      </el-table-column>
      <el-table-column label="推荐?" width="80px">
        <template slot-scope="scope">
          <span>{{ scope.row.recommend?"是":"否" }}</span>
        </template>
      </el-table-column>
      <el-table-column label="热门?" width="80px">
        <template slot-scope="scope">
          <span>{{ scope.row.hot?"是":"否" }}</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="100">
        <template slot-scope="scope">
          <router-link :to="'/cms/newsedit/'+scope.row.id">
            <el-button type="primary" size="small" icon="el-icon-edit">
              Edit
            </el-button>
          </router-link>
        </template>
      </el-table-column>

    </el-table>

    <pagination v-show="pager.total>0" :total="pager.total" :page.sync="pager.pageIndex" :limit.sync="pager.pageSize" @pagination="pageLoad" />
  </div>
</template>

<script>

import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'List',
  components: { Pagination },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  props: {
    callback: {
      type: Function,
      default: undefined
    },
    list: {
      type: Array,
      default: undefined
    },
    total: {
      type: Number,
      default: 0
    },
    loading: {
      type: Boolean,
      default: false
    },
    pager: {
      type: Object,
      default: {
        pageIndex: 1,
        pageSize: 10,
        total: 0
      }
    }
  },
  data() {
    return {

    }
  },
  methods: {
    pageLoad() {
   	this.callback()
    }
  }
}
</script>

<style scoped>
.edit-input {
  padding-right: 100px;
}
.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}
</style>
