package com.mobi.ezypod_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobi.ezypod.Common
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_list.setOnClickListener {
            startActivity(Intent(this, CardListActivity::class.java))
        }
        button_add_card.setOnClickListener {
            startActivity(Intent(this, AddCardActivity::class.java))
        }

    }
}