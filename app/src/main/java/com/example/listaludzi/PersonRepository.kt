package com.example.listaludzi

import android.content.Context
import com.google.gson.Gson

class PersonRepository(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("PeopleList", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun getPeopleList(): MutableList<Person> {
        val peopleListArray = gson.fromJson(sharedPreferences.getString("peopleList", "[]"), Array<Person>::class.java)
        return peopleListArray.toMutableList()
    }

    fun savePerson(person: Person) {
        val editor = sharedPreferences.edit()
        val peopleListArray = gson.fromJson(sharedPreferences.getString("peopleList", "[]"), Array<Person>::class.java)
        val peopleList = peopleListArray.toMutableList()
        peopleList.add(person)
        editor.putString("peopleList", gson.toJson(peopleList))
        editor.apply()
    }

    fun deletePerson(person: Person) {
        val editor = sharedPreferences.edit()
        val peopleListArray = gson.fromJson(sharedPreferences.getString("peopleList", "[]"), Array<Person>::class.java)
        val peopleList = peopleListArray.toMutableList()
        peopleList.remove(person)
        editor.putString("peopleList", gson.toJson(peopleList))
        editor.apply()
    }
}