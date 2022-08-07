package com.example.trainingmate.dataBase.objects

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.trainingmate.dataBase.DatabaseContract
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = DatabaseContract.TABLE_TRAINING)
data class TrainingObject(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_NAME) var trainingName: String = "",
    @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_NAME_WITH_DATE) var trainingNameWithDate: String = "",
    @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_DATE) var trainingDate: String = "",
    @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_EXERCISE_NAME) var trainingExerciseNameList: MutableList<String> = mutableListOf(),
    @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_TIME_BETWEEN_SETS) var trainingTimeBetweenSets: Int = 0,
    @ColumnInfo(name = DatabaseContract.TrainingColumns.TRAINING_TOTAL_TIME) var trainingTotalTime: Int = 0
) : Parcelable

class TrainingExercisesTypeConverter {
    @TypeConverter
    fun fromGson(value: String?): List<String> {
        val list = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, list)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Gson().toJson(list)
    }
}