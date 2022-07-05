package com.anshad.whetherapp.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Calendar

object DateHelper {
    @Throws(ParseException::class, NullPointerException::class, IllegalArgumentException::class)
    fun String.toDate(format: String): Date {
        val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
        return simpleDateFormat.parse(this)
    }

    @Throws(ParseException::class, NullPointerException::class, IllegalArgumentException::class)
    fun Date.toString(format: String): String {
        val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
        return simpleDateFormat.format(this)
    }


    /**
     * Adds given day with Calendar
     */
    fun Calendar.addDays(days: Int): Calendar {
        add(Calendar.DAY_OF_MONTH, days)
        return this
    }

    fun getDateTime(s: Long): String? {
        try {
            val sdf = SimpleDateFormat("hh:mm aa",Locale.getDefault())
            val netDate = Date(s!!.toLong() * 1000)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }

    fun getDateOnly(s: Long): String? {
        try {
            val sdf = SimpleDateFormat("dd-mm-yyyy",Locale.getDefault())
            val netDate = Date(s!!.toLong() * 1000)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }


}