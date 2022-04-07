package com.drivertest.donatenowapp.ui.login

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.drivertest.donatenowapp.Model.User
import com.drivertest.donatenowapp.repository.UserRepository
import com.drivertest.donatenowapp.ui.base.BaseViewModel
import com.drivertest.donatenowapp.utils.common.Resource
import com.drivertest.donatenowapp.utils.common.Status
import com.drivertest.donatenowapp.utils.common.Validation
import com.drivertest.donatenowapp.utils.common.Validator
import com.drivertest.donatenowapp.utils.network.NetworkHelper
import com.drivertest.donatenowapp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    private val validationsList: MutableLiveData<List<Validation>> = MutableLiveData()

    val launchMain: MutableLiveData<User> = MutableLiveData()
    val progress = MutableLiveData<Int>(8)

    val emailField: MutableLiveData<String> = MutableLiveData()
    val passwordField: MutableLiveData<String> = MutableLiveData()
    val loggingIn: MutableLiveData<Boolean> = MutableLiveData()

    val emailValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.EMAIL)
    val passwordValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.PASSWORD)

    private fun filterValidation(field: Validation.Field) =
        Transformations.map(validationsList) {
            it.find { validation -> validation.field == field }
                ?.run { return@run this.resource }
                ?: Resource.unknown()
        }

    override fun onCreate() {

    }


    fun onEmailChange(email: String) = emailField.postValue(email)

    fun onPasswordChange(email: String) = passwordField.postValue(email)

//    @Bindable
//    fun getloggingIn(): Int {
//        return if (loggingIn) VISIBLE else GONE
//    }
    fun onLogin() {
        try{
        val email = emailField.value
        val password = passwordField.value

        val validations = Validator.validateLoginFields(email, password)
        validationsList.postValue(validations)
            loggingIn.postValue(true)

            loggingIn.value = true
        if (validations.isNotEmpty() && email != null && password != null) {

            val successValidation = validations.filter { it.resource.status == Status.SUCCESS }
            if (successValidation.size == validations.size) {
    //           loggingIn.postValue(false)
                progress.value=0

//                loggingIn.postValue(false)
//                              launchMain.postValue(Event(emptyMap()))


                compositeDisposable.addAll(
                    userRepository.doUserLogin(email, password)
                        .subscribeOn(schedulerProvider.io())
                        .subscribe(
                            {
                                it?.let { it1 -> Log.d("+++user", it1.toString()) }

                                userRepository.saveCurrentUser(it)
                                loggingIn.postValue(false)

                               launchMain.postValue(it)


                            },
                            {
                                handleNetworkError(it)

                                loggingIn.postValue(false)


                            }
                        )
                )
            }
        }
    }
        catch (e:Exception){e.printStackTrace()}
}

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


}