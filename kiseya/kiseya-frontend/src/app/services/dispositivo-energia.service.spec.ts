import { TestBed } from '@angular/core/testing';

import { DispositivoEnergiaService } from './dispositivo-energia.service';

describe('DispositivoEnergiaService', () => {
  let service: DispositivoEnergiaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DispositivoEnergiaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
