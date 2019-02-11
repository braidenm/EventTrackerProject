import { Category } from './../models/category';
import { catchError, tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { throwError } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private baseUrl = 'http://localhost:8083/api/';
  private url = this.baseUrl + 'categories';
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  index() {
    return this.http.get<Category[]>(this.url)
    .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('KABOOM broken at Category service index');
          })
     );
  }

  addCategory(data) {

    return this.http.post<Category>(this.url, data, this.httpOptions)
      .pipe(
         catchError(this.handleError)
       );

  }

  handleError(error: any) {
    console.error('Something Broke');
    return throwError(error.json().error || 'Server Error');
  }

  updateCategory(category: Category) {
    return this.http.put<Category>(this.url + '/' + category.id, category)
      .pipe(
         catchError(this.handleError)
       );
  }

  deleteCategory(id: number) {
    return this.http.delete<Category>(this.url + '/' + id, this.httpOptions)
    .pipe(catchError(this.handleError));

  }

  show(id) {
    return this.http.get<Category>(this.url + '/' + id)
    .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('KABOOM broken at Category service show');
          })
     );
  }

}
