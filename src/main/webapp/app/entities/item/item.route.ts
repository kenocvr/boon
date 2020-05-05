import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IItem, Item } from 'app/shared/model/item.model';
import { ItemService } from './item.service';
import { ItemComponent } from './item.component';
import { ItemDetailComponent } from './item-detail.component';
import { ItemUpdateComponent } from './item-update.component';

@Injectable({ providedIn: 'root' })
export class ItemResolve implements Resolve<IItem> {
  constructor(private service: ItemService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IItem> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((item: HttpResponse<Item>) => {
          if (item.body) {
            return of(item.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Item());
  }
}

export const itemRoute: Routes = [
  {
    path: '',
    component: ItemComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'inventoryNoElasticApp.item.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ItemDetailComponent,
    resolve: {
      item: ItemResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'inventoryNoElasticApp.item.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ItemUpdateComponent,
    resolve: {
      item: ItemResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'inventoryNoElasticApp.item.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ItemUpdateComponent,
    resolve: {
      item: ItemResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'inventoryNoElasticApp.item.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
