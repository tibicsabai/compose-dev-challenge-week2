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
package com.example.androiddevchallenge.countdown.model

/**
 * The integer code represents the succession of bits representing the character.
 * For example 0 is represented as
 *      X X X       => 7 = 1*2^2 + 1*2^1 + 1*2^0
 *      X   X       => 5 = 1*2^2 + 0*2^1 + 1*2^0
 *      X   X       => 5
 *      X   X       => 5
 *      X X X       => 7
 */
val DigitCodes = mapOf(
    0 to 75557,
    1 to 26227,
    2 to 71747,
    3 to 71717,
    4 to 55711,
    5 to 74717,
    6 to 74757,
    7 to 71111,
    8 to 75757,
    9 to 75717
)

val LetterCodes: Map<Char, Int> = mapOf(
    'D' to 65556,
    'O' to 75557,
    'N' to 65555,
    'E' to 74647,
    '!' to 22202
)
