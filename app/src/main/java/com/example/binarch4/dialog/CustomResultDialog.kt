package com.example.binarch4.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.binarch4.MenuActivity
import com.example.binarch4.databinding.ActivityCustomResultDialogBinding
class CustomResultDialog(val result : String) : DialogFragment(){
    var binding : ActivityCustomResultDialogBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityCustomResultDialogBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val heigh = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(width, heigh)


        dialog?.setCancelable(false)
        binding?.tvHasil?.text = result

        binding?.btnPlayAgain?.setOnClickListener {
            dismiss()
        }
    }

}