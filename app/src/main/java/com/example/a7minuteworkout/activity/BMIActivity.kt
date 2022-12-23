package com.example.a7minuteworkout.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minuteworkout.R
import com.example.a7minuteworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {
    private var binding: ActivityBmiBinding? = null

    companion object{
        private const val METRIC_UNITS_VIEW="METRIC_UNT_VIEW"
        private const val US_UNITS_VIEW="US_UNIT_VIEW"
    }
    private var currentVisibleView:String ?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBmiBinding.inflate(layoutInflater)

        setContentView(binding?.root)


        setSupportActionBar(binding?.toolbarBmiActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
       supportActionBar?.title = "CALCULATE BMI"
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

       makeVisibleMetricUnitsView()
       binding?.rgUnits?.setOnCheckedChangeListener { _, checkedId :Int->
           if(checkedId == R.id.rbMetricUnits)
           {
               makeVisibleMetricUnitsView()
           }
           else
           {
               makeVisibleUsUnitsView()
           }
       }






        binding?.btnCalculateUnits?.setOnClickListener {
            if(validateMetricUnits())
            {
                calculateUnits()
            }
        }



 }



    private fun makeVisibleMetricUnitsView() {
        currentVisibleView = METRIC_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.VISIBLE

        binding?.tilMetricUsUnitHeightFeet?.visibility = View.INVISIBLE
        binding?.tilMetricUsUnitHeightInch?.visibility = View.INVISIBLE

        binding?.etMetricUnitHeight?.text!!.clear()
        binding?.etMetricUnitWeight?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun makeVisibleUsUnitsView() {
        currentVisibleView = US_UNITS_VIEW
        binding?.tilMetricUnitHeight?.visibility = View.INVISIBLE
        binding?.tilMetricUnitWeight?.visibility = View.VISIBLE

        binding?.tilMetricUsUnitHeightFeet?.visibility = View.VISIBLE
        binding?.tilMetricUsUnitHeightInch?.visibility = View.VISIBLE

        binding?.etMetricUnitWeight?.text!!.clear()
        binding?.etUSMetricUnitHeightFeet?.text!!.clear()
        binding?.etUSMetricUnitHeightInch?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }


    private fun displayBMIResult(bmi: Float) {

        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }


        binding?.llDisplayBMIResult?.visibility=View.VISIBLE

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription
    }

    private fun validateMetricUnits():Boolean{
        var isValid = true
        if(binding?.etMetricUnitHeight?.text.toString().isEmpty())
        {
            isValid = false
        }
        else
            if(binding?.etMetricUnitHeight?.text.toString().isEmpty())
            {
                isValid = false
            }
        return isValid
    }

    private fun calculateUnits(){
        if(currentVisibleView == METRIC_UNITS_VIEW)
        {
            if(validateMetricUnits())
            {
                val heightValue:Float = binding?.etMetricUnitHeight?.text.toString().toFloat()/100
                val weightValue:Float = binding?.etMetricUnitWeight?.text.toString().toFloat()
                val bmi = weightValue / (heightValue*heightValue)

                displayBMIResult(bmi)
            }
            else{
                Toast.makeText(this,"Please enter a valid value",Toast.LENGTH_SHORT).show()
            }

        }
        else if(currentVisibleView == US_UNITS_VIEW){

            if(validateUSUnits())
            {
                val usUnitHeightValueFeet:String = binding?.etUSMetricUnitHeightFeet?.text.toString()
                val usUnitHeightValueInch:String=binding?.etUSMetricUnitHeightInch?.text.toString()
                val usUnitWeightValue:Float = binding?.etMetricUnitWeight?.text.toString().toFloat()

                val heightValue = usUnitHeightValueInch.toFloat() + usUnitHeightValueFeet.toFloat() * 12
                val weightValue= usUnitWeightValue*2.205

                val bmi = 703 * (weightValue / (heightValue * heightValue))
                displayBMIResult(bmi.toFloat())
            }
            else{
                Toast.makeText(this,"Please enter a valid value",Toast.LENGTH_LONG).show()
            }
        }
    }







    private fun validateUSUnits():Boolean{
        var isValid = true
        if(binding?.etUSMetricUnitHeightFeet?.text.toString().isEmpty())
        {
            isValid = false
        }
        else
            if(binding?.etUSMetricUnitHeightInch?.text.toString().isEmpty())
            {
                isValid = false
            }

        else if(binding?.etMetricUnitWeight?.text.toString().isEmpty())
                {
                    isValid = false
                }
        return isValid
    }
}