package com.example.rappitest.movies.custom

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.rappitest.R

class RatingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        const val PERCENTAGE_VALUE = "percentage"
    }

    var parentArcColor: Int? = null
    var fillArcColor: Int? = null
    var angle_start: Float? = null
    var current_percent: Int? = null

    init {
        isClickable = false
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingView)
        typedArray.getColor(R.styleable.RatingView_parentArcColor, 0)
        typedArray.getColor(R.styleable.RatingView_fillArcColor, 0)
        typedArray.getFloat(R.styleable.RatingView_angle_start, 0f)
        typedArray.getInt(R.styleable.RatingView_current_percent, 0)
        typedArray.recycle()
    }

    private val ovalSpace = RectF()


    override fun onDraw(canvas: Canvas?) {
        setSpace()
        canvas?.let {
            drawBackgroundArc(it)
            drawPercent(it)
            drawInnerArc(it)
        }
        //animateProgress()
    }

    private fun setSpace() {
        val horizontalCenter = (width.div(2)).toFloat()
        val verticalCenter = (height.div(2)).toFloat()
        val ovalSize = 40
        ovalSpace.set(
            horizontalCenter - ovalSize,
            verticalCenter - ovalSize,
            horizontalCenter + ovalSize,
            verticalCenter + ovalSize
        )
    }

    private fun drawPercent(it: Canvas) {
        val textPaint = Paint()
        textPaint.textSize = 30.0f
        textPaint.typeface = Typeface.create("", Typeface.BOLD)
        textPaint.color = Color.WHITE
        textPaint.textAlign = Paint.Align.CENTER
        val xPos = it.width / 2
        val yPos = (it.height / 2 - (textPaint.descent() + textPaint.ascent()) / 2).toInt()
        it.drawText("$current_percent%", xPos.toFloat(), yPos.toFloat(), textPaint)
    }

    private fun drawBackgroundArc(it: Canvas) {
        val parentArcPaint = Paint()
        parentArcPaint.style = Paint.Style.STROKE
        parentArcPaint.isAntiAlias = true
        parentArcPaint.color = ContextCompat.getColor(context, parentArcColor!!)
        parentArcPaint.strokeWidth = 10f
        it.drawArc(ovalSpace, 0f, 360f, false, parentArcPaint)
    }

    private fun drawInnerArc(canvas: Canvas) {
        val fillArcPaint = Paint()
        fillArcPaint.style = Paint.Style.STROKE
        fillArcPaint.isAntiAlias = true
        fillArcPaint.color = ContextCompat.getColor(context, fillArcColor!!)
        fillArcPaint.strokeWidth = 10f
        fillArcPaint.strokeCap = Paint.Cap.ROUND

        canvas.drawArc(ovalSpace, 270f, angle_start!!, false, fillArcPaint)
    }

    fun animateProgress() {
        val valuesHolder = PropertyValuesHolder.ofFloat("percentage", 0f, 100f)
        val animator = ValueAnimator().apply {
            setValues(valuesHolder)
            duration = 1000
            addUpdateListener {
                //val percentage = it.getAnimatedValue(PERCENTAGE_VALUE) as Float
                //currentPercentage = percentage.toInt()
                invalidate()
            }
        }
        animator.start()
    }
}