package com.drivertest.donatenowapp.ui.dashboard

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.drivertest.donatenowapp.Model.User
import com.drivertest.donatenowapp.remote.request.donorDetails
import com.drivertest.donatenowapp.repository.DashboardRepository
import com.drivertest.donatenowapp.repository.UserRepository
import com.drivertest.donatenowapp.ui.base.BaseViewModel
import com.drivertest.donatenowapp.utils.network.NetworkHelper
import com.drivertest.donatenowapp.utils.rx.SchedulerProvider
import com.google.gson.GsonBuilder
import com.mindorks.bootcamp.instagram.utils.common.Event
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject


class DashboardViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val userRepository: UserRepository,

    networkHelper: NetworkHelper,
    private val dashboardRepository: DashboardRepository


) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    private val changeProfilestate = MutableLiveData<Event<Boolean>>()

//    val changeProfileState : LiveData<Event<Boolean>>
//        get() = _navigateToDetails


    fun profileEnabledisableToggelOccured(state: Boolean) {
        changeProfilestate.postValue(Event(state))  // Trigger the event by setting a new Event as a new value
    }
    val user: MutableLiveData<User> = MutableLiveData()

    val loggingIn: MutableLiveData<Boolean> = MutableLiveData()
    val enable_disable: MutableLiveData<Event<Boolean>> = MutableLiveData()
fun dialogClicked(){
    enable_disable.postValue(Event(true))
}
fun getLoginData(){
   val userobj = userRepository.getCurrentUser();
    user.postValue(userobj)

}

//    fun getUser():User{
//        return dashboardRepository.getCurrentUser()!!;
//    }
 //   val passwordField: MutableLiveData<String> = MutableLiveData()
 //   val loggingIn: MutableLiveData<Boolean> = MutableLiveData()

//    private val validationsList: MutableLiveData<List<Validation>> = MutableLiveData()
//
//    val launchMain: MutableLiveData<User> = MutableLiveData()
//
//    val emailField: MutableLiveData<String> = MutableLiveData()
//    val passwordField: MutableLiveData<String> = MutableLiveData()
//    val loggingIn: MutableLiveData<Boolean> = MutableLiveData()
//
//    val emailValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.EMAIL)
//    val passwordValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.PASSWORD)

//    private fun filterValidation(field: Validation.Field) =
//        Transformations.map(validationsList) {
//            it.find { validation -> validation.field == field }
//                ?.run { return@run this.resource }
//                ?: Resource.unknown()
//        }

    override fun onCreate() {}



//    fun onLogin() {
//        try{
//            val email = emailField.value
//            val password = passwordField.value
//
//            val validations = Validator.validateLoginFields(email, password)
//            validationsList.postValue(validations)
//
//            if (validations.isNotEmpty() && email != null && password != null) {
//                val successValidation = validations.filter { it.resource.status == Status.SUCCESS }
//                if (successValidation.size == validations.size) {
//                    loggingIn.postValue(true)
////                loggingIn.postValue(false)
////                              launchMain.postValue(Event(emptyMap()))
//
//
//                    compositeDisposable.addAll(
//                        userRepository.doUserLogin(email, password)
//                            .subscribeOn(schedulerProvider.io())
//                            .subscribe(
//                                {
//                                    Log.d("+++user",it.name)
//
//                                    userRepository.saveCurrentUser(it)
//                                    loggingIn.postValue(false)
//                                    launchMain.postValue(it)
//                                },
//                                {
//                                    handleNetworkError(it)
//                                    loggingIn.postValue(false)
//                                }
//                            )
//                    )
//                }
//            }
//        }
//        catch (e: Exception){e.printStackTrace()}
//    }
fun changedonorstatus(status:String){
    loggingIn.postValue(true)
    val obj  = userRepository.getCurrentUser()
//    val map = HashMap<String, Any>()
//    map["donorStatus"] = "Active"
//    map["addressLine1"] = "string"
//    map["addressLine2"]= "string"
//    map["bloodGrp"]= "string"
//    map["city"]= "string"
//    map["deviceToken"]= "string"
//    map["dob"]= "2021-08-30"
//    map["donorID"]= 0
//
//    map["lastBloodDonationDate"] = "2021-08-30"
//    map["lastLocation"]= "string"
//    map["name"]= "string"
//    map["passwd"]= "string"
//    map["rating"]= "string"
//    map["state"]= "string"
//    map["totalDonations"] = 0
//    map["username"] = "string"
//    map["zipCode"]= "string"
//   val body = JSONObject(map as Map<*, *>).toString()
//        .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
   val DonorDetails = donorDetails(status,"string","string")
    val gson = GsonBuilder().disableHtmlEscaping().create()
    val placeJSON = gson.toJson(DonorDetails)
    viewModelScope.launch {
       try {
        coroutineScope {
            if (obj != null) {
                dashboardRepository.updateDonaorStatus(DonorDetails,obj.id.toLong()).subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            it?.let { it1 -> Log.d("+++responseSuccess", it1.toString()) }
                            loggingIn.postValue(false)


                        },
                        {
                            it?.let { it1 -> Log.d("+++responseFail", it1.toString()) }
                            loggingIn.postValue(false)
                        }
                    )
            }
        }
       } catch (e: Exception) {
         e.printStackTrace()

           loggingIn.postValue(false)
           return@launch
       }
    }

}



    fun changedonorstatusNormal(status:String){
    //    loggingIn.postValue(true)
        val obj  = userRepository.getCurrentUser()
//    val map = HashMap<String, Any>()
//    map["donorStatus"] = "Active"
//    map["addressLine1"] = "string"
//    map["addressLine2"]= "string"
//    map["bloodGrp"]= "string"
//    map["city"]= "string"
//    map["deviceToken"]= "string"
//    map["dob"]= "2021-08-30"
//    map["donorID"]= 0
//
//    map["lastBloodDonationDate"] = "2021-08-30"
//    map["lastLocation"]= "string"
//    map["name"]= "string"
//    map["passwd"]= "string"
//    map["rating"]= "string"
//    map["state"]= "string"
//    map["totalDonations"] = 0
//    map["username"] = "string"
//    map["zipCode"]= "string"
//   val body = JSONObject(map as Map<*, *>).toString()
//        .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val DonorDetails = donorDetails(status,"string","string")
//        val gson = GsonBuilder().disableHtmlEscaping().create()
//        val placeJSON = gson.toJson(DonorDetails)
   //     viewModelScope.launch {
            try {
    //            coroutineScope {
                    if (obj != null) {

                        compositeDisposable.addAll(
//                        userRepository.doUserLogin(email, password)
//                            .subscribeOn(schedulerProvider.io())
//                            .subscribe(
//                                {
//                                    Log.d("+++user",it.name)
//
//                                    userRepository.saveCurrentUser(it)
//                                    loggingIn.postValue(false)
//                                    launchMain.postValue(it)
//                                },
//                                {
//                                    handleNetworkError(it)
//                                    loggingIn.postValue(false)
//                                }
//                            )
//                    )
                        dashboardRepository.updateDonaorStatusNormal(DonorDetails,obj.id.toLong()).subscribeOn(schedulerProvider.io())
                            .subscribe(
                                {
                                    it?.let { it1 -> Log.d("+++responseSuccess", it1.toString()) }
                                    loggingIn.postValue(false)


                                },
                                {
                                    it?.let { it1 -> Log.d("+++responseFail", it1.toString()) }
                                    loggingIn.postValue(false)
                                }
                           )

                        )  }
        //        }
            } catch (e: Exception) {
                e.printStackTrace()

                loggingIn.postValue(false)

            }
  //      }

    }
}