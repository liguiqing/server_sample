// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.common with an alias.
import Vue from 'vue';
import ElementUI from 'element-ui';
import EventBus from 'vue-bus-ts';
import App from './app.vue';
import Vue2Filters from 'vue2-filters';
import router from './router';
import * as config from './shared/config/config';

import 'element-ui/lib/theme-chalk/display.css';
import '../content/scss/element-rewrite.scss';

import AlertService from '@/shared/alert/alert.service';
import TranslationService from '@/locale/translation.service';
import ValidateService from '@/shared/validation/validate.service';
import LoginService from './account/login.service';
import AccountService from './account/account.service';
import { formatISO } from 'date-fns';
/* tslint:enable */
Date.prototype.toJSON = function () {
  return formatISO(this);
};
Vue.config.productionTip = false;
config.initVueApp(Vue);
Vue.use(Vue2Filters);
Vue.use(ElementUI);

const i18n = config.initI18N(Vue);
const store = config.initVueXStore(Vue);

const translationService = new TranslationService(store, i18n);
const accountService = new AccountService(store, translationService, router);

router.beforeEach((to, from, next) => {
  if (!to.matched.length) {
    next('/not-found');
  }

  if (to.meta && to.meta.authorities && to.meta.authorities.length > 0) {
    accountService.hasAnyAuthorityAndCheckAuth(to.meta.authorities).then(value => {
      if (!value) {
        sessionStorage.setItem('requested-url', to.fullPath);
        next('/forbidden');
      } else {
        next();
      }
    });
  } else {
    // no authorities, so just proceed
    next();
  }
});

const bus = new EventBus.Bus();
Vue.use(EventBus);
/* tslint:disable */
new Vue({
  el: '#app',
  components: { App },
  template: '<App/>',
  router,
  bus,
  provide: {
    loginService: () => new LoginService(),
    alertService: () => new AlertService(store),
    translationService: () => translationService,
    validateService: () => new ValidateService(),
    accountService: () => accountService,
  },
  i18n,
  store,
});
