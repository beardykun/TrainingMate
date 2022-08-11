package com.example.trainingmate.dataBase.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.trainingmate.dataBase.TrainingDatabase
import com.example.trainingmate.dataBase.objects.TrainingObject
import com.example.trainingmate.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
@HiltAndroidTest
class TrainingDaoTest {

    @Inject
    @Named("test_db")
    lateinit var database: TrainingDatabase
    private lateinit var trainingDao: TrainingDao

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        hiltRule.inject()
        trainingDao = database.trainingDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertExercise() = runTest {
        val trainingObject = TrainingObject(
            1,
            "biceps",
            "biceps2022",
            "2022",
            mutableListOf("one","two","three"),
            60,
            3600
        )
        trainingDao.insertTraining(trainingObject)

        val allTrainings = trainingDao.getAllTrainings().getOrAwaitValueTest()
        assertThat(allTrainings).contains(trainingObject)
    }

    @Test
    fun deleteExercise() = runTest {
        val trainingObject = TrainingObject(
            1,
            "biceps",
            "biceps2022",
            "2022",
            mutableListOf("one","two","three"),
            60,
            3600
        )
        trainingDao.insertTraining(trainingObject)

        trainingDao.deleteTraining(trainingObject)

        val allExercises = trainingDao.getAllTrainings().getOrAwaitValueTest()
        assertThat(allExercises).doesNotContain(trainingObject)
    }

    @Test
    fun updateExercise() = runTest {
        val trainingObject = TrainingObject(
            1,
            "biceps",
            "biceps2022",
            "2022",
            mutableListOf("one","two","three"),
            60,
            3600
        )
        trainingDao.insertTraining(trainingObject)

        val obj = trainingDao.getTrainingWithDate("biceps2022").getOrAwaitValueTest()
        obj.trainingTotalTime = 1800

        trainingDao.updateTraining(obj)

        val objUpdated = trainingDao.getTrainingWithDate("biceps2022").getOrAwaitValueTest()
        assertThat(objUpdated.trainingTotalTime).isEqualTo(1800)
    }

    @Test
    fun getTrainingWithDate() = runTest {
        val trainingObject = TrainingObject(
            1,
            "biceps",
            "biceps2022",
            "2022",
            mutableListOf("one","two","three"),
            60,
            3600
        )
        trainingDao.insertTraining(trainingObject)

        val trainingObject2 = TrainingObject(
            2,
            "biceps",
            "biceps2023",
            "2022",
            mutableListOf("one","two","three"),
            60,
            3600
        )
        trainingDao.insertTraining(trainingObject2)

        val trainingObject3 = TrainingObject(
            3,
            "biceps",
            "biceps2024",
            "2022",
            mutableListOf("one","two","three"),
            60,
            3600
        )
        trainingDao.insertTraining(trainingObject3)

        val obj = trainingDao.getTrainingWithDate("biceps2023").getOrAwaitValueTest()

        assertThat(obj).isEqualTo(trainingObject2)
    }
}