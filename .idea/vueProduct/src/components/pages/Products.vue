<template>
  <div>
    <loading :active.sync="isLoading"></loading>
    <div class="text-right mt-4">
      <button class="btn btn-info" data-toggle="modal" @click="openModal(true)">
        建立新的產品
      </button>
    </div>
    <table class="table mt-4">
      <thead>
        <th width="120">分類</th>
        <th>產品名稱</th>
        <th width="120">原價</th>
        <th width="120">售價</th>
        <th width="80">狀態</th>
        <th width="180"></th>
      </thead>
      <tbody>
        <tr v-for="item in products" :key="item.id">
          <td>{{ item.category }}</td>
          <td>{{ item.title }}</td>
          <td class="text-right">{{ item.origin_price | currency }}</td>
          <td class="text-right">{{ item.price | currency }}</td>
          <td>
            <span v-if="item.is_enabled" class="text-success">啟用</span>
            <span v-else class="text-warning">未啟用</span>
          </td>
          <td>
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
    <Pagination :getProducts="getProducts" :pagination="pagination" />

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
            <strong class="text-danger">{{ tempProduct.title }}</strong>
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
            <button type="button" class="btn btn-danger" @click="deleteProduct">
              確認刪除
            </button>
          </div>
        </div>
      </div>
    </div>
    <div
      class="modal fade"
      id="productModal"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg">
        <div class="modal-content" style="border-radius: 10px">
          <div class="modal-header title">
            <h5 class="modal-title" id="exampleModalLabel">新增產品</h5>
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
              <div class="col-md-4 form-group">
                <label>輸入圖片網址</label>
                <input
                  class="form-control"
                  type="text"
                  placeholder="請輸入圖片連結"
                  v-model="tempProduct.imageUrl"
                  id="image"
                />
                <div class="form-group">
                  <label for="customFile"
                    >或 上傳圖片
                    <i
                      class="fas fa-spinner fa-spin"
                      v-if="status.fileUploading"
                    ></i>
                  </label>
                  <input
                    type="file"
                    id="customFile"
                    class="form-control"
                    ref="files"
                    @change="uploadFile"
                  />
                </div>
                <img
                  img="https://images.unsplash.com/photo-1483985988355-763728e1935b?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=828346ed697837ce808cae68d3ddc3cf&auto=format&fit=crop&w=1350&q=80"
                  class="img-fluid"
                  alt=""
                  :src="tempProduct.imageUrl"
                />
              </div>

              <div class="col-md-8">
                <div class="col-md-12 mb-12">
                  <label>標題</label>
                  <input
                    class="form-control"
                    type="text"
                    placeholder="請輸入標題"
                    v-model="tempProduct.title"
                    id="title"
                  />
                </div>
                <div class="inputFlex">
                  <div class="col-md-6 mb-6">
                    <label for="validationDefault04">分類</label>
                    <input
                      type="text"
                      class="form-control"
                      placeholder="請輸入分類"
                      v-model="tempProduct.category"
                      id="category"
                    />
                  </div>
                  <div class="col-md-6 mb-6">
                    <label for="validationDefault04">單位</label>
                    <input
                      type="text"
                      class="form-control"
                      id="unit"
                      placeholder="請輸入單位"
                      v-model="tempProduct.unit"
                    />
                  </div>
                </div>
                <div class="inputFlex">
                  <div class="col-md-6 mb-6">
                    <label for="originPrice">原價</label>
                    <input
                      type="number"
                      class="form-control"
                      id="originPrice"
                      placeholder="請輸入原價"
                      v-model="tempProduct.origin_price"
                    />
                  </div>
                  <div class="col-md-6 mb-6">
                    <label for="price">售價</label>
                    <input
                      type="number"
                      class="form-control"
                      id="price"
                      placeholder="請輸入售價"
                      v-model="tempProduct.price"
                    />
                  </div>
                </div>
                <div class="col-md-12 mb-12 underLine"></div>
                <div class="col-md-12 mb-12">
                  <label>產品描述</label>
                  <textarea
                    class="form-control"
                    name=""
                    id="description"
                    cols="30"
                    rows="3"
                    placeholder="請輸入產品描述"
                    v-model="tempProduct.description"
                  ></textarea>
                </div>
                <div class="col-md-12 mb-12">
                  <label>說明內容</label>
                  <textarea
                    class="form-control"
                    name=""
                    id="content"
                    cols="30"
                    rows="3"
                    placeholder="請輸入說明內容"
                    v-model="tempProduct.content"
                  ></textarea>
                </div>
                <div class="col-md-6 mb-6">
                  <label></label>
                  <div class="custom-control custom-checkbox">
                    <input
                      type="checkbox"
                      class="custom-control-input"
                      id="isEnabled"
                      v-model="tempProduct.is_enabled"
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
            <button
              type="button"
              class="btn btn-primary"
              @click="updateProduct"
            >
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
      products: [],
      tempProduct: {},
      isNew: false,
      isLoading: false,
      status: {
        fileUploading: false,
      },
      pagination: {},
    };
  },
  components: {
    Pagination,
  },
  methods: {
    getProducts(page = 1) {
      const vm = this;

      if (vm.pagination.total_pages == vm.pagination.current_page) {
        page = 1;
      }
      const api = `/api/${process.env.CUSTOMPATH}/products?page=${page}`;

      vm.isLoading = true;

      this.$http.get(api).then((response) => {
        vm.products = response.data.products;
        vm.isLoading = false;
        vm.pagination = response.data.pagination;
      });
    },
    openModal(isNew, item) {
      if (isNew) {
        this.tempProduct = {};
        this.isNew = true;
      } else {
        this.tempProduct = Object.assign({}, item);
        this.isNew = false;
      }
      $("#productModal").modal("show");
    },
    deleteModal(item) {
      this.tempProduct = Object.assign({}, item);
      $("#deleteModal").modal("show");
    },
    refreshProducts() {
      const api = `/api/${process.env.CUSTOMPATH}/products`;
      const vm = this;
      $("#productModal").modal("hide");
      $("#deleteModal").modal("hide");
      vm.getProducts();
    },
    updateProduct() {
      let api = `/api/${process.env.CUSTOMPATH}/admin/product`;
      let httpMethod = "post";
      const vm = this;

      if (!vm.isNew) {
        api = `/api/${process.env.CUSTOMPATH}/admin/product/${vm.tempProduct.id}`;
        httpMethod = "put";
      }

      this.$http[httpMethod](api, { data: vm.tempProduct }).then((response) => {
        if (response.data.success) {
          vm.refreshProducts();
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
    deleteProduct() {
      let vm = this;
      let api = `/api/${process.env.CUSTOMPATH}/admin/product/${vm.tempProduct.id}`;

      this.$http.delete(api, { data: vm.tempProduct }).then((response) => {
        if (response.data.success) {
          vm.refreshProducts();
        } else {
          vm.$bus.$emit("message:push", response.data.message, "danger");
        }
      });
    },
    uploadFile() {
      console.log(this);
      const uploadedFile = this.$refs.files.files[0];
      const vm = this;
      vm.status.fileUploading = true;
      const formData = new FormData();
      formData.append("file-to-upload", uploadedFile);
      const url = `/api/${process.env.CUSTOMPATH}/admin/upload`;

      this.$http
        .post(url, formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((response) => {
          vm.status.fileUploading = false;
          if (response.data.success) {
            vm.$set(vm.tempProduct, "imageUrl", response.data.imageUrl);
          } else {
            vm.$bus.$emit("message:push", response.data.message, "danger");
          }
        });
    },
  },
  created() {
    this.getProducts();
  },
};
</script>
<style>
.closeButton {
  color: black;
  background-color: white;
  border-color: black;
}

.closeButton:hover {
  color: white;
  background-color: #b5b6b8;
  border-color: #565858;
}
label {
  padding-top: 10px;
}

.title {
  color: white;
  background-color: rgb(80, 76, 76);
  font-weight: bolder;
}

.modalBody {
  display: inline-flex;
}

.inputFlex {
  display: flex;
}
.underLine {
  border-top: 1px solid #dee2e6;
  border-bottom-right-radius: calc(0.3rem - 1px);
  border-bottom-left-radius: calc(0.3rem - 1px);
  display: inline-flex;
  margin-top: 30px;
}
#checkEnabled {
  padding-top: 0px !important;
}
</style>