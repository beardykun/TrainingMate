package com.example.trainingmate.dataBase.dao

import android.Manifest
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.trainingmate.dataBase.TrainingDatabase
import com.example.trainingmate.dataBase.objects.ExerciseInfoObject
import com.example.trainingmate.dataBase.objects.ExerciseSet
import com.example.trainingmate.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
@HiltAndroidTest
class ExerciseInfoDaoTest {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @Inject
    @Named("test_db")
    lateinit var database: TrainingDatabase
    private lateinit var exerciseInfoDao: ExerciseInfoDao

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()

        exerciseInfoDao = database.exerciseInfoDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertExercise() = runTest {
        val exerciseObject = ExerciseInfoObject(
            1,
            "biceps curl",
            "biceps",
            listOf(),
            100.2.toFloat()
        )
        exerciseInfoDao.insertExercise(exerciseObject)

        val exercise = exerciseInfoDao.getExerciseWithTrainingName("biceps curl", "biceps")
            .getOrAwaitValueTest()
        assertThat(exercise).isEqualTo(exerciseObject)
    }

    @Test
    fun deleteExercise() = runTest {
        val exerciseObject = ExerciseInfoObject(
            1,
            "biceps curl",
            "biceps",
            listOf(),
            100.2.toFloat()
        )
        exerciseInfoDao.insertExercise(exerciseObject)

        exerciseInfoDao.deleteExercise(exerciseObject)

        val exercise = exerciseInfoDao.getExerciseWithTrainingName("biceps curl", "biceps").getOrAwaitValueTest()
        assertThat(exercise).isEqualTo(null)
    }

    @Test
    fun updateExercise() = runTest {
        val exerciseObject = ExerciseInfoObject(
            1,
            "biceps curl",
            "biceps",
            listOf(),
            100.2.toFloat()
        )
        exerciseInfoDao.insertExercise(exerciseObject)
        val exerciseObjectUpdated = exerciseInfoDao.getExerciseWithTrainingName("biceps curl", "biceps").getOrAwaitValueTest()
        val set = ExerciseSet(7f,7)
        exerciseObjectUpdated.exerciseSet = listOf(set)
        exerciseInfoDao.updateExercise(exerciseObjectUpdated)

        val exercise = exerciseInfoDao.getExerciseWithTrainingName("biceps curl", "biceps").getOrAwaitValueTest()
        assertThat(exercise.exerciseSet).contains(set)
    }
}