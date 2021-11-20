package com.example.minesweeper


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var _ttmines: TextView
    private lateinit var _reset: Button
    private lateinit var _uncover: Button
    private lateinit var _minesweeper: Minesweeper

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        _minesweeper = findViewById(R.id.minesweeper)
        _ttmines = findViewById(R.id.ttmines)
        _ttmines.text = "20"
        _reset = findViewById<Button>(R.id.reset)
        _reset.setOnClickListener {
            _minesweeper.reset()
        }
        _uncover = findViewById<Button>(R.id.uncover)
        _uncover.setOnClickListener {
            if (_minesweeper.mode) {
                _minesweeper.mode = false
                _uncover.text = "Marking Mode"
            } else {
                _minesweeper.mode = true
                _uncover.text = "Uncover Mode"
            }
        }
    }
}