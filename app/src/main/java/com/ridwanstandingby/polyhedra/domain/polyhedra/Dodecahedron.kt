package com.ridwanstandingby.polyhedra.domain.polyhedra

import com.ridwanstandingby.polyhedra.domain.PolyhedraAnimationRenderer
import com.ridwanstandingby.verve.math.FloatVector2
import com.ridwanstandingby.verve.math.Quaternion
import com.ridwanstandingby.verve.math.Vector3
import kotlin.math.sqrt

class Dodecahedron(
    a: Double,
    position: Vector3,
    velocity: Vector3,
    orientation: Vector3,
    rotation: Double
) : Polyhedron(a, position, velocity, orientation, rotation) {

    private val g = a * (sqrt(5.0) + 1.0) / 2.0
    private val s = a * 2.0 / (sqrt(5.0) + 1.0)

    private var mmm: FloatVector2 = FloatVector2.O
    private var mmp: FloatVector2 = FloatVector2.O
    private var mpm: FloatVector2 = FloatVector2.O
    private var mpp: FloatVector2 = FloatVector2.O
    private var pmm: FloatVector2 = FloatVector2.O
    private var pmp: FloatVector2 = FloatVector2.O
    private var ppm: FloatVector2 = FloatVector2.O
    private var ppp: FloatVector2 = FloatVector2.O
    private var ohh: FloatVector2 = FloatVector2.O
    private var ohg: FloatVector2 = FloatVector2.O
    private var ogh: FloatVector2 = FloatVector2.O
    private var ogg: FloatVector2 = FloatVector2.O
    private var hoh: FloatVector2 = FloatVector2.O
    private var hog: FloatVector2 = FloatVector2.O
    private var goh: FloatVector2 = FloatVector2.O
    private var gog: FloatVector2 = FloatVector2.O
    private var hho: FloatVector2 = FloatVector2.O
    private var hgo: FloatVector2 = FloatVector2.O
    private var gho: FloatVector2 = FloatVector2.O
    private var ggo: FloatVector2 = FloatVector2.O

    override fun screenVertices() =
        listOf(mmm, mmp, mpm, mpp, pmm, pmp, ppm, ppp, ohh, ohg, ogh, ogg, hoh, hog, goh, gog, hho, hgo, gho, ggo)

    override fun prepareRender(renderer: PolyhedraAnimationRenderer) {
        screenPosition = renderer.camera.project(position)

        val transform = Quaternion(rotation, orientation)
        mmm = renderer.camera.project(position + Vector3(-a, -a, -a).rotate(transform))
        mmp = renderer.camera.project(position + Vector3(-a, -a, a).rotate(transform))
        mpm = renderer.camera.project(position + Vector3(-a, a, -a).rotate(transform))
        mpp = renderer.camera.project(position + Vector3(-a, a, a).rotate(transform))
        pmm = renderer.camera.project(position + Vector3(a, -a, -a).rotate(transform))
        pmp = renderer.camera.project(position + Vector3(a, -a, a).rotate(transform))
        ppm = renderer.camera.project(position + Vector3(a, a, -a).rotate(transform))
        ppp = renderer.camera.project(position + Vector3(a, a, a).rotate(transform))
        ohh = renderer.camera.project(position + Vector3(0.0, -g, -s).rotate(transform))
        ohg = renderer.camera.project(position + Vector3(0.0, -g, s).rotate(transform))
        ogh = renderer.camera.project(position + Vector3(0.0, g, -s).rotate(transform))
        ogg = renderer.camera.project(position + Vector3(0.0, g, s).rotate(transform))
        hoh = renderer.camera.project(position + Vector3(-s, 0.0, -g).rotate(transform))
        hog = renderer.camera.project(position + Vector3(-s, 0.0, g).rotate(transform))
        goh = renderer.camera.project(position + Vector3(s, 0.0, -g).rotate(transform))
        gog = renderer.camera.project(position + Vector3(s, 0.0, g).rotate(transform))
        hho = renderer.camera.project(position + Vector3(-g, -s, 0.0).rotate(transform))
        hgo = renderer.camera.project(position + Vector3(-g, s, 0.0).rotate(transform))
        gho = renderer.camera.project(position + Vector3(g, -s, 0.0).rotate(transform))
        ggo = renderer.camera.project(position + Vector3(g, s, 0.0).rotate(transform))

        renderer.lines.add(
            floatArrayOf(
                ppp.x, ppp.y, ggo.x, ggo.y, ggo.x, ggo.y, gho.x, gho.y,
                gho.x, gho.y, pmm.x, pmm.y, pmm.x, pmm.y, ohh.x, ohh.y,
                ohh.x, ohh.y, mmm.x, mmm.y, mmm.x, mmm.y, hho.x, hho.y,
                hho.x, hho.y, hgo.x, hgo.y, hgo.x, hgo.y, mpp.x, mpp.y,
                mpp.x, mpp.y, ogg.x, ogg.y, ogg.x, ogg.y, ppp.x, ppp.y,
                gog.x, gog.y, pmp.x, pmp.y, pmp.x, pmp.y, ohg.x, ohg.y,
                ohg.x, ohg.y, mmp.x, mmp.y, mmp.x, mmp.y, hog.x, hog.y,
                hog.x, hog.y, gog.x, gog.y, gog.x, gog.y, ppp.x, ppp.y,
                pmp.x, pmp.y, gho.x, gho.y, ohg.x, ohg.y, ohh.x, ohh.y,
                mmp.x, mmp.y, hho.x, hho.y, hog.x, hog.y, mpp.x, mpp.y,
                ppm.x, ppm.y, goh.x, goh.y, goh.x, goh.y, hoh.x, hoh.y,
                hoh.x, hoh.y, mpm.x, mpm.y, mpm.x, mpm.y, ogh.x, ogh.y,
                ogh.x, ogh.y, ppm.x, ppm.y, ppm.x, ppm.y, ggo.x, ggo.y,
                goh.x, goh.y, pmm.x, pmm.y, hoh.x, hoh.y, mmm.x, mmm.y,
                mpm.x, mpm.y, hgo.x, hgo.y, ogh.x, ogh.y, ogg.x, ogg.y
            )
        )
    }
}
