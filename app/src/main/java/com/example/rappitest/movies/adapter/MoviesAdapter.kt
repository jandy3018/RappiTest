package com.example.rappitest.movies.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.rappitest.R
import com.example.rappitest.databinding.ItemMostPopularBinding
import com.example.rappitest.movies.custom.RatingView
import com.example.rappitest.movies.model.ClsMovie
import com.example.rappitest.movies.view.DetailMovieActivity
import com.example.rappitest.movies.viewModel.MostPopularViewModel
import com.example.rappitest.movies.viewModel.MoviesListViewModel
import com.example.rappitest.utils.Utils


class MoviesAdapter(var viewModel: MoviesListViewModel) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private lateinit var lstMoviesMostPopular: List<ClsMovie>
    private lateinit var objectsFiltered: ArrayList<ClsMovie>
    var strFilter = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMostPopularBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_most_popular,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val percent = (objectsFiltered[position].voteAverage * 10).toInt()
        holder.rating.parentArcColor =
            if (percent <= 50) R.color.yellow_transparent else R.color.green_transparent
        holder.rating.fillArcColor = if (percent <= 50) R.color.yellow else R.color.green
        holder.rating.angle_start = ((percent * 360) / 100).toFloat()
        holder.rating.current_percent = percent
        objectsFiltered[position].originalLanguage =
            Utils.getStrSearchHtml(objectsFiltered[position].title, strFilter)
        holder.bind(objectsFiltered[position])
        Glide.with(holder.iv_poster.context)
            .load("https://image.tmdb.org/t/p/w500/" + objectsFiltered[position].posterPath)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            //.placeholder(R.drawable.poster_default)
            .into(holder.iv_poster)

        holder.cl_item_most_popular.setOnClickListener {
            Utils.singleClickListener(holder.iv_poster.context) {
                val intent =
                    Intent(holder.iv_poster.context, DetailMovieActivity::class.java)
                intent.putExtra("id_movie", objectsFiltered[position].id)
                intent.putExtra("poster_path", objectsFiltered[position].posterPath)
                intent.putExtra("title", objectsFiltered[position].title)
                intent.putExtra("release_date", objectsFiltered[position].releaseDate)
                intent.putExtra("overview", objectsFiltered[position].overview)

                (holder.iv_poster.context as FragmentActivity).startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (::objectsFiltered.isInitialized) objectsFiltered.size else 0
    }

    fun updatePostList(postList: List<ClsMovie>) {
        this.lstMoviesMostPopular = postList
        this.objectsFiltered = ArrayList(postList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemMostPopularBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = MostPopularViewModel()
        var iv_poster: ImageView = itemView.findViewById(R.id.iv_poster)
        var cl_item_most_popular: ConstraintLayout =
            itemView.findViewById(R.id.cl_item_most_popular)
        var rating: RatingView = itemView.findViewById(R.id.rating)
        fun bind(result: ClsMovie) {
            viewModel.bind(result)
            binding.viewModel = viewModel
        }
    }

    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                strFilter = charSequence.toString()
                if (strFilter.isEmpty()) {
                    objectsFiltered = ArrayList(lstMoviesMostPopular)
                } else {
                    val filteredList: ArrayList<ClsMovie> = ArrayList()
                    for (row in lstMoviesMostPopular) {
                        if (row.title!!.toLowerCase().contains(strFilter.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }
                    objectsFiltered = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = objectsFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                objectsFiltered = filterResults.values as ArrayList<ClsMovie>
                viewModel.tvHeaderMostPopular.value!!.visibility =
                    if (objectsFiltered.size == 0) View.GONE else View.VISIBLE
                if (viewModel.tvHeaderTopRated.value!!.visibility == View.GONE && viewModel.tvHeaderMostPopular.value!!.visibility == View.GONE)
                    viewModel.tvNoFound.value!!.visibility = View.VISIBLE
                else
                    viewModel.tvNoFound.value!!.visibility = View.GONE

                notifyDataSetChanged()
            }
        }
    }
}