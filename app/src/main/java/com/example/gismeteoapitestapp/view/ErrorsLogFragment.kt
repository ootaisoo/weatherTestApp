package com.example.gismeteoapitestapp.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gismeteoapitestapp.R
import com.example.gismeteoapitestapp.component
import com.example.gismeteoapitestapp.viewmodel.ErrorsLogViewModel

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

    private lateinit var errorLogTv: TextView
    private lateinit var emailBtn: Button

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
    ): View? {
        return inflater.inflate(R.layout.fragment_errors_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        errorLogTv = view.findViewById(R.id.error_log_tv)
        emailBtn = view.findViewById(R.id.send_email_btn)

        emailBtn.setOnClickListener {
            viewModel.sendEmail(errorLogTv.text.toString())
        }

        val errorLog = arguments?.getString(STACK_TRACE)
            .plus("\n")
            .plus(arguments?.getString(MESSAGE))

        errorLogTv.text = errorLog
    }
}