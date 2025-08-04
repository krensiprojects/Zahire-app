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
  newProduct: ProductDto = new ProductDto(
  '', '', '', '', null, null, null, null, null
);
  searchTerm: string = '';
  // array with the products to be displayed
  filteredProducts: ProductDto[] = [];

  constructor(private http: HttpClient) { }

  onSearch() {
    // TODO: complete the code
    if (this.searchTerm == '') {
      return;
    }

    // prepare the DTO with req parameters
    let dto = new ProductSearchReqDto();
    dto.searchCriterion = this.searchTerm;

    // send request to the RESTful service
    let obs: Observable<ProductDto[]> = this.http.post<ProductDto[]>(
      'http://localhost:8080/catalogue/searchInCatalogue',
      dto
    );
    obs.subscribe((pa) => {
      this.filteredProducts = pa;
    });
  }

  addNewProduct() {
  const product = this.newProduct;

  this.http
    .post('http://localhost:8080/catalogue/insertProduct', product)
    .subscribe({
      next: () => {
        alert('Product inserted successfully!');
        // Clear the form after success
        this.newProduct = new ProductDto('', '', '', '', null, null, null, null, null);
        // Refresh search results
        if (this.searchTerm.trim() === '') {
          // Option 1: Use the shortDescription as a new temporary search
          const tempSearch = new ProductSearchReqDto();
          tempSearch.searchCriterion = product.shortDescription;
          this.http.post<ProductDto[]>(
            'http://localhost:8080/catalogue/searchInCatalogue',
            tempSearch
          ).subscribe(results => {
            this.filteredProducts = results;
          });
        } else {
          // Option 2: Search with current search term
          this.onSearch();
        }
      },
      error: (err) => alert('Failed to insert product'),
    });
}

  removeProduct(productId: string) {
    console.log("Erasing:", productId);
      let dto = new RemoveProductDto(productId, this.searchTerm); // 1️⃣ Prepare the DTO to send
  // invoke the RESTful service to erase a product
  this.http.post<ProductDto[]>('http://localhost:8080/catalogue/removeProduct', dto)
    .subscribe({
       // and update the displayed list of files
      next: (updatedProducts) => {
        this.filteredProducts = updatedProducts; // 2️⃣ Replace old list with new list
        alert("Product deleted successfully!");   // ✅ Notify user (optional)
      },
      error: (err) => {
        console.error("Failed to delete product", err); // ❌ Error handling
        alert("Failed to delete product.");
      }
    });
  }
}
