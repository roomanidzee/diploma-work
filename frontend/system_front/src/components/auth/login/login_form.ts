import { Component, Vue, Prop } from 'vue-property-decorator';
import { Route } from 'vue-router';
import WithRender from '../../common/form/user_form.html';
import { serviceAppClient } from '@/components/common/services/clients';

@WithRender
@Component
export default class LoginForm extends Vue {
  @Prop()
  private username: String = '';

  @Prop()
  private password: String = '';

  public performAction(): Promise<Route> {
    const resp = serviceAppClient.post(
      '/api/security/login',
      {
        username: this.username,
        password: this.password,
      },
      {
        'Content-Type': 'application/json',
      },
    );

    const loginResult = resp.then((respValue: any) => {
      if (respValue.status_code !== 200) {
        return this.$router.push({ name: 'login_page' });
      }
      sessionStorage.setItem('token', respValue.data.token);
      sessionStorage.setItem('roles', respValue.data.roles.join());
      return this.$router.push({ name: 'profile' });
    });

    return loginResult;
  }
}
