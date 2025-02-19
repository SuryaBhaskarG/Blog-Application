import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {LocalStorageService} from 'ngx-webstorage';
import { RegisterPayload } from './auth/register-payload';
import { JwtAutResponse } from './auth/jwt-aut-response';
import { LoginPayload } from './auth/login-payload';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private url = 'http://localhost:8080/api/auth/';

  constructor(private httpClient: HttpClient, private localStoraqeService: LocalStorageService) {
  }

  register(registerPayload: RegisterPayload): Observable<any> {
    return this.httpClient.post(this.url + 'signup', registerPayload);
  }

  login(loginPayload: LoginPayload): Observable<boolean> {
    return this.httpClient.post<JwtAutResponse>(this.url + 'login', loginPayload).pipe(map(data => {
      this.localStoraqeService.store('authenticationToken', data.authenticationToken);
      this.localStoraqeService.store('username', data.username);
      return true;
    }));
  }

  isAuthenticated(): boolean {
    return this.localStoraqeService.retrieve('username') != null;
  }

  logout() {
    this.localStoraqeService.clear('authenticationToken');
    this.localStoraqeService.clear('username');
  }
}