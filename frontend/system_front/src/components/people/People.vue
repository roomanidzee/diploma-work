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
import PeopleCard from '@/components/common/people/people_card';
import { Component, Vue } from 'vue-property-decorator';

interface SearchResult {
    facilityID: string,
    profileID: string
}

@Component({
  components: {
    'card': PeopleCard,
  },
})
export default class PeopleInfoComponent extends Vue {

  private peopleData: SearchResult[] | null = null;

  public mounted(){

    this.peopleData = JSON.parse(sessionStorage.getItem('searchResult'));

  }

}
</script>
