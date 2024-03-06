package com.school.alertdialog

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

const val TAG_FRAGMENT = "Dialog_fragment_tag"

class MainActivity : AppCompatActivity(), OnClickFragment {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentDialog = ShowAlertDialog.newInstance(
            label = "Это текст для алерт диалога в фрагменте",
            image = R.drawable.logo
        )
        fragmentDialog.show(supportFragmentManager, TAG_FRAGMENT)

//        showAlertDialog()
    }

    override fun clickOk() {
        Toast.makeText(this, "Нажали Ок", Toast.LENGTH_SHORT).show()
    }

    override fun clickCancel() {
        Toast.makeText(this, "Нажали Отмена", Toast.LENGTH_SHORT).show()
    }

    private fun showAlertDialog(){

        val alertDialog = resources.getDrawable(R.drawable.alert_dialog, null)

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