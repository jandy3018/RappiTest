package com.example.rappitest.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.preference.PreferenceManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.rappitest.R
import org.apache.commons.lang3.math.NumberUtils

class Utils {
    companion object {
        fun singleClickListener(context: Context, duration: Long = 500, action: () -> Unit) {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = sharedPreferences.edit()
            if (sharedPreferences.getBoolean("existClicked", false)) {
                Handler().postDelayed({
                    editor?.putBoolean(
                        "existClicked",
                        false
                    );editor?.apply(); editor?.commit()
                }, duration)
                return
            }
            editor.putBoolean("existClicked", true)
            editor.apply()
            editor.commit()
            val dialogTransparent = Dialog(context, R.style.BlockAppDialog)
            dialogTransparent.setCancelable(false)
            dialogTransparent.show()
            try {
                action()
            } catch (ex: NullPointerException) {
            }
            Handler().postDelayed({
                if (context is Activity) {
                    var act = context
                    if (!act.isFinishing && !act.isDestroyed) {
                        if (dialogTransparent.isShowing) {
                            try {
                                dialogTransparent.dismiss()
                                editor?.putBoolean("existClicked", false)
                                editor?.apply()
                                editor?.commit()
                            } catch (ex: Exception) {
                                editor?.putBoolean("existClicked", false)
                                editor?.apply()
                                editor?.commit()
                            }
                        }
                    }
                }
            }, duration)
        }

        fun removeCharacters(input: String): String {
            val original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ"
            val ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC"
            var output = input
            for (i in 0 until original.length) {
                output = output.replace(original[i], ascii[i])
            }
            return output
        }

        fun showKeyboard(edt: EditText) {
            val keyboard=edt.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            keyboard.showSoftInput(edt, 0)
        }

        fun hideKeyboard(activity: Activity, edt: EditText) {
            val imm=activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(edt.windowToken, 0)
        }

        fun getStrSearchHtml(strOriginal: String?, strFilter: String): String? {
            if (strFilter.isEmpty())
                return strOriginal
            var strHtml = ""
            var numCharacterAdd = 0
            val charOriginal = strOriginal!!.toUpperCase().toCharArray()
            val charSearch = strFilter.toUpperCase().toCharArray()
            for (j in charOriginal.indices) {
                var existChar = false
                for (i in charSearch.indices) {
                    if (charOriginal[j] == charSearch[i] && (charSearch.size == 1 ||
                                j - 1 >= 0 && (Character.isDigit(charOriginal[j - 1]) || i - 1 >= 0 && charOriginal[j - 1] == charSearch[i - 1]) ||
                                j + 1 < charOriginal.size && (Character.isDigit(charOriginal[j + 1]) || i + 1 < charSearch.size && charOriginal[j + 1] == charSearch[i + 1]))) {
                        if (numCharacterAdd == 0) {
                            var cadOriginal = strOriginal.substring(j, strOriginal.length).toUpperCase()
                            if (NumberUtils.isDigits(cadOriginal.replace("[^A-Za-z0-9]".toRegex(), "")))
                                cadOriginal = cadOriginal.replace("[^A-Za-z0-9]".toRegex(), "")
                            if (cadOriginal.indexOf(strFilter.toUpperCase()) != -1 && cadOriginal.substring(0, strFilter.length) == strFilter.toUpperCase())
                                existChar = true
                            break
                        } else {
                            if (numCharacterAdd < strFilter.length)
                                existChar = true
                            break
                        }
                    }
                }
                if (existChar) {
                    numCharacterAdd++
                    strHtml += "<font color=\"#039BE5\">" + strOriginal.toCharArray()[j] + "</font>"
                    if (numCharacterAdd == strFilter.length)
                        numCharacterAdd = 0
                } else if (j < strOriginal.length) {
                    strHtml += strOriginal.toCharArray()[j]
                }
            }
            return strHtml
        }
    }
}