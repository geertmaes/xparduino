import {
  MdButtonModule, MdGridListModule, MdSlideToggleModule, MdSelectModule, MdSpinner,
  MdProgressSpinnerModule
} from "@angular/material";
import {NgModule} from "@angular/core";
@NgModule({
  imports: [MdButtonModule, MdSlideToggleModule, MdGridListModule, MdSelectModule, MdProgressSpinnerModule],
  exports: [MdButtonModule, MdSlideToggleModule, MdGridListModule, MdSelectModule, MdProgressSpinnerModule]
})

export class MaterialGroupModule {
}
