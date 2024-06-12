package com.example.kashmirmarsiyaapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView

class NohaActivity : AppCompatActivity(), LanguageAdapter.OnItemClickListener {

    private lateinit var nohaAdapter: LanguageAdapter
    private lateinit var nohaList: List<LanguageData>
    private lateinit var db: NohaDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noha)

        db = NohaDatabaseHelper(this)
        nohaList = db.getAllNohas()

        val recyclerView: RecyclerView = findViewById(R.id.noharecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        nohaAdapter = LanguageAdapter(nohaList, this)
        recyclerView.adapter = nohaAdapter

        val searchView: SearchView = findViewById(R.id.nohasearch)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = nohaList.filter {
                    it.title.contains(newText ?: "", ignoreCase = true)
                }
                nohaAdapter.setFilteredList(filteredList)
                return true
            }
        })
    }

    override fun onItemClick(position: Int) {
        val selectedNoha = nohaList[position]
        val intent = Intent(this, TextViewerActivity::class.java).apply {
            putExtra("HEADING", selectedNoha.heading)
            putExtra("CONTENT", selectedNoha.content)
        }
        startActivity(intent)
    }
}
