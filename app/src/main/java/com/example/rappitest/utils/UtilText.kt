package com.example.rappitest.utils

import android.widget.TextView
import androidx.core.text.HtmlCompat

fun TextView.setTextFiltered(text:String?){
    this.text = HtmlCompat.fromHtml(text!!, HtmlCompat.FROM_HTML_MODE_LEGACY)
}