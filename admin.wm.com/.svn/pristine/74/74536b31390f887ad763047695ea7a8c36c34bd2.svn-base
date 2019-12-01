<template>
  <div class="createPost-main-container">
    <el-row>
      <el-col :span="24">
        <el-form-item label-width="60px" label="标题:" prop="title">
          <el-input v-model="news.title" placeholder="标题" maxlength="150" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="10">
        <div class="image-preview">
          <div v-show="true" class="image-preview-wrapper">
            <img ref="conver" :src="news.coverUrl">
            <div class="image-preview-action" @click="openImgSelector('conver')">
              <i class="el-icon-view" />封面
            </div>
          </div>
        </div>
      </el-col>
      <el-col :span="14">
        <el-row>
          <el-col :span="24">
            <el-form-item label-width="100px" label="所属类别：" prop="metaId">
              <el-select v-model="news.metaId" placeholder="请选择">
                <el-option v-for="item in categories" :key="item.key" :label="item.text" :value="item.key" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label-width="100px" label="作者:">
              <el-input v-model="news.author" placeholder="作者" maxlength="50" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label-width="100px" label="标签:">
              <el-tag v-for="tag in tags" :key="tag" closable :disable-transitions="false" @close="handleClose(tag)">{{ tag }}</el-tag>
              <el-input v-if="inputVisible" ref="saveTagInput" v-model="inputValue" class="input-new-tag" size="small" @keyup.enter.native="handleInputConfirm" @blur="handleInputConfirm" />
              <el-button v-else class="button-new-tag" size="small" @click="showInput">+ New Tag</el-button>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label-width="100px" label="其他:">
              <el-select v-model="others" multiple placeholder="请选择" width="300px">
                <el-option v-for="item in other" :key="item.key" :label="item.text" :value="item.key" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-col>
    </el-row>

    <el-form-item style="margin-bottom: 10px;">
      <tinymce-editor ref="editor" :content.sync="news.content" :token="token" :is-show.sync="isShow" :open-img-selector="openImgSelector" :callback="setContent" />
    </el-form-item>
    <imgSelector :is-show.sync="isShow" :target="target" />
  </div>

</template>

<script>
import TinymceEditor from './tinymceEditor'
import Upload from '@/components/Upload/SingleImage3'
import Sticky from '@/components/Sticky' // 粘性header组件
import { validURL } from '@/utils/validate'
import ImgSelector from './imageSelector'
import { mapGetters } from 'vuex'

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
  name: 'Info',
  components: {
    TinymceEditor,
    Upload,
    Sticky,
    ImgSelector
  },
  props: {
    news: {
      type: Object,
      default: null
    },
    categories: {
      type: Array,
      default: []
    }
  },
  computed: {
  	...mapGetters([
      'token'
    ])
  },
  data() {
    return {
      tags: [],
      others: [],
      inputVisible: false,
      inputValue: '',
      other: [{
        key: 'top',
        text: '置顶'
      }, {
        key: 'hot',
        text: '热门'
      }, {
        key: 'recommend',
        text: '推荐'
      }],
      isShow: false,
      target: undefined,
      value1: [],
      tabIndex: 0,
      loading: false
    }
  },
  methods: {
    // 读取内容
    setContent(val) {
      this.news.content = val
    },
    initOtherOption(news) {
      if (news.tags == undefined || news.tags == null || news.tags == '') {
        this.tags = []
      } else {
        this.tags = news.tags.split(',')
      }
      if (news.top) { this.others.push('top') }
      if (news.hot) { this.others.push('hot') }
      if (news.recommend) { this.others.push('recommend') }
    },
    setOtherResult() {
      this.news.tags = this.tags.join(',')
      this.news.top = this.others.indexOf('top') > -1
      this.news.hot = this.others.indexOf('hot') > -1
      this.news.tecommend = this.others.indexOf('recommend') > -1
    },
    openImgSelector(type) {
      this.isShow = true
      this.target = type
    },
    insertImage(res) {
      if (this.target == 'conver' && res != null && res.length > 0) {
        this.news.coverUrl = res[0]
      } else if (this.target == 'editor') {
        if (this.news.content) { this.news.content += '' }
        this.$refs.editor.insertImage(res)
      }
      this.isShow = false
    },

    handleClose(tag) {
      this.tags.splice(this.tags.indexOf(tag), 1)
    },

    showInput() {
      this.inputVisible = true
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus()
      })
    },

    handleInputConfirm() {
      const inputValue = this.inputValue
      if (inputValue) {
        this.tags.push(inputValue)
      }
      this.inputVisible = false
      this.inputValue = ''
    }

  }
}
</script>

<style lang="scss" scoped>
	@import "~@/styles/mixin.scss";
	.el-tag+.el-tag {
		margin-left: 10px;
	}

	.button-new-tag {
		margin-left: 10px;
		height: 32px;
		line-height: 30px;
		padding-top: 0;
		padding-bottom: 0;
	}

	.input-new-tag {
		width: 90px;
		margin-left: 10px;
		vertical-align: bottom;
	}

	.tab-container {
		margin: 30px;
	}

	.image-preview {
		width: 300px;
		height: 180px;
		position: relative;
		border: 1px dashed #d9d9d9;
		float: left;
		.image-preview-wrapper {
			position: relative;
			width: 100%;
			height: 100%;
			img {
				width: 100%;
				height: 100%;
			}
		}
		.image-preview-action {
			position: absolute;
			width: 100%;
			height: 100%;
			left: 0;
			top: 0;
			cursor: default;
			text-align: center;
			color: #fff;
			opacity: 0;
			font-size: 20px;
			background-color: rgba(0, 0, 0, .5);
			transition: opacity .3s;
			cursor: pointer;
			text-align: center;
			line-height: 200px;
			.el-icon-delete {
				font-size: 36px;
			}
		}
		&:hover {
			.image-preview-action {
				opacity: 1;
			}
		}
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
