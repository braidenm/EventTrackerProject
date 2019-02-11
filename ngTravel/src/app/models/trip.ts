import { Activity } from './activity';

export class Trip {

  id: number;
  name: string;
  description: string;
  activities: Activity[];

  constructor(id: number, name: string, description: string, activities: Activity[]) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.activities = activities;
  }
}
