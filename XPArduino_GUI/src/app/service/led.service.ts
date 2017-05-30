import { Injectable } from '@angular/core';
import {Http, Response} from "@angular/http";
import {environment} from "../../environments/environment";

@Injectable()
export class LedService {


  constructor(private http: Http) {
  }

  ledAan() {
    return "aan";
  }

}
