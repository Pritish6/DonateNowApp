package com.drivertest.donatenowapp.repository


import com.drivertest.donatenowapp.Model.User
import com.drivertest.donatenowapp.data.local.prefs.UserPreferences
import com.drivertest.donatenowapp.remote.NetworkService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val networkService: NetworkService,
//    private val databaseService: DatabaseService,
    private val userPreferences: UserPreferences
) {

    fun saveCurrentUser(user: User) {
        userPreferences.setUserId(user.id.toString())
        userPreferences.setUserName(user.name.toString())
       userPreferences.setBloodGroup(user.bloodGrp)
//        userPreferences.setAccessToken(user.accessToken)
    }
//
//    fun removeCurrentUser() {
//        userPreferences.removeUserId()
//        userPreferences.removeUserName()
//        userPreferences.removeUserEmail()
//        userPreferences.removeAccessToken()
//    }
//
    fun getCurrentUser(): User? {

        val userId = userPreferences.getUserId()
        val userName = userPreferences.getUserName()
        val bloodGroup = userPreferences.getBloodGroup()


        return if (userId !== null && userName != null && bloodGroup != null)
            User(userId.toInt(), userName, bloodGroup)
        else
            null
    }
//
    fun doUserLogin(email: String, password: String): Single<User> =
        networkService.doLoginCall()
            .map {
                User(
                    it.donorID,
                    it.name,

                    it.bloodGrp

                )
            }
}