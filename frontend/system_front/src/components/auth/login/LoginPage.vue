<template>
  <div>
    <section class="hero has-background-info is-fullheight">
      <div class="hero-body">
        <div class="container has-text-centered">
          <UserForm />
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
import UserForm from '@/components/common/form/user_form';

export default {
  components: {
    UserForm,
  },
};
</script>
