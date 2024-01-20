import { Component } from '@angular/core';
import { NotesService } from '../services/notes.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  constructor(
    private notesService: NotesService,
    private router: Router,
    private route: ActivatedRoute
  ) {}
  allNotes: any = [];
  id: any = 0;
  ngOnInit(): void {
    if (this.notesService.getToken() == null) this.router.navigate(['/']);
    this.id = localStorage.getItem('id');
    this.notesService.getAllNotes(this.id).subscribe((result) => {
      this.allNotes = result;
    });
  }
  delete(id: any) {
    this.notesService.deleteNote(id).subscribe(async (result) => {
      await Swal.fire({
        title: "<h5 style='color:success;font-size:25px'>Note Deleted!</h5>",
        timer: 1000,
        showConfirmButton: false,
        icon: 'success',
      });
      window.location.reload();
    });
  }
  logout() {
    this.notesService.removeToken();
    localStorage.removeItem('id');
    this.router.navigate(['/']);
  }
}
