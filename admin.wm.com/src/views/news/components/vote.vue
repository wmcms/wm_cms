<template>

  <div class="createPost-main-container">
    <div class="filter-container">
      <el-button class="filter-item filter-btnNew" type="primary" icon="el-icon-edit" @click="createVote">New </el-button>
    </div>
    <el-table :data="votes" border style="width: 100%">
      <el-table-column fixed prop="title" label="项目" />
      <el-table-column prop="total" label="总票数" width="200" />

      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="handleClick(scope.row)">查看</el-button>
          <el-button type="text" size="small" @click="editVote(scope.row,scope.$index)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog v-el-drag-dialog title="编辑项目" :visible.sync="dialogView">
      <el-form ref="voteForm" :rules="rules" :model="vote" label-position="right" label-width="70px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="项目" prop="title">
              <el-input v-model="vote.title" placeholder="请输入项目标题" maxlength="50" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="描述">
              <el-input v-model="vote.description" placeholder="请输入项目描述" maxlength="200" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="票数">
              <el-input v-model="vote.total" placeholder="请输入初始项目票数" maxlength="10" />
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogView = false">
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
import elDragDialog from '@/directive/el-drag-dialog'
export default {
  name: 'Vote',
  components: {

  },
		 directives: {

    elDragDialog
  },
  props: {
    votes: {
      type: Array,
      default: undefined
    }
  },
  data() {
    return {
      voteInex: -1,
      dialogView: false,
      vote: {
        id: undefined,
        title: undefined,
        total: undefined,
        description: undefined
      },
      rules: {
        title: [{
          required: true,
          message: '项目不能为空',
          trigger: 'blur'
        }]
      }
    }
  },
  methods: {
    createVote() {
      this.voteInex = -1
      this.dialogView = true
      this.$nextTick(() => {
        this.$refs['voteForm'].clearValidate()
      })
    },
    editVote(data, index) {
      this.voteInex = index
      this.vote = this.votes[index]
      this.dialogView = true
    },
			    saveData() {
      this.$refs['voteForm'].validate((valid) => {
        if (valid) {
          const vote = Object.assign({}, this.vote)
        	if (this.voteInex > -1) {
        		this.votes[this.voteInex] = vote
        	} else {
        		this.votes.push(vote)
        	}
        	this.dialogView = false
        }
      })
    }
  }

}
</script>

<style lang="scss" scoped>
	@import "~@/styles/mixin.scss";
	.tab-container {
		margin: 30px;
	}

	.createPost-container {
		position: relative;
		.createPost-main-container {
			padding: 40px 45px 20px 50px;
			.postInfo-container {
				position: relative;
				@include clearfix;
				margin-bottom: 10px;
				.postInfo-container-item {
					float: left;
				}
			}
		}
		.word-counter {
			width: 40px;
			position: absolute;
			right: 10px;
			top: 0px;
		}
	}

	.article-textarea /deep/ {
		textarea {
			padding-right: 40px;
			resize: none;
			border: none;
			border-radius: 0px;
			border-bottom: 1px solid #bfcbd9;
		}
	}
</style>
