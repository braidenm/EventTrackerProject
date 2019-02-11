import { Activity } from './../models/activity';
import { catchError, tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ActivityService {

  private baseUrl = 'http://localhost:8083/api/';
  private url = this.baseUrl + 'activities';
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  index() {
    return this.http.get<Activity[]>(this.url)
    .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('KABOOM broken at activity service index');
          })
     );
  }

  addActivity(data, tripId) {

    return this.http.post<Activity>(this.url + '/' + tripId, data, this.httpOptions)
      .pipe(
         catchError(this.handleError)
       );

  }

  handleError(error: any) {
    console.error('Something Broke');
    return throwError(error.json().error || 'Server Error');
  }

  updateActivity(activity: Activity) {
    return this.http.put<Activity>(this.url + '/' + activity.id, activity)
      .pipe(
         catchError(this.handleError)
       );
  }

  deleteActivity(id: number) {
    return this.http.delete<Activity>(this.url + '/' + id, this.httpOptions)
    .pipe(catchError(this.handleError));

  }

  show(id) {
    return this.http.get<Activity>(this.url + '/' + id)
    .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('KABOOM broken at activity service show');
          })
     );
  }

  search(kword: string) {
    return this.http.get<Activity[]>(this.url + '/search/' + kword)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('error in search Activity');
        })
      );
  }
  searchByTripName(kword: string) {
    return this.http.get<Activity[]>(this.url + '/search/trip/' + kword)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('error in search Activity by Trip');
        })
      );
  }
  searchByCategoryId(catId: number) {
    return this.http.get<Activity[]>(this.url + '/search/category/' + catId)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('error in search Activity by Category');
        })
      );
  }

}
