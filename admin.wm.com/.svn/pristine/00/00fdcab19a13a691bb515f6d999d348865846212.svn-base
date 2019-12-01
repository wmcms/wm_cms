<template>
  <div>
    <editor id="tinymceEditor" :key="tinymceFlag" v-model="text" :init="tinymceInit" />
  </div>
</template>
<script>
import tinymce from 'tinymce/tinymce'
import 'tinymce/themes/silver/theme'
import Editor from '@tinymce/tinymce-vue'
import ImgSelector from './imageSelector'

import 'tinymce/plugins/textcolor'
import 'tinymce/plugins/advlist'
import 'tinymce/plugins/table'
import 'tinymce/plugins/lists'
import 'tinymce/plugins/paste'
import 'tinymce/plugins/preview'
import 'tinymce/plugins/fullscreen'

export default {
  name: 'TinymceEditor',
  components: {
    'editor': Editor,
    ImgSelector
  },
  props: {
    content: {
      type: String,
      default: undefined
    },
    token: {
      type: String,
      default: undefined
    },
    openImgSelector: {
      type: Function,
      default: null
    },
    callback: {
      type: Function,
      default: undefined
    }
  },
  data() {
    return {
      tinymceFlag: 1,
      text: this.content
    }
  },
  watch: {
    content(val) {
      this.text = val
    },
    text(val) {
      this.$emit('update:content', val)
    }
  },
  created() {
    const that = this
    that.text = this.content
    this.tinymceInit = {
      skin_url: '/tinymce/skins/ui/oxide',
      language_url: `/tinymce/langs/zh_CN.js`,
      language: 'zh_CN',
      height: document.body.offsetHeight - 100,
      browser_spellcheck: true, // 拼写检查
      branding: false, // 去水印
      height: 500,
      // elementpath: false,  //禁用编辑器底部的状态栏
      statusbar: false, // 隐藏编辑器底部的状态栏
      paste_data_images: true, // 允许粘贴图像
      menubar: false, // 隐藏最上方menu
      powerpaste_word_import: 'propmt', // 参数可以是propmt, merge, clear，效果自行切换对比
      powerpaste_html_import: 'propmt', // propmt, merge, clear
      powerpaste_allow_local_images: true,
      paste_data_images: true,
      images_upload_handler: function(blobInfo, success, failure) {
        var formData = new FormData()
        formData.append('files', blobInfo.blob(), blobInfo.filename())
        that.$HttpUpload('/upload/image', formData, function(xhr) {
          if (xhr.status != 200) {
            failure('HTTP Error: ' + xhr.status)
            return
          }
          var res = JSON.parse(xhr.responseText)
          if (res.code != 200) {
            failure(res.msg)
            return
          }
          success(res.data[0] && res.data[0].url)
        })
      },
      plugins: 'advlist table lists paste preview fullscreen',
      toolbar: 'fontselect fontsizeselect forecolor backcolor bold italic underline strikethrough | alignleft aligncenter alignright alignjustify |  quicklink h2 h3 blockquote table numlist bullist preview fullscreen imageUpload',
      setup: (editor) => {
        editor.ui.registry.addButton('imageUpload', {
          tooltip: '插入图片',
          icon: 'image',
          onAction: () => {
            this.openImgSelector('editor')
          }
        })
      }
    }
  },
  activated() {
    this.tinymceFlag++
  },
  methods: {
    // 插入图片至编辑器
    insertImage(res, file) {
      res.reduce((acc, cur) => {
        tinymce.execCommand('mceInsertContent', true, `<img src=${cur}>`)
      }, {})

      this.callback && this.callback(this.content)
      this.$emit('update:isShow', false)
    }
  }
}
</script>
