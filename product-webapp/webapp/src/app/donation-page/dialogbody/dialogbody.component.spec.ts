import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogbodyComponent } from './dialogbody.component';

describe('DialogbodyComponent', () => {
  let component: DialogbodyComponent;
  let fixture: ComponentFixture<DialogbodyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogbodyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DialogbodyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
