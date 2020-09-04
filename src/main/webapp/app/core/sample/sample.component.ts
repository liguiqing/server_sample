import { Vue } from 'vue-property-decorator';
import axios from 'axios';
import { ISample } from './sample.model';
import buildPaginationQueryOpts from '@/shared/sort/sorts';
import JhiDataUtils from '@/shared/data/data-utils.service';
import Component, { mixins } from 'vue-class-component';
import AlertMixin from '@/shared/alert/alert.mixin';

const baseApiUrl = 'api/samples';
@Component
export default class Sample extends mixins(JhiDataUtils, AlertMixin) {
  public samples: ISample[] = [];
  public propOrder = 'id';
  public reverse = true;

  mounted(): void {
    this.load();
  }

  public load(): void {
    this.retrieve({
      page: 0,
      size: 1000,
      sort: this.sort(),
    }).then(res => {
      this.samples = res.data;
    });
  }

  public sort(): Array<any> {
    const result = [this.propOrder + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.propOrder !== 'id') {
      result.push('id');
    }
    return result;
  }

  public retrieve(paginationQuery?: any): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}?${buildPaginationQueryOpts(paginationQuery)}`)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
