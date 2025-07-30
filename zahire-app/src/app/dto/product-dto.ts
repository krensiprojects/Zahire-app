export class ProductDto {
  code: string;
  description: string;
  shortDescription: string;
  price: number;
  minimumQty: number | null;
  stockQty: number;
  packageSize: number | null;
  packageWeight: number;

  constructor(
    code: string,
    description: string,
    shortDescription: string,
    price: number,
    minimumQty: number | null,
    stockQty: number,
    packageSize: number | null,
    packageWeight: number
  ) {
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
