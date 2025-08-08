import { ProductSearchReqDto } from './../../dto/product-search-dto';
import { Component } from '@angular/core';
import { ProductDto } from '../../dto/product-dto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RemoveProductDto } from '../../dto/remove-product-dto';

@Component({
  standalone: false,
  selector: 'app-search-in-catalogue',
  templateUrl: './search-in-catalogue.page.html',
  styleUrls: ['./search-in-catalogue.page.scss'],
})
export class SearchInCataloguePage {
  // Base API path
  private apiBase = 'http://localhost:8080/catalogue';

  // Form model
  newProduct: ProductDto = new ProductDto('', '', '', '', null, null, null, null, null);

  // Search + list
  searchTerm: string = '';
  filteredProducts: ProductDto[] = [];

  // Edit state
  isEditMode: boolean = false;

  constructor(private http: HttpClient) { }

  // ========================
  // SEARCH
  // ========================
  onSearch() {
    if (this.searchTerm.trim() === '') {
      return;
    }

    let dto = new ProductSearchReqDto();
    dto.searchCriterion = this.searchTerm;

    let obs: Observable<ProductDto[]> = this.http.post<ProductDto[]>(
      `${this.apiBase}/searchInCatalogue`,
      dto
    );
    obs.subscribe((pa) => {
      this.filteredProducts = pa;
    });
  }

  // ========================
  // ADD NEW PRODUCT
  // ========================
  addNewProduct() {
    const product = this.newProduct;

    this.http
      .post(`${this.apiBase}/insertProduct`, product)
      .subscribe({
        next: () => {
          alert('Product inserted successfully!');
          this.resetForm();
          this.refreshAfterChange(product.shortDescription);
        },
        error: () => alert('Failed to insert product'),
      });
  }

  // ========================
  // START EDIT MODE
  // ========================
  startEdit(product: ProductDto) {
    this.isEditMode = true;

    // Populate form with selected product
    (this.newProduct as any).productId    = (product as any).productId ?? null;
    this.newProduct.code                  = product.code ?? '';
    this.newProduct.description           = product.description ?? '';
    this.newProduct.shortDescription      = product.shortDescription ?? '';
    this.newProduct.price                 = Number(product.price ?? 0);
    this.newProduct.minimumQty            = Number(product.minimumQty ?? 0);
    this.newProduct.stockQty              = Number(product.stockQty ?? 0);
    this.newProduct.packageSize           = Number(product.packageSize ?? 0);
    this.newProduct.packageWeight         = Number(product.packageWeight ?? 0);
  }

  // ========================
  // CONFIRM EDIT
  // ========================
  confirmEdit() {
    const productId = (this.newProduct as any).productId;
    if (!productId) {
      alert('Missing productId for update.');
      return;
    }

    const dto = {
      productId: productId,
      code: this.newProduct.code,
      description: this.newProduct.description,
      shortDescription: this.newProduct.shortDescription,
      price: Number(this.newProduct.price),
      minimumQty: Number(this.newProduct.minimumQty),
      stockQty: Number(this.newProduct.stockQty),
      packageSize: Number(this.newProduct.packageSize),
      packageWeight: Number(this.newProduct.packageWeight),
    };

    this.http.put(`${this.apiBase}/updateProduct`, dto).subscribe({
      next: () => {
        alert('Product updated!');
        this.refreshAfterChange(this.searchTerm);
        this.exitEditMode();
      },
      error: (err) => {
        console.error(err);
        alert('Failed to update product.');
      }
    });
  }

  // ========================
  // CANCEL EDIT
  // ========================
  cancelEdit() {
    this.exitEditMode();
  }

  // ========================
  // REMOVE PRODUCT
  // ========================
  removeProduct(productId: string) {
    console.log("Erasing:", productId);
    let dto = new RemoveProductDto(productId, this.searchTerm);

    this.http.post<ProductDto[]>(`${this.apiBase}/removeProduct`, dto)
      .subscribe({
        next: (updatedProducts) => {
          this.filteredProducts = updatedProducts;
          alert("Product deleted successfully!");
        },
        error: (err) => {
          console.error("Failed to delete product", err);
          alert("Failed to delete product.");
        }
      });
  }

  // ========================
  // HELPERS
  // ========================
  private resetForm() {
    this.newProduct = new ProductDto('', '', '', '', null, null, null, null, null);
    (this.newProduct as any).productId = null;
  }

  private exitEditMode() {
    this.isEditMode = false;
    this.resetForm();
  }

  private refreshAfterChange(fallbackSearch: string) {
    if (this.searchTerm.trim()) {
      this.onSearch();
    } else if (fallbackSearch?.trim()) {
      const tempSearch = new ProductSearchReqDto();
      tempSearch.searchCriterion = fallbackSearch;
      this.http.post<ProductDto[]>(`${this.apiBase}/searchInCatalogue`, tempSearch)
        .subscribe(results => {
          this.filteredProducts = results;
        });
    }
  }
}
