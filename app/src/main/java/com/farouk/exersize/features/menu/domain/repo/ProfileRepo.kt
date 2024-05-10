package com.farouk.exersize.features.menu.domain.repo

import com.farouk.exersize.features.menu.domain.Entity.EditeProfileResponse
import com.farouk.exersize.features.menu.domain.Entity.UnsubscribeResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface ProfileRepo {


    suspend fun updateProfile(
        fname: RequestBody,
        lname: RequestBody,
        phone: RequestBody,
        img: MultipartBody.Part,
        token: RequestBody,
    ): EditeProfileResponse

    suspend fun unsubscribe(token: String): UnsubscribeResponse


}