package com.example.gismeteoapitestapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gismeteoapitestapp.view.MainFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        TODO() make adecuate routing
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment())
            .commit()
    }
}