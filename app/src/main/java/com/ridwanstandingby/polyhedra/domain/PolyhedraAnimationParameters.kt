package com.ridwanstandingby.polyhedra.domain

import com.ridwanstandingby.polyhedra.domain.polyhedra.PolyhedronType
import com.ridwanstandingby.verve.animation.AnimationParameters
import com.ridwanstandingby.verve.math.SphericalVector2
import com.ridwanstandingby.verve.math.Vector3
import kotlin.math.exp
import kotlin.math.log
import kotlin.random.Random

class PolyhedraAnimationParameters(
    val numberOfPolyhedra: Int = 100,
    val maxSphereScale: Double = 2.0,
    val swipePixelRadius: Float = 120f,
    val swipeStrength: Double = 0.16,
    val velocityDecayRate: Double = 0.000008,
    val angularToLinearSpeedRatio: Double = 0.01,
    private val cubeLengthMin: Double = 12.0,
    private val cubeLengthMax: Double = 120.0,
    private val cubeLengthSkew: Double = 0.05,
    private val velocityMin: Double = 18.0,
    private val velocityMax: Double = 360.0
) : AnimationParameters() {

    fun generateRandomPolyhedron() = generateRandom(randomPolyhedronType())

    private fun randomPolyhedronType(): PolyhedronType =
        when (Random.nextInt(10)) {
            in 0..1 -> PolyhedronType.TETRAHEDRON
            in 2..4 -> PolyhedronType.CUBE
            in 5..7 -> PolyhedronType.OCTAHEDRON
            8 -> PolyhedronType.DODECAHEDRON
            9 -> PolyhedronType.ICOSAHEDRON
            else -> PolyhedronType.CUBE
        }

    private fun generateRandom(polyhedron: PolyhedronType) = polyhedron.constructor(
        -log(
            exp(-cubeLengthMin * cubeLengthSkew) - Random.nextDouble() * (exp(
                -cubeLengthMin * cubeLengthSkew
            ) - exp(-cubeLengthMax * cubeLengthSkew)), Math.E
        ) / cubeLengthSkew,
        Vector3.O,
        (randomSph2() * Random.nextDouble(velocityMin, velocityMax)).resolve(),
        randomSph2().resolve(),
        Random.nextDouble(0.0, Math.PI * 2)
    )

    private fun randomSph2() =
        SphericalVector2(
            Random.nextDouble(0.0, Math.PI * 2),
            Random.nextDouble(0.0, Math.PI)
        )
}
