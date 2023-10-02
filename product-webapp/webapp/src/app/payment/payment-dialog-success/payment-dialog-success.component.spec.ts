import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentDialogSuccessComponent } from './payment-dialog-success.component';

describe('PaymentDialogSuccessComponent', () => {
  let component: PaymentDialogSuccessComponent;
  let fixture: ComponentFixture<PaymentDialogSuccessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaymentDialogSuccessComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PaymentDialogSuccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
