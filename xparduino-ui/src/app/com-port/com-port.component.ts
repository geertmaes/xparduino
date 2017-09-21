import {Component, OnInit, Output, Input, EventEmitter} from '@angular/core';
import {ComPortService} from "../service/com-port.service";

@Component({
  selector: 'com-port-selection',
  templateUrl: './com-port.component.html',
  styleUrls: ['./com-port.component.css']
})
export class ComPortComponent implements OnInit {

  comPorts = ["COM1"];
  @Output() comPort = new EventEmitter();

  constructor(private comPortService: ComPortService) {
  }

  ngOnInit(): void {
    this.comPortService.getComPorts().subscribe(data =>
      this.comPorts = data);
  }

  openPort(event): void {
    this.comPort.emit(event.value);
    this.comPortService.openPort(event.value);
  }
}
