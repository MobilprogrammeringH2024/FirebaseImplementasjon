package com.example.firebaseimplementation.common.ext

import android.util.Patterns
import java.util.regex.Pattern

/**
 * @reference This whole file and underlying methods was made the same way as instructed under lecture: https://youtu.be/7Ta_6JO4y1g?si=2hrYuFCxefR554fI && https://github.com/larseknu/mobilprogrammering_2023/blob/main/Lecture09_Firebase_Authentication/AfterLecture/PlayingWithFirebase/app/src/main/java/no/larseknu/hiof/compose/playingwithfirebase/common/ext/StringExt.kt
 */
private const val MIN_PASS_LENGTH = 8
private const val PASS_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"

fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    return this.isNotBlank() &&
            this.length >= MIN_PASS_LENGTH &&
            Pattern.compile(PASS_PATTERN).matcher(this).matches()
}