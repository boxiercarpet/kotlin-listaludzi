package com.example.listaludzi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter(
    private val context: Context,
    private val peopleList: MutableList<Person>,
    private val onDeleteClick: (Person) -> Unit
): RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    inner class PersonViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nameTextView = view.findViewById<TextView>(R.id.textViewName)
        val surnameTextView = view.findViewById<TextView>(R.id.textViewSurname)
        val ageTextView = view.findViewById<TextView>(R.id.textViewAge)
        val heightTextView = view.findViewById<TextView>(R.id.textViewHeight)
        val weightTextView = view.findViewById<TextView>(R.id.textViewWeight)
        val deleteButton = view.findViewById<Button>(R.id.buttonDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = peopleList[position]
        holder.nameTextView.text = "Imię: ${person.name}"
        holder.surnameTextView.text = "Nazwisko: ${person.surname}"
        holder.ageTextView.text = "Wiek: ${person.age}"
        holder.heightTextView.text = "Wzrost: ${person.height} cm"
        holder.weightTextView.text = "Waga: ${person.weight} kg"
        holder.deleteButton.setOnClickListener {
            onDeleteClick(person)
        }
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }
}