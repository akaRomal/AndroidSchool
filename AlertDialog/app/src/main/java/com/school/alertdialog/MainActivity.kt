package com.school.alertdialog

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showAlertDialog()
    }

    private fun showAlertDialog(){

        val alertDialog = resources.getDrawable(R.drawable.alert_dialog)

        val builder = AlertDialog.Builder(this)
        val dialog = builder
            .setView(R.layout.alert_dialog)
            .setPositiveButton(R.string.ok_alert_dialog){ dialog, _ ->
                dialog.cancel()
            }
            .setNegativeButton(R.string.cancel_alert_dialog){ dialog, _ ->
                dialog.cancel()
            }
            .create()
        dialog.show()
        dialog.window?.setBackgroundDrawable(alertDialog)
    }
}