import {
  HttpInterceptor,
  HttpEvent,
  HttpRequest,
  HttpHandler,
  HTTP_INTERCEPTORS,
} from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import { NotesService } from './notes.service';
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private notesService: NotesService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const token = this.notesService.getToken();
    if (token == null) {
      return next.handle(req);
    }

    const auth = 'Bearer ' + this.notesService.getToken();

    let tokenizedReq = req;
    tokenizedReq = req.clone({
      headers: req.headers.set('Authorization', auth),
    });

    return next.handle(tokenizedReq);
  }
}
export const authInterceptorProviders = [
  {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true,
  },
];
