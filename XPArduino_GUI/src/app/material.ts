import {
  MdButtonModule, MdGridListModule, MdSlideToggleModule, MdSelectModule, MdSpinner,
  MdProgressSpinnerModule, MdInputModule, MdTabsModule, MdIconModule
} from "@angular/material";
import {NgModule} from "@angular/core";
@NgModule({
  imports: [MdButtonModule, MdSlideToggleModule, MdGridListModule, MdSelectModule, MdProgressSpinnerModule, MdInputModule, MdTabsModule, MdIconModule],
  exports: [MdButtonModule, MdSlideToggleModule, MdGridListModule, MdSelectModule, MdProgressSpinnerModule, MdInputModule, MdTabsModule, MdIconModule]
})

export class MaterialGroupModule {
}
