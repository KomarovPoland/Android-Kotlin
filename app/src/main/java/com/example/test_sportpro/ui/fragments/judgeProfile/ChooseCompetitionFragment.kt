package com.example.test_sportpro.ui.fragments.judgeProfile

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.test_sportpro.R
import com.example.test_sportpro.databinding.FragmentChooseCompetitionBinding
import com.example.test_sportpro.databinding.FragmentJudgeBinding
import com.example.test_sportpro.ui.fragments.auth.NumberFragmentArgs
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

class ChooseCompetitionFragment : Fragment(R.layout.fragment_choose_competition) {

//    val args: ChooseCompetitionFragmentArgs by navArgs()

    private var fragmentChooseCompetitionBinding : FragmentChooseCompetitionBinding? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentChooseCompetitionBinding = FragmentChooseCompetitionBinding.bind(view)

//        val competition = args.competition
//
//        Glide.with(this)
//                .load(competition.photo)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(fragmentChooseCompetitionBinding!!.image)
//
//        fragmentChooseCompetitionBinding!!.category.text = competition.sport.name
//        fragmentChooseCompetitionBinding!!.status.text = competition.status
//        fragmentChooseCompetitionBinding!!.title.text = competition.description
//        fragmentChooseCompetitionBinding!!.startDate.text = formatDateStr(competition.dateofstart)
//        fragmentChooseCompetitionBinding!!.endDate.text = formatDateStr(competition.dateofend)
//        fragmentChooseCompetitionBinding!!.date.text = "Дата: ".plus(formatDateStr(competition.dateofstart))
//        fragmentChooseCompetitionBinding!!.time.text = "Время: ".plus(formatTimeStr(competition.dateofstart))
//
//
//        fragmentChooseCompetitionBinding!!.button.setOnClickListener {
//            findNavController().navigate(R.id.action_chooseCompetitionFragment_to_categoriesFragment)
//        }
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDateStr(strDate: String): String {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val targetFormat = SimpleDateFormat("dd.MM.yyyy")

        val date : Date = originalFormat.parse(strDate)
        return targetFormat.format(date)
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatTimeStr(strDate: String): String {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val targetFormat = SimpleDateFormat("HH:mm")

        val date : Date = originalFormat.parse(strDate)
        return targetFormat.format(date)
    }
}