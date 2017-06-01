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
    this.ledService.blinkLed(this.blinkingStatus);
    if (this.blinkingStatus) {
      this.observableBlinker = Observable.timer(1000, 3000).subscribe(t =>
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
