<template>
    <div>
        <register-page/>
    </div>
</template>

<script lang = "ts">

import RegisterPage from '@/components/auth/register/RegisterPage.vue';

export default {
  name: 'register_page',
  components: {
    'register-page': RegisterPage,
  },
};

</script>
