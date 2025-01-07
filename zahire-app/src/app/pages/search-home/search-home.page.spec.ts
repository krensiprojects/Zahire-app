import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SearchHomePage } from './search-home.page';

describe('SearchHomePage', () => {
  let component: SearchHomePage;
  let fixture: ComponentFixture<SearchHomePage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchHomePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
