package com.example.kashmirmarsiyaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

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

        val newsCardView: CardView = findViewById(R.id.cardView5)
        newsCardView.setOnClickListener {
            Toast.makeText(this, "Coming Soon!", Toast.LENGTH_SHORT).show()
        }

        val newCardView: CardView = findViewById(R.id.cardView4)
        newCardView.setOnClickListener {
            Toast.makeText(this, "Coming Soon!", Toast.LENGTH_SHORT).show()
        }


    }
}