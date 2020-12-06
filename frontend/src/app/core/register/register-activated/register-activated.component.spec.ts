import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterActivatedComponent } from './register-activated.component';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { RegisterService } from '../register-service/register.service';

describe('RegisterActivatedComponent', () => {
  let component: RegisterActivatedComponent;
  let fixture: ComponentFixture<RegisterActivatedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterActivatedComponent ],
      imports: [FormsModule, HttpClientTestingModule, RouterTestingModule],
      providers: [RegisterService],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterActivatedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
