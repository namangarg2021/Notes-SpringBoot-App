import { Component } from '@angular/core';
import { NotesService } from '../services/notes.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent {
  Registration = new FormGroup({
    email: new FormControl('', [Validators.email, Validators.required]),
    password: new FormControl('', [Validators.required]),
    confirmPassword: new FormControl('', [Validators.required]),
  });
  error = false;

  get email() {
    return this.Registration.get('email');
  }
  get password() {
    return this.Registration.get('password');
  }
  get confirmPassword() {
    return this.Registration.get('confirmPassword');
  }

  constructor(private noteService: NotesService, private router: Router) {}

  ngOnInit(): void {}
  msg: any;
  register() {
    if (this.password?.value != this.confirmPassword?.value) {
      this.error = true;
      this.msg = 'Password and Confirm Password do not match!!!';
      return;
    }

    this.noteService
      .postregister(this.Registration.value)
      .subscribe(async (result: any) => {
        if (result === 'User Added Successfully') {
          await Swal.fire({
            title: `<h5 style='color:green;font-size:30px'>${result!}</h5>`,
            timer: 1000,
            showConfirmButton: false,
            icon: 'success',
          });
          this.router.navigate(['login']);
        } else {
          this.error = true;
          Swal.fire({
            title:
              "<h5 style='color:red;font-size:30px'>User Already Exists!!!!</h5>",
            showConfirmButton: true,
            icon: 'error',
          });
          this.msg = result;
        }
      });
  }
}
