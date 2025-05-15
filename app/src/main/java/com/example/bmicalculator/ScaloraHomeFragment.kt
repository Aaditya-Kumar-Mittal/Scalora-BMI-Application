package com.example.bmicalculator

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bmicalculator.databinding.FragmentScaloraHomeBinding

class ScaloraHomeFragment : Fragment() {

    private lateinit var binding: FragmentScaloraHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScaloraHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireContext().getSharedPreferences("ScaloraPrefs", Context.MODE_PRIVATE)
        val bmiValue = sharedPreferences.getString("bmi", null)
        val userName = sharedPreferences.getString("name", "User") // Optional username support

        if (bmiValue != null) {
            val bmi = bmiValue.toFloat()
            val bmiPercent = (bmi / 40f * 100).toInt().coerceIn(0, 100) // normalize to 0–100%

            binding.homeProgressMeter1.progress = bmiPercent
            binding.homeProgressText1.text = bmiValue

            val bodyType = getBodyTypeCategory(bmi)
            binding.homeBodyType1.text = bodyType
        } else {
            binding.homeProgressMeter1.progress = 0
            binding.homeProgressText1.text = "--"
            binding.homeBodyType1.text = "Unknown"
        }

        // Set greeting username if stored
        binding.homeUserName1.text = userName

        // Navigate to BMI calculator screen
        binding.bmiCalculateButton1.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.homeFragmentContainer1, ScaloraBMIFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun getBodyTypeCategory(bmi: Float): String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi < 24.9 -> "Healthy"
            bmi < 29.9 -> "Overweight"
            else -> "Obese"
        }
    }
}
