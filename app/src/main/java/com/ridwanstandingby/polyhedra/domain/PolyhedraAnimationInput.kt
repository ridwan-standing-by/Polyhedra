package com.ridwanstandingby.polyhedra.domain

import com.ridwanstandingby.verve.animation.AnimationInput
import com.ridwanstandingby.verve.sensor.RotationDetector

class PolyhedraAnimationInput(
    private val rotationDetector: RotationDetector,
    private val pollingIntervalMicros: Int = 2000
) : AnimationInput() {

    fun start() {
        rotationDetector.start(pollingIntervalMicros)
    }

    fun getOrientation() = rotationDetector.lastKnownOrientation
}
