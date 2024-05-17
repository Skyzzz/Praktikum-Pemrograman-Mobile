package com.example.tiptime

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var costOfServiceEditText: EditText
    private lateinit var tipOptions: RadioGroup
    private lateinit var calculateButton: Button
    private lateinit var tipResult: TextView
    private lateinit var roundUpSwitch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        costOfServiceEditText = findViewById(R.id.cost_of_service_edit_text)
        tipOptions = findViewById(R.id.tip_options)
        calculateButton = findViewById(R.id.calculate_button)
        tipResult = findViewById(R.id.tip_result)
        roundUpSwitch = findViewById(R.id.round_up_switch)

        // Calculate button click listener
        calculateButton.setOnClickListener {
            calculateTip()
        }

        // Clear tip result text when costOfServiceEditText is focused
        costOfServiceEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                tipResult.text = ""
            }
        }
    }

    private fun calculateTip() {
        val costOfService = costOfServiceEditText.text.toString().toDoubleOrNull()
        if (costOfService == null) {
            tipResult.text = getString(R.string.invalid_input)
            return
        }

        val selectedTipPercentage = when (tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            R.id.option_fifteen_percent -> 0.15
            else -> 0.15 // Default to 15% if no option is selected
        }

        var tipAmount = costOfService * selectedTipPercentage

        // Round up tip if switch is checked
        if (roundUpSwitch.isChecked) {
            tipAmount = kotlin.math.ceil(tipAmount)
        }

        // Display the tip amount
        val formattedTipAmount = String.format("%.2f", tipAmount)
        tipResult.text = getString(R.string.tip_amount_result, formattedTipAmount)
    }
}