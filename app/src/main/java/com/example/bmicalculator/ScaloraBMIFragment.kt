package com.example.bmicalculator

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.android.material.slider.Slider

class ScaloraBMIFragment : Fragment() {

    private lateinit var maleRadioButton: RadioButton
    private lateinit var femaleRadioButton: RadioButton
    private lateinit var heightSlider: Slider
    private lateinit var selectedHeightText: TextView
    private lateinit var weightEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var calculateButton: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scalora_b_m_i, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bind views
        maleRadioButton = view.findViewById(R.id.bmiMaleRadioButton)
        femaleRadioButton = view.findViewById(R.id.bmiFemaleRadioButton)
        heightSlider = view.findViewById(R.id.heightSlider)
        selectedHeightText = view.findViewById(R.id.selectedHeightText)
        weightEditText = view.findViewById(R.id.bmiWeightValue1)
        ageEditText = view.findViewById(R.id.bmiAgeValue1)
        calculateButton = view.findViewById(R.id.bmiCalculateButton2)

        // Restore saved preferences
        restorePreviousValues()

        // Height slider listener
        heightSlider.addOnChangeListener { _, value, _ ->
            selectedHeightText.text = String.format("%.2f m", value)
        }

        // Calculate BMI button listener
        calculateButton.setOnClickListener {
            calculateAndStoreBMI()
        }
    }

    private fun restorePreviousValues() {
        val sharedPreferences = requireContext().getSharedPreferences("ScaloraPrefs", Context.MODE_PRIVATE)

        val gender = sharedPreferences.getString("gender", null)
        val height = sharedPreferences.getFloat("height", 1.5f)
        val weight = sharedPreferences.getFloat("weight", 70f)
        val age = sharedPreferences.getInt("age", 20)

        // Set gender radio
        when (gender) {
            "Male" -> maleRadioButton.isChecked = true
            "Female" -> femaleRadioButton.isChecked = true
        }

        // Set height
        heightSlider.value = height
        selectedHeightText.text = String.format("%.2f m", height)

        // Set weight and age
        weightEditText.setText(weight.toString())
        ageEditText.setText(age.toString())
    }

    private fun calculateAndStoreBMI() {
        val selectedGender = when {
            maleRadioButton.isChecked -> "Male"
            femaleRadioButton.isChecked -> "Female"
            else -> "Not selected"
        }

        val height = heightSlider.value
        val weightText = weightEditText.text.toString()
        val ageText = ageEditText.text.toString()

        if (weightText.isEmpty() || ageText.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter weight and age", Toast.LENGTH_SHORT).show()
            return
        }

        val weight = weightText.toFloatOrNull()
        val age = ageText.toIntOrNull()

        if (weight == null || age == null) {
            Toast.makeText(requireContext(), "Invalid weight or age", Toast.LENGTH_SHORT).show()
            return
        }

        val bmi = weight / (height * height)
        val bmiRounded = String.format("%.2f", bmi)

        // Save to SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("ScaloraPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().apply {
            putString("gender", selectedGender)
            putFloat("height", height)
            putFloat("weight", weight)
            putInt("age", age)
            putString("bmi", bmiRounded)
            apply()
        }

        Toast.makeText(requireContext(), "BMI Calculated: $bmiRounded", Toast.LENGTH_LONG).show()

        parentFragmentManager.beginTransaction()
            .replace(R.id.homeFragmentContainer1, ScaloraHomeFragment())
            .addToBackStack(null)
            .commit()
    }
}