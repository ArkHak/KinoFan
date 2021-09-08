package com.example.kinofan.ui.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kinofan.ui.viewModel.ComingSoonFilmsViewModel
import com.example.kinofan.R
import com.example.kinofan.databinding.ComingSoonFilmsFragmentBinding
import com.example.kinofan.databinding.FilmsFragmentBinding

class ComingSoonFilmsFragment : Fragment() {

    companion object {
        private const val ARG_COUNT = "param1"
        fun newInstance(counter: Int?): ComingSoonFilmsFragment {
            val fragment = ComingSoonFilmsFragment()
            val args = Bundle()
            args.putInt(ARG_COUNT, counter!!)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: ComingSoonFilmsViewModel
    private var _binding: ComingSoonFilmsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.coming_soon_films_fragment, container, false)
        _binding = ComingSoonFilmsFragmentBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ComingSoonFilmsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}