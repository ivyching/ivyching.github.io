<template>
  <div>
    <loading :active.sync="isLoading"></loading>
    <div class="row mt-4">
      <div class="col-md-4 mb-4" v-for="item in products" :key="item.id">
        <div class="card border-0 shadow-sm">
          <div
            style="
              height: 250px;
              background-size: cover;
              background-position: center;
            "
            :style="{ backgroundImage: `url(${item.imageUrl})` }"
          ></div>
          <div class="card-body">
            <span class="badge badge-secondary float-right ml-2">{{
              item.category
            }}</span>
            <h5 class="card-title">
              <a href="#" class="text-dark">{{ item.title }}</a>
            </h5>
            <p class="card-text">{{ item.content }}</p>
            <div class="d-flex justify-content-between align-items-baseline">
              <!-- <div class="h5">2,800 元</div> -->
              <del class="h6">{{ item.origin_price | currency }}元</del>
              <div class="h5">現在只要 {{ item.price | currency }} 元</div>
            </div>
          </div>
          <div class="card-footer d-flex">
            <button
              type="button"
              class="btn btn-outline-secondary btn-sm"
              @click="getProduct(item.id)"
            >
              <i
                class="fas fa-spinner fa-spin"
                v-if="status.loadingItem == item.id"
              ></i>
              查看更多
            </button>
            <button
              type="button"
              class="btn btn-outline-danger btn-sm ml-auto"
              @click="addToCart(item.id)"
            >
              <i
                class="fas fa-spinner fa-spin"
                v-if="status.loadingItem == item.id"
              ></i>
              加到購物車
            </button>
          </div>
        </div>
      </div>
    </div>
    <div class="cartList mt-4 mb-4"></div>
    <div class="mt-4 mb-4 col-sm-6" style="left: 25%">
      <table class="table mt-4">
        <thead>
          <th></th>
          <th width="50%">品名</th>
          <th>數量</th>
          <th>單價</th>
        </thead>
        <tbody>
          <tr v-for="item in carts" :key="item.id">
            <td>
              <button class="btn btn-outline-danger btn-sm">
                <i class="fas fa-trash"></i>
              </button>
            </td>
            <td>{{ item.product.title }}</td>
            <td>{{ item.qty }}/{{ item.product.unit }}</td>
            <td class="text-right">{{ item.final_total }}</td>
          </tr>
          <tr>
            <td colspan="2"></td>
            <td class="text-right">總計</td>
            <td class="text-right">{{ cartTotalAmount }}</td>
          </tr>
          <tr class="finalTotal">
            <td colspan="2"></td>
            <td class="text-right">折扣價</td>
            <td class="text-right">{{ finalTotalAmount }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="mt-4 mb-4 col-sm-6" style="left: 25%">
      <div class="input-group mb-3">
        <input
          type="text"
          class="form-control"
          placeholder="請輸入優惠碼"
          aria-label="Recipient's username"
          aria-describedby="basic-addon2"
        />
        <button class="btn btn-outline-secondary" type="button">
          套用優惠碼
        </button>
      </div>
      <label for="basic-url" class="form-label">Email</label>
      <input
        type="text"
        class="form-control"
        placeholder="請輸入Email"
        aria-label="Recipient's username"
        aria-describedby="basic-addon2"
      />
    </div>
    <div
      class="modal fade"
      id="productModal"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content" style="border-radius: 10px">
          <div class="modal-header title">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              :style="{ 'font-weight': 'bolder' }"
            >
              {{ product.title }}
            </h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
              style="color: black"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="modalBody">
              <div
                style="
                  height: 350px;
                  background-size: cover;
                  background-position: center;
                "
                :style="{ backgroundImage: `url(${product.imageUrl})` }"
              ></div>
              <div class="col-md-12 mt-4">
                <div class="title">
                  <h5
                    class="modal-title"
                    id="exampleModalLabel"
                    :style="{ 'font-weight': 'bolder' }"
                  >
                    {{ product.description }}
                  </h5>
                </div>
                <div class="mt-4">
                  <div
                    class="d-flex justify-content-between align-items-baseline"
                  >
                    <!-- <div class="h5">2,800 元</div> -->
                    <del class="h6"
                      >{{ product.origin_price | currency }}元</del
                    >
                    <div class="h5">
                      現在只要 {{ product.price | currency }} 元
                    </div>
                  </div>
                  <div class="mt-4">
                    <select
                      class="form-control"
                      name="unitOption"
                      id="unit"
                      @change="calculateTotalAmount(product.num, product.price)"
                      v-model="product.num"
                    >
                      <option :value="num" v-for="num in 10" :key="num">
                        選購 {{ num }} {{ product.unit }}
                      </option>
                    </select>
                  </div>
                  <div class="mt-4 fa-pull-right">
                    小計 {{ totalAmount }} 元
                  </div>
                  <div class="mt-4">
                    <button
                      type="button"
                      class="btn btn-outline-danger btn-sm ml-auto"
                      @click="addToCart(product.id, product.num)"
                    >
                      <i
                        class="fas fa-spinner fa-spin"
                        v-if="status.loadingItem == product.id"
                      ></i>
                      加到購物車
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer"></div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import $ from "jquery";
export default {
  data() {
    return {
      products: [],
      isLoading: false,
      product: {},
      status: {
        loadingItem: "",
      },
      totalAmount: 0,
      carts: [],
      finalTotalAmount: 0,
      cartTotalAmount: 0,
    };
  },
  methods: {
    getProducts(page = 1) {
      const vm = this;

      const url = `/api/${process.env.CUSTOMPATH}/products/all`;
      vm.isLoading = true;

      this.$http.get(url).then((response) => {
        vm.products = response.data.products;
        vm.isLoading = false;
      });
    },
    getProduct(id) {
      const vm = this;
      const url = `/api/${process.env.CUSTOMPATH}/product/${id}`;
      vm.status.loadingItem = id;
      this.$http.get(url).then((response) => {
        $("#productModal").modal("show");
        vm.product = response.data.product;
        vm.status.loadingItem = "";
      });
    },
    calculateTotalAmount(qty, price) {
      let vm = this;

      console.log(qty, price);

      let totalAmount = price * qty;

      vm.totalAmount = totalAmount;
    },
    addToCart(id, qty = 1) {
      const vm = this;
      const url = `/api/${process.env.CUSTOMPATH}/cart`;
      vm.status.loadingItem = id;
      const cart = {
        product_id: id,
        qty,
      };
      this.$http.post(url, { data: cart }).then((response) => {
        console.log(response);
        vm.status.loadingItem = "";
        vm.getCart();
        $("#productModal").modal("hide");
      });
    },
    getCart() {
      const vm = this;

      const url = `/api/${process.env.CUSTOMPATH}/cart`;
      vm.isLoading = true;
      vm.finalTotalAmount = 0;
      this.$http.get(url).then((response) => {
        console.log(response);
        vm.carts = response.data.data.carts;
        vm.isLoading = false;

        vm.finalTotalAmount = response.data.data.final_total;
        vm.cartTotalAmount = response.data.data.total;
      });
    },
  },
  created() {
    this.getProducts();
    this.getCart();
  },
};
</script>