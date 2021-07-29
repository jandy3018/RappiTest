package com.example.rappitest.movies.view

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rappitest.R
import com.example.rappitest.databinding.ActivityMovieDetailsBinding
import com.example.rappitest.movies.viewModel.MovieDetailViewModel
import com.google.android.material.snackbar.Snackbar

class DetailMovieActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var viewModelDetail: MovieDetailViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        val manager = GridLayoutManager(this, 3)
        binding.rvGenre.layoutManager=manager
        binding.lifecycleOwner=this
        viewModelDetail = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
        viewModelDetail.imageView.value = binding.ivPoster

        viewModelDetail.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(
                errorMessage
            ) else hideError()
        })
        viewModelDetail.loadDetailById(intent.extras!!.getLong("id_movie"), intent.extras!!.getString("poster_path",""),
            intent.extras!!.getString("title",""), intent.extras!!.getString("release_date",""), intent.extras!!.getString("overview",""))

        binding.ivBack.setOnClickListener { onBackPressed() }
        binding.viewModel =viewModelDetail
        this.window.navigationBarColor= ContextCompat.getColor(this, R.color.blue)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this,R.color.blue)
            window.decorView.systemUiVisibility =0
        }
    }

    private fun showError(@StringRes errorMessage: Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModelDetail.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }
}