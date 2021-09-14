package com.example.kinofan.ui.viewModel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kinofan.R
import com.example.kinofan.databinding.FilmItemBinding
import com.example.kinofan.ui.model.Film
import com.example.kinofan.ui.view.ReleasedFilmsFragment

class ReleasedFilmsAdapter(
    private var onItemViewClickListener:
    ReleasedFilmsFragment.OnItemViewClickListener?
) :
    RecyclerView.Adapter<ReleasedFilmsAdapter.ReleasedFilmsViewHolder>() {

    private var filmsData: List<Film> = listOf()

    fun setFilms(data: List<Film>) {
        filmsData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReleasedFilmsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FilmItemBinding.inflate(layoutInflater, parent, false)

        return ReleasedFilmsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReleasedFilmsViewHolder, position: Int) {
        holder.bind(filmsData[position])
    }

    override fun getItemCount(): Int = filmsData.size

    fun removeListener() {
        onItemViewClickListener = null
    }


    inner class ReleasedFilmsViewHolder(
        private val binding: FilmItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Film) {
            itemView.apply {
                setOnClickListener { onItemViewClickListener?.onItemViewClick(film) }
            }
            with(binding) {
                filmTitle.text = film.title
                filmReleaseDate.text = film.yearСreation
                filmPopularity.text = film.rating.toString()
                if (film.like) {
                    icLike.setImageResource(R.drawable.ic_like_on_64)
                }
            }
        }
    }
}