package com.example.trainingmate.data.dataBase.objects

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.trainingmate.data.dataBase.DatabaseContract
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = DatabaseContract.TABLE_EXERCISE_INFO)
data class ExerciseInfoObject(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = DatabaseContract.ExerciseInfoColumns.EXERCISE_NAME) val exerciseName: String,
    @ColumnInfo(name = DatabaseContract.ExerciseInfoColumns.EXERCISE_TRAINING_NAME) val exerciseTrainingName: String,
    @ColumnInfo(name = DatabaseContract.ExerciseInfoColumns.EXERCISE_REPS) val exerciseSet: List<ExerciseSet>,
    @ColumnInfo(name = DatabaseContract.ExerciseInfoColumns.EXERCISE_MAX_WEIGHT) val exerciseMaxWeight: Float
) : Parcelable {

    class ExerciseSetTypeConverter {
        @TypeConverter
        fun fromGson(value: String?): List<ExerciseSet> {
            val list = object : TypeToken<List<ExerciseSet>>() {}.type
            return Gson().fromJson(value, list)
        }

        @TypeConverter
        fun fromList(list: List<ExerciseSet>): String {
            val type = object : TypeToken<List<ExerciseSet>>() {}.type
            return Gson().toJson(list, type)
        }
    }
}