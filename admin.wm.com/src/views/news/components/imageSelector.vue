<template>
  <el-dialog v-if="isShow" v-el-drag-dialog title="选择图片" :visible.sync="isDialog" width="800px">
    <div class="filter-container">
      <el-row>
        <el-col :span="10">
          <el-form-item label-width="100px" label="素材分类：" prop="metaId">
            <el-select v-model="queryArgs.metaId" placeholder="请选择">
              <el-option v-for="item in materialTypes" :key="item.key" :label="item.text" :value="item.key" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-input v-model="queryArgs.keyword" class="filter-item query-keyword" placeholder="关键字" @keyup.enter.native="handleSearch" />
        </el-col>
        <el-col :span="4">
          <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleSearch">查询 </el-button>
        </el-col>
      </el-row>

    </div>
    <ul class="el-upload-list el-upload-list--picture img-selector">
      <li v-for="(it,i) in list" class="el-upload-list__item is-success">
        <img :src="it.url" alt="" class="el-upload-list__item-thumbnail" @click="change(i)">
        <label v-if="imageSelected[i].checked" class="el-upload-list__item-status-label">
          <i class="el-icon-upload-success el-icon-check" /></label>
      </li>
    </ul>
    <div slot="footer" class="dialog-footer">
      <el-button @click="isDialog=false">
        取消
      </el-button>
      <el-button type="primary" @click="ok()">
        确定
      </el-button>
    </div>
  </el-dialog>

</template>
<script>
import elDragDialog from '@/directive/el-drag-dialog'
import waves from '@/directive/waves' // waves directive
import { searchMaterial } from '@/api/materail'
import { getMeta } from '@/api/meta'
export default {
  name: 'ImageSelector',
  directives: {
    waves,
    elDragDialog
  },
  props: {
    isShow: {
      type: Boolean,
      default: false
    },
    target: {
      type: String,
      default: '1'
    }
  },
  data() {
    return {
      queryArgs: {
        metaId: undefined,
        keyword: undefined,
        pageIndex: 1,
        pageSize: 100
      },
      isDialog: this.isShow,
      list: [],
      materialTypes: [],
      imageSelected: []
    }
  },
  watch: {
    isDialog: function(newValue, oldValue) {
       	this.$emit('update:isShow', newValue)
      for (var i in this.imageSelected) {
        this.imageSelected[i].checked = false
      }
    },
    isShow: function(newValue, oldValue) {
      this.isDialog = newValue
    }
  },
  created() {
    this.getMeta()
    this.loadDta()
  },
  methods: {
    getMeta() {
      this.httpGet('enum/materialtype').then(res => {
        this.materialTypes = res.data
      })
    },
    loadDta() {
      this.httpPost('search/material', this.queryArgs).then(res => {
        this.list = res.data.items
        this.imageSelected = new Array()
        for (var i in this.list) {
          this.imageSelected.push({
            checked: false,
            url: this.list[i].url
          })
        }
      })
    },
    handleSearch() {
      this.loadDta()
    },
    change(i) {
      this.imageSelected[i].checked = !this.imageSelected[i].checked
    },
    ok() {
      const res = new Array()
      this.imageSelected.reduce((e, cur) => {
        if (cur.checked) {
          res.push(cur.url)
        }
      }, {})

      this.$parent.insertImage(res)
    }
  }
}
</script>
<style lang="scss" scoped>
	.img-selector {
		max-height: 600px;
		max-width: 780px;
		overflow-y: scroll;
		li {
			padding: 0px;
			height: 100%;
			width: 180px;
			float: left;
			cursor: pointer;
			margin: 2px;
			img {
				width: 180px;
				height: 180px;
				margin-left: 0px;
			}
			label {
				z-index: 100;
			}
		}
	}
</style>
