package com.mindorks.bootcamp.instagram.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @Expose
    @SerializedName("name")
    var name: String,

    @Expose
    @SerializedName("donorID")
    var donorID: Int,

    @Expose
    @SerializedName("bloodGrp")
    var bloodGrp: String,

    @Expose
    @SerializedName("accessToken")
    var accessToken: String,

    @Expose
    @SerializedName("dob")
    var dob: String,

//    @Expose
//    @SerializedName("userName")
//    var userName: String,
//
//    @Expose
//    @SerializedName("userEmail")
//    var userEmail: String,
//
//    @Expose
//    @SerializedName("profilePicUrl")
//    var profilePicUrl: String?

)
/*
{
    "donorID": 3,
    "name": "Ajay",
    "addressLine1": "IBM",
    "addressLine2": "ozone 1",
    "city": "Mumbai",
    "state": "Maharashtra",
    "zipCode": "412308",
    "dob": "1991-01-01",
    "bloodGrp": "A+",
    "lastLocation": null,
    "donorStatus": "Active",
    "rating": null,
    "username": "ajay",
    "passwd": "ajay",
    "deviceToken": null,
    "totalDonations": 0,
    "lastBloodDonationDate": null
}
* */