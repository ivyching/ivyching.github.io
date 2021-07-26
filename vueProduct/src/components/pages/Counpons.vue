<template>
  <div>
    <loading :active.sync="isLoading"></loading>
    <div class="text-right mt-4">
      <button class="btn btn-info" data-toggle="modal" @click="openModal(true)">
        建立新的優惠券
      </button>
    </div>

    <table class="table mt-4">
      <thead>
        <th class="text-center">#</th>
        <th class="text-left">優惠券名稱</th>
        <th class="text-center">到期日</th>
        <th class="text-right">折扣</th>
        <th class="text-center">是否啟用</th>
        <th class="text-center"></th>
      </thead>
      <tbody>
        <tr v-for="(item, index) in coupons" :key="item.id">
          <td class="text-center">{{ index + 1 }}</td>
          <td class="text-left">{{ item.title }}</td>
          <td class="text-center">{{ item.dueDate }}</td>
          <td class="text-right">{{ item.percent }}</td>
          <td class="text-center">
            <span v-if="item.is_enabled" class="text-success">啟用</span>
            <span v-else class="text-warning">未啟用</span>
          </td>
          <td class="text-center">
            <button
              class="btn btn-outline-primary btn-sm"
              @click="openModal(false, item)"
            >
              <i class="fas fa-pencil-alt"></i> 編輯
            </button>
            <button
              class="btn btn-outline-danger btn-sm"
              @click="deleteModal(item)"
            >
              <i class="fas fa-trash"></i> 刪除
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <Pagination :getProducts="getCoupons" :pagination="pagination" />

    <div
      class="modal fade"
      id="deleteModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content border-0">
          <div class="modal-header bg-danger text-white">
            <h5 class="modal-title" id="exampleModalLabel">
              <span>刪除產品</span>
            </h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            是否刪除
            <strong class="text-danger">{{ tempCoupon.title }}</strong>
            商品(刪除後將無法恢復)。
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-outline-secondary"
              data-dismiss="modal"
            >
              取消
            </button>
            <button type="button" class="btn btn-danger" @click="deleteCoupon">
              確認刪除
            </button>
          </div>
        </div>
      </div>
    </div>
    <div
      class="modal fade"
      id="couponModal"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg">
        <div class="modal-content" style="border-radius: 10px">
          <div class="modal-header title">
            <h5 class="modal-title" id="exampleModalLabel">新增優惠券</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
              style="color: white"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form class="modalBody">
              <div class="inputFlex">
                <div class="col-md-6 mb-6 mt-3">
                  <label>優惠券名稱</label>
                  <input
                    class="form-control"
                    type="text"
                    placeholder="請輸入優惠券名稱"
                    v-model="tempCoupon.title"
                    id="title"
                  />
                </div>
                <div class="col-md-6 mb-6 mt-3">
                  <label>優惠券代碼</label>
                  <input
                    class="form-control"
                    type="text"
                    placeholder="請輸入代碼"
                    v-model="tempCoupon.code"
                    id="code"
                  />
                </div>
              </div>
              <div class="inputFlex">
                <div class="col-md-6 mb-6 mt-3">
                  <label>到期日</label>
                  <input
                    class="form-control"
                    type="text"
                    placeholder="請輸入到期日"
                    v-model="tempCoupon.due_date"
                    id="dueDate"
                  />
                </div>
                <div class="col-md-6 mb-6 mt-3">
                  <label>折扣</label>
                  <input
                    class="form-control"
                    type="text"
                    placeholder="請輸入折扣%數"
                    v-model="tempCoupon.percent"
                    id="percent"
                  />
                </div>
              </div>
              <div class="col-md-6 mb-6 mt-3">
                <label></label>
                <div class="custom-control custom-checkbox">
                  <input
                    type="checkbox"
                    class="custom-control-input"
                    id="isEnabled"
                    v-model="tempCoupon.is_enabled"
                    :true-value="1"
                    :false-value="0"
                  />
                  <label
                    class="custom-control-label"
                    id="checkEnabled"
                    for="isEnabled"
                    >是否啟用</label
                  >
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-dismiss="modal"
            >
              關閉
            </button>
            <button type="button" class="btn btn-primary" @click="updateCoupon">
              {{ isNew ? "新增" : "修改" }}
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import $ from "jquery";
import Pagination from "@/components/Pagination";
export default {
  data() {
    return {
      isLoading: false,
      pagination: {},
      isNew: true,
      tempCoupon: {},
      coupons: [],
    };
  },
  components: { Pagination },
  methods: {
    getCoupons(page = 1) {
      const vm = this;

      if (vm.pagination.total_pages == vm.pagination.current_page) {
        page = 1;
      }
      const api = `/api/${process.env.CUSTOMPATH}/admin/coupons?page=${page}`;

      vm.isLoading = true;

      this.$http.get(api).then((response) => {
        vm.coupons = response.data.coupons.map(function (value, index) {
          let date = new Date(value.due_date * 1000);
          value["dueDate"] =
            date.getFullYear() +
            "/" +
            (date.getMonth() + 1) +
            "/" +
            date.getDate();

          return value;
        });

        console.log(vm.coupons);

        vm.isLoading = false;
        vm.pagination = response.data.pagination;
      });
    },
    openModal(isNew, item) {
      if (isNew) {
        this.tempCoupon = {};
        this.isNew = true;
      } else {
        this.tempCoupon = Object.assign({}, item);
        this.isNew = false;
      }
      $("#couponModal").modal("show");
    },
    updateCoupon() {
      let api = `/api/${process.env.CUSTOMPATH}/admin/coupon`;
      let httpMethod = "post";
      const vm = this;

      if (!vm.isNew) {
        api = `/api/${process.env.CUSTOMPATH}/admin/coupon/${vm.tempCoupon.id}`;
        httpMethod = "put";
      }

      this.$http[httpMethod](api, { data: vm.tempCoupon }).then((response) => {
        if (response.data.success) {
          vm.refreshCoupons();
          if (!vm.isNew) {
            vm.$bus.$emit("message:push", "修改成功", "info");
          } else {
            vm.$bus.$emit("message:push", "新增成功", "success");
          }
        } else {
          vm.$bus.$emit("message:push", response.data.message, "danger");
        }
      });
    },
    refreshCoupons() {
      const api = `/api/${process.env.CUSTOMPATH}/admin/coupons`;
      const vm = this;
      $("#couponModal").modal("hide");
      $("#deleteModal").modal("hide");
      vm.getCoupons();
    },
    deleteModal(item) {
      this.tempCoupon = Object.assign({}, item);
      $("#deleteModal").modal("show");
    },
    deleteCoupon() {
      let vm = this;
      let api = `/api/${process.env.CUSTOMPATH}/admin/coupon/${vm.tempCoupon.id}`;

      this.$http.delete(api, { data: vm.tempCoupon }).then((response) => {
        if (response.data.success) {
          vm.$bus.$emit("message:push", "刪除成功", "success");
          vm.refreshCoupons();
        } else {
          vm.$bus.$emit("message:push", response.data.message, "danger");
        }
      });
    },
  },
  created() {
    this.getCoupons();
  },
};
</script>
<style>
.title {
  color: white;
  background-color: rgb(80, 76, 76);
  font-weight: bolder;
}

.inputFlex {
  display: flex;
}
</style>