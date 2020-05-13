package com.sitiaisyah.idn.finaltest.comon

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import com.sitiaisyah.idn.finaltest.R
import com.sitiaisyah.idn.finaltest.data.MainObservableViewModel

object BindingAdapter {

    @BindingAdapter("app:popularityIcon")
    @JvmStatic
    fun popularityIcon(imageView: ImageView, popularity: MainObservableViewModel.LikeNumbers){
        val color =
            getAssociateLikeColor(
                popularity,
                imageView.context
            )
        ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(color))
        imageView.setImageDrawable(
            getDrawableLikePopularity(
                popularity,
                imageView.context
            )
        )
    }

    private fun getDrawableLikePopularity(
        popularity: MainObservableViewModel.LikeNumbers,
        context: Context
    ): Drawable? {
        return when(popularity){
            MainObservableViewModel.LikeNumbers.START -> ContextCompat.getDrawable(context,
                R.drawable.like_ig
            )
            MainObservableViewModel.LikeNumbers.NORMAL -> ContextCompat.getDrawable(context,
                R.drawable.likes_ig
            )
            MainObservableViewModel.LikeNumbers.POP -> ContextCompat.getDrawable(context, R.drawable.likes_ig)
        }
    }

    private fun getAssociateLikeColor(
        popularity: MainObservableViewModel.LikeNumbers,
        context: Context
    ): Int{
        return when (popularity){
            MainObservableViewModel.LikeNumbers.START -> ContextCompat.getColor(context,
                R.color.colorStart
            )
            MainObservableViewModel.LikeNumbers.NORMAL -> ContextCompat.getColor(context,
                R.color.colorNormal
            )
            MainObservableViewModel.LikeNumbers.POP -> ContextCompat.getColor(context, R.color.colorPop)
        }
    }
}