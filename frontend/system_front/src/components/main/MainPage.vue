<template>
  <div>
    <section class="hero is-success is-fullheight">
      <header-elem />
      <div class="hero-body">
        <div class="container has-text-centered">
          <h1 class="title">Studeeper</h1>
          <h2 class="subtitle">Сервис для поиска места в жизни</h2>
        </div>
      </div>
    </section>
    <section class="hero is-info is-medium">
      <div class="hero-body">
        <div class="container has-text-centered">
          <h1 class="title">
            Начать использовать сервис
            <br />
            <br />
          </h1>
          <h2 class="subtitle has-text-centered">
            <div class="buttons">
              <router-link to="/login" class="button">
                <b-button type="is-danger" expanded>Войти</b-button>
              </router-link>
            </div>
          </h2>
        </div>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import Header from '@/components/common/main/header';

export default {
  components: {
    'header-elem': Header,
  },
};
</script>
