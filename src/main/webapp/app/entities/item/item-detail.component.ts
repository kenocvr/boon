import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IItem } from 'app/shared/model/item.model';

@Component({
  selector: 'jhi-item-detail',
  templateUrl: './item-detail.component.html',
  styles: ['@media print {.no-print{display: none;} .col-8  {display: none;}  .printme  { display: block; } }'],
  encapsulation: ViewEncapsulation.None
})
export class ItemDetailComponent implements OnInit {
  item: IItem | null = null;

  value?: string = 'http://localhost:8080/item/' + this.item?.id + '/view';

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ item }) => (this.item = item));
    this.value = 'https://inventory-no-elastic.herokuapp.com/item/' + this.item?.id + '/view';
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }

  printPage(): void {
    window.print();
  }
}
