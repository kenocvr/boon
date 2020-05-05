import { IItem } from 'app/shared/model/item.model';

export interface ILocation {
  id?: number;
  locationName?: string;
  imageContentType?: string;
  image?: any;
  tag?: string;
  qrCodeContentType?: string;
  qrCode?: any;
  barCodeContentType?: string;
  barCode?: any;
  items?: IItem[];
}

export class Location implements ILocation {
  constructor(
    public id?: number,
    public locationName?: string,
    public imageContentType?: string,
    public image?: any,
    public tag?: string,
    public qrCodeContentType?: string,
    public qrCode?: any,
    public barCodeContentType?: string,
    public barCode?: any,
    public items?: IItem[]
  ) {}
}
