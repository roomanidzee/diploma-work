<template>
  <div class="home">
    <main-page/>
  </div>
</template>

<script>

import MainPage from '@/components/main/MainPage.vue';

export default {
  name: 'home',
  components: {
    'main-page': MainPage,
  },
};
</script>
