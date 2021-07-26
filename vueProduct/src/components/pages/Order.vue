<template>
  <div>
    <loading :active.sync="isLoading"></loading>
    <table class="table mt-4">
      <thead>
        <th>購買時間</th>
        <th>Email</th>
        <th>購買款項</th>
        <th>應付金額</th>
        <th>是否付款</th>
      </thead>
      <tbody>
        <tr></tr>
      </tbody>
    </table>
  </div>
</template>
<script>
export default {
  data() {
    return {
      OrderList: {},
      pagination: {},
      isLoading: false,
    };
  },
  methods: {
    getOrder(page) {
      const vm = this;

      if (vm.pagination.total_pages == vm.pagination.current_page) {
        page = 1;
      }
      const api = `/api/${process.env.CUSTOMPATH}/admin/orders?page=${page}`;

      vm.isLoading = true;

      this.$http.get(api).then((response) => {
        vm.OrderList = response.data.products;
        vm.isLoading = false;
        vm.pagination = response.data.pagination;
      });
    },
  },
  created() {
    this.getOrder();
  },
};
</script>