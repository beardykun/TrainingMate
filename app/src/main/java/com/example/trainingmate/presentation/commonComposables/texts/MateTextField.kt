package com.example.trainingmate.presentation.commonComposables.texts

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MateTextFieldDouble(stateValue: MutableState<String>, modifier: Modifier) {
    TextField(
        value = stateValue.value,
        onValueChange = {
            stateValue.value = it
        },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal).copy(
            imeAction = ImeAction.Done
        ),
        modifier = modifier,
        label = @Composable { Text("Weight") }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MateTextFieldInt(stateValue: MutableState<String>, modifier: Modifier) {
    TextField(
        value = stateValue.value,
        onValueChange = {
            stateValue.value = it
        },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number).copy(
            imeAction = ImeAction.Done
        ),
        modifier = modifier,
        label = @Composable { Text("Num of Reps") }
    )
}