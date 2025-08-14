package com.example.news_eat_fronted.util.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.DialogPopupBinding
import com.example.news_eat_fronted.util.base.BindingDialogFragment

class DialogPopupFragment(
    private val title: String,
    private val content: String,
    private val leftBtnText: String,
    private val rightBtnText: String,
    private val clickLeftBtn: () -> Unit,
    private val clickRightBtn: () -> Unit,
    private val closeDialog: () -> Unit = {},
): BindingDialogFragment<DialogPopupBinding>(R.layout.dialog_popup) {
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
            dialogTitle.text = title
            dialogContent.text = content
            btnCancel.text = leftBtnText
            btnConfirm.text = rightBtnText
        }
    }

    private fun addListeners() {
        binding.btnCancel.setOnClickListener {
            clickLeftBtn()
            dismiss()
        }

        binding.btnConfirm.setOnClickListener {
            clickRightBtn()
            dismiss()
        }

        binding.dialogBackground.setOnClickListener {
            dismiss()
        }
    }
}