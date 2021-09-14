package com.example.kinofan.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kinofan.R
import com.example.kinofan.databinding.FragmentDetailsFilmBinding
import com.example.kinofan.ui.model.Film

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Film>(BUNDLE_EXTRA)?.let { film ->
            film.also {
                binding.filmTitle.text = film.title
                binding.filmGenre.text = film.genre
                binding.filmRating.text = film.rating.toString()
                binding.filmYearCreated.text = film.yearСreation
                if (film.like) {
                    binding.icLike.setImageResource(R.drawable.ic_like_on_64)
                }
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