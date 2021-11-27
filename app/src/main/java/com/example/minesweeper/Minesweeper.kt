package com.example.minesweeper

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class Minesweeper (context: Context, attrs: AttributeSet) : View(context, attrs){
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var cellColor = Color.BLACK
    private var backColor = Color.WHITE
    private var digColor = Color.GRAY

    private var sizeH = 10
    private var sizeW = 10

    private var cellSize = 100
    private var gameBoard = Array(sizeH){ IntArray(sizeW){0} }

    override fun onDraw(canvas: Canvas){
        var x = 0
        var y = 0

        drawBackground(canvas)
        while (x < sizeW) {
            while(y < sizeH){
                drawCell(canvas, x, y)
                y++
            }
            y = 0
            x++
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        var touchX : Int = (event.x/100).toInt()
        var touchY : Int= (event.y/100).toInt()

        Log.i("X:", touchX.toString())
        Log.i("Y:", touchY.toString())
        if (touchX < sizeW && touchY < sizeW && touchX >= 0 && touchY >= 0)
            gameBoard[touchX][touchY] = -2

        invalidate()
        return true
    }

    private fun drawBackground(canvas: Canvas){
        paint.color = backColor
        paint.style = Paint.Style.FILL

        val backgroud = Rect(25, 0, ((cellSize + 2) * sizeH)+ 10, ((cellSize + 2) * sizeW))
        canvas.drawRect(backgroud, paint)
    }

    private fun drawCell(canvas: Canvas, posX: Int, posY: Int) {
        if (gameBoard[posX][posY] == 0)
            paint.color = cellColor
        else if (gameBoard[posX][posY] == -2)
            paint.color = digColor
        paint.style = Paint.Style.FILL

        val cell = Rect((posX * cellSize) + 30, (posY * cellSize) + 5, ((posX  * cellSize) + cellSize) + 25, (posY  * cellSize) + cellSize)
        canvas.drawRect(cell, paint)
    }
}