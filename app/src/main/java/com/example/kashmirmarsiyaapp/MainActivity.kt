package com.example.kashmirmarsiyaapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale
import kotlin.math.log

class MainActivity : AppCompatActivity(), LanguageAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<LanguageData>()
    private lateinit var adapter: LanguageAdapter
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "This is a debug message")
        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)
        dbHelper = DatabaseHelper(this)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Populate the database with initial data if empty


        addDataToList()

        adapter = LanguageAdapter(mList, this)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<LanguageData>()
            for (i in mList) {
                if (i.title.toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))) {
                    filteredList.add(i)
                }
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No data Found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList() {
        mList.addAll(dbHelper.getAllMarsiyas())
        Log.d("MainActivity", "This is a debug message")
    }

    override fun onItemClick(position: Int) {
        val selectedLanguage = mList[position]
        val heading = selectedLanguage.heading
        val content = dbHelper.getMarsiyaContent(selectedLanguage.title)

        if (content != null && heading != null) {
            Log.d("MainActivity", "Retrieved content and heading for: ${selectedLanguage.title}")
            val intent = Intent(this, TextViewerActivity::class.java)
            intent.putExtra("HEADING", heading)
            intent.putExtra("CONTENT", content)
            startActivity(intent)
        } else {
            Log.d("MainActivity", "Content or heading not available for: ${selectedLanguage.title}")
            Toast.makeText(this, "Marsiya Not Available", Toast.LENGTH_SHORT).show()
        }
    }
}
