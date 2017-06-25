import { Injectable } from '@angular/core';
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable()
export class ComPortService {

  constructor(private http: Http) { }

  getComPorts() : Observable<string[]> {
    return this.http.get(environment.restUrl + 'port')
      .map((response: Response) => response.json() as string[])
      .catch((error: any) => Observable.throw(error));
  }

  openPort(port: string) : void {
    this.http.post(environment.restUrl + 'port/open', {
      port
    }).subscribe();
  }

}
