import { LoginModel, IpAddressModel } from './login.model';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { environment } from '../../../../environments/environment';

const headers = new HttpHeaders().set('Accept', 'application/json');
const loginApi = environment.env.apiUrl + '/logins';

@Injectable()
export class LoginService {
  loginList: LoginModel[] = [];

  constructor(private http: HttpClient) { }

  load(): Observable<LoginModel[]> {
    return this.http.get<LoginModel[]>(loginApi, { headers });
  }

  save(entity: LoginModel): Observable<LoginModel> {
    let response: Observable<LoginModel>;
    const url = `${loginApi}`;
    response = this.http.post<LoginModel>(url, entity, { headers });
    return response;
  }

  public ipAddress(): Promise<IpAddressModel> {

    // TODO : change provider because not meant for commercial use
    const url = 'https://json.geoiplookup.io/api'; // non-commercial
    // url = 'http://ipstack.com'; // free with account
    // url = 'https://api.geoiplookup.net'; // non-commercial
    // url = 'https://www.cloudflare.com/cdn-cgi/trace';
    // url = 'http://gd.geobytes.com/GetCityDetails?'; // free
    // url = 'https://ipapi.co/json/'; // free good
    // url = 'https://api.ipify.org?format=json'; // simple
    // url = 'https://ipinfo.io/json'; // simple
    // url = 'http://ipinfo.io/json?callback=JSON_CALLBACK';
    // url = 'http://maps.googleapis.com/maps/api/geocode/json?sensor=true';

    return this.http.get<IpAddressModel>(url).toPromise();
  }
}
