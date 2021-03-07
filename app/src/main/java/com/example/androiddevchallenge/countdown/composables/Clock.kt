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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable

@Composable
fun DotsDelimiter() {
    Column {
        CharacterLine(lineBits = 0)
        CharacterLine(lineBits = 2)
        CharacterLine(lineBits = 0)
        CharacterLine(lineBits = 2)
        CharacterLine(lineBits = 0)
    }
}

@Composable
fun TimeRow(minutes: Int, seconds: Int) {
    Row() {
        repeat(3) {
            SpaceDelimiter()
        }
        Time(minutes, seconds)
        repeat(3) {
            SpaceDelimiter()
        }
    }
}

@Composable
fun Time(minutes: Int, seconds: Int) {
    Value(minutes)
    DotsDelimiter()
    Value(seconds)
}

@Composable
fun DisplaySeconds(seconds: Int) {
    Column {
        RowSpacer(7)
        TimeRow(minutes = seconds / 60, seconds = seconds % 60)
        RowSpacer(2)
        Progress(0)
        RowSpacer(7)
    }
}
