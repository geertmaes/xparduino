import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {AppComponent} from "./app.component";
import {LedService} from "./service/led.service";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MaterialGroupModule} from "./material";
import {ComPortService} from "./service/com-port.service";
import { LedComponent } from './led/led.component';
import { ComponentsComponent } from './components/components.component';
import { ComPortComponent } from './com-port/com-port.component';
import { TrainComponent } from './train/train.component';
import {TrainsComponent} from "./trains/trains.component";
import {TrainService} from "./service/train.service";

@NgModule({
  declarations: [
    AppComponent,
    LedComponent,
    ComponentsComponent,
    ComPortComponent,
    TrainComponent,
    TrainsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    BrowserAnimationsModule,
    MaterialGroupModule
  ],
  providers: [LedService, ComPortService, TrainService],
  bootstrap: [AppComponent]
})
export class AppModule { }
