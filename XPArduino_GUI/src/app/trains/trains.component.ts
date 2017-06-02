import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'trains-component',
  templateUrl: './trains.component.html',
  styleUrls: ['./trains.component.css']
})
export class TrainsComponent implements OnInit {

  speed = 0;
  whiteTrainSource = "assets/white_train.png";
  yellowTrainSource = "assets/yellow_train.png";
  blueTrainSource = "assets/blue_train.png";

  constructor() { }

  ngOnInit() {
  }

  setSpeed(speed: number) {
    if (this.speed == speed) {
      this.speed = 0;
    } else {
      this.speed = speed;
    }
  }

  isArrowActive(speed:number) {
    if (speed > 0) {
      return this.speed >= speed;
    } else {
      return this.speed <= speed;
    }
  }

}
