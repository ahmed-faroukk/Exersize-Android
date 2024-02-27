package com.farouk.exersize.user.data.local

import com.farouk.exersize.user.data.model.UserDataModel
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {

    suspend fun setLoggedIn()

    suspend fun isLoggedIn(): Flow<Boolean>

    suspend fun saveData(userDataModel: UserDataModel)

    suspend fun getData(): Flow<UserDataModel?>

}