import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DispositivoEnergiaComponent } from './dispositivo-energia.component';

describe('DispositivoEnergiaComponent', () => {
  let component: DispositivoEnergiaComponent;
  let fixture: ComponentFixture<DispositivoEnergiaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DispositivoEnergiaComponent]
    });
    fixture = TestBed.createComponent(DispositivoEnergiaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
