package com.example.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.navigation.fragment.findNavController
import com.example.quiz.QuestionsFragment.Companion.KEY_TEST_RESULT
import com.example.quiz.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewResult.translationY = -200f
        binding.result.apply {
            scaleX = 0f
            scaleY = 0f
            alpha = 0f
        }

        binding.result.text = arguments?.getInt(KEY_TEST_RESULT).toString()
        binding.buttonNew.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_QuestionsFragment)
        }

        binding.textViewResult.animate().apply {
            duration = 500
            startDelay = 300
            translationY(0f)
            interpolator = AccelerateInterpolator()
        }.start()

        binding.result.animate().apply {
            duration = 300
            startDelay = 800
            scaleX(2f)
            scaleY(2f)
            alpha(1f)
            interpolator = AccelerateInterpolator()
        }.withEndAction {
            binding.result.animate().apply {
                duration = 300
                scaleX(1f)
                scaleY(1f)
                interpolator = AccelerateInterpolator()
            }.start()
        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}