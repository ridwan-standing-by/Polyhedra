package com.ridwanstandingby.polyhedra.domain

import com.ridwanstandingby.polyhedra.domain.polyhedra.PolyhedronFactory
import com.ridwanstandingby.verve.animation.AnimationParameters
import com.ridwanstandingby.verve.math.SphericalVector2
import com.ridwanstandingby.verve.math.Vector3
import kotlin.math.exp
import kotlin.math.log
import kotlin.random.Random

class PolyhedraAnimationParameters(
    val numberOfCubes: Int = 100,
    val maxSphereScale: Double = 2.0,
    private val cubeLengthMin: Double = 10.0,
    private val cubeLengthMax: Double = 200.0,
    private val cubeLengthSkew: Double = 0.04,
    private val velocityMin: Double = 10.0,
    private val velocityMax: Double = 400.0,
    private val angularVelocityMin: Double = 0.2,
    private val angularVelocityMax: Double = 4.0
) : AnimationParameters() {

    private fun randomSph2() =
        SphericalVector2(
            Random.nextDouble(0.0, Math.PI * 2),
            Random.nextDouble(0.0, Math.PI)
        )

    fun generateRandom(polyhedron: PolyhedronFactory) = polyhedron.constructor(
        -log(
            exp(-cubeLengthMin * cubeLengthSkew) - Random.nextDouble() * (exp(
                -cubeLengthMin * cubeLengthSkew
            ) - exp(-cubeLengthMax * cubeLengthSkew)), Math.E
        ) / cubeLengthSkew,
        Vector3(0.0, 0.0, 0.0),
        (randomSph2() * Random.nextDouble(velocityMin, velocityMax)).resolve(),
        randomSph2().resolve(),
        Random.nextDouble(0.0, Math.PI * 2),
        Random.nextDouble(angularVelocityMin, angularVelocityMax)
    )
}
