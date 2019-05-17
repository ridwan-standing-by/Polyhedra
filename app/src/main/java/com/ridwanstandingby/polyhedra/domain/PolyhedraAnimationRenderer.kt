package com.ridwanstandingby.polyhedra.domain

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.ridwanstandingby.verve.animation.AnimationRenderer
import com.ridwanstandingby.verve.math.IntVector2
import com.ridwanstandingby.verve.render.Camera
import com.ridwanstandingby.verve.render.rotationSensorOutputToOuterSphericalCameraDirection

class PolyhedraAnimationRenderer(screenDimension: IntVector2) : AnimationRenderer() {

    val camera = Camera(screenDimension, rotationSensorOutputToOuterSphericalCameraDirection)

    var lines = mutableListOf<FloatArray>()

    private val lineStyle = Paint().apply { color = Color.WHITE }

    override fun updateCanvas(canvas: Canvas) {
        canvas.drawColor(Color.BLACK)
        lines.forEach {
            canvas.drawLines(it, lineStyle)
        }
    }
}
