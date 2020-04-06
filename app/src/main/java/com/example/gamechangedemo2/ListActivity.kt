package com.example.gamechangedemo2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
    }

    companion object{
        fun launch(activity:Activity){
            val intent = Intent(activity,ListActivity::class.java)
        }
    }
}
