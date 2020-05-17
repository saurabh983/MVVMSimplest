package com.app.cheffypartner.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle


fun Activity.goToActivity(newActivity: Class<*>,bundle: Bundle = Bundle(), clearTask : Boolean = false) {
    val intent = Intent(this, newActivity)
    intent.putExtra("data",bundle)
    if (clearTask){
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    startActivity(intent)
}