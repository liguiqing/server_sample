import { mixins } from 'vue-class-component';
import Component from 'vue-class-component';
import { Inject } from 'vue-property-decorator';
import AlertMixin from '@/shared/alert/alert.mixin';

import JhiDataUtils from '@/shared/data/data-utils.service';
import LoginService from '@/account/login.service';
import AccountService from '@/account/account.service';
import { IAccount } from '@/account/account.model';

@Component
export default class Home extends mixins(JhiDataUtils, AlertMixin) {
  mounted(): void {
    console.log('Helo baby!');
  }
}
