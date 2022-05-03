package com.example.kinofan.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kinofan.R
import com.example.kinofan.databinding.RatingsFilmsFragmentBinding
import com.example.kinofan.ui.viewModel.RatingsFilmsViewModel

class RatingsFilmsFragment : Fragment() {

    companion object {
        fun newInstance() = RatingsFilmsFragment()
    }

    private var _binding: RatingsFilmsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var filmsViewModel: RatingsFilmsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.ratings_films_fragment, container, false)
        _binding = RatingsFilmsFragmentBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmsViewModel = ViewModelProvider(this).get(RatingsFilmsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}