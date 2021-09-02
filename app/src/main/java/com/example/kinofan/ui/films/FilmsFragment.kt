package com.example.kinofan.ui.films

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.kinofan.R
import com.example.kinofan.databinding.FilmsFragmentBinding

class FilmsFragment : Fragment() {

    companion object {
        fun newInstance() = FilmsFragment()
    }

    private lateinit var viewModel: FilmsViewModel
    private var _binding: FilmsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.films_fragment, container, false)
        _binding = FilmsFragmentBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FilmsViewModel::class.java)

        viewModel.getData().observe(viewLifecycleOwner, { renderData(it) })

    }

    private fun renderData(data: String?) {
        view?.findViewById<TextView>(R.id.film)?.text = data

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}