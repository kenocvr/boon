import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILocation } from 'app/shared/model/location.model';
import { LocationService } from './location.service';
import { LocationDeleteDialogComponent } from './location-delete-dialog.component';

@Component({
  selector: 'jhi-location',
  templateUrl: './location.component.html'
})
export class LocationComponent implements OnInit, OnDestroy {
  locations?: ILocation[];
  eventSubscriber?: Subscription;

  constructor(
    protected locationService: LocationService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.locationService.query().subscribe((res: HttpResponse<ILocation[]>) => (this.locations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLocations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILocation): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInLocations(): void {
    this.eventSubscriber = this.eventManager.subscribe('locationListModification', () => this.loadAll());
  }

  delete(location: ILocation): void {
    const modalRef = this.modalService.open(LocationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.location = location;
  }
}
