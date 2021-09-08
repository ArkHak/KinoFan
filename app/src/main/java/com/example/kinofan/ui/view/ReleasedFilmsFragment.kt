package com.example.kinofan.ui.view

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kinofan.R
import com.example.kinofan.databinding.ReleasedFilmsFragmentBinding
import com.example.kinofan.ui.viewModel.ReleasedFilmsViewModel
import com.example.kinofan.ui.viewModel.AppState
import com.google.android.material.snackbar.Snackbar

class ReleasedFilmsFragment : Fragment() {

    companion object {
        private const val ARG_COUNT = "param2"
        fun newInstance(counter: Int?): ReleasedFilmsFragment {
            val fragment = ReleasedFilmsFragment()
            val args = Bundle()
            args.putInt(ARG_COUNT, counter!!)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModelReleased: ReleasedFilmsViewModel
    private var _binding: ReleasedFilmsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.released_films_fragment, container, false)
        _binding = ReleasedFilmsFragmentBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelReleased = ViewModelProvider(this).get(ReleasedFilmsViewModel::class.java)
        viewModelReleased.livaData.observe(viewLifecycleOwner, { state ->
            renderData(state)
        })

        viewModelReleased.getFilmFromLocalSource()

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
                    .setAction("Reload") { viewModelReleased.getFilmFromLocalSource() }
                    .show()
            }
        }
    }

    private fun initBtn() {
        binding.btnDataUpdate.setOnClickListener {
            viewModelReleased.getFilmFromRemoteSource()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
