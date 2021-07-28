package com.example.rappitest.movies.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rappitest.R
import com.example.rappitest.databinding.ActivityMoviesListBinding
import com.example.rappitest.di.ViewModelFactory
import com.example.rappitest.movies.viewModel.MoviesListViewModel
import com.example.rappitest.utils.Utils
import com.google.android.material.snackbar.Snackbar

class ListPopularAndTopMoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesListBinding
    private lateinit var viewModel: MoviesListViewModel
    var errorSnackbar: Snackbar? = null
    private val RECOGNIZE_SPEECH_ACTIVITY=1

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movies_list)
        binding.lifecycleOwner=this
        binding.rvTopRated.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMostPopular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(MoviesListViewModel ::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        viewModel.tvTitleToolbar.value = binding.tvTitleToolbar
        viewModel.tvHeaderTopRated.value = binding.tvHeaderTopRated
        viewModel.tvHeaderMostPopular.value = binding.tvHeaderMostPopular
        viewModel.tvNoFound.value = binding.tvNoFound

        binding.viewModel = viewModel
        OnListeners()
        this.window.navigationBarColor= ContextCompat.getColor(this, R.color.blue)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this,R.color.blue)
            window.decorView.systemUiVisibility =0
        }
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

    private fun OnListeners()
    {
        binding.ivSearch.setOnClickListener {
            binding.ivSearch.visibility= View.GONE
            binding.tvTitleToolbar.visibility= View. GONE
            binding.tilEdtSearch.visibility= View.VISIBLE
            binding.edtSearch.requestFocus()
            Utils.showKeyboard(binding.edtSearch)
        }
        binding.ivBackSearch.setOnClickListener {
            binding.ivSearch.visibility= View.VISIBLE
            binding.tvTitleToolbar.visibility= View. VISIBLE
            binding.tilEdtSearch.visibility= View.GONE
            binding.edtSearch.setText("")
            Utils.hideKeyboard(this, binding.edtSearch)
        }
        binding.ivDeleteTextSearch.setOnClickListener {
            if (binding.edtSearch.text.toString().isEmpty()) {
                val intentActionRecognizeSpeech= Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                intentActionRecognizeSpeech.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX")
                try {
                    startActivityForResult(intentActionRecognizeSpeech, 1)
                } catch (a: ActivityNotFoundException) {
                    Toast.makeText(this, R.string.error_voice_recognition, Toast.LENGTH_SHORT).show()
                }
            } else {
                binding.edtSearch.setText("")
                viewModel.movieTopRatedAdapter.getFilter().filter("")
                viewModel.movieMostPopularAdapter.getFilter().filter("")
            }
        }

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                binding.ivDeleteTextSearch.setImageResource(if (s.toString().isEmpty()) R.drawable.ic_microphone else R.drawable.ic_close)
                viewModel.movieTopRatedAdapter.getFilter().filter(s.toString())
                viewModel.movieMostPopularAdapter.getFilter().filter(s.toString())
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RECOGNIZE_SPEECH_ACTIVITY -> if (resultCode=== RESULT_OK && null!=data) {
                val speech=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                var strSpeech2Text= speech?.get(0)
                if (TextUtils.isDigitsOnly(strSpeech2Text!!.replace(" ", "")))
                    strSpeech2Text=strSpeech2Text.replace(" ", "")
                binding.edtSearch.setText(Utils.removeCharacters(strSpeech2Text))
                binding.edtSearch.setSelection(binding.edtSearch.text.length)
            }
            else -> {
            }
        }
    }
}