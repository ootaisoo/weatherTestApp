package com.example.gismeteoapitestapp.ui.view

import android.content.Context
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gismeteoapitestapp.component
import com.example.gismeteoapitestapp.databinding.FragmentErrorsLogBinding
import com.example.gismeteoapitestapp.ui.viewmodel.ErrorsLogViewModel

class ErrorsLogFragment : Fragment() {

    companion object {
        const val STACK_TRACE = "stack_trace"
        const val MESSAGE = "message"

        fun newInstance(bundle: Bundle? = null): ErrorsLogFragment {
            return ErrorsLogFragment().apply {
                arguments = bundle
            }
        }
    }

    private lateinit var binding: FragmentErrorsLogBinding

    private val viewModel: ErrorsLogViewModel by viewModels {
        requireContext().component.viewModelsFactory()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.onAttachFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentErrorsLogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sendEmailBtn.setOnClickListener {
            viewModel.sendEmail(binding.errorLogTv.text.toString())
        }

        val errorLog = arguments?.getString(STACK_TRACE)
            .plus("\n")
            .plus(arguments?.getString(MESSAGE))

        binding.errorLogTv.movementMethod = ScrollingMovementMethod()
        binding.errorLogTv.text = errorLog
    }
}