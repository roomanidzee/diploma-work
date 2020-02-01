import { Component, Vue } from 'vue-property-decorator';
import WithRender from './header.html';

@WithRender
@Component
export default class Header extends Vue { }
