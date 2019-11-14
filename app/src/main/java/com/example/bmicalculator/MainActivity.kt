package com.example.bmicalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import kotlinx.android.synthetic.*

class MainActivity : AppCompatActivity() {

    lateinit var imageView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageViewProfile)

        findViewById<Button>(R.id.buttonCalculate).setOnClickListener {
            cal(it)
        }
        findViewById<Button>(R.id.buttonReset).setOnClickListener {
            resetBmi()
        }

    }
    private fun cal(view: View) {
        val editWeight = findViewById<EditText>(R.id.editTextWeight)
        val editHeight = findViewById<EditText>(R.id.editTextHeight)

        val imageView: ImageView = findViewById(R.id.imageViewProfile)

        val bmiView = findViewById<TextView>(R.id.textViewBMI)

        val weight = editWeight.text.toString()
        val height = editHeight.text.toString()


        val bmi = weight.toDouble() /((height.toDouble() * height.toDouble()) / 10000)
        bmiView.text = "BMI :%.2f".format(bmi)
        bmiView.visibility = View.VISIBLE

        if(bmi < 18.5){
            imageView.setImageResource(R.drawable.under)
        }
        else if(bmi < 24.9){// bmi in 18.6..24.9
            imageView.setImageResource(R.drawable.normal)
        }
        else{
            imageView.setImageResource(R.drawable.over)
        }


        // don't the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun resetBmi() {
        val editWeight = findViewById<EditText>(R.id.editTextWeight)
        val editHeight = findViewById<EditText>(R.id.editTextHeight)
        val bmiView = findViewById<TextView>(R.id.textViewBMI)

        imageView.setImageResource(R.drawable.empty)
        editWeight.text.clear()
        editHeight.text.clear()
        bmiView.text = ""
        Toast.makeText(this, "All Reset",
            Toast.LENGTH_SHORT).show()
    }
}
