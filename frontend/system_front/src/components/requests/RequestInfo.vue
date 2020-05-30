<template>
  <section class="hero is-link">
    <div class="hero-body">
      <div class="container">
        <div class="card">

          <div class="card-content">
            <div class="content">Здесь будет информация о заявке</div>
          </div>

          <footer class="card-footer">
            <a
              href="#"
              class="card-footer-item"
              @click="isAboutActive = !isAboutActive"
            >Подробнее</a>
            <a
              href="#"
              class="card-footer-item"
              @click="isDeclineActive = !isDeclineActive"
            >Отозвать</a>
          </footer>
        </div>

        <b-message
          title="Подробнее"
          :active.sync="isAboutActive"
          aria-close-label="Закрыть"
        >Здесь подробности о заявке</b-message>

        <b-message
          title="Отозвать"
          :active.sync="isDeclineActive"
          aria-close-label="Закрыть"
        >Здесь отзыв заявки</b-message>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
export default {
  data() {
    return {
      isAboutActive: true,
      isDeclineActive: true,
    };
  },
};
</script>
