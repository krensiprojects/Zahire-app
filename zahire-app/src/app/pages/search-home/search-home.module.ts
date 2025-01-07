import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { SearchHomePageRoutingModule } from './search-home-routing.module';

import { SearchHomePage } from './search-home.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    SearchHomePageRoutingModule
  ],
  declarations: [SearchHomePage]
})
export class SearchHomePageModule {}
