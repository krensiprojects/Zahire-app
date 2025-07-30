import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { FolderPageRoutingModule } from './search-in-catalogue-routing.module';

import { SearchInCataloguePage } from './search-in-catalogue.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    FolderPageRoutingModule
  ],
  declarations: [SearchInCataloguePage]
})
export class SearchInCataloguePageModule  {}
