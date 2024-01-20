import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root',
})
export class NotesService {
  constructor(private http: HttpClient) {}

  url = 'http://localhost:9090/';

  //calling the server to generate token
  // generateToken(credentials: {}) {
  //   return this.http.post(this.url + 'api/generate-token', credentials);
  // }

  loginUser(token: any) {
    localStorage.setItem('token', token);
    return true;
  }
  isLoggedIn() {
    let token = localStorage.getItem('token');
    console.log(token);
    if (token == undefined || token == null || token == '') {
      return false;
    }
    return true;
  }

  removeToken() {
    localStorage.removeItem('token');
    return true;
  }

  getToken() {
    return localStorage.getItem('token');
  }

  login(data: any) {
    return this.http.post(this.url + 'login/user', data, {
      responseType: 'text',
    });
  }

  postregister(data: any) {
    return this.http.post(this.url + 'register/user', data, {
      responseType: 'text',
    });
  }

  getAllNotes(id: any) {
    console.log(this.url + 'notes/id/' + id);
    return this.http.get(this.url + 'notes/id/' + id);
  }
  addNote(note: any) {
    return this.http.post(this.url + 'notes/', note, {
      responseType: 'text',
    });
  }
  deleteNote(id: any) {
    return this.http.delete(this.url + 'notes/' + id, {
      responseType: 'text',
    });
  }
}
