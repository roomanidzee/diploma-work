<template>
    <div>
      <b-field class="file">
        <b-upload v-model="file">
            <a class="button is-primary">
                <b-icon icon="upload"></b-icon>
                <span>Загрузить файл</span>
            </a>
        </b-upload>
        <span class="file-name" v-if="file">
            {{ file.name }}
        </span>
      </b-field>
      <Card/>
    </div>
</template>

<script lang="ts">
import Card from '@/components/common/card/card';

export default {
  components: {
    Card,
  },
};
</script>
