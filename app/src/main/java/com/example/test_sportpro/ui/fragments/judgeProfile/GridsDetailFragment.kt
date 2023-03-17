package com.example.test_sportpro.ui.fragments.judgeProfile

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.test_sportpro.R
import com.example.test_sportpro.api.RetrofitInstance
import com.example.test_sportpro.databinding.FragmentGridsDetailBinding
import com.example.test_sportpro.models.Matche
import com.example.test_sportpro.models.ScoreRequest
import kotlinx.android.synthetic.main.layout_dialog.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GridsDetailFragment : Fragment(R.layout.fragment_grids_detail) {

    val args : GridsDetailFragmentArgs by navArgs()

    private var fragmentGridsDetailBinding : FragmentGridsDetailBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentGridsDetailBinding = FragmentGridsDetailBinding.bind(view)

        val matche = args.matche
        val competition = args.competition

        Glide.with(this)
            .load(matche.player1.photo)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(fragmentGridsDetailBinding!!.image)

        Glide.with(this)
            .load(matche.player2.photo)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(fragmentGridsDetailBinding!!.image2)

        fragmentGridsDetailBinding!!.name.text = matche.player1.surname.plus(" ").plus(matche.player1.name).plus(" ").plus(matche.player1.middlename)
        fragmentGridsDetailBinding!!.name2.text = matche.player2.surname.plus(" ").plus(matche.player2.name).plus(" ").plus(matche.player2.middlename)

        fragmentGridsDetailBinding!!.button.setOnClickListener{
            //Inflate the dialog with custom view
            val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.layout_dialog, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(requireContext())
                .setView(mDialogView)
            //show dialog
            val  mAlertDialog = mBuilder.show()
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(mAlertDialog.window?.attributes)
            layoutParams.width = 900
            mAlertDialog.window?.attributes = layoutParams
            mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            //login button click of custom layout
            mDialogView.btn_no.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
            }
            //cancel button click of custom layout
            mDialogView.btn_yes.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
            }
        }

        fragmentGridsDetailBinding!!.button2.setOnClickListener{
            //Inflate the dialog with custom view
            val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.layout_dialog, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(requireContext())
                .setView(mDialogView)
            //show dialog
            val  mAlertDialog = mBuilder.show()
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(mAlertDialog.window?.attributes)
            layoutParams.width = 900
            mAlertDialog.window?.attributes = layoutParams
            mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            //login button click of custom layout
            mDialogView.btn_no.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
            }
            //cancel button click of custom layout
            mDialogView.btn_yes.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
            }
        }

        var score1 = 0
        var score2 = 0

        fragmentGridsDetailBinding!!.plusOne.setOnClickListener {
            score1++
            fragmentGridsDetailBinding!!.score.text = score1.toString()
        }

        fragmentGridsDetailBinding!!.plusTwo.setOnClickListener {
            score2++
            fragmentGridsDetailBinding!!.score2.text = score2.toString()
        }

        fragmentGridsDetailBinding!!.minusOne.setOnClickListener {
            if(score1 != 0)
                score1--
            fragmentGridsDetailBinding!!.score.text = score1.toString()
        }

        fragmentGridsDetailBinding!!.minusTwo.setOnClickListener {
            if(score2 != 0)
                score2--
            fragmentGridsDetailBinding!!.score2.text = score2.toString()
        }

        fragmentGridsDetailBinding!!.plus2One.setOnClickListener {
            score1 += 2
            fragmentGridsDetailBinding!!.score.text = score1.toString()
        }

        fragmentGridsDetailBinding!!.plus2Two.setOnClickListener {
            score2 += 2
            fragmentGridsDetailBinding!!.score2.text = score2.toString()
        }

        fragmentGridsDetailBinding!!.reset.setOnClickListener {
            score1 = 0
            score2 = 0
            fragmentGridsDetailBinding!!.score.text = score1.toString()
            fragmentGridsDetailBinding!!.score2.text = score2.toString()
        }

        fragmentGridsDetailBinding!!.save.setOnClickListener {
            val scoreRequest = ScoreRequest(score1, score2)

            val id = matche.id

            RetrofitInstance.api.updateScore(id, scoreRequest)
                .enqueue(object : Callback<Matche> {
                    override fun onResponse(call: Call<Matche>, response: Response<Matche>) {
                        if (response.isSuccessful) {
                            Log.d("TAG_SUCCESS" + response.body(), response.message())
                            Log.d("TAG_SUCCESS", response.message())

                        } else {
                            Log.d("TAG_ERROR_MESSAGE", response.message())
                            Log.d("TAG_ERROR_BODY", response.body().toString())
                            Log.d("TAG_ERROR_ERRORBODY", response.errorBody().toString())
                            Log.d("TAG_ERROR_CODE", response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<Matche>, t: Throwable) {
                        Log.d("TAG_failure", t.message.toString())
                    }
                })

            val bundle = Bundle().apply {
                putSerializable("competition", competition)
            }
            findNavController().navigate(R.id.action_gridsDetailGridFragment_to_competitionsGridFragment, bundle)
        }
    }
}