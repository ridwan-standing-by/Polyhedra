package com.ridwanstandingby.polyhedra.domain

import com.ridwanstandingby.verve.animation.Animation

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
        renderer.lines = mutableListOf()
        renderer.camera.updateCamera(input.getOrientation())

        input.getSwipes().forEach { swipe ->
            polyhedra.forEach { polyhedron ->
                polyhedron.handleSwipe(swipe, parameters.swipePixelRadius, parameters.swipeStrength, renderer.camera)
            }
        }

        polyhedra.forEach {
            it.update(dt, parameters.angularToLinearSpeedRatio)
            it.decayVelocity(parameters.velocityDecayRate)
            it.handleBounces(renderer.camera, parameters.maxSphereScale)
            it.prepareRender(renderer)
        }
    }
}
