package com.example.unitconverter

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private lateinit var inputEditText: EditText
    private lateinit var fromUnitSpinner: Spinner
    private lateinit var toUnitSpinner: Spinner
    private lateinit var categorySpinner: Spinner
    private lateinit var convertButton: Button
    private lateinit var resultTextView: TextView

    private val categories = arrayOf(
        "Length",
        "Temperature",
        "Weight"
    )
    private val units = mapOf(
        "Length" to arrayOf(
            "Millimeters (mm)",
            "Centimeters (cm)",
            "Meters (m)",
            "Kilometers (km)",
            "Inches (in)",
            "Feet (ft)",
            "Yards (yd)",
            "Miles (mi)"
        ), "Temperature" to arrayOf(
            "Fahrenheit",
            "Celsius",
            "Kelvin"
        ), "Weight" to arrayOf(
            "Milligrams (mg)",
            "Grams (g)",
            "Kilograms (kg)",
            "Metric tons (t)",
            "Ounces (oz)",
            "Pounds (lb)",
            "Stones (st)",
            "Short tons (US)",
            "Long tons (UK)"

        )
    )
    private val conversions = mapOf(
        "Length" to mapOf(
            "Millimeters (mm)" to mapOf(
                "Centimeters (cm)" to 0.1,
                "Meters (m)" to 0.001,
                "Kilometers (km)" to 0.000001,
                "Inches (in)" to 0.0393701,
                "Feet (ft)" to 0.00328084,
                "Yards (yd)" to 0.00109361,
                "Miles (mi)" to 0.00000621371,
                "Millimeters (mm)" to 1
            ), "Centimeters (cm)" to mapOf(
                "Millimeters (mm)" to 10.0,
                "Meters (m)" to 0.01,
                "Kilometers (km)" to 0.00001,
                "Inches (in)" to 0.393701,
                "Feet (ft)" to 0.0328084,
                "Yards (yd)" to 0.0109361,
                "Miles (mi)" to 0.0000621371,
                "Centimeters (cm)" to 1
            ), "Meters (m)" to mapOf(
                "Millimeters (mm)" to 1000.0,
                "Centimeters (cm)" to 100.0,
                "Kilometers (km)" to 0.001,
                "Inches (in)" to 39.3701,
                "Feet (ft)" to 3.28084,
                "Yards (yd)" to 1.09361,
                "Miles (mi)" to 0.000621371,
                "Meters (m)" to 1
            ), "Kilometers (km)" to mapOf(
                "Millimeters (mm)" to 1000000.0,
                "Centimeters (cm)" to 100000.0,
                "Meters (m)" to 1000.0,
                "Inches (in)" to 39370.1,
                "Feet (ft)" to 3280.84,
                "Yards (yd)" to 1093.61,
                "Miles (mi)" to 0.621371,
                "Kilometers (km)" to 1
            ), "Inches (in)" to mapOf(
                "Millimeters (mm)" to 25.4,
                "Centimeters (cm)" to 2.54,
                "Meters (m)" to 0.0254,
                "Kilometers (km)" to 0.0000254,
                "Feet (ft)" to 0.0833333,
                "Yards (yd)" to 0.0277778,
                "Miles (mi)" to 0.000015783,
                "Inches (in)" to 1
            ), "Feet (ft)" to mapOf(
                "Millimeters (mm)" to 304.8,
                "Centimeters (cm)" to 30.48,
                "Meters (m)" to 0.3048,
                "Kilometers (km)" to 0.0003048,
                "Inches (in)" to 12.0,
                "Yards (yd)" to 0.3333333,
                "Miles (mi)" to 0.000189394,
                "Feet (ft)" to 1
            ), "Yards (yd)" to mapOf(
                "Millimeters (mm)" to 914.4,
                "Centimeters (cm)" to 91.44,
                "Meters (m)" to 0.9144,
                "Kilometers (km)" to 0.0009144,
                "Inches (in)" to 36.0,
                "Feet (ft)" to 3.0,
                "Miles (mi)" to 0.000568182,
                "Yards (yd)" to 1
            ), "Miles (mi)" to mapOf(
                "Millimeters (mm)" to 1609344.0,
                "Centimeters (cm)" to 160934.4,
                "Meters (m)" to 1609.344,
                "Kilometers (km)" to 1.609344,
                "Inches (in)" to 63360.0,
                "Feet (ft)" to 5280.0,
                "Yards (yd)" to 1760.0,
                "Miles (mi)" to 1
            )
        ), "Temperature" to mapOf(
            "Fahrenheit" to mapOf(
                "Celsius" to 1,
                "Kelvin" to 1,
                "Fahrenheit" to 1
            ), "Celsius" to mapOf(
                "Fahrenheit" to 1,
                "Kelvin" to 1,
                "Celsius" to 1
            ), "Kelvin" to mapOf(
                "Fahrenheit" to 1,
                "Celsius" to 1,
                "Kelvin" to 1
            )

        ), "Weight" to mapOf(
            "Milligrams (mg)" to mapOf(
                "Grams (g)" to 0.001,
                "Kilograms (kg)" to 0.000001,
                "Metric tons (t)" to 0.000000001,
                "Ounces (oz)" to 0.000035274,
                "Pounds (lb)" to 0.00000220462,
                "Stones (st)" to 0.000000157473,
                "Short tons (US)" to 0.00000000110231,
                "Long tons (UK)" to 0.000000000984207,
                "Milligrams (mg)" to 1
            ), "Grams (g)" to mapOf(
                "Milligrams (mg)" to 1000.0,
                "Kilograms (kg)" to 0.001,
                "Metric tons (t)" to 0.000001,
                "Ounces (oz)" to 0.035274,
                "Pounds (lb)" to 0.00220462,
                "Stones (st)" to 0.000157473,
                "Short tons (US)" to 0.00000110231,
                "Long tons (UK)" to 0.000000984207,
                "Grams (g)" to 1
            ), "Kilograms (kg)" to mapOf(
                "Milligrams (mg)" to 1000000.0,
                "Grams (g)" to 1000.0,
                "Metric tons (t)" to 0.001,
                "Ounces (oz)" to 35.274,
                "Pounds (lb)" to 2.20462,
                "Stones (st)" to 0.157473,
                "Short tons (US)" to 0.00110231,
                "Long tons (UK)" to 0.000984207,
                "Kilograms (kg)" to 1
            ), "Metric tons (t)" to mapOf(
                "Milligrams (mg)" to 1000000000.0,
                "Grams (g)" to 1000000.0,
                "Kilograms (kg)" to 1000.0,
                "Ounces (oz)" to 35274.0,
                "Pounds (lb)" to 2204.62,
                "Stones (st)" to 157.473,
                "Short tons (US)" to 1.10231,
                "Long tons (UK)" to 0.984207,
                "Metric tons (t)" to 1
            ), "Ounces (oz)" to mapOf(
                "Milligrams (mg)" to 28349.5,
                "Grams (g)" to 28.3495,
                "Kilograms (kg)" to 0.0283495,
                "Metric tons (t)" to 0.0000283495,
                "Pounds (lb)" to 0.0625,
                "Stones (st)" to 0.00446429,
                "Short tons (US)" to 0.00003125,
                "Long tons (UK)" to 0.0000279018,
                "Ounces (oz)" to 1
            ), "Pounds (lb)" to mapOf(
                "Milligrams (mg)" to 453592.0,
                "Grams (g)" to 453.592,
                "Kilograms (kg)" to 0.453592,
                "Metric tons (t)" to 0.000453592,
                "Ounces (oz)" to 16.0,
                "Stones (st)" to 0.0714286,
                "Short tons (US)" to 0.0005,
                "Long tons (UK)" to 0.000446429,
                "Pounds (lb)" to 1
            ), "Stones (st)" to mapOf(
                "Milligrams (mg)" to 6350290.0,
                "Grams (g)" to 6350.29,
                "Kilograms (kg)" to 6.35029,
                "Metric tons (t)" to 0.00635029,
                "Ounces (oz)" to 224.0,
                "Pounds (lb)" to 14.0,
                "Short tons (US)" to 0.007,
                "Long tons (UK)" to 0.00625,
                "Stones (st)" to 1
            ), "Short tons (US)" to mapOf(
                "Milligrams (mg)" to 907185000.0,
                "Grams (g)" to 907185.0,
                "Kilograms (kg)" to 907.185,
                "Metric tons (t)" to 0.907185,
                "Ounces (oz)" to 32000.0,
                "Pounds (lb)" to 2000.0,
                "Stones (st)" to 142.857,
                "Long tons (UK)" to 0.892857,
                "Short tons (US)" to 1
            ), "Long tons (UK)" to mapOf(
                "Milligrams (mg)" to 1016000000.0,
                "Grams (g)" to 1016000.0,
                "Kilograms (kg)" to 1016.0,
                "Metric tons (t)" to 1.016,
                "Ounces (oz)" to 35840.0,
                "Pounds (lb)" to 2240.0,
                "Stones (st)" to 160.0,
                "Short tons (US)" to 1.12,
                "Long tons (UK)" to 1
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Change top bar color
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.header_color)

        inputEditText = findViewById(R.id.input_value)
        fromUnitSpinner = findViewById(R.id.from_unit_spinner)
        toUnitSpinner = findViewById(R.id.to_unit_spinner)
        categorySpinner = findViewById(R.id.category_spinner)
        convertButton = findViewById(R.id.convert_button)
        resultTextView = findViewById(R.id.result_textview)

        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = categoryAdapter

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View, position: Int, id: Long
            ) {
                val selectedCategory = categories[position]
                val unitAdapter = ArrayAdapter(
                    this@MainActivity,
                    android.R.layout.simple_spinner_item,
                    units[selectedCategory]!!
                )
                unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                fromUnitSpinner.adapter = unitAdapter
                toUnitSpinner.adapter = unitAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        convertButton.setOnClickListener {
            val inputValueStr = inputEditText.text.toString()
            if (inputValueStr.isNotEmpty()) {
                val inputValue = inputValueStr.toDoubleOrNull()
                if (inputValue != null) {
                    val fromUnit = fromUnitSpinner.selectedItem.toString()
                    val toUnit = toUnitSpinner.selectedItem.toString()
                    val selectedCategory = categorySpinner.selectedItem.toString()
                    val conversionFactor = conversions[selectedCategory]?.get(fromUnit)?.get(toUnit)

                    if (conversionFactor != null) {

                        // Perform the conversion calculation
                        var result =  inputValue * conversionFactor.toString().toDouble()
                        if (selectedCategory == "Temperature") when(fromUnit){
                            "Fahrenheit" -> when(toUnit){
                                "Celsius" -> result = (inputValue -32)*5/9
                                "Kelvin" -> result = ((inputValue-32)*5/9)+273.15
                                "Fahrenheit" -> result = inputValue*1
                            }
                            "Celsius" -> when(toUnit){
                                "Celsius" -> result = inputValue
                                "Kelvin" -> result = inputValue+273.15
                                "Fahrenheit" -> result = inputValue*9/5+32
                            }
                            "Kelvin" -> when(toUnit){
                                "Celsius" -> result = inputValue-273.15
                                "Kelvin" -> result = inputValue
                                "Fahrenheit" -> result = (inputValue*9/5+32)-273.15
                            }
                            else -> {
                                resultTextView.text = "Unsupported conversion factor type"
                                return@setOnClickListener
                            }
                        }
                        resultTextView.text = "$result $toUnit"
                    } else {
                        resultTextView.text = "Conversion factor not found"
                    }
                } else {
                    resultTextView.text = "Invalid input"
                }
            } else {
                resultTextView.text = "Please enter a value"
            }
        }
    }
}