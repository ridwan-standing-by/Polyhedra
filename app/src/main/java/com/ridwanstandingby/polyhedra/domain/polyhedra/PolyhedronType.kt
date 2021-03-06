package com.ridwanstandingby.polyhedra.domain.polyhedra

import com.ridwanstandingby.verve.math.Vector3

enum class PolyhedronType(
    val constructor: (
        a: Double,
        position: Vector3,
        velocity: Vector3,
        orientation: Vector3,
        rotation: Double
    ) -> Polyhedron
) {
    TETRAHEDRON(::Tetrahedron),
    CUBE(::Cube),
    OCTAHEDRON(::Octahedron),
    DODECAHEDRON(::Dodecahedron),
    ICOSAHEDRON(::Icosahedron)
}
