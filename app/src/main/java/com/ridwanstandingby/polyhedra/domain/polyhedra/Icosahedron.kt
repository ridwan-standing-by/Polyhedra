package com.ridwanstandingby.polyhedra.domain.polyhedra

import com.ridwanstandingby.polyhedra.domain.PolyhedraAnimationRenderer
import com.ridwanstandingby.verve.math.FloatVector2
import com.ridwanstandingby.verve.math.Quaternion
import com.ridwanstandingby.verve.math.Vector3
import kotlin.math.sqrt

class Icosahedron(
    a: Double,
    position: Vector3,
    velocity: Vector3,
    orientation: Vector3,
    rotation: Double,
    angularVelocity: Double
) : Polyhedron(a, position, velocity, orientation, rotation, angularVelocity) {

    private val g = a * (sqrt(5.0) + 1.0) / 2.0

    private var opg: FloatVector2 = FloatVector2(0f, 0f)
    private var oph: FloatVector2 = FloatVector2(0f, 0f)
    private var omg: FloatVector2 = FloatVector2(0f, 0f)
    private var omh: FloatVector2 = FloatVector2(0f, 0f)
    private var gop: FloatVector2 = FloatVector2(0f, 0f)
    private var hop: FloatVector2 = FloatVector2(0f, 0f)
    private var gom: FloatVector2 = FloatVector2(0f, 0f)
    private var hom: FloatVector2 = FloatVector2(0f, 0f)
    private var pgo: FloatVector2 = FloatVector2(0f, 0f)
    private var pho: FloatVector2 = FloatVector2(0f, 0f)
    private var mgo: FloatVector2 = FloatVector2(0f, 0f)
    private var mho: FloatVector2 = FloatVector2(0f, 0f)

    override fun vertices() =
        listOf(opg, oph, omg, omh, gop, hop, gom, hom, pgo, pho, mgo, mho)

    override fun prepareRender(renderer: PolyhedraAnimationRenderer) {
        val transform = Quaternion(rotation, orientation)
        opg = renderer.camera.project(position + Vector3(0.0, a, g).rotate(transform))
        oph = renderer.camera.project(position + Vector3(0.0, -a, g).rotate(transform))
        omg = renderer.camera.project(position + Vector3(0.0, a, -g).rotate(transform))
        omh = renderer.camera.project(position + Vector3(0.0, -a, -g).rotate(transform))
        gop = renderer.camera.project(position + Vector3(g, 0.0, a).rotate(transform))
        hop = renderer.camera.project(position + Vector3(g, 0.0, -a).rotate(transform))
        gom = renderer.camera.project(position + Vector3(-g, 0.0, a).rotate(transform))
        hom = renderer.camera.project(position + Vector3(-g, 0.0, -a).rotate(transform))
        pgo = renderer.camera.project(position + Vector3(a, g, 0.0).rotate(transform))
        pho = renderer.camera.project(position + Vector3(-a, g, 0.0).rotate(transform))
        mgo = renderer.camera.project(position + Vector3(a, -g, 0.0).rotate(transform))
        mho = renderer.camera.project(position + Vector3(-a, -g, 0.0).rotate(transform))

        renderer.lines.add(
            floatArrayOf(
                opg.x, opg.y, pgo.x, pgo.y, pgo.x, pgo.y, hop.x, hop.y,
                hop.x, hop.y, mgo.x, mgo.y, mgo.x, mgo.y, oph.x, oph.y,
                oph.x, oph.y, opg.x, opg.y, gop.x, gop.y, opg.x, opg.y,
                gop.x, gop.y, pgo.x, pgo.y, gop.x, gop.y, hop.x, hop.y,
                gop.x, gop.y, mgo.x, mgo.y, gop.x, gop.y, oph.x, oph.y,
                omh.x, omh.y, mho.x, mho.y, mho.x, mho.y, gom.x, gom.y,
                gom.x, gom.y, pho.x, pho.y, pho.x, pho.y, omg.x, omg.y,
                omg.x, omg.y, omh.x, omh.y, hom.x, hom.y, omh.x, omh.y,
                hom.x, hom.y, mho.x, mho.y, hom.x, hom.y, gom.x, gom.y,
                hom.x, hom.y, pho.x, pho.y, hom.x, hom.y, omg.x, omg.y,
                opg.x, opg.y, pho.x, pho.y, pho.x, pho.y, pgo.x, pgo.y,
                pgo.x, pgo.y, omg.x, omg.y, omg.x, omg.y, hop.x, hop.y,
                hop.x, hop.y, omh.x, omh.y, omh.x, omh.y, mgo.x, mgo.y,
                mgo.x, mgo.y, mho.x, mho.y, mho.x, mho.y, oph.x, oph.y,
                oph.x, oph.y, gom.x, gom.y, gom.x, gom.y, opg.x, opg.y
            )
        )
    }
}
