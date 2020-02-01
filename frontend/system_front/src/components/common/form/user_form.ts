import { Component, Vue } from 'vue-property-decorator';
import WithRender from './user_form.html';

@WithRender
@Component
export default class UserForm extends Vue { }
