package com.ridwanstandingby.polyhedra.domain.polyhedra

import com.ridwanstandingby.verve.math.Vector3

enum class PolyhedronFactory(
    val constructor: (
        a: Double,
        position: Vector3,
        velocity: Vector3,
        orientation: Vector3,
        rotation: Double,
        angularVelocity: Double
    ) -> Polyhedron
) {
    TETRAHEDRON(::Tetrahedron),
    CUBE(::Cube),
    OCTAHEDRON(::Octahedron),
    DODECAHEDRON(::Dodecahedron)
}
