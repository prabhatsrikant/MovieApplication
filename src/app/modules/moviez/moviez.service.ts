import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movie } from './movie';

@Injectable({
  providedIn: 'root'
})
export class MoviezService {

  endPoint : string;  
  username : string;

  constructor( private httpClient : HttpClient) { 
    this.endPoint = "http://localhost:9090/movie/";
    this.username = localStorage.getItem('username');
   }

  getAllMovies() : Observable<any>{
 
    const url = this.endPoint + "getAll";

    return this.httpClient.get(url);
}

addMovieToCart(movie : Movie){
  const url = this.endPoint + this.username + "/save";
  console.log(url);
  return this.httpClient.post(url, movie,
    {
      observe: "response"
    });
}

getAllMoviesFromCart() : Observable<Movie[]>{
  const url = '${this.endPoint} ${this.username}' + "/tracks";
  return this.httpClient.get<Movie[]>(url);
}

deleteMovieFromCart(movie : Movie) {
  const url = `${this.endPoint} ${this.username}` + `${movie.movieId}`;
  return this.httpClient.delete(url, { responseType : "text"});
}
}
