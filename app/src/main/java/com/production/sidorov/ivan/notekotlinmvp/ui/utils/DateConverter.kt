package com.production.sidorov.ivan.notekotlinmvp.ui.utils

import java.text.SimpleDateFormat
import java.util.*

class DateConverter{
    companion object {
        fun convert(date: Date): String{
            val dateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm", Locale.getDefault())
            return dateFormat.format(date)
        }
    }
}