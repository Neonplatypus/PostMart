package com.PosterMaster.Posters.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.PosterMaster.Posters.R

class StrokeTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var strokeWidth: Float = 0f
    private var strokeColor: Int = 0

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.StrokeTextView)
        strokeWidth = typedArray.getFloat(R.styleable.StrokeTextView_strokeWidth, 0f)
        strokeColor = typedArray.getColor(R.styleable.StrokeTextView_strokeColor, 0)
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        if (strokeWidth > 0) {
            val textColor = textColors.defaultColor
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = strokeWidth
            setTextColor(strokeColor)
            super.onDraw(canvas)

            paint.style = Paint.Style.FILL
            setTextColor(textColor)
        }
        super.onDraw(canvas)
    }
} 