package com.example.rappitest.movies.view

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.rappitest.R
import com.example.rappitest.databinding.VideoTrailerBinding
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

class PlayTrailerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private lateinit var binding: VideoTrailerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.video_trailer)

        this.window.navigationBarColor = ContextCompat.getColor(this, R.color.blue)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.blue)
            window.decorView.systemUiVisibility = 0
        }

        binding.ivBack.setOnClickListener { finish() }
        binding.youtubePlayerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        if (!p2) {
            p1?.cueVideo(intent.getStringExtra("key"))
            p1?.loadVideo(intent.getStringExtra("key"))
        }
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        val REQUEST_CODE = 0

        if (p1?.isUserRecoverableError == true) {
            p1.getErrorDialog(this, REQUEST_CODE).show()
        } else {
            val errorMessage = "There was an error initializing the YoutubePlayer ($p1)"
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

}