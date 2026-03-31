import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

//TODO: remove with all usages, test-only purpose
@Injectable({
  providedIn: 'root',
})
export class TestService {

  constructor(
    private http: HttpClient
  ) {}

  callBackend(): Observable<any> {
    let payload = {
      firstName: "First name",
      lastName: "Last name",
      email: "example1@mail.com",
      password: "test_password",
      phoneNumber: "+1234567890",
      birthDate: "1995-08-21"
    };
    return this.http.post('http://localhost:8080/api/users/register', payload);
  }
}
