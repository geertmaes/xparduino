import { Component, OnInit } from '@angular/core';
import {LedService} from "../service/led.service";
import {Observable, Subscription} from "rxjs";

@Component({
  selector: 'led-component',
  templateUrl: './led.component.html',
  styleUrls: ['./led.component.css']
})
export class LedComponent {

  ledStatus = false;
  blinkingStatus = false;
  lightBulbSrc = "assets/lightbulb-off.png";
  observableBlinker : Subscription;
  delay: number;
  period: number;

  constructor(private ledService: LedService) { }

  toggleLed() {
    this.ledService.toggleLed(this.ledStatus);
    if (this.ledStatus) {
      this.lightBulbSrc = "assets/lightbulb-on.png";
    } else {
      this.lightBulbSrc = "assets/lightbulb-off.png";
    }
  }

  toggleBlinking() {
    this.ledService.blinkLed(this.blinkingStatus, this.delay, this.period);
    if (this.blinkingStatus) {
      this.observableBlinker = Observable.timer(this.delay, this.period).subscribe(t =>
        {
          if (this.lightBulbSrc.indexOf("on") == -1) {
            this.lightBulbSrc = "assets/lightbulb-on.png";
          } else {
            this.lightBulbSrc = "assets/lightbulb-off.png";
          }
        }
      );
    } else {
      this.observableBlinker.unsubscribe();
      this.lightBulbSrc = "assets/lightbulb-off.png";
    }
  }

}
