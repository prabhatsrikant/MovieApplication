import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MovieComponent } from './modules/moviez/components/movie/movie.component';
import { CartComponent } from './modules/moviez/components/cart/cart.component';


const routes: Routes = [
  {
    path: "",
    component: MovieComponent
  },
  {
    path: "cart",
    component: CartComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
