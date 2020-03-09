<template>
  <people-page/>
</template>

<script>

import People from '@/components/people/People.vue';

export default {
  name: 'people',
  components: {
    'people-page': People,
  },
};
</script>
