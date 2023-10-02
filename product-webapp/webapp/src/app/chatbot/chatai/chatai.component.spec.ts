import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChataiComponent } from './chatai.component';

describe('ChataiComponent', () => {
  let component: ChataiComponent;
  let fixture: ComponentFixture<ChataiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChataiComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChataiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
