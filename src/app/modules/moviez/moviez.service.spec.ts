import { TestBed } from '@angular/core/testing';

import { MoviezService } from './moviez.service';

describe('MoviezService', () => {
  let service: MoviezService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MoviezService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
