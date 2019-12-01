<template>
  <div class="app-container">
    <list :list="list" :pager="pager" :loading="loading" :callback="search" />
  </div>
</template>
<script>
import list from './components/list'
export default {
  name: 'News',
  components: {
    list
  },
  data() {
    return {
      list: null,
      isLoad: false,
      total: 0,
      loading: true,
      requestParam: {
        pageIndex: 1,
        pageSize: 20,
        status: 0
      }
    }
  },
  computed: {

    pager() {
      return {
        pageIndex: this.requestParam.pageIndex,
        pageIndex: this.requestParam.pageIndex,
        total: this.total
      }
    }

  },
  created() {
    this.search()
  },
  methods: {
    search() {
      this.loading = true
      this.httpPost('search/news', this.requestParam).then(res => {
        this.list = res.data.items
        this.total = res.data.total
        this.loading = false
      })
    }
  }
}
</script>
