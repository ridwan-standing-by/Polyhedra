package com.ridwanstandingby.polyhedra.domain.polyhedra

import com.ridwanstandingby.polyhedra.domain.PolyhedraAnimationRenderer
import com.ridwanstandingby.verve.math.FloatVector2
import com.ridwanstandingby.verve.math.Quaternion
import com.ridwanstandingby.verve.math.Vector3

class Tetrahedron(
    a: Double,
    position: Vector3,
    velocity: Vector3,
    orientation: Vector3,
    rotation: Double
) : Polyhedron(a, position, velocity, orientation, rotation) {

    private var vertexMMP: FloatVector2 = FloatVector2(0f, 0f)
    private var vertexMPM: FloatVector2 = FloatVector2(0f, 0f)
    private var vertexPMM: FloatVector2 = FloatVector2(0f, 0f)
    private var vertexPPP: FloatVector2 = FloatVector2(0f, 0f)

    override fun screenVertices() =
        listOf(vertexMMP, vertexMPM, vertexPMM, vertexPPP)

    override fun prepareRender(renderer: PolyhedraAnimationRenderer) {
        screenPosition = renderer.camera.project(position)

        val transform = Quaternion(rotation, orientation)
        vertexMMP = renderer.camera.project(position + Vector3(-a, -a, a).rotate(transform))
        vertexMPM = renderer.camera.project(position + Vector3(-a, a, -a).rotate(transform))
        vertexPMM = renderer.camera.project(position + Vector3(a, -a, -a).rotate(transform))
        vertexPPP = renderer.camera.project(position + Vector3(a, a, a).rotate(transform))

        renderer.lines.add(
            floatArrayOf(
                vertexPPP.x, vertexPPP.y, vertexPMM.x, vertexPMM.y, vertexPPP.x, vertexPPP.y, vertexMPM.x, vertexMPM.y,
                vertexPPP.x, vertexPPP.y, vertexMMP.x, vertexMMP.y, vertexMMP.x, vertexMMP.y, vertexMPM.x, vertexMPM.y,
                vertexMPM.x, vertexMPM.y, vertexPMM.x, vertexPMM.y, vertexPMM.x, vertexPMM.y, vertexMMP.x, vertexMMP.y
            )
        )
    }
}
