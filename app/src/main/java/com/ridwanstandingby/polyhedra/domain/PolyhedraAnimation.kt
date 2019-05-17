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
        renderer.camera.updateTransform(input.getOrientation())
        renderer.lines = mutableListOf()

        polyhedra.forEach {
            it.update(dt)
            it.handleBounces(renderer.camera, parameters.maxSphereScale)
            it.prepareRender(renderer)
        }
    }
}
