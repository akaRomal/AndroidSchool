package com.school.alertdialog

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment

const val KEY_LABEL = "key_label"
const val KEY_IMAGE = "key_image"

class ShowAlertDialog : DialogFragment(R.layout.dialog_fragment) {

    var onClickFragment: OnClickFragment? = null

    companion object {
        fun newInstance(label: String, image: Int): ShowAlertDialog {
            val fragment = ShowAlertDialog()
            val bundle = Bundle()
            bundle.putString(KEY_LABEL, label)
            bundle.putInt(KEY_IMAGE, image)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onClickFragment = context as OnClickFragment
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = view.findViewById<ImageView>(R.id.iv_logo)
        val textView = view.findViewById<TextView>(R.id.tv_dialog)
        val bntOk = view.findViewById<Button>(R.id.btn_ok)
        val btnCancel = view.findViewById<Button>(R.id.btn_cancel)
        arguments?.let { args ->
            imageView.setImageDrawable(resources.getDrawable(args.getInt(KEY_IMAGE), null))
            textView.text = args.getString(KEY_LABEL)
        }
        bntOk.setOnClickListener {
            onClickFragment?.clickOk()
            dismiss()
        }
        btnCancel.setOnClickListener {
            onClickFragment?.clickCancel()
            dismiss()
        }
    }

}