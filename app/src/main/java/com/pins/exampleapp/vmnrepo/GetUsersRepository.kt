package com.woo.groupchat.viewmodels.addmember

import android.util.Log
import com.pins.exampleapp.di.ApiService
import com.pins.exampleapp.models.LiveTvDetail

class GetUsersRepository(var service : ApiService) {

    suspend fun getUserList(): MutableList<LiveTvDetail>?{
        return try {
            service.getUsers()
        }
        catch (e : Exception){
            Log.e(GetUsersRepository::class.java.name,e.localizedMessage)
            null
        }
    }

//    suspend fun SendMessage(apiAccessToken: String,
//                           userid: String,
//                           group_id: RequestBody,
//                           msgtxt: RequestBody,
//                           contains_image: RequestBody,
//                           is_replied: RequestBody,
//                           reply_msg_id: RequestBody
//    ): SendMessageRespo?{
//        return try {
//            service.sendMessage(apiAccessToken, userid,group_id,msgtxt,contains_image,is_replied,reply_msg_id)
//        }
//        catch (e : Exception){
//            Log.e(ChatsRepository::class.java.name,e.localizedMessage)
//            null
//        }
//    }

}