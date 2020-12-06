import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { RegisterModel, RegisterStateModel } from './register.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private httpClient: HttpClient) { }

  register(model: RegisterModel): Observable<RegisterStateModel> {
    return this.httpClient.post<RegisterStateModel>(environment.env.apiUrl + '/signup', model);
  }

  activate(username: string, code: string): Observable<RegisterStateModel> {
    // const params = new HttpParams().set('username', username).set('code', code);
    const body = {
      username,
      code: Number(code)
    };
    return this.httpClient.post<RegisterStateModel>(environment.env.apiUrl + '/signup/activate', body);
  }

  state(username: string): Observable<RegisterStateModel> {
    // const params = new HttpParams().set('username', username)
    return this.httpClient.get<RegisterStateModel>(`${environment.env.apiUrl}/signup/state/${encodeURI(username)}`);
  }

}
