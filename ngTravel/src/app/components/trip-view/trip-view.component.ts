import { Activity } from './../../models/activity';
import { ActivityService } from './../../services/activity.service';
import { TripService } from './../../services/trip.service';
import { Component, OnInit } from '@angular/core';
import { Trip } from 'src/app/models/trip';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-trip-view',
  templateUrl: './trip-view.component.html',
  styleUrls: ['./trip-view.component.css']
})
export class TripViewComponent implements OnInit {

  trips: Trip[];
  // selected: Trip = null;
  newTrip: Trip = new Trip(0, '', '', null);
  editTrip = null;
  newActivity = new Activity(0, '', '', '', '', null, null);
  tripCount: number;
  count = function() {
    return this.trips.length;
  };
  constructor(private tService: TripService, private aService: ActivityService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.reload();
  }
  reload() {
    this.tService.index().subscribe(
      data => {
        this.trips = data,
        this.tripCount = this.count();
      },

      err => console.error('trips reload got an error: ' + err)
    );

  }


  // displayTrip = function(todo) {
  //   this.selected = todo;
  // };

  // displayTable = function() {
  //   this.selected = null;
  // };

  addTrip(form: NgForm) {
    this.newTrip.name = form.value.name;
    this.newTrip.description = '';
    this.newTrip.activities = null;
    this.tService.addTrip(this.newTrip).subscribe(
      data => {
        this.newTrip = new Trip(0, '', '', null);
        this.reload();
        form.reset();
      },
      err => console.error('Observer got an error: ' + err)
    );
}
  addActivity(form: NgForm, tripId: number) {
    this.newActivity.name = form.value.name;
    console.log(tripId);
    // for (const trip of this.trips) {

    //   if (trip.id === tripId) {
    //     console.log('in trips if loop');

    //     this.newActivity.trip = trip;
    //     console.log(this.newActivity.trip);


    //   }
    // }
    this.aService.addActivity(this.newActivity, tripId).subscribe(
      data => {
        for (let i = 0; i < this.trips.length; i++) {
          if (this.trips[i].id === tripId) {
            this.tService.show(tripId).subscribe(
              success => {
                this.trips[i] = success;
              }
            );
          }

        }
        this.newActivity = new Activity(0, '', '', '', '', null, null);
        // this.reload();
        form.reset();
      },
      err => console.error('Observer got an error: ' + err)
    );
}

  setEditTrip(trip: Trip) {
    this.editTrip = Object.assign({}, trip);

  }
  cancelTrip() {
    this.editTrip = null;
  }
  updateTrip() {
      this.tService.updateTrip(this.editTrip).subscribe(
        data => {
          this.editTrip = null;
          this.reload();
        },
        err => console.error('Observer got an error: ' + err)
      );

    }

  deleteTrip(id) {
      this.tService.deleteTrip(id).subscribe(
        data => {
          this.reload();
        },
        err => console.error('Observer got an error: ' + err)
      );
  }

  activityView(id: number) {
    return this.router.navigateByUrl('activityView/' + id);
  }
  deleteActivity(id: number) {

    this.aService.deleteActivity(id).subscribe(
      data => {

        this.reload();
      },
      err => console.error('could not delete')


    );
  }




}
