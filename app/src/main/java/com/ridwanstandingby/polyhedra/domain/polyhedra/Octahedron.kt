package com.ridwanstandingby.polyhedra.domain.polyhedra

import com.ridwanstandingby.polyhedra.domain.PolyhedraAnimationRenderer
import com.ridwanstandingby.verve.math.FloatVector2
import com.ridwanstandingby.verve.math.Quaternion
import com.ridwanstandingby.verve.math.Vector3

class Octahedron(
    a: Double,
    position: Vector3,
    velocity: Vector3,
    orientation: Vector3,
    rotation: Double
) : Polyhedron(a, position, velocity, orientation, rotation) {

    private var vertexOOP: FloatVector2 = FloatVector2.O
    private var vertexOPO: FloatVector2 = FloatVector2.O
    private var vertexPOO: FloatVector2 = FloatVector2.O
    private var vertexOOM: FloatVector2 = FloatVector2.O
    private var vertexOMO: FloatVector2 = FloatVector2.O
    private var vertexMOO: FloatVector2 = FloatVector2.O

    override fun screenVertices() =
        listOf(vertexOOP, vertexOPO, vertexPOO, vertexOOM, vertexOMO, vertexMOO)

    override fun prepareRender(renderer: PolyhedraAnimationRenderer) {
        screenPosition = renderer.camera.project(position)

        val transform = Quaternion(rotation, orientation)
        vertexOOP = renderer.camera.project(position + Vector3(0.0, 0.0, a).rotate(transform))
        vertexOPO = renderer.camera.project(position + Vector3(0.0, a, 0.0).rotate(transform))
        vertexPOO = renderer.camera.project(position + Vector3(a, 0.0, 0.0).rotate(transform))
        vertexOOM = renderer.camera.project(position + Vector3(0.0, 0.0, -a).rotate(transform))
        vertexOMO = renderer.camera.project(position + Vector3(0.0, -a, 0.0).rotate(transform))
        vertexMOO = renderer.camera.project(position + Vector3(-a, 0.0, 0.0).rotate(transform))

        renderer.lines.add(
            floatArrayOf(
                vertexPOO.x, vertexPOO.y, vertexOOP.x, vertexOOP.y, vertexOOP.x, vertexOOP.y, vertexOPO.x, vertexOPO.y,
                vertexMOO.x, vertexMOO.y, vertexOOP.x, vertexOOP.y, vertexOOP.x, vertexOOP.y, vertexOMO.x, vertexOMO.y,
                vertexPOO.x, vertexPOO.y, vertexOOM.x, vertexOOM.y, vertexOOM.x, vertexOOM.y, vertexOMO.x, vertexOMO.y,
                vertexMOO.x, vertexMOO.y, vertexOOM.x, vertexOOM.y, vertexOOM.x, vertexOOM.y, vertexOPO.x, vertexOPO.y,
                vertexPOO.x, vertexPOO.y, vertexOPO.x, vertexOPO.y, vertexOPO.x, vertexOPO.y, vertexMOO.x, vertexMOO.y,
                vertexMOO.x, vertexMOO.y, vertexOMO.x, vertexOMO.y, vertexOMO.x, vertexOMO.y, vertexPOO.x, vertexPOO.y
            )
        )
    }
}
