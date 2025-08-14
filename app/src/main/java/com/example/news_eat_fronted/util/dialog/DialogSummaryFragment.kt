package com.example.news_eat_fronted.util.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.DialogSummaryBinding
import com.example.news_eat_fronted.util.base.BindingDialogFragment

class DialogSummaryFragment(
    private val title: String,
    private val content: String,
    private val tag: String,
    private val closeDialog: () -> Unit = {},
): BindingDialogFragment<DialogSummaryBinding>(R.layout.dialog_summary) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
        addListeners()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        closeDialog()
    }

    private fun initLayout() {
        with(binding) {
            summaryTitle.text = "\"$title\""
            summaryContent.text = content
            summaryTag.text = tag
        }
    }

    private fun addListeners() {
        binding.dialogBackground.setOnClickListener {
            dismiss()
        }

        binding.btnBack.setOnClickListener {
            dismiss()
        }
    }
}