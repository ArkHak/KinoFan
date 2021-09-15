package com.example.kinofan.ui.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kinofan.R
import com.example.kinofan.databinding.FavoriteFilmsFragmentBinding
import com.example.kinofan.ui.viewModel.FavoriteFilmsViewModel

class FavoriteFilmsFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteFilmsFragment()
    }

    private var _binding: FavoriteFilmsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FavoriteFilmsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.favorite_films_fragment, container, false)
        _binding = FavoriteFilmsFragmentBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoriteFilmsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}