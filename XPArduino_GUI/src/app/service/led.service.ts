import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {environment} from "../../environments/environment";

@Injectable()
export class LedService {


  constructor(private http: Http) {
  }

  toggleLed(toggle: boolean) : void{
    let action = this.getOnOffState(toggle);
    this.http.post(environment.restUrl + "led/" + action, null).subscribe();
  }

  blinkLed(toggle: boolean) {
    let action = "blink" + this.getOnOffState(toggle);
    this.http.post(environment.restUrl + "led/" + action, null).subscribe();
  }

  private getOnOffState(toggle: boolean) {
    return toggle ? "ON" : "OFF";
  }

}
