package com.example.api2

import android.content.Context
import com.example.api2.Constants.PREF_KEY
import com.example.api2.Constants.PREF_NAME
import java.sql.Date
import java.sql.Timestamp
import java.text.ParseException
import java.text.SimpleDateFormat

object Utils {

    fun getDateFromTimestampWithFormat(
        timestamp: String?,
        fromFormatformat: String?,
        toFormatformat: String?
    ):String{
        var date: java.util.Date? = null
        var res = ""
        try {
            val format = SimpleDateFormat(fromFormatformat)
            date = format.parse(timestamp)
        }catch (e: ParseException){
            e.printStackTrace()
        }
        val df = SimpleDateFormat(toFormatformat)
        res = df.format(date)
        return res
    }

    fun saveLastSearch(context: Context, query: String){
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(PREF_KEY, query).apply()
    }

    fun getLastSearch(context: Context):String?{
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(PREF_KEY, null)
    }
}

