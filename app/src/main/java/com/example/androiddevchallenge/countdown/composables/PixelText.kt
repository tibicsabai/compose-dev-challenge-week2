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

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.countdown.model.DigitCodes
import com.example.androiddevchallenge.countdown.model.LetterCodes
import com.example.androiddevchallenge.ui.theme.BackgroundColor
import com.example.androiddevchallenge.ui.theme.CELL_DIMENSION
import com.example.androiddevchallenge.ui.theme.ForegroundColor
import com.example.androiddevchallenge.ui.theme.LineColor
import java.util.ArrayList

@Composable
fun DoneText(onDoneClick: () -> Unit) {
    Column(modifier = Modifier.clickable { onDoneClick() }) {
        RowSpacer(height = 8)
        TextRow("DONE!")
        RowSpacer(height = 9)
    }
}

@Composable
fun TextRow(text: String) {
    Row() {
        val spaces = (25 - (text.length * 4) - 1) / 2
        repeat(spaces) {
            SpaceDelimiter()
        }
        text.forEachIndexed { index, letter ->
            LetterCodes[letter]?.let { Character(it) }
            if (index != text.length - 1)
                SpaceDelimiter()
        }
        repeat(spaces) {
            SpaceDelimiter()
        }
    }
}

@Composable
fun CellOn() {
    Box(
        modifier = Modifier
            .height(CELL_DIMENSION.dp)
            .width(CELL_DIMENSION.dp)
            .background(LineColor)
            .padding(1.dp)
            .background(ForegroundColor)
    )
}

@Composable
fun CellOff() {
    Box(
        modifier = Modifier
            .height(CELL_DIMENSION.dp)
            .width(CELL_DIMENSION.dp)
            .background(LineColor)
            .padding(1.dp)
            .background(BackgroundColor)
    )
}

@Composable
fun CellDelimiter() {
    Box(
        modifier = Modifier
            .height(CELL_DIMENSION.dp)
            .width(CELL_DIMENSION.dp)
    ) {
        Box(
            modifier = Modifier
                .height(CELL_DIMENSION.dp)
                .width(1.dp)
                .background(LineColor)
        )
        Spacer(modifier = Modifier.width((CELL_DIMENSION - 2).dp))
        Box(
            modifier = Modifier
                .height(CELL_DIMENSION.dp)
                .width(1.dp)
                .background(LineColor)
        )
    }
}

@Composable
fun SpaceDelimiter(height: Int = 5) {
    if (height > 1) {
        Column {
            repeat(height) {
                CellOff()
            }
        }
    } else {
        CellOff()
    }
}

@Composable
fun CharacterLine(lineBits: Int) {
    Row {
        if (lineBits and 4 == 4) CellOn() else CellOff()
        if (lineBits and 2 == 2) CellOn() else CellOff()
        if (lineBits and 1 == 1) CellOn() else CellOff()
    }
}

@Composable
fun Character(charCode: Int) {
    var number = charCode
    val lineBits: ArrayList<Int> = arrayListOf()
    Column() {
        while (number != 0) {
            val digit = number % 10
            number /= 10
            lineBits.add(0, digit)
        }
        lineBits.forEach { CharacterLine(lineBits = it) }
    }
    println()
}

@Composable
fun Value(value: Int) {
    val lastDigit = value % 10
    val firstDigit = (value / 10) % 10

    Row {
        DigitCodes[firstDigit]?.let { Character(it) }
        SpaceDelimiter()
        DigitCodes[lastDigit]?.let { Character(it) }
    }
}

@Composable
fun RowSpacer(height: Int = 1) = Row { repeat(23) { SpaceDelimiter(height) } }
