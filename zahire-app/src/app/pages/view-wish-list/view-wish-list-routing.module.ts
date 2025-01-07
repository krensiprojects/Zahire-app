import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ViewWishListPage } from './view-wish-list.page';

const routes: Routes = [
  {
    path: '',
    component: ViewWishListPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ViewWishListPageRoutingModule {}
