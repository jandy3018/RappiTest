package com.example.rappitest.movies.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.rappitest.R
import com.example.rappitest.movies.model.ClsMovie
import com.example.rappitest.movies.view.DetailMovieActivity
import com.example.rappitest.movies.viewModel.MoviesListViewModel
import com.example.rappitest.utils.Utils
import kotlin.collections.ArrayList

class MoviesTopRatedAdapter(var viewModel: MoviesListViewModel) :
    RecyclerView.Adapter<MoviesTopRatedAdapter.ViewHolder>() {
    private lateinit var listMoviesTopRated: List<ClsMovie>
    private lateinit var objectsFiltered: ArrayList<ClsMovie>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_top_rated, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.iv_poster.context)
            .load("https://image.tmdb.org/t/p/w500/" + objectsFiltered[position].posterPath)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.iv_poster)

        holder.iv_poster.setOnClickListener {
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
        this.listMoviesTopRated = postList
        this.objectsFiltered = ArrayList(postList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_poster: ImageView

        init {
            iv_poster = itemView.findViewById(R.id.iv_poster)
        }
    }

    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val strFilter = charSequence.toString()
                if (strFilter.isEmpty()) {
                    objectsFiltered = ArrayList(listMoviesTopRated)
                } else {
                    val filteredList: ArrayList<ClsMovie> = ArrayList()
                    for (row in listMoviesTopRated) {
                        if (row.title!!.lowercase()
                                .contains(strFilter.lowercase())
                        ) {
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
                viewModel.tvHeaderTopRated.value!!.visibility =
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