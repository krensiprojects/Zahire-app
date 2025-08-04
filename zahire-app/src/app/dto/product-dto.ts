export class ProductDto {
  productId: string = '';
  code: string;
  description: string;
  shortDescription: string;
  price: number | null;
  minimumQty: number | null;
  stockQty: number | null;
  packageSize: number | null;
  packageWeight: number | null;

  constructor(
    productId: string,
    code: string,
    description: string,
    shortDescription: string,
    price: number | null,
    minimumQty: number | null,
    stockQty: number | null,
    packageSize: number | null,
    packageWeight: number | null
  ) {
    this.productId = productId;
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
