import { CategoryService } from './services/category.service';
import { ActivityService } from './services/activity.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TripViewComponent } from './components/trip-view/trip-view.component';
import { ActivityViewComponent } from './components/activity-view/activity-view.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { AboutComponent } from './components/about/about.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { EditTripComponent } from './components/edit-trip/edit-trip.component';
import { EditActivityComponent } from './components/edit-activity/edit-activity.component';
import { TripService } from './services/trip.service';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    TripViewComponent,
    ActivityViewComponent,
    NotFoundComponent,
    AboutComponent,
    NavigationComponent,
    EditTripComponent,
    EditActivityComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    NgbModule.forRoot(),
    HttpClientModule,
  ],
  providers: [
    ActivityService,
    CategoryService,
    TripService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
