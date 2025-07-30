import { Component } from '@angular/core';


@Component({
  standalone: false,
  selector: 'app-search-in-catalogue',
  templateUrl: './search-in-catalogue.page.html',
  styleUrls: ['./search-in-catalogue.page.scss'],
})
export class SearchInCataloguePage {
  searchTerm: string = '';
  allProducts = [
    { name: 'Grain Flour', price: '160 Lek', stock: 2 },
    { name: 'Corn Flour', price: '100 Lek', stock: 6 },
    { name: 'Oat Flour', price: '200 Lek', stock: 9 },
  ];
  filteredProducts = [...this.allProducts];

  onSearch() {
    const term = this.searchTerm.toLowerCase();
    this.filteredProducts = this.allProducts.filter(product =>
      product.name.toLowerCase().includes(term)
    );
  }
}