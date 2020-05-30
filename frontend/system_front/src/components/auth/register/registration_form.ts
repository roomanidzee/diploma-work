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
    const resp = serviceAppClient.post(
      '/api/security/register',
      {
        username: this.username,
        password: this.password,
      },
    );
    resp.then((respValue: any) => {
      if (respValue.status_code !== 200) {
        return this.$router.push({ name: 'register_page' });
      }
      return this.$router.push({ name: 'profile' });
    });

    return this.$router.push({ name: 'register_page' });
  }
}
