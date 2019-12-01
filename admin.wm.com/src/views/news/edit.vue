<template>
  <div class="createPost-container">
    <sticky :z-index="10" :class-name="'sub-navbar '+postForm.status">
      <el-button v-loading="loading" class="ml10" type="success" @click="publush">发布 </el-button>
      <el-button v-loading="loading" type="warning" @click="save"> 保存 </el-button>
    </sticky>
    <div class="tab-container">
      <el-tabs type="border-card">
        <el-tab-pane label="基础信息">
          <el-form ref="newsForm" :rules="rules" :model="news">
            <info ref="info" v-model="news" :news="news" :categories="categories" :others="others" />
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="投票项目">
          <vote :votes="votes" />
        </el-tab-pane>
        <el-tab-pane label="SEO">
          <el-form>
            <seo :news="news" />
          </el-form>

        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import Sticky from '@/components/Sticky' // 粘性header组件
import { validURL } from '@/utils/validate'
import Warning from './components/Warning'
import Info from './components/info'
import Vote from './components/vote'
import Seo from './components/seo'

const defaultForm = {
  status: 'draft',
  title: '', // 文章题目
  content: '', // 文章内容
  content_short: '', // 文章摘要
  source_uri: '', // 文章外链
  image_uri: '', // 文章图片
  display_time: undefined, // 前台展示时间
  id: undefined,
  platforms: ['a-platform'],
  comment_disabled: false,
  importance: 0
}

export default {
  name: 'ArticleDetail',
  components: {
    Seo,
    Sticky,
    Info,
    Vote
  },
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      content: undefined,
      news: {
        title: undefined,
        name: undefined,
        metaId: '',
        keyword: undefined,
        tags: undefined,
        coverUrl: undefined,
        author: undefined,
        description: undefined
      },
      votes: [],
      categories: [],
      others: ['istop'],
      postForm: Object.assign({}, defaultForm),
      loading: false,
      userListOptions: [],
      rules: {
        title: [{
          required: true,
          message: '标题不能为空',
          trigger: 'blur'
        }],
        metaId: [{
          required: true,
          message: '请选择分类',
          trigger: 'blur'
        }]
      },
      tempRoute: {}
    }
  },
  computed: {

    contentShortLength() {
      return this.postForm.content_short.length
    },
    displayTime: {
      get() {
        return (+new Date(this.postForm.display_time))
      },
      set(val) {
        this.postForm.display_time = new Date(val)
      }
    }
  },
  created() {
    this.getCategories()
  },
  methods: {
    getNews(id) {
      this.httpGet('get/news/' + (id || 0)).then(res => {
        this.news = res.data.news
        this.votes = res.data.votes
        this.$refs.info.initOtherOption(this.news)
      })
    },
    getCategories() {
      this.httpGet('enum/newscategory').then(res => {
        this.categories = res.data
        this.getNews(this.$route.params.id || 0)
      })
    },
    publush() {
      this.news.status = 1
      this.save()
    },
    save() {
      this.$refs['newsForm'].validate((valid) => {
        if (valid) {
          this.getResult()
          var data = {
            news: this.news,
            votes: this.votes
          }
          this.httpPost('save/news', data).then(res => {
            this.news.id = res.data
            this.success('操作成功!')
          })
        }
      })
    },
    getResult() {
      this.$refs.info.setOtherResult()
    }

  }
}
</script>

<style lang="scss" scoped>
	@import "~@/styles/mixin.scss";
	.tab-container {
		margin: 30px;
	}

	.ml10 {
		margin-left: 10px;
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
