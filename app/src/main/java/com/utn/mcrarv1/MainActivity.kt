package com.utn.mcrarv1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.Menu
import android.widget.EditText
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.utn.mcrarv1.fragment.notifyDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.adduser_dialog.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(){

//    notifyDialog.NoticeDialogListener {
//
//    fun showNoticeDialog() {
//        // Create an instance of the dialog fragment and show it
//        val newDialog = notifyDialog()
//        newDialog.show(supportFragmentManager, "addUser")
//    }
//
//    override fun onDialogPositiveClick(dialog: DialogFragment) {
//
//       // Snackbar.make(findViewById(R.id.appListFragment), newUserName.text.toString()+ phone.text.toString(), Snackbar.LENGTH_LONG).show();
//    }
//
//    override fun onDialogNegativeClick(dialog: DialogFragment) {
//        // User touched the dialog's negative button
//    }

    // private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}