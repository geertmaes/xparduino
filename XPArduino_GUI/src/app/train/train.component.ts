import {Component, OnInit, Input} from '@angular/core';

@Component({
  selector: 'train-component',
  templateUrl: './train.component.html',
  styleUrls: ['./train.component.css']
})
export class TrainComponent {

  @Input() imageSource;

  speed = 0;

  constructor() { }

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
