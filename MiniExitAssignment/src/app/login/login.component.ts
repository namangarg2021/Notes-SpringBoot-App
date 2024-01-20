import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NotesService } from '../services/notes.service';
import Swal from 'sweetalert2';
import { JwtHelperService } from '@auth0/angular-jwt';
// import Swal from 'sweetalert2';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  Login = new FormGroup({
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });
  error = false;

  get email() {
    return this.Login.get('email');
  }
  get password() {
    return this.Login.get('password');
  }

  constructor(private notesService: NotesService, private router: Router) {}

  ngOnInit(): void {}
  r: any;

  login() {
    this.notesService.login(this.Login.value).subscribe(async (token) => {
      this.r = JSON.parse(token);
      console.log(this.r.token);
      if (this.r.token === 'Bad Credentials') {
        await Swal.fire({
          title:
            "<h5 style='color:red;font-size:20px'>Wrong Credentials!!!</h5>",
          icon: 'error',
        });
        this.error = true;
      } else {
        const helper = new JwtHelperService();

        const decodedToken = helper.decodeToken(token);
        console.log(decodedToken);

        this.notesService.loginUser(this.r.token);

        await Swal.fire({
          title:
            "<h5 style='color:green;font-size:25px'>Logging In...Please Wait !</h5>",
          timer: 1000,
          showConfirmButton: false,
          icon: 'success',
        });
        localStorage.setItem('id', decodedToken.id);
        this.router.navigate(['home']);
      }
    });
  }
}
