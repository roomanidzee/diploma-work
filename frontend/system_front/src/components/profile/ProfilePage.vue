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
                  <article class="tile is-child notification is-primary">
                    <p class="title">Работники</p>
                    <p class="subtitle">Посмотреть список работников</p>
                  </article>
                </div>
              </div>
              <div class="tile is-parent is-vertical">
                <article class="tile is-child notification is-primary">
                  <p class="title">Заявки</p>
                  <p class="subtitle">Распределить те заявки, которые у вас есть</p>
                </article>
              </div>
            </div>
            <div class="tile is-parent">
              <article class="tile is-child is-8 notification is-danger">
                <p class="title">Файлы</p>
                <p class="subtitle">Поработать с имеющимися файлами</p>
              </article>
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
