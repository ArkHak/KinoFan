package com.example.kinofan.ui.data.api

import com.example.kinofan.ui.data.vo.MovieDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDBInterface {

//    https://api.themoviedb.org/3/movie/566525?api_key=5a4d3f5b8db540f6abf906e6fdf2fdb9&language=ru-RU
//    https://api.themoviedb.org/3/movie/popular?api_key=5a4d3f5b8db540f6abf906e6fdf2fdb9&language=ru-RU&page=1

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>
}