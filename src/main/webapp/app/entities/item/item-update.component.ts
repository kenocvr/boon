import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IItem, Item } from 'app/shared/model/item.model';
import { ItemService } from './item.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { ILocation } from 'app/shared/model/location.model';
import { LocationService } from 'app/entities/location/location.service';

@Component({
  selector: 'jhi-item-update',
  templateUrl: './item-update.component.html'
})
export class ItemUpdateComponent implements OnInit {
  isSaving = false;
  locations: ILocation[] = [];

  editForm = this.fb.group({
    id: [],
    itemName: [],
    image: [],
    imageContentType: [],
    tag: [],
    qrCode: [],
    qrCodeContentType: [],
    barCode: [],
    barCodeContentType: [],
    locationId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected itemService: ItemService,
    protected locationService: LocationService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ item }) => {
      this.updateForm(item);

      this.locationService.query().subscribe((res: HttpResponse<ILocation[]>) => (this.locations = res.body || []));
    });
  }

  updateForm(item: IItem): void {
    this.editForm.patchValue({
      id: item.id,
      itemName: item.itemName,
      image: item.image,
      imageContentType: item.imageContentType,
      tag: item.tag,
      qrCode: item.qrCode,
      qrCodeContentType: item.qrCodeContentType,
      barCode: item.barCode,
      barCodeContentType: item.barCodeContentType,
      locationId: item.locationId
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('inventoryNoElasticApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string): void {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const item = this.createFromForm();
    if (item.id !== undefined) {
      this.subscribeToSaveResponse(this.itemService.update(item));
    } else {
      this.subscribeToSaveResponse(this.itemService.create(item));
    }
  }

  private createFromForm(): IItem {
    return {
      ...new Item(),
      id: this.editForm.get(['id'])!.value,
      itemName: this.editForm.get(['itemName'])!.value,
      imageContentType: this.editForm.get(['imageContentType'])!.value,
      image: this.editForm.get(['image'])!.value,
      tag: this.editForm.get(['tag'])!.value,
      qrCodeContentType: this.editForm.get(['qrCodeContentType'])!.value,
      qrCode: this.editForm.get(['qrCode'])!.value,
      barCodeContentType: this.editForm.get(['barCodeContentType'])!.value,
      barCode: this.editForm.get(['barCode'])!.value,
      locationId: this.editForm.get(['locationId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IItem>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ILocation): any {
    return item.id;
  }
}
