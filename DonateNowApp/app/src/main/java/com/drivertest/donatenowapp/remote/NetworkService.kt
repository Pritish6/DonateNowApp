package com.drivertest.donatenowapp.remote

import com.drivertest.donatenowapp.remote.request.donorDetails
import com.mindorks.bootcamp.instagram.data.remote.response.*
import io.reactivex.Single
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface NetworkService {




    @GET(Endpoints.LOGIN)
    fun doLoginCall(
     //   @Body request: LoginRequest,
     //   @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<LoginResponse>



@PUT(Endpoints.UPDATE_DONOR_DETAILS)
suspend fun updateDonorDetails(

   // @Body params: RequestBody,
    @Path("id") id: Long,
    @Body donorDetails: donorDetails,
): Single<Unit>

    @PUT(Endpoints.UPDATE_DONOR_DETAILS)
     fun updateDonorDetailsNormal(

        // @Body params: RequestBody,
        @Path("id") id: Long,
        @Body donorDetails: donorDetails,
    ): Single<ResponseBody>

//    @GET(Endpoints.HOME_POSTS_LIST)
//    fun doHomePostListCall(
//        @Query("firstPostId") firstPostId: String?,
//        @Query("lastPostId") lastPostId: String?,
//        @Header(Networking.HEADER_USER_ID) userId: String,
//        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String,
//        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
//    ): Single<PostListResponse>
//
//    @PUT(Endpoints.POST_LIKE)
//    fun doPostLikeCall(
//        @Body request: PostLikeModifyRequest,
//        @Header(Networking.HEADER_USER_ID) userId: String,
//        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String,
//        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
//    ): Single<GeneralResponse>
//
//    @PUT(Endpoints.POST_UNLIKE)
//    fun doPostUnlikeCall(
//        @Body request: PostLikeModifyRequest,
//        @Header(Networking.HEADER_USER_ID) userId: String,
//        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String,
//        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
//    ): Single<GeneralResponse>
//
//    @Multipart
//    @POST(Endpoints.UPLOAD_IMAGE)
//    fun doImageUpload(
//        @Part image: MultipartBody.Part,
//        @Header(Networking.HEADER_USER_ID) userId: String,
//        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String,
//        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
//    ): Single<ImageResponse>
//
//    @POST(Endpoints.CREATE_POST)
//    fun doPostCreationCall(
//        @Body request: PostCreationRequest,
//        @Header(Networking.HEADER_USER_ID) userId: String,
//        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String,
//        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
//    ): Single<PostCreationResponse>
}