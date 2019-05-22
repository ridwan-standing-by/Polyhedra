package com.ridwanstandingby.polyhedra.domain.polyhedra

import com.ridwanstandingby.polyhedra.domain.PolyhedraAnimationRenderer
import com.ridwanstandingby.verve.math.FloatVector2
import com.ridwanstandingby.verve.math.Quaternion
import com.ridwanstandingby.verve.math.Vector3

class Cube(
    a: Double,
    position: Vector3,
    velocity: Vector3,
    orientation: Vector3,
    rotation: Double
): Polyhedron(a, position, velocity, orientation, rotation) {

    private var vertexMMM: FloatVector2 = FloatVector2(0f, 0f)
    private var vertexMMP: FloatVector2 = FloatVector2(0f, 0f)
    private var vertexMPM: FloatVector2 = FloatVector2(0f, 0f)
    private var vertexMPP: FloatVector2 = FloatVector2(0f, 0f)
    private var vertexPMM: FloatVector2 = FloatVector2(0f, 0f)
    private var vertexPMP: FloatVector2 = FloatVector2(0f, 0f)
    private var vertexPPM: FloatVector2 = FloatVector2(0f, 0f)
    private var vertexPPP: FloatVector2 = FloatVector2(0f, 0f)

    override fun vertices() =
        listOf(vertexMMM, vertexMMP, vertexMPM, vertexMPP, vertexPMM, vertexPMP, vertexPPM, vertexPPP)

    override fun prepareRender(renderer: PolyhedraAnimationRenderer) {
        val transform = Quaternion(rotation, orientation)
        vertexMMM = renderer.camera.project(position + Vector3(-a, -a, -a).rotate(transform))
        vertexMMP = renderer.camera.project(position + Vector3(-a, -a, a).rotate(transform))
        vertexMPM = renderer.camera.project(position + Vector3(-a, a, -a).rotate(transform))
        vertexMPP = renderer.camera.project(position + Vector3(-a, a, a).rotate(transform))
        vertexPMM = renderer.camera.project(position + Vector3(a, -a, -a).rotate(transform))
        vertexPMP = renderer.camera.project(position + Vector3(a, -a, a).rotate(transform))
        vertexPPM = renderer.camera.project(position + Vector3(a, a, -a).rotate(transform))
        vertexPPP = renderer.camera.project(position + Vector3(a, a, a).rotate(transform))

        renderer.lines.add(
            floatArrayOf(
                vertexMMM.x, vertexMMM.y, vertexMMP.x, vertexMMP.y, vertexMMP.x, vertexMMP.y, vertexMPP.x, vertexMPP.y,
                vertexMPP.x, vertexMPP.y, vertexMPM.x, vertexMPM.y, vertexMPP.x, vertexMPP.y, vertexPPP.x, vertexPPP.y,
                vertexPPP.x, vertexPPP.y, vertexPMP.x, vertexPMP.y, vertexPMP.x, vertexPMP.y, vertexMMP.x, vertexMMP.y,
                vertexPMP.x, vertexPMP.y, vertexPMM.x, vertexPMM.y, vertexPMM.x, vertexPMM.y, vertexPPM.x, vertexPPM.y,
                vertexPPM.x, vertexPPM.y, vertexPPP.x, vertexPPP.y, vertexPPM.x, vertexPPM.y, vertexMPM.x, vertexMPM.y,
                vertexMPM.x, vertexMPM.y, vertexMMM.x, vertexMMM.y, vertexMMM.x, vertexMMM.y, vertexPMM.x, vertexPMM.y
            )
        )
    }
}
