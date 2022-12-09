package com.example.fitnesstracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class TmbActivity : AppCompatActivity() {

    private lateinit var lifestyle: AutoCompleteTextView
    private lateinit var editWeight: EditText
    private lateinit var editHeight: EditText
    private lateinit var editAge: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tmb)

        editWeight = findViewById(R.id.edit_tmb_weight)
        editHeight = findViewById(R.id.edit_tmb_height)
        editAge = findViewById(R.id.edit_tmb_age)

        lifestyle = findViewById(R.id.auto_lifestyle)
        val items = resources.getStringArray(R.array.tmb_lifestyle)
        lifestyle.setText(items.first())
        val adapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, items)
        lifestyle.setAdapter(adapter)

        val btnSend: Button = findViewById(R.id.btn_tmb_send)

        btnSend.setOnClickListener {
            if (!validate()) {
                Toast.makeText(this, R.string.fields_messages, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val weight = editWeight.text.toString().toInt()
            val height = editHeight.text.toString().toInt()
            val age = editAge.text.toString().toInt()

            val result = calculateTmb(weight, height, age)
            val response = tmbRequest(result)
        }
    }

    private fun tmbRequest(result: Double): Double {
        val items = resources.getStringArray(R.array.tmb_lifestyle)
        return when {
            lifestyle.text.toString() == items[0] -> result * 1.2
            lifestyle.text.toString() == items[1] -> result * 1.375
            lifestyle.text.toString() == items[2] -> result * 1.55
            lifestyle.text.toString() == items[3] -> result * 1.725
            lifestyle.text.toString() == items[4] -> result * 1.9
            else -> 0.0
        }
    }

    private fun calculateTmb(weight: Int, height: Int, age: Int): Double {
        return 66 + (13.8 * weight) + (5 * height) - (6.8 * age)
    }

    private fun validate(): Boolean {
        return editWeight.text.toString().isNotEmpty()
                && editHeight.text.toString().isNotEmpty()
                && editAge.text.toString().isNotEmpty()
                && !editWeight.text.toString().startsWith("0")
                && !editHeight.text.toString().startsWith("0")
                && !editAge.text.toString().startsWith("0")
    }
}