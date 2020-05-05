import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InventoryNoElasticSharedModule } from 'app/shared/shared.module';
import { ItemComponent } from './item.component';
import { ItemDetailComponent } from './item-detail.component';
import { ItemUpdateComponent } from './item-update.component';
import { ItemDeleteDialogComponent } from './item-delete-dialog.component';
import { itemRoute } from './item.route';
import { ZXingScannerModule } from '@zxing/ngx-scanner';
import { QRCodeModule } from 'angular2-qrcode';

@NgModule({
  imports: [InventoryNoElasticSharedModule, RouterModule.forChild(itemRoute), ZXingScannerModule, QRCodeModule],
  declarations: [ItemComponent, ItemDetailComponent, ItemUpdateComponent, ItemDeleteDialogComponent],
  entryComponents: [ItemDeleteDialogComponent]
})
export class InventoryNoElasticItemModule {}
