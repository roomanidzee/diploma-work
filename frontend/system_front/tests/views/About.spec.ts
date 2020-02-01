import { mount } from '@vue/test-utils';
import About from '../../src/views/About.vue';

describe('About Component', () => {
    test('renders correctly', () => {
        const wrapper = mount(About);
        expect(wrapper.isVueInstance()).toBeTruthy();
    });
});