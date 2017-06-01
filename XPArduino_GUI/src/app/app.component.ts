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
  comPorts;
  comPort;

  constructor(private comPortService: ComPortService) {
  }


  ngOnInit(): void {
    this.comPortService.getComPorts().subscribe(data =>
      this.comPorts = data);
  }

  openPort() {
    this.comPortService.openPort(this.comPort);
  }



}
