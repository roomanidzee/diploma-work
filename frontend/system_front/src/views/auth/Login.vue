<template>
    <div>
        <login-page/>
    </div>
</template>

<script lang = "ts">

import LoginPage from '@/components/auth/login/LoginPage.vue';

export default {
  name: 'login_page',
  components: {
    'login-page': LoginPage,
  },
};

</script>
