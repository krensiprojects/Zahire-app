import { ProductSearchReqDto } from './../../dto/product-search-dto';
import { Component } from '@angular/core';
import { ProductDto } from '../../dto/product-dto';
import {HttpClient} from '@angular/common/http'
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

  constructor(private http: HttpClient){}

  onSearch() {
    // TODO: complete the code
    if (this.searchTerm == '') {
      return;
    }

    // prepare the DTO with req parameters
    let dto = new ProductSearchReqDto();
    dto.searchCriterion = this.searchTerm;

    // send request to the RESTful service
    let obs : Observable<ProductDto[]> = this.http.post<ProductDto[]>
    ("http://localhost:8080/catalogue/searchInCatalogue",
      dto
    );
    obs.subscribe(pa => {
      this.filteredProducts = pa;
    });
  }
}
