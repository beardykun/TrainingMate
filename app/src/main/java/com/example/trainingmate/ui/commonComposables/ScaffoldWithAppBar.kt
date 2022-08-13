package com.example.trainingmate.ui.commonComposables

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffordWithAppBar(
    appBarTitle: String, navigate: () -> Unit,
    content: @Composable () -> Unit,
    fab: @Composable () -> Unit = { }
) {
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        decayAnimationSpec,
        rememberTopAppBarState()
    )
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(appBarTitle) },
                navigationIcon = {
                    IconButton(onClick = {
                        navigate()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back button"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                content()
            }
        },
        floatingActionButton = { fab() },
        floatingActionButtonPosition = FabPosition.End,
    )
}