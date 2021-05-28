import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Book} from "./book";

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private url = 'http://localhost:8080/books/';

  constructor(private http: HttpClient) {
  }

  getBooks(): Observable<any> {
    return this.http.get(`${this.url}`);
  }

  addBook(book: Book): Observable<any> {
    return this.http.post(`${this.url}`, book);
  }

  deleteBook(id: number): Observable<any> {
    return this.http.delete(`${this.url}/${id}`, {responseType: 'text'});
  }
}
