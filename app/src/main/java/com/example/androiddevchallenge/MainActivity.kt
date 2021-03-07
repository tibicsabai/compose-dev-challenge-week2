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
package com.example.androiddevchallenge

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.countdown.composables.CountDown
import com.example.androiddevchallenge.countdown.composables.DisplaySeconds
import com.example.androiddevchallenge.ui.theme.BackgroundColor
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.dark_magenta

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = BackgroundColor) {
        val informed = remember { mutableStateOf(false) }
        val configuredSeconds = remember { mutableStateOf(0) }
        val configurable = remember { mutableStateOf(true) }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .draggable(
                    orientation = Orientation.Vertical,
                    state = rememberDraggableState { delta ->
                        if (configurable.value) {
                            configuredSeconds.value = (configuredSeconds.value - delta).toInt()
                            if (configuredSeconds.value < 0) configuredSeconds.value = 0
                            if (configuredSeconds.value > 59 * 60 + 59) configuredSeconds.value =
                                59 * 60 + 59
                        }
                    }
                )
        ) {
            if (configurable.value) {
                Box(
                    modifier = Modifier
                        .clickable {
                            configurable.value = false
                        }
                        .background(dark_magenta)
                        .padding(0.5.dp)
                        .background(BackgroundColor)
                ) {
                    DisplaySeconds(configuredSeconds.value)
                }
            } else {
                Box(
                    modifier = Modifier
                        .background(dark_magenta)
                        .padding(0.5.dp)
                        .background(BackgroundColor),
                ) {
                    CountDown(
                        countDownSeconds = configuredSeconds.value,
                        onCompleted = {
                            // vibrate
                        },
                        onDoneClick = {
                            configuredSeconds.value = 0
                            configurable.value = true
                        }
                    )
                }
            }
            if (!informed.value) {
                AlertDialog(
                    onDismissRequest = {},
                    title = {
                        Text(text = "Countdown")
                    },
                    text = {
                        Column {
                            Row {
                                Text(fontWeight = FontWeight.Bold, text = "Configure timer: ")
                                Text(text = "swipe up and down")
                            }
                            Row {
                                Text(fontWeight = FontWeight.Bold, text = "Start countdown: ")
                                Text(text = "tap on time")
                            }
                            Row {
                                Text(fontWeight = FontWeight.Bold, text = "Start again: ")
                                Text(text = "tap on 'Done'")
                            }
                        }
                    },
                    confirmButton = {
                        Button(onClick = { informed.value = true }) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}
