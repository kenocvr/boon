export interface IItem {
  id?: number;
  itemName?: string;
  imageContentType?: string;
  image?: any;
  tag?: string;
  qrCodeContentType?: string;
  qrCode?: any;
  barCodeContentType?: string;
  barCode?: any;
  locationId?: number;
}

export class Item implements IItem {
  constructor(
    public id?: number,
    public itemName?: string,
    public imageContentType?: string,
    public image?: any,
    public tag?: string,
    public qrCodeContentType?: string,
    public qrCode?: any,
    public barCodeContentType?: string,
    public barCode?: any,
    public locationId?: number
  ) {}
}
