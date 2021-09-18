package com.example.kinofan.ui.single_movie_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.kinofan.R
import com.example.kinofan.ui.data.api.POSTER_BASE_URL
import com.example.kinofan.ui.data.api.TheMovieDBClient
import com.example.kinofan.ui.data.api.TheMovieDBInterface
import com.example.kinofan.ui.data.repository.NetworkState
import com.example.kinofan.ui.data.vo.MovieDetails
import com.example.kinofan.ui.model.Film
import kotlinx.android.synthetic.main.fragment_single_movie.*
import java.text.NumberFormat
import java.util.*


class SingleMovieFragment : Fragment() {

    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieRepository: MovieDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_single_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Film>(SingleMovieFragment.BUNDLE_EXTRA)?.let { film ->
            val movieId = film.id.toInt()

            val apiService: TheMovieDBInterface = TheMovieDBClient.getClient()
            movieRepository = MovieDetailsRepository(apiService)

            viewModel = getViewModel(movieId)

            viewModel.movieDetails.observe(viewLifecycleOwner, Observer {
                bindUI(it)
            })

            viewModel.networkState.observe(viewLifecycleOwner, Observer {
                progress_bar.visibility =
                    if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
                txt_error.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE

            })
        }
    }

    @SuppressLint("SetTextI18n")
    fun bindUI(it: MovieDetails) {
        movie_title.text = it.title
        movie_tagline.text = it.tagline
        movie_release_date.text = it.releaseDate
        movie_rating.text = it.rating.toString()
        movie_runtime.text = it.runtime.toString() + " minutes"
        movie_overview.text = it.overview

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        movie_budget.text = formatCurrency.format(it.budget)
        movie_revenue.text = formatCurrency.format(it.revenue)

        val moviePosterURL = POSTER_BASE_URL + it.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(iv_movie_poster);


    }

    private fun getViewModel(movieId: Int): SingleMovieViewModel {
        return ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SingleMovieViewModel(movieRepository, movieId) as T
            }
        }).get(SingleMovieViewModel::class.java)
    }

    companion object {
        const val BUNDLE_EXTRA = "FILM"

        fun newInstance(bundle: Bundle): SingleMovieFragment {
            val fragment = SingleMovieFragment()
            fragment.arguments = bundle
            return fragment
        }
    }


}