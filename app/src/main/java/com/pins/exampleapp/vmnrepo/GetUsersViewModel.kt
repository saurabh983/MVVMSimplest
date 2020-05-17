package com.woo.groupchat.viewmodels.addmember

import android.content.Context
import android.text.Editable
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.pins.exampleapp.extensions.Outcome
import com.pins.exampleapp.models.LiveTvDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.security.NoSuchAlgorithmException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class GetUsersViewModel(
    private val chatsRepository: GetUsersRepository
) : ViewModel(), LifecycleObserver {

    val outComeData: MutableLiveData<Outcome<MutableList<LiveTvDetail>>> = MutableLiveData()

    private var mContext: Context? = null

    private var listener: LoadUserListner? = null

    val msgText = ObservableField<String>()
    val msgTextError = ObservableField<String>()

    fun setListener(listener: LoadUserListner) {
        this.listener = listener
    }

    fun checkMsg(editable: Editable) {

        when (editable.toString()) {
            "" -> {
                msgTextError.set("Please enter your email")
            }
            else -> {
                msgTextError.set(null)
            }
        }
    }

    fun setContext(context: Context) {
        mContext = context
    }
// Uncomment this to get users on Activity Resume
//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    fun getUsersList() {
//        getUsersListing()
//    }

    fun getUsersListing() {
        viewModelScope.launch(Dispatchers.IO) {

            val responseJson =
                chatsRepository.getUserList()

            if (responseJson != null) {
                responseJson.let {
                    withContext(Dispatchers.Main) {
//                        if (it.status.equals("error")) {
//                            outcomeDataPending.postValue(Outcome.failure(Throwable(it.msg)))
//                        } else {
                        outComeData.postValue(Outcome.success(it))
//                        }
                    }
                    return@launch
                }
            } else {
                withContext(Dispatchers.Main) {
                    outComeData.postValue(Outcome.failure(Throwable("Error")))
                }
            }
        }
    }

    //on click of a button
    fun getUsers(view: View) {
        listener!!.onLoadingStarted()
        viewModelScope.launch(Dispatchers.IO) {

            val responseJson =
                chatsRepository.getUserList()

            if (responseJson != null) {
                responseJson.let {
                    withContext(Dispatchers.Main) {
//                        if (it.status.equals("error")) {
//                            outcomeDataPending.postValue(Outcome.failure(Throwable(it.msg)))
//                        } else {
                            outComeData.postValue(Outcome.success(it))
//                        }
                        listener!!.onLoadingEnded()
                    }
                    return@launch
                }
            } else {
                withContext(Dispatchers.Main) {
                    outComeData.postValue(Outcome.failure(Throwable("Error")))
                    listener!!.onLoadingEnded()
                }
            }
        }
    }

    interface LoadUserListner {
        fun onLoadingStarted()
        fun onLoadingEnded()
    }

}