package com.example.emicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var editAmount: EditText
    private lateinit var editInterest: EditText
    private lateinit var editPeriod: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var button: Button
    private lateinit var monthlyemioutput: TextView
    private lateinit var totalInterestOutput: TextView
    private lateinit var totalPaymentOutput: TextView
    private lateinit var warningMessage : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editAmount = findViewById(R.id.editAmount)
        editInterest = findViewById(R.id.editInterest)
        editPeriod = findViewById(R.id.editPeriod)
        radioGroup = findViewById(R.id.radioGroup)
        button = findViewById(R.id.button)
        monthlyemioutput = findViewById(R.id.monthlyemioutput)
        totalInterestOutput = findViewById(R.id.totalInterestOutput)
        totalPaymentOutput = findViewById(R.id.totalPaymentOutput)
        warningMessage = findViewById(R.id.warningMessage)

        button.setOnClickListener{
            calculateEMI()
            //Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateEMI() {
        val amount = editAmount.text.toString().toDoubleOrNull()
        val interest = editInterest.text.toString().toDoubleOrNull()
        val period = editPeriod.text.toString().toIntOrNull()

        if (amount != null && interest != null && period != null) {
            val rate = interest / 100.0
            val monthlyInterest = rate / 12.0
            val numberOfMonths = if (radioGroup.checkedRadioButtonId == R.id.radioOption1) {
                period * 12
            } else {
                period
            }

            var total_payment = ((interest/100) * amount) * period + amount
            var total_interest = ((interest/100) * amount) * period
            var monthly_emi = total_payment/numberOfMonths

            monthlyemioutput.text = String.format("%.2f", monthly_emi)
            totalInterestOutput.text = String.format("%.2f", total_interest)
            totalPaymentOutput.text = String.format("%.2f", total_payment)
            warningMessage.visibility = View.INVISIBLE
        } else {
            // Handle invalid input
            monthlyemioutput.text = "0"
            totalInterestOutput.text = "0"
            totalPaymentOutput.text = "0"
            warningMessage.visibility = View.VISIBLE
        }
    }
}