package com.ahmed3v.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ahmed3v.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateBtn.setOnClickListener{ calculateTip() }
    }

    private fun calculateTip(){

        val stringInEditText = binding.costOfService.text.toString()

        val cost = stringInEditText.toDoubleOrNull()

        if (cost == null) {

            binding.tipResult.text = ""
            return
        }


        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {

            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost

        if (binding.roundUpSwitch.isChecked) { tip = ceil(tip) }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount_text, formattedTip)
    }
}