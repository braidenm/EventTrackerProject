import { catchError, tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { throwError } from 'rxjs';
import { Trip } from '../models/trip';

@Injectable({
  providedIn: 'root'
})
export class TripService {
  private baseUrl = 'http://localhost:8083/api/';
  private url = this.baseUrl + 'trips';
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  index() {
    return this.http.get<Trip[]>(this.url)
    .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('KABOOM broken at Trip service index');
          })
     );
  }

  addTrip(data) {

    return this.http.post<Trip>(this.url, data, this.httpOptions)
      .pipe(
         catchError(this.handleError)
       );

  }

  handleError(error: any) {
    console.error('Something Broke');
    return throwError(error.json().error || 'Server Error');
  }

  updateTrip(trip: Trip) {
    return this.http.put<Trip>(this.url + '/' + trip.id, trip)
      .pipe(
         catchError(this.handleError)
       );
  }

  deleteTrip(id: number) {
    return this.http.delete<Trip>(this.url + '/' + id, this.httpOptions)
    .pipe(catchError(this.handleError));

  }

  show(id) {
    return this.http.get<Trip>(this.url + '/' + id)
    .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError('KABOOM broken at Trip service show');
          })
     );
  }

  search(kword: string) {
    return this.http.get<Trip[]>(this.url + '/search/' + kword)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('error in search trip');
        })
      );

  }
}

