export class ProductDto {
  idProduct : string= "";
  code: string;
  description: string;
  shortDescription: string;
  price: number;
  minimumQty: number | null;
  stockQty: number;
  packageSize: number | null;
  packageWeight: number;

  constructor(
    idProduct: string,
    code: string,
    description: string,
    shortDescription: string,
    price: number,
    minimumQty: number | null,
    stockQty: number,
    packageSize: number | null,
    packageWeight: number
  ) {
    this.idProduct=idProduct;
    this.code = code;
    this.description = description;
    this.shortDescription = shortDescription;
    this.price = price;
    this.minimumQty = minimumQty;
    this.stockQty = stockQty;
    this.packageSize = packageSize;
    this.packageWeight = packageWeight;
  }
}
