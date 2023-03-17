package com.GDSC_IUCA.iuca_tour.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.GDSC_IUCA.iuca_tour.MyStringListener
import com.GDSC_IUCA.iuca_tour.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main_page.view.*
import kotlinx.android.synthetic.main.fragment_dialog_lang_chose.view.*
import java.util.*


class DialogLangChoseFragment : DialogFragment() {

//    private var listener: EventListener? = null
    private var listener: MyStringListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_dialog_lang_chose, container, false)
        val activityView: View = inflater.inflate(R.layout.activity_main, container, false)

        val sharedPre = this.activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPre?.edit()

        rootView.engBtnDialog.setOnClickListener {
            editor?.apply {
                putString("lang", "ENG")
            }?.apply()


//            activityView.lngBtnTopBar.setImageResource(R.drawable.flag_us)

        }
        rootView.kgBtnDialog.setOnClickListener {
            editor?.apply {
                putString("lang", "KGZ")
            }?.apply()
        }

        rootView.rusBtnDialog.setOnClickListener {
            editor?.apply {
                putString("lang", "RUS")
            }?.apply()
            dismiss()
            activityView.lngBtnTopBar.setImageResource(R.drawable.flag_us)
        }

        rootView.chBtnDialog.setOnClickListener {
            editor?.apply {
                putString("lang", "CHN")
            }?.apply()
            dismiss()
            activityView.lngBtnTopBar.setImageResource(R.drawable.flag_us)
        }
        return rootView
//        if (lang == "ENG") lngBtnTopBar.setImageResource(R.drawable.flag_us)
//        if (lang == "RUS") lngBtnTopBar.setImageResource(R.drawable.flag_ru)
//        if (lang == "CHN") lngBtnTopBar.setImageResource(R.drawable.flag_ch)
//        if (lang == "KGZ") lngBtnTopBar.setImageResource(R.drawable.flag_kg)
//        else binding.lngBtnTopBar.setImageResource(R.drawable.ic_arrow)ic_arrow
    }




}
