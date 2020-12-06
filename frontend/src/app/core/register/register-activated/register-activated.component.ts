import { Component, OnInit } from '@angular/core';
import { RegisterService } from '../register-service/register.service';
import { Router, ActivatedRoute } from '@angular/router';
import { RegisterStateModel } from '../register-service/register.model';

@Component({
  selector: 'app-register-activated',
  templateUrl: './register-activated.component.html',
  styleUrls: ['./register-activated.component.scss']
})
export class RegisterActivatedComponent implements OnInit {

  feedback: any;
  emailValidationFeedback: any;
  phoneValidationFeedback: any;

  constructor(private registerService: RegisterService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {

    const username = this.route.snapshot.paramMap.get('username');
    // this.route.paramMap.subscribe(params => {
    //   this.username = params.get('username');
    //  });

    this.registerService.state(username)
      .subscribe(
        (state: RegisterStateModel) => {
          console.log('state = ', state);
          if (state.emailValidated) {
            this.emailValidationFeedback = { type: 'success', message: 'Your email has been successful validated!' };
          }
          if (state.activatedByCode) {
            this.phoneValidationFeedback = { type: 'success', message: 'Your phone number has been successful validated!' };
          }
        },
        (error) => {
          this.feedback = { type: 'failed', message: 'the activation has failed!' };
          console.log('caught an error for the activation!');
        }
      );
  }

}
