import { Component, Vue, Prop } from 'vue-property-decorator';
import WithRender from './card.html';
import { analysisAppClient } from '@/components/common/services/clients';

@WithRender
@Component
export default class FilesCard extends Vue {
    @Prop({ default: false })
    isFacility: boolean = false;

    @Prop()
    fileName: string = '';

    @Prop()
    fileType: string = '';

    @Prop()
    fileID: string = '';

    public mounted() {
      this.isFacility = sessionStorage.getItem('roles')!!.includes('FACILITY');
    }

    public sendForAnalyse(fileID: string) {
      const headers = {
        'Content-Type': 'application/json',
        Authorization: sessionStorage.getItem('token'),
      };

      const resp = analysisAppClient.get(`/api/analyse/${fileID}/check_model`, headers);

      resp.then((respValue: any) => {
        if (respValue.status_code !== 400) {
          const analysisResp = analysisAppClient.get(`/api/analyse/${fileID}/predict`, headers);

          analysisResp.then((respAnValue: any) => this.$router.push({ name: 'profile' }));
        }
        const analysisBaseResp = analysisAppClient.get(`/api/analyse/${fileID}/launch_base_analysis`, headers);
        analysisBaseResp.then((respAnBaseValue: any) => this.$router.push({ name: 'profile' }));
      });
    }
}
