import { Router } from '@angular/router';

import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { HttpErrorResponse, HttpClient } from '@angular/common/http';
import { LoginService } from 'src/app/core/login/login-service/login.service';

import { Injectable } from '@angular/core';
import { LoginModel, IpAddressModel } from 'src/app/core/login/login-service/login.model';

@Injectable()
export class LoginHandler {

    constructor(
        private http: HttpClient,
        private loginService: LoginService,
        private router: Router) { }

    public async onAuthSuccess(username: string) {
        // await this.createLogin(username, true).then((data) => this.saveLogin(data));
        const loginModel = await this.createLogin(username, true);
        await this.saveLogin(loginModel);
    }

    public async onAuthFailure(username: string) {
        // await this.createLogin(username, false).then((data) => this.saveLogin(data));
        const loginModel = await this.createLogin(username, false);
        await this.saveLogin(loginModel);
    }

    private async saveLogin(loginModel: LoginModel) {

        console.log('saving a login attempt: ', loginModel);
        await this.loginService.save(loginModel).toPromise();
    }

    private async createLogin(username: string, isSuccess: boolean): Promise<LoginModel> {
        const loginModel = new LoginModel();
        loginModel.username = username;
        loginModel.loginDate = new Date();
        loginModel.device = '';
        loginModel.success = isSuccess;

        loginModel.ipAddress = '11111';
        loginModel.city = 'paris';
        loginModel.region = 'france';

        const ipAddressModel = await this.loginService.ipAddress();

        console.log(`ip: ${ipAddressModel}`);
        loginModel.ipAddress = ipAddressModel.ip;
        loginModel.city = ipAddressModel.city;
        loginModel.region = ipAddressModel.region;

        console.log('saving login: ', loginModel);
        return loginModel;
    }

    private handleError(error: HttpErrorResponse) {
        if (error.error instanceof ErrorEvent) {
            console.error('An error occurred:', error.error.message);
        } else {
            console.error(
                `returned code ${error.status}, ` +
                `body was: ${error.error}`);
        }
        return throwError('Something bad happened; please try again later.');
    }

}
