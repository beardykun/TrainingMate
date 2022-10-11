package com.example.trainingmate.domain.useCases.cureentExerciseUseCases

import com.example.trainingmate.domain.repositories.ICurrentExerciseRepository

class ValidateInputUseCase(private val repository: ICurrentExerciseRepository) {

    suspend fun invoke(weight: String, reps: String): String {
        return repository.validateInput(weight, reps)
    }
}