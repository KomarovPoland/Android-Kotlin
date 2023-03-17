package com.GDSC_IUCA.iuca_tour

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.GDSC_IUCA.iuca_tour.databinding.FragmentFirstBinding
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.kaopiz.kprogresshud.KProgressHUD


class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)

        val hud = KProgressHUD.create(activity)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setBackgroundColor(Color.parseColor("#6F7E7D7D"))
            .show()



        // Slider ...
        val imageList = ArrayList<SlideModel>() // Create image list

        imageList.add(SlideModel("http://tour.iuca.kg/media/images/1649303093342_e6OeRvg.jpg", ScaleTypes.FIT))
        imageList.add(SlideModel("http://tour.iuca.kg/media/images/IMG_20220328_125301.jpg", ScaleTypes.FIT))

//        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
        val imageS = binding.imageSlider

        imageS.setImageList(imageList)
        hud.dismiss()



    }
}