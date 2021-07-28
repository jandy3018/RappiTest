package com.example.rappitest.movies.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.rappitest.R
import com.example.rappitest.di.base.BaseViewModel
import com.example.rappitest.movies.model.ClsMovie
import java.text.SimpleDateFormat
import java.util.*


class MostPopularViewModel : BaseViewModel() {
    private val title = MutableLiveData<String>()
    private val release_date = MutableLiveData<String>()
    private val average_parentArcColor = MutableLiveData<Int>()
    private val average_fillArcColor = MutableLiveData<Int>()
    private val average_angle_start = MutableLiveData<Float>()
    private val average_current_percent = MutableLiveData<Int>()


    fun bind(result: ClsMovie) {
        if (result.releaseDate!!.isNotEmpty())
            release_date.value = SimpleDateFormat("MMM dd, yyyy", Locale("en", "EN")).format(
                SimpleDateFormat("yyyy-mm-dd", Locale("en", "EN")).parse(
                    result.releaseDate
                )
            )
        val percent = (result.voteAverage * 10).toInt()
        average_parentArcColor.value =
            if (percent <= 50) R.color.yellow_transparent else R.color.green_transparent
        average_fillArcColor.value = if (percent <= 50) R.color.yellow else R.color.green
        average_angle_start.value = ((percent * 360) / 100).toFloat()
        average_current_percent.value = percent
        title.value = result.originalLanguage
    }

    fun getTitle(): MutableLiveData<String> {
        return title
    }

    fun getReleaseDate(): MutableLiveData<String> {
        return release_date
    }

    fun getAverageParentArcColor(): MutableLiveData<Int> {
        return average_parentArcColor
    }

    fun getAverageFillArcColor(): MutableLiveData<Int> {
        return average_fillArcColor
    }

    fun getAverageAngleStart(): MutableLiveData<Float> {
        return average_angle_start
    }

    fun getAverageCurrentPecent(): MutableLiveData<Int> {
        return average_current_percent
    }
}