import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnersignupComponent } from './ownersignup.component';

describe('OwnersignupComponent', () => {
  let component: OwnersignupComponent;
  let fixture: ComponentFixture<OwnersignupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OwnersignupComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OwnersignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
