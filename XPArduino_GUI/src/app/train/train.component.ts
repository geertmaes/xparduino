import {Component, OnInit, Input} from '@angular/core';
import {TrainService} from "../service/train.service";
import {MdSnackBar} from "@angular/material";
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'train-component',
  templateUrl: './train.component.html',
  styleUrls: ['./train.component.css']
})
export class TrainComponent implements OnInit{

  ngOnInit(): void {
    Observable.timer(10000, 2000).subscribe(t =>
      {
        this.trainService.pollTrainsPassed().subscribe(
          data => this.openSnackBar(data)
        );
      }
    );
  }


  @Input() train;

  speed = 0;

  constructor(private trainService : TrainService, public snackBar: MdSnackBar) {
  }

  setSpeed(speed: number) {
    if (this.speed == speed) {
      this.speed = 0;
    } else {
      this.speed = speed;
    }

    this.trainService.setSpeed(this.train, this.speed);
  }

  isArrowActive(speed:number) {
    if (speed > 0) {
      return this.speed >= speed;
    } else {
      return this.speed <= speed;
    }
  }

  getImageSource() {
    return "assets/" + this.train + "_train.png";
  }

  openSnackBar(color: string) {
    if(color != "") {
      this.snackBar.open("Train passed", color, {
        duration: 2000
      });
    }
  }

}
