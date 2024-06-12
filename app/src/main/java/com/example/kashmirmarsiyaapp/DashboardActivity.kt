package com.example.kashmirmarsiyaapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        val myCardView: CardView = findViewById(R.id.cardView2)
        myCardView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val newsCardView: CardView = findViewById(R.id.nohaCard)
        newsCardView.setOnClickListener {
            val intent= Intent(this,NohaActivity::class.java)
            startActivity(intent)
        }

        val newCardView: CardView = findViewById(R.id.contactCard)
        newCardView.setOnClickListener {
            val intent= Intent(this,ContactActivity::class.java)
            startActivity(intent)
        }


    }
}