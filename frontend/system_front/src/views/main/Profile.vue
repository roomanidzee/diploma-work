<template>
  <div class="profile">
    <profile-page/>
  </div>
</template>

<script>

import ProfilePage from '@/components/profile/ProfilePage.vue';

export default {
  name: 'profile',
  components: {
    'profile-page': ProfilePage,
  },
};
</script>
