import {
  MdButtonModule, MdGridListModule, MdSlideToggleModule, MdSelectModule, MdSpinner,
  MdProgressSpinnerModule, MdInputModule, MdTabsModule, MdIconModule, MdSnackBarModule
} from "@angular/material";
import {NgModule} from "@angular/core";
@NgModule({
  imports: [MdButtonModule, MdSlideToggleModule, MdGridListModule, MdSelectModule, MdProgressSpinnerModule, MdInputModule, MdTabsModule, MdIconModule, MdSnackBarModule],
  exports: [MdButtonModule, MdSlideToggleModule, MdGridListModule, MdSelectModule, MdProgressSpinnerModule, MdInputModule, MdTabsModule, MdIconModule, MdSnackBarModule]
})

export class MaterialGroupModule {
}
