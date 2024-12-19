package com.example.listaludzi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var personRepository: PersonRepository

    @SuppressLint("MissingInflatedId", "CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        personRepository = PersonRepository(this)

        val nameInput = findViewById<EditText>(R.id.editTextName)
        val surnameInput = findViewById<EditText>(R.id.editTextSurname)
        val ageInput = findViewById<EditText>(R.id.editTextAge)
        val heightInput = findViewById<EditText>(R.id.editTextHeight)
        val weightInput = findViewById<EditText>(R.id.editTextWeight)
        val saveButton = findViewById<Button>(R.id.buttonSave)

        saveButton.setOnClickListener {
            val name = nameInput.text.toString()
            val surname = surnameInput.text.toString()
            val age = ageInput.text.toString()
            val height = heightInput.text.toString()
            val weight = weightInput.text.toString()

            if (validateInputs(name, surname, age, height, weight)) {
                val person = Person(name, surname, age.toInt(), height.toFloat(), weight.toFloat())
                personRepository.savePerson(person)

                nameInput.text.clear()
                surnameInput.text.clear()
                ageInput.text.clear()
                heightInput.text.clear()
                weightInput.text.clear()

                showToast("Zapisano osobę!")
            }
        }

        val screen2Button = findViewById<Button>(R.id.buttonScreen2)
        screen2Button.setOnClickListener {
            startActivity(Intent(this, SecoundActivity::class.java))
        }
    }

    private fun validateInputs(
        name: String,
        surname: String,
        age: String,
        height: String,
        weight: String
    ): Boolean {
        // Validate name and surname
        if (name.isBlank()) {
            showToast("Imię nie może być puste!")
            return false
        }
        if (surname.isBlank()) {
            showToast("Nazwisko nie może być puste!")
            return false
        }

        // Validate age
        val ageInt = age.toIntOrNull()
        if (ageInt == null || ageInt <= 0) {
            showToast("Wpisz poprawny wiek!")
            return false
        }

        // Validate height
        val heightFloat = height.toFloatOrNull()
        if (heightFloat == null || heightFloat !in 50.0..250.0) {
            showToast("Wpisz poprawny wzrost (50-250)!")
            return false
        }

        // Validate weight
        val weightFloat = weight.toFloatOrNull()
        if (weightFloat == null || weightFloat !in 3.0..200.0) {
            showToast("Wpisz poprawną wage (3-200)!")
            return false
        }

        return true // All inputs are valid
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}