import { AboutComponent } from './components/about/about.component';
import { ActivityViewComponent } from './components/activity-view/activity-view.component';
import { EditActivityComponent } from './components/edit-activity/edit-activity.component';
import { EditTripComponent } from './components/edit-trip/edit-trip.component';
import { NgModule } from '@angular/core';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { Routes, RouterModule } from '@angular/router';
import { TripViewComponent } from './components/trip-view/trip-view.component';

const routes: Routes = [
 { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: TripViewComponent },
  { path: 'activityView/:id', component: ActivityViewComponent },
  // { path: 'editActivity/id', component: EditActivityComponent },
  // { path: 'editTrip/id', component: EditTripComponent },
  { path: 'about', component: AboutComponent },
  { path: '**', component: NotFoundComponent },
  { path: 'notFound', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
