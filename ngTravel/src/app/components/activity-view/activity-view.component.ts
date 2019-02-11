import { Activity } from 'src/app/models/activity';
import { Component, OnInit } from '@angular/core';
import { ActivityService } from './../../services/activity.service';
import { ActivatedRoute, Router } from '@angular/router';
import { TripService } from './../../services/trip.service';
import { CategoryService } from './../../services/category.service';
import { Category } from 'src/app/models/category';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-activity-view',
  templateUrl: './activity-view.component.html',
  styleUrls: ['./activity-view.component.css']
})
export class ActivityViewComponent implements OnInit {

  activity: Activity;
  categories: Category[];
  editActivity: Activity = null;

  constructor(private aService: ActivityService, private tService: TripService,
              private cService: CategoryService, private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.setUp();

  }

  setUp() {
    const id = this.route.snapshot.paramMap.get('id');
    this.aService.show(id).subscribe(
        data => {
          console.log(data);

          this.activity = data;
          console.log(this.activity);
        },
        err => {
          this.router.navigateByUrl('notFound');
        }
      );
  }


  deleteActivity(id: number) {
    this.aService.deleteActivity(id).subscribe(
      data => {
        return this.router.navigateByUrl('home');
      },
      err => console.error('could not delete')


    );
  }

  addCategory(id) {
    console.log('************');
    console.log(id);

    this.cService.show(id).subscribe(
        cat => {
          for (const c of this.activity.categories) {

            if (c.id === cat.id) {
              console.log('in if statement');
              this.categories = [];
              return;
            }
          }
          console.log(cat);

          this.activity.categories.push(cat);
          this.updateActivity();
          this.categories = [];
        },
        err => console.error('could not add')
      );

  }
  removeCategory(catId: number) {
      this.cService.show(catId).subscribe(
        cat => {
          this.activity.categories.splice(this.activity.categories.indexOf(cat), 1);
          this.updateActivity();
        },
        err => console.error('could not remove category')
      );

  }
  updateActivity() {
    this.aService.updateActivity(this.activity).subscribe(
      data => {
        this.activity = data;
      },
      err => console.error('could not update activity')
    );
  }

  getAllCategories() {
    this.cService.index().subscribe(
      data => {
        this.categories = data;
        console.log(this.categories);

      },
      err => console.log('error getting categories')

    );
  }
  setEditActivity() {
    this.editActivity = Object.assign({}, this.activity);

  }
  cancelEdit() {
    this.editActivity = null;
  }

  confirmEdit() {
    this.activity = this.editActivity;
    this.editActivity = null;
    this.updateActivity();
  }

}
