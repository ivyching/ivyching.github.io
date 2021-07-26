<template>
  <div>
    <NavBar />
    <Alert />
    <div class="container-fluid">
      <div class="row">
        <SideBar></SideBar>
        <div
          class="col-md-9 ms-sm-auto col-lg-10 px-md-4"
          style="margin-left: 16%"
        >
          <router-view></router-view>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBar from "./NavBar";
import SideBar from "./SideBar";
import Alert from "./AlertMessage";

export default {
  components: {
    NavBar,
    SideBar,
    Alert,
  },
};
</script>