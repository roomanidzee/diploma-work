<template>
  <div>
    <section class="hero has-background-info is-fullheight">
      <div class="hero-body">
        <div class="container has-text-centered">
          <login-form />
          <h2 class="subtitle has-text-white">У вас ещё нет аккаунта?</h2>
          <div class="buttons">
            <router-link to="/register" class="button" tag="span">
              <b-button type="is-warning">Зарегистрироваться</b-button>
            </router-link>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang = "ts">
import LoginForm from '@/components/auth/login/login_form';

export default {
  components: {
    'login-form': LoginForm,
  },
};
</script>
