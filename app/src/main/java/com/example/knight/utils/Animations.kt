package com.example.knight.utils

import androidx.compose.animation.core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

suspend fun triggerShakeAnimation(shakeOffset: Animatable<Float, AnimationVector1D>) {
    shakeOffset.animateTo(targetValue = 20f, animationSpec = tween(durationMillis = 50))
    shakeOffset.animateTo(targetValue = -20f, animationSpec = tween(durationMillis = 50))
    shakeOffset.animateTo(targetValue = 0f, animationSpec = tween(durationMillis = 50))
}

suspend fun triggerHPScaleAnimation(hpScale: Animatable<Float, AnimationVector1D>) {
    hpScale.animateTo(targetValue = 1.3f, animationSpec = tween(durationMillis = 50))
    hpScale.animateTo(targetValue = 1f, animationSpec = tween(durationMillis = 50))
}