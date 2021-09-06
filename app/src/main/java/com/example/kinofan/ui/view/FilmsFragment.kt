package com.example.kinofan.ui.view

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kinofan.CommingSoonFilmsFragment
import com.example.kinofan.R
import com.example.kinofan.databinding.FilmsFragmentBinding
import com.example.kinofan.ui.viewModel.FilmsViewModel
import com.example.kinofan.ui.viewModel.AppState
import com.google.android.material.snackbar.Snackbar

class FilmsFragment : Fragment() {

    companion object {
        private const val ARG_COUNT = "param2"
        fun newInstance(counter: Int?): FilmsFragment {
            val fragment = FilmsFragment()
            val args = Bundle()
            args.putInt(ARG_COUNT, counter!!)
            fragment.arguments = args
            return fragment
        }
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
        viewModel.livaData.observe(viewLifecycleOwner, { state ->
            renderData(state)
        })

        viewModel.getFilmFromLocalSource()

        initBtn()
    }

    @SuppressLint("SetTextI18n")
    private fun renderData(state: AppState) {
        when (state) {
            is AppState.Loading -> binding.loadingLayout.visibility = View.VISIBLE
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                binding.film.text = "Название: ${state.film.title} \n" +
                        "Жанр: ${state.film.genre}\n" +
                        "Рейтинг: ${state.film.rating}\n" +
                        "Год: ${state.film.yearСreation}\n"
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar
                    .make(binding.filmsView, "Error: ${state.error}", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getFilmFromLocalSource() }
                    .show()
            }
        }
    }

    private fun initBtn() {
        binding.btnDataUpdate.setOnClickListener {
            viewModel.getFilmFromRemoteSource()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
