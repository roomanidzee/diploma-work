import { Component, Vue } from 'vue-property-decorator';
import WithRender from './search.html';

@WithRender
@Component
export default class SearchElem extends Vue {}
