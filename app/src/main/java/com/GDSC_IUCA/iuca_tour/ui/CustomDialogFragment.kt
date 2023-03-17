package com.GDSC_IUCA.iuca_tour.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.DialogFragment
import com.GDSC_IUCA.iuca_tour.R
import kotlinx.android.synthetic.main.fragment_custom_dialog.view.*


class CustomDialogFragment : DialogFragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val rootView: View =  inflater.inflate(R.layout.fragment_custom_dialog, container, false)

        rootView.tv_no.setOnClickListener {
            dismiss()
        }
        rootView.tv_yes.setOnClickListener {
            activity?.finishAffinity()
        }
        return rootView
    }


}