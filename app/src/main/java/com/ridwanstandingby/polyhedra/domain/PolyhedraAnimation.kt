package com.ridwanstandingby.polyhedra.domain

import com.ridwanstandingby.polyhedra.domain.polyhedra.PolyhedronFactory
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

    private val polyhedra = List(parameters.numberOfPolyhedra) { parameters.generateRandom(PolyhedronFactory.DODECAHEDRON) }

    init {
        input.start()
    }

    override fun update(dt: Double) {
        renderer.camera.transform(input.getOrientation())
        renderer.lines = mutableListOf()

        polyhedra.forEach {
            it.update(dt)
            it.handleBounceOffCameraSides(renderer.camera)
            it.handleBounceOffMaximumSphere(renderer.camera, parameters.maxSphereScale)
            it.prepareRender(renderer)
        }
    }
}
