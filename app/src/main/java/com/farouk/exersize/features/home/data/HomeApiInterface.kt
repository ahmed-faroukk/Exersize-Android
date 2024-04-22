package com.farouk.exersize.features.home.data

import com.farouk.exersize.features.home.domain.entity.RequestPackageResponse
import com.farouk.exersize.features.home.domain.entity.ShowCoachResponse
import com.farouk.exersize.features.home.domain.entity.ShowCoachesResponse
import com.farouk.exersize.features.home.domain.entity.ShowPackagesResponse
import retrofit2.http.POST

interface HomeApiInterface {
companion object{

    const val GET_ALL_COACHES_ENDPOINT = "api/trainee/coaches"
    const val GET_COACH_ENDPOINT = "api/trainee/coach"
    const val GET_PACKAGES_ENDPOINT = "api/trainee/package/show"
    const val REQ_PACKAGES_ENDPOINT = "api/trainee/package/request"

}
    @POST(GET_ALL_COACHES_ENDPOINT)
    suspend fun getAllCoaches(token : String) : ShowCoachesResponse

    @POST(GET_COACH_ENDPOINT)
    suspend fun getCoach(id : String , token : String) : ShowCoachResponse

    @POST(GET_PACKAGES_ENDPOINT)
    suspend fun getPackages(coach_id : String , token : String) : ShowPackagesResponse

    @POST(REQ_PACKAGES_ENDPOINT)
    suspend fun reqPackages(coach_id : String ,package_id : String, token : String) : RequestPackageResponse


}

