import { Component, OnInit } from '@angular/core';
import {LedService} from "../service/led.service";

@Component({
  selector: 'led-component',
  templateUrl: './led.component.html',
  styleUrls: ['./led.component.css']
})
export class LedComponent {

  ledStatus = false;
  blinkingStatus = false;
  lightBulbSrc = "assets/lightbulb-off.png";
  lightBulbBlinkSrc = "assets/blinking-off.png"

  constructor(private ledService: LedService) { }

  toggleLed() {
    this.ledStatus = !this.ledStatus;
    this.ledService.toggleLed(this.ledStatus);
    if (this.ledStatus) {
      this.lightBulbSrc = "assets/lightbulb-on.png";
    } else {
      this.lightBulbSrc = "assets/lightbulb-off.png";
    }
  }

  toggleBlinking() {
    this.blinkingStatus = !this.blinkingStatus;
    this.ledService.blinkLed(this.blinkingStatus);
    if (this.blinkingStatus) {
      this.lightBulbBlinkSrc = "assets/blinking-on.gif";
    } else {
      this.lightBulbBlinkSrc = "assets/blinking-off.png";
    }
  }

}
