<style lang="scss">
	.query-keyword {
		width: 300px;
	}

	.query-type {
		width: 150px;
	}

	.filter-btnNew {
		margin-left: 10px;
	}

	.w100 {
		width: 100px;
	}

	.w150 {
		width: 150px;
	}

	.w50 {
		width: 50px;
	}

	.w200 {
		width: 200px;
	}

	.txt-left {
		text-align: left;
	}

	.tbUser {
		width: 100%;
	}

	.tbUser th {
		text-align: center;
	}

	.ellipsis {
		overflow: hidden;
		width: 70px;
		display: inline-block;
		text-overflow: ellipsis;
		white-space: nowrap;
	}

	.user-form {
		width: 98%;
		margin: 0px 2px;
	}

	.user-form .el-col label {
		padding-right: 10px;
		text-align: right;
	}

	.el-dialog {
		width: 60%;
	}

	.i-btn {
		cursor: pointer;
		background-color: transparent;
		margin: 0px 5px;
	}

	.blue {
		color: blue;
	}

	.red {
		color: red;
	}
</style>
<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item filter-btnNew" type="primary" icon="el-icon-edit" @click="createUser">New </el-button>
      <el-button class="filter-item filter-btnNew" type="default" icon="el-icon-delete" @click="batchDelete">删除所选 </el-button>
      <el-input v-model="queryArgs.keyword" class="filter-item query-keyword" placeholder="用户名/姓名/昵称/手机号" @keyup.enter.native="search" />
      <el-select v-model="queryArgs.type" class="filter-item query-type" placeholder="用户类型" clearable>
        <el-option v-for="item in userTypes" :key="item.key" :label="item.text+'('+item.key+')'" :value="item.key" />
      </el-select>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="search">查询 </el-button>

    </div>
    <el-table v-loading="loading" class="tbUser" :data="list" border fit highlight-current-row @ @selection-change="selectChange">
      <el-table-column type="selection" align="center" />
      <el-table-column label="#ID" class="w50">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户名" class="txt-left">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
          <i v-if="scope.row.type==1" class="el-icon-star-on" />
        </template>
      </el-table-column>
      <el-table-column label="姓名" class="txt-left w100">
        <template slot-scope="scope">
          <span>{{ scope.row.realName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="昵称" class="txt-left w100">
        <template slot-scope="scope">
          <span>{{ scope.row.nickname }}</span>
        </template>
      </el-table-column>
      <el-table-column label="手机号码" class="w100">
        <template slot-scope="scope">
          <span>{{ scope.row.mobile }}</span>
        </template>
      </el-table-column>
      <el-table-column label="等级" class="w100">
        <template slot-scope="scope">
          <svg-icon v-for="n in +scope.row.level" :key="n" icon-class="star" class="meta-item__icon" />
        </template>
      </el-table-column>
      <el-table-column label="状态" class-name="status-col" class="w100">
        <template slot-scope="scope">
          <span>{{ scope.row.status | statusFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class="w150" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <i class="el-icon-edit i-btn blue" @click="editUser(row)" />
          <i class="el-icon-delete i-btn red" @click="updateStatus(row.id,2)" />
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page-index.sync="queryArgs.pageIndex" :page-size.sync="queryArgs.pageSize" @pagination="pageLoad" />

    <el-dialog v-el-drag-dialog :title="isNew?'New':'Edit'" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" class="user-form" :model="user" label-position="right" label-width="70px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="用户名" prop="name">
              <el-input v-if="!isNew" v-model="user.name" readonly="readonly" />
              <el-input v-if="isNew" v-model="user.name" placeholder="请输入用户名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="昵称" prop="nickname">
              <el-input v-model="user.nickname" placeholder="请输入昵称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="类型" prop="type">
              <el-select v-model="user.type" class="filter-item" placeholder="Please select">
                <el-option v-for="item in userTypes" :key="item.key" :label="item.text" :value="item.key" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="姓名">
              <el-input v-model="user.realName" placeholder="姓名" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="手机号码">
              <el-input v-model="user.mobile" placeholder="手机号码" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="Email">
              <el-input v-model="user.email" placeholder="Email" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="生日">
              <el-date-picker v-model="user.birthDate" type="date" placeholder="Please pick a date" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别">
              <el-radio-group v-model="user.gender">
                <el-radio label="男" value="M" />
                <el-radio label="女" value="F" />
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="等级">
              <el-rate v-model="user.level" :colors="['#99A9BF', '#F7BA2A', '#FF9900', '#F6416C', '#FF0000']" :max="5" style="margin-top:8px;" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item v-if="isNew" label="密码" prop="Password">
              <el-input v-model="user.Password" type="password" />
            </el-form-item>
            <el-form-item v-if="!isNew" label="密码">
              <el-input v-model="user.Password" type="password" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="saveData()">
          保存
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { searchUser, saveUser, batchDelete } from '@/api/user'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import elDragDialog from '@/directive/el-drag-dialog'
const userTypes = [{
  key: 1,
  text: '系统用户'
},
{
  key: 2,
  text: '普通会员'
}
]
const userTypeKeyValue = userTypes.reduce((acc, cur) => {
  acc[cur.key] = cur.text
  return acc
}, {})

export default {

  components: {
    Pagination
  },
  directives: {
    waves,
    elDragDialog
  },
  filters: {
    statusFilter(status) {
      return status == 0 ? '有效' : '禁用'
    },
    typeFilter(type) {
      return userTypeKeyValue[type]
    }
  },
  data() {
    return {
      visible: true,
      tableKey: 0,
      list: null, // 数据源列表
      total: 0, // 总页数
      loading: true, // 加载图标
      queryArgs: {
        pageIndex: 1,
        pageSize: 20,
        keyword: undefined,
        type: undefined
      },
      selectedIds: [],
      userTypes,
      showReviewer: false,
      user: {
        id: undefined,
        name: undefined,
        nickname: undefined,
        realName: undefined,
        mobile: undefined,
        type: undefined,
        email: undefined,
        level: undefined,
        updateTime: undefined,
        createTime: undefined,
        birthdate: undefined,
        gender: undefined,
        Password: undefined
      },
      dialogFormVisible: false,
      isNew: true,
      rules: {
        type: [{
          required: true,
          message: '请选择用户类型',
          trigger: 'change'
        }],
        name: [{
          required: true,
          message: '用户名不能为空',
          trigger: 'blur'
        }],
        Password: [{
          required: true,
          message: '请设置密码',
          trigger: 'blur'
        }],
        nickname: [{
          required: true,
          message: '昵称不能为空',
          trigger: 'blur'
        }]
      },
      downloadLoading: false
    }
  },
  created() {
    this.pageLoad()
  },
  methods: {
			 selectChange(val) {
      this.selectedIds = val
    },
    pageLoad() { // 分页加载
      this.loading = true
      searchUser(this.queryArgs).then(res => {
        const vm = this
        vm.list = res.data.items
        vm.total = res.data.total
        vm.loading = false
      })
    },
    search() { // 搜索
      this.queryArgs.pageIndex = 1
      this.pageLoad()
    },
    updateStatus(id, status) { // 更新状态
      this.$confirm('确定是否要提交?').then(async() => {
        saveUser({
          id: id,
          status: status
        }).then(res => {
          this.success()
          this.search()
        })
      })
    },

    batchDelete() {
				 if (this.selectedIds.length) {
        this.$confirm('确定是否要提交?').then(async() => {
        	  const filterVal = ['id']
        	  debugger
          const list = this.multipleSelection
          const data = this.formatJson(filterVal, list)
          batchDelete({ ids: data }).then(res => {
        		this.success()
            this.search()
          })
        })
      } else {
        this.$message({
          message: '请先至少选择一条数据',
          type: 'warning'
        })
      }
    },
    newUser() {
      this.user = {
        id: 0,
        name: undefined,
        nickname: undefined,
        realName: undefined,
        mobile: undefined,
        type: undefined,
        email: undefined,
        level: undefined,
        updateTime: undefined,
        createTime: undefined,
        birthdate: undefined,
        gender: undefined,
        Password: undefined,
        password: undefined
      }
    },
    createUser() {
      this.newUser()
      this.isNew = true
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    editUser(row) {
      this.user = Object.assign({}, row) // copy obj
      this.user.birthDate = new Date(this.user.birthDate)
      this.isNew = false
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    saveData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const user = Object.assign({}, this.user)
          user.createTime = undefined
          user.updateTime = undefined
          user.password = this.isNew ? user.Password : user.password
          // user.birthDay = +new Date(user.birthDay) // change Thu Nov 30 2017 16:41:05 GMT+0800 (CST) to 1512031311464
          saveUser(user).then(() => {
            this.dialogFormVisible = false
            this.success()
            this.search()
          })
        }
      })
    },
    success() {
      this.$message({
        type: 'success',
        message: '操作成功!'
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    }
  }
}
</script>
