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
  switches = [false, false, false, false];

  constructor(private trainService: TrainService) {
    trainService.getTrains().subscribe(trains =>
      this.trains = trains
    );
  }

  ngOnInit() {
  }

  getSwitchSrc(nmbr: number) {
    return this.switches[nmbr] ? "assets/switch-on.png" : "assets/switch-off.png";
  }

  toggleSwitch(nmbr: number) {
    this.switches[nmbr] = !this.switches[nmbr];
  }

}
