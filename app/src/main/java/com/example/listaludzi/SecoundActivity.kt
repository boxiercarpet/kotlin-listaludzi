package com.example.listaludzi

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class SecoundActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var personAdapter: PersonAdapter
    private lateinit var peopleList: MutableList<Person>
    private lateinit var personRepository: PersonRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_secound)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        personRepository = PersonRepository(this)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadPeopleList()
        personAdapter = PersonAdapter(this, peopleList) { person ->
            deletePerson(person)
        }
        recyclerView.adapter = personAdapter
    }

    private fun loadPeopleList() {
        peopleList = personRepository.getPeopleList()
    }

    private fun deletePerson(person: Person) {
        personRepository.deletePerson(person)
        loadPeopleList()
        personAdapter.notifyDataSetChanged()
    }
}