<template>
    <div>

        <section class="hero is-success">
            <Header />
        </section>

        <div class="tile is-ancestor">
          <div class="tile is-vertical is-8">
            <div class="tile">
              <div class = "tile is-parent">
                <div class="tile is-parent is-vertical">
                  <router-link to="/people">
                    <article class="tile is-child notification is-primary">
                      <p class="title">Люди</p>
                      <p class="subtitle">Найти тех людей, которые нужны вам</p>
                    </article>
                  </router-link>
                </div>
              </div>
              <div class="tile is-parent is-vertical">
                <router-link to="/requests">
                  <article class="tile is-child notification is-primary">
                    <p class="title">Заявки</p>
                    <p class="subtitle">Информация о ваших заявках</p>
                  </article>
                </router-link>
              </div>
            </div>
            <div class="tile is-parent">
                <router-link to="/files">
                  <article class="tile is-child is-8 notification is-danger">
                    <p class="title">Файлы</p>
                    <p class="subtitle">Информация о ваших файлах</p>
                  </article>
                </router-link>
            </div>
          </div>
        </div>

    </div>
</template>

<script lang="ts">
import Header from '@/components/common/main/header';

export default {
  components: {
    Header,
  },
};
</script>
