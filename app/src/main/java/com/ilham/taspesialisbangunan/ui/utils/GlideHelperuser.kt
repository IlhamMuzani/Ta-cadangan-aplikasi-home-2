package com.ilham.taspesialisbangunan.ui.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ilham.taspesialisbangunan.R

class GlideHelperuser {

    companion object {

        fun setImage(context: Context, urlImage: String, imageView: ImageView){
            Glide.with(context)
                .load(urlImage)
                .centerCrop()
                .placeholder(R.drawable.img_no_image)
                .error(R.drawable.pagar2)
                .into(imageView)
        }
    }
}