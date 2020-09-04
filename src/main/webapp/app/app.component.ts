import Vue from 'vue';
import Component from 'vue-class-component';
import Navbar from '@/core/navbar/navbar.vue';
import LoginForm from '@/account/login-form/login-form.vue';
import { Inject } from 'vue-property-decorator';
import TranslationService from './locale/translation.service';

@Component({
  components: {
    navbar: Navbar,
    'login-form': LoginForm,
  },
})
export default class App extends Vue {
  @Inject('translationService') private translationService: () => TranslationService;
  public created(): void {
    this.translationService().refreshTranslation('zh-cn');
  }

  public closeLoginDialog(done): void {
    done();
    this.$store.commit('hideLoginDialog');
  }
}
