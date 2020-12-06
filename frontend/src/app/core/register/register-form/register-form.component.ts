import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OktaAuthService } from '@okta/okta-angular';
import { RegisterModel } from '../register-service/register.model';
import { RegisterService } from '../register-service/register.service';

@Component({
  selector: 'app-login',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.scss']
})
export class RegisterComponent implements OnInit {
  form: FormGroup;
  formGroup: FormGroup;
  registerModel = new RegisterModel();
  feedback: any = {};
  registered = false;

  constructor(
    private fb: FormBuilder,
    private authService: OktaAuthService,
    private registerService: RegisterService,
    private router: Router
  ) { }

  ngOnInit() {
    this.form = this.fb.group({
      username: ['', Validators.compose([Validators.required, Validators.email])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(8)])],
      lastname: ['', Validators.compose([Validators.required, Validators.minLength(2)])],
      firstname: ['', Validators.compose([Validators.required, Validators.minLength(2)])],
      phoneNumber: ['', Validators.compose([Validators.required, Validators.minLength(10)])],
      activationCode: ['', Validators.compose([Validators.required, Validators.minLength(6)])],
    });
  }

  defaultValues() {
    this.form.patchValue({
      username: 'newlight77@gmail.com',
      password: 'password',
      lastname: 'to',
      firstname: 'kong',
      phoneNumber: '0123456789',
      activationCode: '123456'
    });
  }

  register() {
    console.log('Registerting');
    if (!this.form.valid) {
      console.log('Form not valid. Please check that fields are correctly filled in');
      return;
    }

    console.log('Form valid: , form.valid');

    this.registerModel.username = this.form.get('username').value.toString();
    this.registerModel.password = this.form.get('password').value.toString();
    this.registerModel.firstname = this.form.get('firstname').value.toString();
    this.registerModel.lastname = this.form.get('lastname').value.toString();
    this.registerModel.phoneNumber = this.form.get('phoneNumber').value.toString();
    this.registerModel.activationCode = this.form.get('activationCode').value.toString();

    this.registerService.register(this.registerModel)
      .subscribe(
        (state) => {
          this.feedback = { type: 'success', message: 'An account has been create successful on OKTA platform!' };
          this.registered = state.oktaRegistered;

          // this.router.navigate(['/login']);
        },
        (error) => {
          this.feedback = { type: 'error', message: 'signup has failed!' };
          console.log('caught an error while registering!');
        }
      );
  }

  activate() {
    console.log('send activatino code');
    this.registerService.activate(this.registerModel.username, this.registerModel.activationCode)
          .subscribe(
            (state) => {
              this.feedback = { type: 'success', message: 'The phone number has been validated successful !' };
              this.router.navigate(['/login']);
            },
            (error) => {
              this.feedback = { type: 'error', message: 'Code activation has failed!' };
              console.log('caught an error while activating by code!');
            }
          );
  }
}
