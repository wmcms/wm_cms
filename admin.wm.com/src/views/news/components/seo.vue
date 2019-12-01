<template>
  <div class="createPost-main-container">
    <el-row>
      <el-col :span="24">
        <el-form-item style="margin-bottom: 40px;">
          <MDinput v-model="news.keyword" :maxlength="100" name="keyword">
            Keyword
          </MDinput>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <el-form-item style="margin-bottom: 40px;">
          <MDinput v-model="news.description" :maxlength="200" name="description">
            Description
          </MDinput>
        </el-form-item>
      </el-col>
    </el-row>
  </div>

</template>

<script>
import MDinput from '@/components/MDinput'
export default {
  name: 'Seo',
  components: {
    MDinput
  },
  props: {
    news: {
      type: Object,
      default: {
        keyword: undefined,
        description: undefined
      }
    }
  }
}
</script>
