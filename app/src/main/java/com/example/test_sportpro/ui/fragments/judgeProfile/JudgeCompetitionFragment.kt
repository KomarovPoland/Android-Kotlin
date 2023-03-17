package com.example.test_sportpro.ui.fragments.judgeProfile

import android.annotation.SuppressLint
import android.graphics.Color
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
import com.example.test_sportpro.databinding.FragmentJudgeCompetitionBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class JudgeCompetitionFragment : Fragment(R.layout.fragment_judge_competition) {

    val args : JudgeCompetitionFragmentArgs by navArgs()

    private var fragmentJudgeCompetitionBinding : FragmentJudgeCompetitionBinding? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentJudgeCompetitionBinding = FragmentJudgeCompetitionBinding.bind(view)

        val competition = args.competition

        val currentDate = LocalDateTime.now().toString()

        val date = formatDateStr(currentDate)
        val hours = formatHoursStr(currentDate)
        val minutes = formatMinutesStr(currentDate)

        Glide.with(this)
                .load(competition.photo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(fragmentJudgeCompetitionBinding!!.image)
        fragmentJudgeCompetitionBinding!!.category.text = competition.sport.name
        fragmentJudgeCompetitionBinding!!.status.text = competition.status
        fragmentJudgeCompetitionBinding!!.title.text = competition.description
        fragmentJudgeCompetitionBinding!!.startDate.text = formatDateStr(competition.dateofstart)
        fragmentJudgeCompetitionBinding!!.endDate.text = formatDateStr(competition.dateofend)
        fragmentJudgeCompetitionBinding!!.date.text = "Дата: ".plus(formatDateStr(competition.dateofstart))
        fragmentJudgeCompetitionBinding!!.time.text = "Время: ".plus(formatTimeStr(competition.dateofstart))

//        fragmentJudgeCompetitionBinding!!.ageCategory.text = category

        if (date == formatDateStr(competition.dateofstart) &&
            hours >= formatHoursStr(competition.dateofstart) &&
            minutes >= formatMinutesStr(competition.dateofstart)) {
            fragmentJudgeCompetitionBinding!!.button.setBackgroundColor(Color.parseColor("#ED2840"))
            fragmentJudgeCompetitionBinding!!.button.setOnClickListener {
                val bundle = Bundle().apply {
                    putSerializable("competition", competition)
                }
                findNavController().navigate(R.id.action_judgeCompetitionFragment_to_competitionsGridFragment, bundle)
            }
        }
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

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatHoursStr(strDate: String): Int {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val targetFormat = SimpleDateFormat("HH")

        val date : Date = originalFormat.parse(strDate)
        return targetFormat.format(date).toInt()
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatMinutesStr(strDate: String): Int {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val targetFormat = SimpleDateFormat("mm")

        val date : Date = originalFormat.parse(strDate)
        return targetFormat.format(date).toInt()
    }
}