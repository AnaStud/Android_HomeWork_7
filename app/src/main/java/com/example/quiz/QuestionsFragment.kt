package com.example.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.quiz.databinding.FragmentQuestionsBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class QuestionsFragment : Fragment() {

    private var _binding: FragmentQuestionsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_QuestionsFragment_to_StartFragment)
        }

        binding.buttonFinish.setOnClickListener {
            var testResult = 0
            if (binding.radioGroupQuestion1.checkedRadioButtonId == binding.answer11.id) {
                testResult = testResult + 1
            }
            if (binding.radioGroupQuestion2.checkedRadioButtonId == binding.answer22.id) {
                testResult = testResult + 1
            }
            if (binding.radioGroupQuestion3.checkedRadioButtonId == binding.answer31.id) {
                testResult = testResult + 1
            }

            val bundle = bundleOf("testResult" to testResult)
            findNavController().navigate(R.id.action_QuestionsFragment_to_resultFragment, bundle)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}