<template>
  <div>
    <nav
      id="sidebarMenu"
      class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse"
    >
      <div class="position-sticky pt-3">
        <ul class="nav flex-column">
          <h6
            class="
              sidebar-heading
              d-flex
              justify-content-between
              align-items-center
              px-3
              mt-4
              mb-1
              text-muted
            "
          >
            <span>管理員</span>
          </h6>
          <li class="nav-item">
            <router-link to="products" class="nav-link" active-class="active"
              ><i class="fas fa-clipboard-list"></i> 產品列表</router-link
            >
          </li>
          <li class="nav-item">
            <router-link to="orders" class="nav-link" active-class="active"
              ><i class="fal fa-list-alt"></i> 訂單列表</router-link
            >
          </li>
          <li class="nav-item">
            <router-link to="coupons" class="nav-link" active-class="active"
              ><i class="far fa-money-bill"></i> 優惠券</router-link
            >
          </li>
          <h6
            class="
              sidebar-heading
              d-flex
              justify-content-between
              align-items-center
              px-3
              mt-4
              mb-1
              text-muted
            "
          >
            <span>模擬功能</span>
          </h6>
          <li class="nav-item">
            <router-link
              to="/customer_orders"
              class="nav-link"
              active-class="active"
              ><i class="fas fa-cart-plus"></i> 模擬訂單</router-link
            >
          </li>
        </ul>
      </div>
    </nav>
  </div>
</template>
<script>
export default {
  data() {
    return {
      isActive: false,
    };
  },
};
</script>
<style>
</style>