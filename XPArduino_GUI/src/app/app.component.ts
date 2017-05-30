import {Component, OnInit} from '@angular/core';
import {ComPortService} from "./service/com-port.service";
import {LedService} from "./service/led.service";
import {Response} from "@angular/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app works!';
  ledStatus = false;
  comPorts;
  comPort;
  lightBulbSrc = "assets/lightbulb-off.png";

  constructor(private comPortService: ComPortService, private ledService: LedService) {
  }


  ngOnInit(): void {
    this.comPortService.getComPorts().subscribe(data =>
      this.comPorts = data);
  }

  openPort() {
    this.comPortService.openPort(this.comPort);
  }

  toggleLed() {
    this.ledStatus = !this.ledStatus;
    this.ledService.toggleLed(this.ledStatus);
    if (this.ledStatus) {
      this.lightBulbSrc = "assets/lightbulb-on.png";
    } else {
      this.lightBulbSrc = "assets/lightbulb-off.png";
    }
  }

}
