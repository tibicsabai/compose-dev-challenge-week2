/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.countdown.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.CELL_DIMENSION
import com.example.androiddevchallenge.ui.theme.LineColor
import com.example.androiddevchallenge.ui.theme.ProgressConsumedColor
import com.example.androiddevchallenge.ui.theme.ProgressRemainingColor

@Composable
fun CountDown(countDownSeconds: Int, onCompleted: () -> Unit, onDoneClick: () -> Unit) {

    val progress = remember { mutableStateOf(0f) }
    val theSeconds = remember { mutableStateOf(0) }
    val theMinutes = remember { mutableStateOf(0) }
    val completed = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        animate(
            initialValue = countDownSeconds.toFloat() + 1,
            targetValue = 1f,
            animationSpec = tween(countDownSeconds * 1000, easing = LinearEasing)
        ) { remainingSeconds, _ ->
            progress.value = (remainingSeconds * 100) / countDownSeconds
            theMinutes.value = (remainingSeconds / 60).toInt()
            theSeconds.value = (remainingSeconds % 60).toInt()
            completed.value = remainingSeconds == 1f
        }
    }
    if (completed.value) {
        DoneText(onDoneClick)
        onCompleted()
    } else {
        Column {
            RowSpacer(7)
            TimeRow(minutes = theMinutes.value, seconds = theSeconds.value)
            RowSpacer(2)
            Progress(countDownSeconds)
            RowSpacer(7)
        }
    }
}

@Composable
fun Progress_Bar(progress: Float) {
    Box(
        modifier = Modifier
            .height(CELL_DIMENSION.dp)
            .width((17 * CELL_DIMENSION).dp)
            .background(LineColor)
            .padding(1.dp)
            .background(ProgressConsumedColor)
    ) {
        Box(modifier = Modifier.matchParentSize()) {
            Row {
                Box(
                    modifier = Modifier
                        .height((CELL_DIMENSION - 2).dp)
                        .fillMaxWidth((progress / 100f))
                        .background(
                            ProgressConsumedColor
                        )
                )
                Box(
                    modifier = Modifier
                        .height((CELL_DIMENSION - 2).dp)
                        .fillMaxWidth()
                        .background(
                            ProgressRemainingColor
                        )
                )
            }
        }
        Box(modifier = Modifier.matchParentSize()) {
            Row {
                repeat(17) {
                    CellDelimiter()
                }
            }
        }
    }
}

@Composable
fun Progress(seconds: Int) {
    Row {
        repeat(3) { CellOff() }
        val progress = remember { mutableStateOf(0f) }
        LaunchedEffect(Unit) {
            animate(
                initialValue = 0f,
                targetValue = 100f,
                animationSpec = tween(seconds * 1000, easing = LinearEasing)
            ) { value, _ ->
                progress.value = value
            }
        }
        Progress_Bar(progress = progress.value)
        repeat(3) {
            CellOff()
        }
    }
}
