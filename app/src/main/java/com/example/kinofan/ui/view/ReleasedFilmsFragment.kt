package com.example.kinofan.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.kinofan.R
import com.example.kinofan.databinding.ReleasedFilmsFragmentBinding
import com.example.kinofan.ui.model.Film
import com.example.kinofan.ui.viewModel.AppState
import com.example.kinofan.ui.viewModel.ReleasedFilmsAdapter
import com.example.kinofan.ui.viewModel.ReleasedFilmsViewModel
import com.google.android.material.snackbar.Snackbar

class ReleasedFilmsFragment : Fragment() {

    companion object {
        private const val ARG_COUNT = "param1"
        fun newInstance(counter: Int?): ReleasedFilmsFragment {
            val fragment = ReleasedFilmsFragment()
            val args = Bundle()
            args.putInt(ARG_COUNT, counter!!)
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: ReleasedFilmsFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModelReleased: ReleasedFilmsViewModel by lazy {
        ViewModelProvider(this).get(ReleasedFilmsViewModel::class.java)
    }

    private val adapter = ReleasedFilmsAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(film: Film) {
            val bundle = Bundle()
            bundle.putParcelable(DetailsFilmFragment.BUNDLE_EXTRA, film)
            findNavController(requireParentFragment()).navigate(
                R.id.action_navigation_films_to_detailsFilmFragment,
                bundle
            )
        }

    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ReleasedFilmsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.releasedFilmsRecyclerView.adapter = adapter
        viewModelReleased.getLiveDate().observe(viewLifecycleOwner, { state ->
            renderData(state)
        })
        viewModelReleased.getFilmsFromLocalSourceReleased()
    }

    private fun renderData(state: AppState) {
        when (state) {
            is AppState.Loading -> binding.loadingLayout.visibility = View.VISIBLE
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                adapter.setFilms(state.filmsData)
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                binding.filmsFragmentContainer.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    { viewModelReleased.getFilmsFromLocalSourceReleased() })
            }
        }
    }

    private fun View.showSnackBar(
        text: String,
        actionText: String,
        action: (View) -> Unit,
        length: Int = Snackbar.LENGTH_INDEFINITE
    ) {
        Snackbar.make(this, text, length).setAction(actionText, action).show()
    }


    interface OnItemViewClickListener {
        fun onItemViewClick(film: Film)
    }

    override fun onDestroy() {
        adapter.removeListener()
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
