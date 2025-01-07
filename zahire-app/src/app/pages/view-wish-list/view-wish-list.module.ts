import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ViewWishListPageRoutingModule } from './view-wish-list-routing.module';

import { ViewWishListPage } from './view-wish-list.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ViewWishListPageRoutingModule
  ],
  declarations: [ViewWishListPage]
})
export class ViewWishListPageModule {}
