package com.example.minesweeper

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class Minesweeper (context: Context, attrs: AttributeSet) : View(context, attrs){
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var cellColor = Color.BLACK
    private var backColor = Color.WHITE

    private var sizeH = 10
    private var sizeW = 10

    private var cellSize = 100
    
    override fun onDraw(canvas: Canvas){
        var x = 0
        var y = 0

        drawBackground(canvas)
        while (x < sizeW) {
            while(y < sizeH){
                drawCell(canvas, x, y, cellColor)
                y++
            }
            y = 0
            x++
        }
    }

    private fun drawBackground(canvas: Canvas){
        paint.color = backColor
        paint.style = Paint.Style.FILL

        val backgroud = Rect(25, 0, ((cellSize + 2) * sizeH)+ 10, ((cellSize + 2) * sizeW))
        canvas.drawRect(backgroud, paint)
    }

    private fun drawCell(canvas: Canvas, posX: Int, posY: Int, color: Int) {
        paint.color = color
        paint.style = Paint.Style.FILL

        val cell = Rect((posX * cellSize) + 30, (posY * cellSize) + 5, ((posX  * cellSize) + cellSize) + 25, (posY  * cellSize) + cellSize)
        canvas.drawRect(cell, paint)
    }
}