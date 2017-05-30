import {Component, OnInit} from '@angular/core';
import {ComPortService} from "./service/com-port.service";
import {LedService} from "./service/led.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'app works!';
  ledStatus = false;
  comPorts = ["COM1", "COM2"];

  constructor(private comPortService: ComPortService, private ledService: LedService) {
  }


  ngOnInit(): void {
    this.comPortService.getComPorts()
  }


}
