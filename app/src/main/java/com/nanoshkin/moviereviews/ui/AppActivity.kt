package com.nanoshkin.moviereviews.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nanoshkin.moviereviews.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
    }
}