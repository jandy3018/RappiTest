package com.example.rappitest.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rappitest.R
import com.example.rappitest.databinding.ItemGenreBinding
import com.example.rappitest.movies.model.Genre
import com.example.rappitest.movies.viewModel.MovieDetailViewModel

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {
    private lateinit var postList:List<Genre>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemGenreBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_genre, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int {
        return if(::postList.isInitialized) postList.size else 0
    }

    fun updatePostList(postList:List<Genre>){
        this.postList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemGenreBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = MovieDetailViewModel()

        fun bind(genre: Genre){
            viewModel.bind(genre)
            binding.viewModel = viewModel
        }
    }
}