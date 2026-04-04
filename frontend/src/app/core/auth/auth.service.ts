import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Router } from '@angular/router';

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  phoneNumber: string;
  birthDate: string;
}

export interface JwtResponse {
  jwt: string;
  expiresAt: string;
}

export interface AuthUser {
  email: string;
  isAuthenticated: boolean;
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api';
  private tokenKey = 'auth_token';
  private expirationKey = 'auth_expiration';

  private authUser$ = new BehaviorSubject<AuthUser | null>(this.loadAuthState());

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  register(request: RegisterRequest): Observable<any> {
    return this.http.post(`${this.apiUrl}/users/register`, request);
  }

  login(request: LoginRequest): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(`${this.apiUrl}/auth/login`, request).pipe(
      tap(response => {
        this.saveToken(response.jwt, response.expiresAt);
        this.updateAuthUser({
          email: request.email,
          isAuthenticated: true,
        });
      })
    );
  }

  logout(): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/auth/logout`, {}).pipe(
      tap(() => {
        this.clearToken();
        this.updateAuthUser(null);
        this.router.navigate(['/login']);
      })
    );
  }

  getAuthUser(): Observable<AuthUser | null> {
    return this.authUser$.asObservable();
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    if (!token) return false;

    const expiration = localStorage.getItem(this.expirationKey);
    if (!expiration) return false;

    return new Date(expiration) > new Date();
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  private saveToken(token: string, expiresAt: string): void {
    localStorage.setItem(this.tokenKey, token);
    localStorage.setItem(this.expirationKey, expiresAt);
  }

  private clearToken(): void {
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem(this.expirationKey);
  }

  private updateAuthUser(user: AuthUser | null): void {
    this.authUser$.next(user);
  }

  private loadAuthState(): AuthUser | null {
    const token = this.getToken();
    if (!token) return null;

    if (!this.isAuthenticated()) {
      this.clearToken();
      return null;
    }

    return {
      email: '',
      isAuthenticated: true,
    };
  }
}
