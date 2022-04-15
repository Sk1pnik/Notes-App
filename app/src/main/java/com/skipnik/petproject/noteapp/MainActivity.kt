package com.skipnik.petproject.noteapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.skipnik.petproject.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}