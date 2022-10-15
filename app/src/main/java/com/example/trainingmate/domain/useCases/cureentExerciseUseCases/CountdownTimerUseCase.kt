package com.example.trainingmate.domain.useCases.cureentExerciseUseCases

import com.example.trainingmate.domain.repositories.ICurrentExerciseRepository
import kotlinx.coroutines.flow.Flow

class CountdownTimerUseCase(private val repository: ICurrentExerciseRepository) {

    fun invoke(time: Int): Flow<String> {
        return repository.countdownTimer(time)
    }
}