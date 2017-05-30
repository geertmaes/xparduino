import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {AppComponent} from "./app.component";
import {LedService} from "./service/led.service";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MaterialGroupModule} from "./material";
import {ComPortService} from "./service/com-port.service";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    BrowserAnimationsModule,
    MaterialGroupModule
  ],
  providers: [LedService, ComPortService],
  bootstrap: [AppComponent]
})
export class AppModule { }
