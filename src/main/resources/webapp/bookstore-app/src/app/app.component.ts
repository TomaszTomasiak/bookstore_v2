import {Component, OnInit} from '@angular/core';
import {Book} from './book/book';
import {BookService} from './book/book-service.service';
import {Router} from "@angular/router";
import {SplitioService} from "./splitio.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent implements OnInit {
  title = 'BookStore';
  books: Book[] | undefined;
  private splitIoService: any;

  constructor(private router: Router, private bookService: BookService) {
  }

  getBooks() {
    this.bookService.getBooks().subscribe(data => {
      this.books = data;
    });
  }

  addBook(): void {
    this.router.navigate(['add-book'])
      .then((e: boolean) => {
        if (e) {
          // @ts-ignore
          console.log("Navigation is successful!");
        } else {
          // @ts-ignore
          console.log("Navigation has failed!");
        }
      });
  };

  deleteBook(book: { id: number; }) { this.bookService.deleteBook(book.id); this.router.navigate(['list-books']); }

  deleteAllowed() { return this.splitIoService.isTreatmentOn('allow-delete'); }

  ngOnInit(): void {
    this.router.events.subscribe(value => {
      this.getBooks();
    });
    this.splitIoService.initSdk(); // <-- Add This Line
  }

}
