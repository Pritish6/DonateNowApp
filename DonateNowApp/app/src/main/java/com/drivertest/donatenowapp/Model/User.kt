package com.drivertest.donatenowapp.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class User(


    @Expose
    @SerializedName("donorId")
    val id: Int,

    @Expose
    @SerializedName("userName")
    val name: String?,

    @Expose
    @SerializedName("bloodGrp")
    val bloodGrp: String,


    )

