package com.example.trainingmate.data.dataBase.objects

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExerciseSet(val weight: Float, val reps: Int) : Parcelable