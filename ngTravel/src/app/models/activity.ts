import { Category } from './category';
import { Trip } from './trip';
export class Activity {
  id: number;
  name: string;
  startDate: string;
  endDate: string;
  description: string;
  trip: Trip;
  categories: Category[];

  constructor(id: number, name: string, startDate: string, endDate: string,
              description: string, trip: Trip, categories: Category[]) {

      this.id = id;
      this.name = name;
      this.startDate = startDate;
      this.endDate = endDate;
      this.description = description;
      this.trip = trip;
      this.categories = categories;
  }
}
