import { Component, Vue } from 'vue-property-decorator';
import WithRender from './card.html';

@WithRender
@Component
export default class Card extends Vue { }
