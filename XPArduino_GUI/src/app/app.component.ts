import {Component, OnInit} from '@angular/core';
import {ComPortService} from "./service/com-port.service";
import {LedService} from "./service/led.service";
import {Response} from "@angular/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{

  comPort;
  spinnerVisible = false;


  constructor() {
  }

  handleComPort(event) {
    this.spinnerVisible = true;
    setTimeout(() => {
      this.comPort = event;
      this.spinnerVisible = false;
    } , 0);
  }

}
