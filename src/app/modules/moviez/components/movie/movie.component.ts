import { Component, OnInit } from '@angular/core';
import { MoviezService } from '../../moviez.service';
import { Movie } from '../../movie';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.css']
})
export class MovieComponent implements OnInit {

  movies : Array<Movie>;
  searchedMovies : Array<Movie>;
  id : number;
  movieName : string;
  statusCode : number;
  errorStatus : string;
  
  
  constructor(private moviezService : MoviezService,
              private matSnackbar : MatSnackBar) { 
                this.movies = [];
              }

  ngOnInit() {

    this.moviezService.getAllMovies().subscribe(movies => {
      console.log(movies);
      this.movies = [];
      this.movies = movies;
      this.searchedMovies = this.movies;
    });

    localStorage.setItem('username', "ABC");
  }

  onKey(event : any){
    this.movieName = event.target.value;
    console.log("movieName :: " , this.movieName);
    const result = this.searchedMovies.filter( movie => {
      return movie.title.toLowerCase().match(this.movieName.toLowerCase());
    });
    console.log("Filtered Data",result)
    this.movies = result;

  }

  addToCart(movie){
    console.log('Inside the Cart :: ', movie);
    this.moviezService.addMovieToCart(movie).subscribe(
      data => {
      console.log('Response Data from Backend :: ', data);
      
      this.statusCode = data.status;
      if(this.statusCode === 201){
        console.log("Success", this.statusCode);
        this.matSnackbar.open("Movie Successfully added !!!", " ",{
          duration: 1000
        });
     
      }
    },
      error => {

        this.errorStatus = `${error.status}`;
        const errorMsg = `${error.error.message}`;
        this.statusCode = parseInt(this.errorStatus, 10);
        if( this.statusCode === 409) {

          this.matSnackbar.open(errorMsg, "", {
            duration : 1000
          });
          this.statusCode = 0;
        }
      }

    );
    }

}
