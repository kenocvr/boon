import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { InventoryNoElasticTestModule } from '../../../test.module';
import { ItemComponent } from 'app/entities/item/item.component';
import { ItemService } from 'app/entities/item/item.service';
import { Item } from 'app/shared/model/item.model';

describe('Component Tests', () => {
  describe('Item Management Component', () => {
    let comp: ItemComponent;
    let fixture: ComponentFixture<ItemComponent>;
    let service: ItemService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InventoryNoElasticTestModule],
        declarations: [ItemComponent]
      })
        .overrideTemplate(ItemComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ItemComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ItemService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Item(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.items && comp.items[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
