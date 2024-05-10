package com.farouk.exersize.features.menu.data.repo

import com.farouk.exersize.features.menu.data.remote.ProfileApiInterface
import com.farouk.exersize.features.menu.domain.Entity.EditeProfileResponse
import com.farouk.exersize.features.menu.domain.Entity.UnsubscribeResponse
import com.farouk.exersize.features.menu.domain.repo.ProfileRepo
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class ProfileRepoImpl @Inject constructor(
    val apiInterface: ProfileApiInterface
) : ProfileRepo {
    override suspend fun updateProfile(
        fname: RequestBody,
        lname: RequestBody,
        phone: RequestBody,
        img: MultipartBody.Part,
        token: RequestBody,
    ): EditeProfileResponse = apiInterface.updateProfile(fname, lname, phone, img, token)

    override suspend fun unsubscribe(token: String): UnsubscribeResponse = apiInterface.unsubscribe(token)

}