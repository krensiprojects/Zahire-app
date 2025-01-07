import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ViewWishListPage } from './view-wish-list.page';

describe('ViewWishListPage', () => {
  let component: ViewWishListPage;
  let fixture: ComponentFixture<ViewWishListPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewWishListPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
