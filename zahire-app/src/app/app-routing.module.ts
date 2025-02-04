import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'folder/inbox',
    pathMatch: 'full'
  },
  {
    path: 'folder/:id',
    loadChildren: () => import('./folder/folder.module').then( m => m.FolderPageModule)
  },
  {
    path: 'login',
    loadChildren: () => import('./pages/login/login.module').then( m => m.LoginPageModule)
  },
  {
    path: 'search-home',
    loadChildren: () => import('./pages/search-home/search-home.module').then( m => m.SearchHomePageModule)
  },
  {
    path: 'view-cart',
    loadChildren: () => import('./pages/view-cart/view-cart.module').then( m => m.ViewCartPageModule)
  },
  {
    path: 'view-wish-list',
    loadChildren: () => import('./pages/view-wish-list/view-wish-list.module').then( m => m.ViewWishListPageModule)
  },
  {
    path: 'view-details',
    loadChildren: () => import('./pages/view-details/view-details.module').then( m => m.ViewDetailsPageModule)
  },
  {
    path: 'checkout',
    loadChildren: () => import('./pages/checkout/checkout.module').then( m => m.CheckoutPageModule)
  },

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
