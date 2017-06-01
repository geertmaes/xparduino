import {
  MdButtonModule, MdGridListModule, MdSlideToggleModule, MdSelectModule, MdSpinner,
  MdProgressSpinnerModule, MdInputModule
} from "@angular/material";
import {NgModule} from "@angular/core";
@NgModule({
  imports: [MdButtonModule, MdSlideToggleModule, MdGridListModule, MdSelectModule, MdProgressSpinnerModule, MdInputModule],
  exports: [MdButtonModule, MdSlideToggleModule, MdGridListModule, MdSelectModule, MdProgressSpinnerModule, MdInputModule]
})

export class MaterialGroupModule {
}
