import {MdButtonModule, MdGridListModule, MdSlideToggleModule, MdSelectModule} from "@angular/material";
import {NgModule} from "@angular/core";
@NgModule({
  imports: [MdButtonModule, MdSlideToggleModule, MdGridListModule, MdSelectModule],
  exports: [MdButtonModule, MdSlideToggleModule, MdGridListModule, MdSelectModule]
})

export class MaterialGroupModule {
}
