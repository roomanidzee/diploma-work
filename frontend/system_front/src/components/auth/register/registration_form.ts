import { Component, Vue, Prop } from 'vue-property-decorator';
import { Route } from 'vue-router';
import WithRender from '../../common/form/user_form.html';
import { serviceAppClient } from '@/components/common/services/clients';

@WithRender
@Component
export default class RegistrationForm extends Vue {
    @Prop()
    private username: String = '';

    @Prop()
    private password: String = '';

    public performAction(): Promise<Route> {
      /* TODO: fix soon
        const resp = serviceAppClient.post(
          '/api/security/login',
          {
          username: this.username,
          password: this.password,
        },
      );
      resp.then(resp_value => {
        if (resp_value.status_code !== 200) {
          return this.$router.push({ name: 'profile' });
        }
      });
      */

      return this.$router.push({ name: 'profile' });
    }
}
