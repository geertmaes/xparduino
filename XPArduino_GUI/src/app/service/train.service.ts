import { Injectable } from '@angular/core';
import {Http, Response} from "@angular/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

@Injectable()
export class TrainService {

  constructor(private http:Http) { }

  setSpeed(train: string, speed: number) {
    return this.http.post(environment.restUrl + "train", {
      "identifier": train,
      "speed": speed
    }).subscribe();

  }

  getTrains(): Observable<string> {
    return this.http.get(environment.restUrl + "train")
      .map((response: Response) => response.json() as string[])
      .catch((error: any) => Observable.throw(error));
  }

  toggleSwitch() : void {
    this.http.post(environment.restUrl + "switch", null).subscribe();
  }

}
