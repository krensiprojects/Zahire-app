import { ProductSearchReqDto } from './../../dto/product-search-dto';
import { Component } from '@angular/core';
import { ProductDto } from '../../dto/product-dto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  standalone: false,
  selector: 'app-search-in-catalogue',
  templateUrl: './search-in-catalogue.page.html',
  styleUrls: ['./search-in-catalogue.page.scss'],
})
export class SearchInCataloguePage {
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
    // hardcode the product to be added
    let p = new ProductDto(
      "",
      'GR123', // code
      'Grain Flour', // description
      'Finely milled', // shortDescription
      160, // price
      1, // minimumQty
      100, // stockQty
      2, // packageSize
      1.5 // packageWeight
    );

    // send to the insertProduct REST service
    this.http
      .post('http://localhost:8080/catalogue/insertProduct', p)
      .subscribe({
        next: () => alert('Product inserted successfully!'),
        error: (err) => alert('Failed to insert product'),
      });
  }

  removeProduct(idProduct: string) {
    console.log("Removing: ", idProduct);

    // invoke the RESTful service
    // and update the displayedlist of products
    // TODO: do it!
  }
}
