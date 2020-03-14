<template>
    <div>

        <section class="hero is-medium is-primary is-bold">

            <div class="hero-body">
                <search-info/>
            </div>

            <b-message title="Результаты поиска" type="is-info" has-icon>
              *в этом блоке будет количество результатов*
            </b-message>

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
