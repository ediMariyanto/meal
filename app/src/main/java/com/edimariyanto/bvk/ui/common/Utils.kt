package com.edimariyanto.bvk.ui

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.edimariyanto.bvk.ui.category.CategoryFragment
import com.edimariyanto.bvk.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar


fun <A : Activity> Activity.startNewActivity(activity: Class<A>){
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}


fun View.snackbar(message: String, action: (() -> Unit)? = null) {

    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()

}

fun Fragment.handleApiError(
        failure: com.edimariyanto.bvk.data.network.Resources.Failure,
        retry: (() -> Unit)? = null) {

    when{
        failure.isNetworkError -> requireView().snackbar("Please check your internet connection", retry)
        failure.errorCode == 401 ->{
            if (this is CategoryFragment){
                requireView().snackbar("You've entered incorrect email pr password")
            }
        }
        else -> {
            val error = failure.errorBody?.string().toString()
            requireView().snackbar(error)
        }
    }
}