<template>
  <requests-page/>
</template>

<script>

import Requests from '@/components/requests/Requests.vue';

export default {
  name: 'requests',
  components: {
    'requests-page': Requests,
  },
};
</script>
