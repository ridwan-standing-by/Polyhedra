package com.ridwanstandingby.polyhedra.domain

import com.ridwanstandingby.verve.animation.Animation
import com.ridwanstandingby.verve.math.Vector3
import com.ridwanstandingby.verve.math.dist
import com.ridwanstandingby.verve.sensor.swipe.Swipe

class PolyhedraAnimation(
    parameters: PolyhedraAnimationParameters,
    renderer: PolyhedraAnimationRenderer,
    input: PolyhedraAnimationInput
) :
    Animation<PolyhedraAnimationParameters, PolyhedraAnimationRenderer, PolyhedraAnimationInput>(
        parameters,
        renderer,
        input
    ) {

    private val polyhedra = List(parameters.numberOfPolyhedra) { parameters.generateRandomPolyhedron() }

    init {
        input.start()
    }

    override fun update(dt: Double) {
        renderer.camera.updateTransform(input.getOrientation())
        renderer.lines = mutableListOf()

        input.getSwipes().forEach { it.resolveSwipe() }

        polyhedra.forEach {
            it.update(dt, parameters.angularToLinearSpeedRatio)
            it.decayVelocity(parameters.velocityDecayRate)
            it.handleBounces(renderer.camera, parameters.maxSphereScale)
            it.prepareRender(renderer)
        }
    }

    private fun Swipe.resolveSwipe() {
        polyhedra.filter {
            dist(renderer.camera.project(it.position), this.screenPosition) < parameters.swipePixelRadius
        }.forEach {
            it.velocity += renderer.camera.inverseTransform(
                Vector3(
                    this.screenVelocity.x.toDouble(),
                    -this.screenVelocity.y.toDouble(),
                    0.0
                )
            ) * parameters.swipeStrength
        }
    }
}
