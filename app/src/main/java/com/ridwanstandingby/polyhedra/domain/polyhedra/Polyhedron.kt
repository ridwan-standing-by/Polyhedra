package com.ridwanstandingby.polyhedra.domain.polyhedra

import com.ridwanstandingby.polyhedra.domain.PolyhedraAnimationRenderer
import com.ridwanstandingby.verve.math.FloatVector2
import com.ridwanstandingby.verve.math.Vector3
import com.ridwanstandingby.verve.render.Camera
import kotlin.math.max

abstract class Polyhedron(
    var a: Double,
    var position: Vector3,
    var velocity: Vector3,
    var orientation: Vector3,
    var rotation: Double,
    var angularVelocity: Double
) {

    abstract fun vertices(): List<FloatVector2>

    fun update(dt: Double) {
        position += velocity * dt
        rotation += angularVelocity * dt
    }

    fun handleBounces(camera: Camera, maxSphereScale: Double) {
        handleBounceOffCameraSides(camera)
        handleBounceOffMaximumSphere(camera, maxSphereScale)
        handleBounceInScreenDirection(camera, maxSphereScale)
    }

    private fun handleBounceOffCameraSides(camera: Camera) {
        val vertices = vertices()
        velocity = when {
            vertices.any { it.x < 0 } && velocity dot camera.leftDirection > 0 ->
                velocity.reflect(camera.leftDirection)
            vertices.any { it.y < 0 } && velocity dot camera.upDirection > 0 ->
                velocity.reflect(camera.upDirection)
            vertices.any { it.x > camera.screenDimension.x } && velocity dot camera.rightDirection > 0 ->
                velocity.reflect(camera.rightDirection)
            vertices.any { it.y > camera.screenDimension.y } && velocity dot camera.downDirection > 0 ->
                velocity.reflect(camera.downDirection)
            else -> velocity
        }
    }

    private fun handleBounceOffMaximumSphere(camera: Camera, maxSphereScale: Double) {
        if (isOffScreen(camera) && isOutsideMaximumSphere(maxSphereScale, camera) && isTravellingRadiallyOutwards()) {
            velocity = velocity.reflect(position.normalise())
        }
    }

    private fun handleBounceInScreenDirection(camera: Camera, maxSphereScale: Double) {
        if (!isOffScreen(camera) && isOutsideMaximumSphere(maxSphereScale, camera)) {
            velocity = velocity.reflect(camera.direction)
        }
    }

    private fun isOffScreen(camera: Camera) = vertices()
        .none { 0 <= it.x && it.x <= camera.screenDimension.x && 0 <= it.y && it.y <= camera.screenDimension.y }

    private fun isOutsideMaximumSphere(maxSphereScale: Double, camera: Camera) =
        position.size() > maxSphereScale * max(camera.screenDimension.x, camera.screenDimension.y)

    private fun isTravellingRadiallyOutwards() = position dot velocity > 0

    abstract fun prepareRender(renderer: PolyhedraAnimationRenderer)
}
