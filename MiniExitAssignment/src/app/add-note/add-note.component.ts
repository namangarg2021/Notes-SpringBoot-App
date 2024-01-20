import { Component } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { NotesService } from '../services/notes.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-add-note',
  templateUrl: './add-note.component.html',
  styleUrls: ['./add-note.component.css'],
})
export class AddNoteComponent {
  Note = new FormGroup({
    note: new FormControl('', [
      Validators.required,
      this.maxWordsValidator(500),
      this.specialCharactersValidator(),
    ]),
  });

  get note() {
    return this.Note.get('note');
  }

  constructor(private notesService: NotesService, private router: Router) {}

  ngOnInit(): void {
    if (this.notesService.getToken() == null) this.router.navigate(['/']);
  }
  r: any;
  error = false;

  maxWordsValidator(maxWords: number): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const words = control.value ? control.value.trim().split(/\s+/) : [];
      return words.length > maxWords ? { maxWords: true } : null;
    };
  }
  specialCharactersValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const specialCharsPattern = /^[a-zA-Z0-9@;&*+-,\-\s]+$/;
      const containsSpecialChars = !specialCharsPattern.test(control.value);
      return containsSpecialChars ? { invalidSpecialChars: true } : null;
    };
  }

  add() {
    let note: any = this.Note.value;
    note.userId = localStorage.getItem('id');
    this.notesService.addNote(note).subscribe(async (result) => {
      this.r = result;
      console.log(result);
      // if (result == null) {
      //   this.error = true;
      // } else {
      await Swal.fire({
        title: "<h5 style='color:green;font-size:35px'>Note Added!</h5>",
        timer: 1000,
        showConfirmButton: false,
        icon: 'success',
      });

      this.router.navigate(['home']);
      //}
    });
  }
}
