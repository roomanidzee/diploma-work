<template>
    <div>

        <section class="hero is-fullheight is-primary is-bold">

            <div class="hero-body">
                <search-info/>
            </div>

        </section>

    </div>
</template>

<script lang="ts">
import SearchElem from '@/components/common/search_elem/search';

export default {
  components: {
    'search-info': SearchElem,
  },
};

</script>
