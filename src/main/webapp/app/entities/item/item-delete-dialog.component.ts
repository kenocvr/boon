import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IItem } from 'app/shared/model/item.model';
import { ItemService } from './item.service';

@Component({
  templateUrl: './item-delete-dialog.component.html'
})
export class ItemDeleteDialogComponent {
  item?: IItem;

  constructor(protected itemService: ItemService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.itemService.delete(id).subscribe(() => {
      this.eventManager.broadcast('itemListModification');
      this.activeModal.close();
    });
  }
}
