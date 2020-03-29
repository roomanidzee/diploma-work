<template>
    <requests-info-page/>
</template>

<script>

import RequestInfo from '@/components/requests/RequestInfo.vue';

export default {
    name: 'request-info',
    components: {
        'requests-info-page': RequestInfo
    }
}
</script>
