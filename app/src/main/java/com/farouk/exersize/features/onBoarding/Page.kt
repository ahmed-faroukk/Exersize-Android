package com.farouk.exersize.features.onBoarding

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.ui.platform.LocalContext
import com.farouk.exersize.R
import dagger.hilt.android.qualifiers.ApplicationContext


data class Page(
    @DrawableRes val background: Int = R.drawable.ellipse,
    @DrawableRes val img: Int,
    val title: Int,
    val desc: Int,
) {
    companion object {

        val listOfPages = mutableListOf(
            Page(
                img = R.drawable.onboarding1,
                title = R.string.onboarding1_title,
                desc = R.string.onboarding1_body,
            ),


            Page(
                img = R.drawable.onboarding2,
                title = R.string.onboarding2_title,
                desc = R.string.onboarding2_body
            ),

            Page(
                img = R.drawable.onboarding3,
                title = R.string.onboarding3_title,
                desc = R.string.onboarding3_body

            )

        )
    }
}

