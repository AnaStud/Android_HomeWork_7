package com.example.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.core.os.bundleOf
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.quiz.databinding.FragmentQuestionsBinding

class QuestionsFragment : Fragment() {

    private var _binding: FragmentQuestionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentQuestionsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.linearLayoutQuestions.alpha = 0f
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_QuestionsFragment_to_StartFragment)
        }

        binding.buttonFinish.setOnClickListener {
            val bundle = setBundleTestResult()

            val navOptions = NavOptions.Builder()
                .setEnterAnim(R.anim.pop_in)
                .setExitAnim(R.anim.pop_out)
                .setPopEnterAnim(R.anim.fade_in)
                .setPopExitAnim(R.anim.fade_out)
                .build()

            findNavController().navigate(R.id.action_QuestionsFragment_to_resultFragment,
                bundle, navOptions)
        }

        binding.linearLayoutQuestions.animate().apply {
            duration = 2000
            alpha(1f)
            interpolator = AccelerateInterpolator()
        }.start()
    }

    private fun setBundleTestResult():Bundle {
        var testResult = 0
        if (binding.radioGroupQuestion1.checkedRadioButtonId == binding.answer11.id) {
            testResult += 1
        }
        if (binding.radioGroupQuestion2.checkedRadioButtonId == binding.answer22.id) {
            testResult += 1
        }
        if (binding.radioGroupQuestion3.checkedRadioButtonId == binding.answer31.id) {
            testResult += 1
        }
        return bundleOf(KEY_TEST_RESULT to testResult)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_TEST_RESULT = "testResult"
    }
}