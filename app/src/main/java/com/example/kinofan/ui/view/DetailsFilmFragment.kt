package com.example.kinofan.ui.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.kinofan.databinding.FragmentDetailsFilmBinding
import com.example.kinofan.ui.model.Film
import com.example.kinofan.ui.model.FilmDTO
import com.example.kinofan.ui.model.FilmLoader

class DetailsFilmFragment : Fragment() {

    private var _binding: FragmentDetailsFilmBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Film>(BUNDLE_EXTRA)?.let { film ->

            FilmLoader(film.id, object : FilmLoader.FilmLoaderListener {
                override fun onLoaded(filmDTO: FilmDTO) {
                    requireActivity().runOnUiThread {
                        displayFilm(filmDTO)
                    }
                }

                override fun onFailed(throwable: Throwable) {
                    requireActivity().runOnUiThread {
                        Toast.makeText(
                            requireContext(),
                            throwable.localizedMessage,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

            }).goToInternet()
        }
    }

    private fun displayFilm(film: FilmDTO) {
        film.also {
            with(binding) {
                filmTitle.text = film.title
                filmGenre.text = film.genre
                filmRating.text = film.vote_average.toString()
                filmYearCreated.text = film.year_creation
                filmOriginalTitle.text = film.original_title
                filmRuntime.text = film.runtime.toString()
                filmReleaseDate.text = film.release_date
                filmBudget.text = film.budget.toString()
                filmOverview.text = film.overview
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val BUNDLE_EXTRA = "FILM"

        fun newInstance(bundle: Bundle): DetailsFilmFragment {
            val fragment = DetailsFilmFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}