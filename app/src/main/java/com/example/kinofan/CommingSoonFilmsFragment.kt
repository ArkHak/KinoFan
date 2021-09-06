package com.example.kinofan

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kinofan.ui.viewModel.RatingsViewModel

class CommingSoonFilmsFragment : Fragment() {

    companion object {
        private const val ARG_COUNT = "param1"
        fun newInstance(counter: Int?): CommingSoonFilmsFragment {
            val fragment = CommingSoonFilmsFragment()
            val args = Bundle()
            args.putInt(ARG_COUNT, counter!!)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: CommingSoonFilmsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.comming_soon_films_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CommingSoonFilmsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}