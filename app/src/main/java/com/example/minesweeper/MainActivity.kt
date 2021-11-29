package com.example.minesweeper


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var _textview: TextView
    private lateinit var _reset: Button
    private lateinit var _minesweeper: Minesweeper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _reset = findViewById<Button>(R.id.reset)
        _minesweeper = findViewById(R.id.minesweeper)
        _reset.setOnClickListener {
            _minesweeper.reset()
        }
    }
}