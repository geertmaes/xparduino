import {Component, OnInit, Input} from '@angular/core';

@Component({
  selector: 'components',
  templateUrl: './components.component.html',
  styleUrls: ['./components.component.css']
})
export class ComponentsComponent {

  constructor() { }

  @Input() comPort;



}
