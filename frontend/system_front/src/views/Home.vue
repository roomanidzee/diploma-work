<template>
  <div class="home">
    <MainPage/>
  </div>
</template>

<script>
// @ is an alias to /src
import MainPage from '@/components/main/MainPage.vue';

export default {
  name: 'home',
  components: {
    MainPage,
  },
};
</script>
