import { Component, OnInit } from '@angular/core';
import {TrainService} from "../service/train.service";

@Component({
  selector: 'trains-component',
  templateUrl: './trains.component.html',
  styleUrls: ['./trains.component.css']
})
export class TrainsComponent implements OnInit {

  speed = 0;
  trains;
  wissel = false;

  constructor(private trainService: TrainService) {
    trainService.getTrains().subscribe(trains =>
      this.trains = trains
    );
  }

  ngOnInit() {
  }

  getSwitchSrc() {
    return this.wissel ? "assets/switch-on.png" : "assets/switch-off.png";
  }

  toggleSwitch() {
    this.wissel = !this.wissel;
    this.trainService.toggleSwitch();
  }

}
