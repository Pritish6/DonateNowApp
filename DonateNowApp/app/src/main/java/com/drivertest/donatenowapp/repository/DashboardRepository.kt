package com.drivertest.donatenowapp.repository

import com.drivertest.donatenowapp.Model.User
import com.drivertest.donatenowapp.data.local.prefs.UserPreferences
import com.drivertest.donatenowapp.remote.NetworkService
import com.drivertest.donatenowapp.remote.request.donorDetails
import io.reactivex.Single
import okhttp3.RequestBody
import okhttp3.ResponseBody
import javax.inject.Inject


class DashboardRepository @Inject constructor(
    private val networkService: NetworkService,
//    private val databaseService: DatabaseService,
    private val userPreferences: UserPreferences
)  {


        fun getCurrentUser(): User? {

        val userId = userPreferences.getUserId()
        val bloodroup = userPreferences.getBloodGroup()
      val userName = userPreferences.getUserName()
//        val accessToken = userPreferences.getAccessToken()

        return if (userId !== null && bloodroup != null )
            User(userId.toInt(),userName, bloodroup)
        else
            null
    }
    suspend fun updateDonaorStatus(body : donorDetails, id:Long):Single<Unit>{
      return  networkService.updateDonorDetails(id,body)
    }
    fun updateDonaorStatusNormal(body : donorDetails, id:Long):Single<ResponseBody>{
        return  networkService.updateDonorDetailsNormal(id,body)
    }
}