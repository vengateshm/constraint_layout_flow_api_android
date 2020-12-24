package com.vengateshm.teamlineupview.teamLineUpView

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import com.vengateshm.teamlineupview.R

const val NUM_OF_GRASS_ROWS = 8

class HalfFieldDrawable(context: Context) : Drawable() {

    private val grassColor1 =
        ResourcesCompat.getColor(context.resources, R.color.grass1, context.theme)
    private val grassColor2 =
        ResourcesCompat.getColor(context.resources, R.color.grass2, context.theme)
    private val grassPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 3.0f
        color = ResourcesCompat.getColor(context.resources, R.color.fieldLine, context.theme)
    }
    private val rect = Rect()
    private val padding = 8
    private val halfInnerGoalWidth = 16
    private val halfInnerGoalHeight = 12
    private val halfGoalWidth = 52
    private val halfGoalHeight = 32
    private val cornerSize = 12
    private val middleCircleSize = 24
    private val middleInnerCircleSize = 4

    override fun draw(canvas: Canvas) {
        val width = bounds.width()
        val height = bounds.height()
        val rowHeight = height / NUM_OF_GRASS_ROWS

        with(canvas) {
            // Draw alternate grass fields
            for (i in 0..NUM_OF_GRASS_ROWS) {
                val top = i * rowHeight
                rect.set(0, top, width, top + rowHeight)
                grassPaint.color = if ((i % 2) == 0) grassColor1 else grassColor2
                drawRect(rect, grassPaint)
            }
            // Draw outer rectangle
            linePaint.style = Paint.Style.STROKE
            rect.set(padding, padding, width - padding, height - padding)

            // Draw inner goal rectangle
            rect.set(
                (width / 2 - halfInnerGoalWidth),
                padding,
                (width / 2 + halfInnerGoalWidth),
                padding + halfInnerGoalHeight
            )
            drawRect(rect, linePaint)
            // Draw goal rectangle
            rect.set(
                (width / 2 - halfGoalWidth),
                padding,
                (width / 2 + halfGoalWidth),
                padding + halfGoalHeight
            )
            drawRect(rect, linePaint)
            // Draw corner arcs
            drawArc(
                padding.toFloat() - cornerSize,
                padding.toFloat() - cornerSize,
                (padding + cornerSize).toFloat(),
                (padding + cornerSize).toFloat(),
                0f, 90f, false, linePaint
            )
            drawArc(
                (width - padding - cornerSize).toFloat(),
                padding.toFloat() - cornerSize,
                (width - padding + cornerSize).toFloat(),
                (padding + cornerSize).toFloat(),
                90f, 90f, false, linePaint
            )
            // Draw middle circles
            drawArc(
                (width / 2 - middleCircleSize).toFloat(),
                (height - padding - middleCircleSize).toFloat(),
                (width / 2 - middleCircleSize).toFloat(),
                (height - padding + middleCircleSize).toFloat(),
                180f, 180f, false, linePaint
            )
            // Draw middle circles
            drawArc(
                (width / 2 - middleInnerCircleSize).toFloat(),
                (height - padding - middleInnerCircleSize).toFloat(),
                (width / 2 - middleInnerCircleSize).toFloat(),
                (height - padding + middleInnerCircleSize).toFloat(),
                180f, 180f, false, linePaint
            )
        }
    }

    override fun setAlpha(alpha: Int) {

    }

    override fun setColorFilter(colorFilter: ColorFilter?) {

    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }
}