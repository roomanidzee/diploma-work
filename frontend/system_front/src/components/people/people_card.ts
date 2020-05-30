import { Component, Vue, Prop } from 'vue-property-decorator';
import WithRender from './card.html';

@WithRender
@Component
export default class PeopleCard extends Vue {

    @Prop()
    facilityID: string = '';

    @Prop()
    profileID: string = '';

}