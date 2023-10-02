import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyCampaignComponent } from './my-campaign.component';

describe('MyCampaignComponent', () => {
  let component: MyCampaignComponent;
  let fixture: ComponentFixture<MyCampaignComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyCampaignComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MyCampaignComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
