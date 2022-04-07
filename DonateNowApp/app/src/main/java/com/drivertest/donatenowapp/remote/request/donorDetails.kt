package com.drivertest.donatenowapp.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class donorDetails(
//    @Expose
//    @SerializedName("id")
//    var id: Int,

    @Expose
    @SerializedName("donorStatus")
    var donorStatus: String,

@SerializedName("deviceToken")
var deviceToken: String,

@Expose
@SerializedName("lastLocation")
var lastLocation: String
)
