package com.ridwanstandingby.polyhedra.activities

import com.ridwanstandingby.polyhedra.domain.PolyhedraAnimation
import com.ridwanstandingby.polyhedra.domain.PolyhedraAnimationInput
import com.ridwanstandingby.polyhedra.domain.PolyhedraAnimationParameters
import com.ridwanstandingby.polyhedra.domain.PolyhedraAnimationRenderer
import com.ridwanstandingby.verve.activities.AnimationActivity
import com.ridwanstandingby.verve.activities.calculateScreenSize
import com.ridwanstandingby.verve.activities.createRotationDetector
import com.ridwanstandingby.verve.activities.createSwipeDetector
import com.ridwanstandingby.verve.animation.AnimationRenderView
import com.ridwanstandingby.verve.animation.AnimationRule

class PolyhedraAnimationActivity : AnimationActivity() {

    override fun createAnimationRenderView() = AnimationRenderView(
        this, AnimationRule(
            ::PolyhedraAnimation,
            PolyhedraAnimationParameters(),
            PolyhedraAnimationRenderer(calculateScreenSize()),
            PolyhedraAnimationInput(createSwipeDetector(), createRotationDetector())
        )
    )
}
