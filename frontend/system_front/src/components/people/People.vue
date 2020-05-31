<template>
    <div>

      <div v-for="people in peopleData" v-bind:key="people">
        <card
          v-bind:facilityID="people.facilityID"
          v-bind:profileID="people.profileID"
        />
    </div>

    </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import PeopleCard from '@/components/people/people_card';

interface SearchResult {
    facilityID: string,
    profileID: string
}

@Component({
  components: {
    card: PeopleCard,
  },
})
export default class PeopleInfoComponent extends Vue {
  private peopleData: SearchResult[] | null = null;

  public mounted() {
    this.peopleData = JSON.parse(sessionStorage.getItem('searchResult'));
  }
}
</script>
