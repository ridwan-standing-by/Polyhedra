package com.ridwanstandingby.polyhedra.domain

import com.ridwanstandingby.verve.animation.AnimationInput
import com.ridwanstandingby.verve.sensor.RotationDetector
import com.ridwanstandingby.verve.sensor.swipe.SwipeDetector

class PolyhedraAnimationInput(
    private val swipeDetector: SwipeDetector,
    private val rotationDetector: RotationDetector,
    private val pollingIntervalMicros: Int = 2000
) : AnimationInput() {

    fun start() {
        rotationDetector.start(pollingIntervalMicros)
    }

    fun getOrientation() = rotationDetector.lastKnownOrientation

    fun getSwipes() = swipeDetector.swipes.also { swipeDetector.swipes = mutableListOf() }
}
