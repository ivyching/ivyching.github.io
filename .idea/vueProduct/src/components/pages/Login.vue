<template>
  <div class="form-signin">
    <form @submit.prevent="signin">
      <h1 class="h3 mb-3 fw-normal">請先登入</h1>

      <div class="form-floating">
        <label for="floatingInput">帳號</label>
        <input
          type="email"
          class="form-control"
          id="floatingInput"
          placeholder="name@example.com"
          v-model="user.username"
        />
      </div>
      <div class="form-floating">
        <label for="floatingPassword">密碼</label>
        <input
          type="password"
          class="form-control"
          id="floatingPassword"
          placeholder="Password"
          v-model="user.password"
        />
      </div>

      <div class="checkbox mb-3">
        <label> <input type="checkbox" value="remember-me" /> 記住我 </label>
      </div>
      <button class="w-100 btn btn-lg btn-primary" type="submit">登入</button>
      <p class="mt-5 mb-3 text-muted">&copy; 2017–2021</p>
    </form>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    signin() {
      const api = `/admin/signin`;
      const vm = this;
      this.$http.post(api, vm.user).then((response) => {
        if (response.data.success) {
          vm.$router.push("/admin/products");
        }
      });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
html,
body {
  height: 100%;
}

body {
  display: flex;
  align-items: center;
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #f5f5f5;
}

.form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
}

.form-signin .checkbox {
  font-weight: 400;
}

.form-signin .form-floating:focus-within {
  z-index: 2;
}

.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>
