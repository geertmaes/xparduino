import {Component, OnInit, Input} from '@angular/core';
import {TrainService} from "../service/train.service";

@Component({
  selector: 'train-component',
  templateUrl: './train.component.html',
  styleUrls: ['./train.component.css']
})
export class TrainComponent {


  @Input() train;

  speed = 0;

  constructor(private trainService : TrainService) { }

  setSpeed(speed: number) {
    if (this.speed == speed) {
      this.speed = 0;
    } else {
      this.speed = speed;
    }

    this.trainService.setSpeed(this.train, this.speed);
  }

  isArrowActive(speed:number) {
    if (speed > 0) {
      return this.speed >= speed;
    } else {
      return this.speed <= speed;
    }
  }

  getImageSource() {
    return "assets/" + this.train + "_train.png";
  }

}
