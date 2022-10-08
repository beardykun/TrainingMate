package com.example.trainingmate.utils

import android.os.Build
import android.util.Log
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object Utils {
    fun getTrainingNameWithDate(name: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            name + current.format(formatter)
        } else {
            val date = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.UK)
            Log.i("TAGGER", name + dateFormat.format(date))
            name + dateFormat.format(date)
        }
    }
}