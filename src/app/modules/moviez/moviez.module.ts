import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatSnackBarModule } from '@angular/material/snack-bar'; 
import { CartComponent } from './components/cart/cart.component';
import { MovieComponent } from './components/movie/movie.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


@NgModule({
  declarations: [
     CartComponent,
     MovieComponent
    ],
  imports: [
    CommonModule,
    MatSnackBarModule,
    BrowserAnimationsModule
  ],
  exports : [
    MovieComponent,
    CartComponent
  ]
})
export class MoviezModule { }
