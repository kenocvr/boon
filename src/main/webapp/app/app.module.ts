import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { InventoryNoElasticSharedModule } from 'app/shared/shared.module';
import { InventoryNoElasticCoreModule } from 'app/core/core.module';
import { InventoryNoElasticAppRoutingModule } from './app-routing.module';
import { InventoryNoElasticHomeModule } from './home/home.module';
import { InventoryNoElasticEntityModule } from './entities/entity.module';
import { ZXingScannerModule } from '@zxing/ngx-scanner';

// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    InventoryNoElasticSharedModule,
    InventoryNoElasticCoreModule,
    InventoryNoElasticHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    InventoryNoElasticEntityModule,
    InventoryNoElasticAppRoutingModule,
    ZXingScannerModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent]
})
export class InventoryNoElasticAppModule {}
