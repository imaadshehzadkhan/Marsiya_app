package com.example.kashmirmarsiyaapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import android.widget.ZoomControls
import androidx.appcompat.app.AppCompatActivity

class TextViewerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_viewer)

        val headingTextView: TextView = findViewById(R.id.headingTextView)
        val contentTextView: TextView = findViewById(R.id.contentTextView)
        val zoomControls: ZoomControls = findViewById(R.id.zoomControls)

        val heading = intent.getStringExtra("HEADING")
        val content = intent.getStringExtra("CONTENT")

        if (heading != null && content != null) {
            headingTextView.text = heading // Set heading text directly
            contentTextView.text = content // Set content text directly
            Log.d("TextViewerActivity", "Displaying heading and content")
        } else {
            Log.d("TextViewerActivity", "No heading or content to display")
            Toast.makeText(this, "No heading or content to display", Toast.LENGTH_SHORT).show()
        }

        zoomControls.setOnZoomInClickListener {
            val textSize = contentTextView.textSize
            contentTextView.setTextSize(android.util.TypedValue.COMPLEX_UNIT_PX, textSize + 2)
        }

        zoomControls.setOnZoomOutClickListener {
            val textSize = contentTextView.textSize
            contentTextView.setTextSize(android.util.TypedValue.COMPLEX_UNIT_PX, textSize - 2)
        }
    }
}
