<template>
    <div>

        <section class="hero is-medium is-primary is-bold">

            <div class="hero-body">
                <search-info/>
            </div>

            <b-message title="Результаты поиска" type="is-info" has-icon>
              По введённому запросу нет никаких результатов
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
