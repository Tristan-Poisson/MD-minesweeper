package com.example.minesweeper

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class Minesweeper (context: Context, attrs: AttributeSet) : View(context, attrs){
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply{
        textSize = 55.0f
    }

    private var cellColor = Color.BLACK
    private var backColor = Color.WHITE
    private var digColor = Color.GRAY
    private var mineColor = Color.RED
    private var txtColor = Color.BLACK

    private var sizeH = 10
    private var sizeW = 10

    private var cellSize = 100

    private var mine = 20
    private var gameBoard = Array(sizeH){ IntArray(sizeW){0} }
    private var mineBoard = Array(sizeH){ IntArray(sizeW){-2} }

    init {
        while (mine > 0){
            val x = Random.nextInt(0, sizeH - 1)
            val y = Random.nextInt(0, sizeW - 1)
            if (mineBoard[x][y] == -2) {
                mineBoard[x][y] = -1
                mine--
            }
        }
    }
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

        if (touchX < sizeW && touchY < sizeW && touchX >= 0 && touchY >= 0)
            gameBoard[touchX][touchY] = mineBoard[touchX][touchY]

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
        var txt = ""
        if (gameBoard[posX][posY] == 0)
            paint.color = cellColor
        else if (gameBoard[posX][posY] == -1) {
            paint.color = mineColor
            txt = "M"
        }
        else if (gameBoard[posX][posY] == -2)
            paint.color = digColor
        paint.style = Paint.Style.FILL

        val cell = Rect((posX * cellSize) + 30, (posY * cellSize) + 5, ((posX  * cellSize) + cellSize) + 25, (posY  * cellSize) + cellSize)
        canvas.drawRect(cell, paint)
        paint.color = txtColor
        canvas.drawText(txt, ((posX * cellSize) + 60).toFloat(), ((posY*cellSize) + 60).toFloat(), paint)
    }
}