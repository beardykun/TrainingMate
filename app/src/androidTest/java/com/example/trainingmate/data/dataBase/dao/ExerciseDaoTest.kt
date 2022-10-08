package com.example.trainingmate.data.dataBase.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.trainingmate.data.dataBase.Constants
import com.example.trainingmate.data.dataBase.TrainingDatabase
import com.example.trainingmate.data.dataBase.objects.ExerciseObject
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

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
@HiltAndroidTest
class ExerciseDaoTest {

    @Inject
    @Named("test_db")
    lateinit var database: TrainingDatabase
    private lateinit var exerciseDao: ExerciseDao

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()

        exerciseDao = database.exerciseDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertExercise() = runTest {
        val exerciseObject = ExerciseObject(1, "biceps curl", "biceps", 0)
        exerciseDao.insertExercise(exerciseObject)

        val allExercises = exerciseDao.getAllExercises().getOrAwaitValueTest()
        assertThat(allExercises).contains(exerciseObject)
    }

    @Test
    fun deleteExercise() = runTest {
        val exerciseObject = ExerciseObject(1, "biceps curl", "biceps", 0)
        exerciseDao.insertExercise(exerciseObject)

        exerciseDao.deleteExercise(exerciseObject)

        val allExercises = exerciseDao.getAllExercises().getOrAwaitValueTest()
        assertThat(allExercises).doesNotContain(exerciseObject)
    }

    @Test
    fun getExercisesWithGroup() = runTest {
        val exerciseObject1 = ExerciseObject(1, "biceps curl", Constants.BICEPS_GROUP, 0)
        exerciseDao.insertExercise(exerciseObject1)
        val exerciseObject2 = ExerciseObject(2, "abs curl", Constants.CHEST_GROUP, 0)
        exerciseDao.insertExercise(exerciseObject2)

        val allExercises = exerciseDao.getAllExercisesWithGroup(Constants.BICEPS_GROUP).getOrAwaitValueTest()
        assertThat(allExercises).contains(exerciseObject1)
        assertThat(allExercises).doesNotContain(exerciseObject2)
    }

    @Test
    fun updateExercise() = runTest {
        val exerciseObject = ExerciseObject(1, "biceps curl", "biceps", 0)
        exerciseDao.insertExercise(exerciseObject)

        val exerciseObjectUpdated = ExerciseObject(1, "squat", "legs", 0)
        exerciseDao.updateExercise(exerciseObjectUpdated)

        val allExercises = exerciseDao.getAllExercises().getOrAwaitValueTest()
        assertThat(allExercises).contains(exerciseObjectUpdated)
    }

    @Test
    fun getExerciseWithName() = runTest {
        val exerciseObject1 = ExerciseObject(1, "biceps curl", "biceps", 0)
        exerciseDao.insertExercise(exerciseObject1)

        val exerciseObject2 = ExerciseObject(2, "squat", "legs", 0)
        exerciseDao.insertExercise(exerciseObject2)

        val exerciseObject3 = ExerciseObject(3, "shoulder press", "shoulders", 0)
        exerciseDao.insertExercise(exerciseObject3)

        val item = exerciseDao.getExerciseWithName("squat").getOrAwaitValueTest()
        assertThat(item.exerciseName).isEqualTo("squat")
    }
}