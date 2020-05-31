import { Component, Vue, Prop } from 'vue-property-decorator';
import { Route } from 'vue-router';
import WithRender from './search.html';
import { serviceAppClient } from '@/components/common/services/clients';

interface SearchResult {
    facilityID: string,
    profileID: string
}

@WithRender
@Component
export default class SearchElem extends Vue {
    @Prop()
    private searchQuery: string = '';

    public performAction(): Promise<Route> {
      const roles = sessionStorage.getItem('roles');

      if (roles === undefined) {
        throw new Error('unspecfied input search query');
      }

      if (this.searchQuery.split(',').length === 0) {
        throw new Error('wrong input query');
      }

      const headers = {
        'Content-Type': 'application/json',
        Authorization: sessionStorage.getItem('token'),
      };

      const resp = serviceAppClient.get(`/api/facility/show_students?speciality=${this.searchQuery}`, headers);

      const result = resp.then((respValue: any) => {
        const data: SearchResult[] = respValue.data.foreach((elem: any) => {
          return <SearchResult> {
            facilityID: elem.facility_id,
            profileID: elem.profile_id,
          };
        });

        sessionStorage.setItem('searchResult', JSON.stringify(data));

        return this.$router.push({ name: 'people' });
      });

      return result;
    }
}
