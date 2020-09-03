import Vue from 'vue';
import Component from 'vue-class-component';
import Navbar from '@/core/navbar/navbar.vue';
import LoginForm from '@/account/login-form/login-form.vue';

@Component({
  components: {
    navbar: Navbar,
    'login-form': LoginForm,
  },
})
export default class App extends Vue {
  public created(): void {}

  public closeLoginDialog(done): void {
    done();
    this.$store.commit('hideLoginDialog');
  }
}
