import { Injectable } from '@angular/core';
import {Http, Response} from "@angular/http";
import {environment} from "../../environments/environment";

@Injectable()
export class LedService {


  constructor(private http: Http) {
  }

  toggleLed(toggle: boolean) : void{
    let action = toggle ? "ON" : "OFF";
    this.http.post(environment.restUrl + "led/" + action, null).subscribe();
  }

}
