package com.example.newsproject.utils

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.example.newsproject.R

object ProgressBar {

    var dialog : AlertDialog ? = null

    fun showProgressBar(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.progress_bar,null,false)
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setView(view)
        dialog = dialogBuilder.create()

        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.show()

    }
    fun hideProgressBar(){
        if(dialog != null && dialog!!.isShowing){

            dialog!!.dismiss()

        }
    }

}