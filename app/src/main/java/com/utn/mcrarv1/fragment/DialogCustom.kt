package com.utn.mcrarv1.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar
import com.utn.mcrarv1.R
import kotlinx.coroutines.NonCancellable.cancel
import java.lang.IllegalStateException

class DialogCustom : DialogFragment() {

    lateinit var v : View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout to use as dialog or embedded fragment

        v = inflater.inflate(R.layout.adduser_dialog, container, false)

        return  v
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater;

        v = inflater.inflate(R.layout.adduser_dialog, null)

        return v?.let {
            val builder = AlertDialog.Builder(v.context)

            builder.setView(inflater.inflate(R.layout.adduser_dialog, null))
                .setPositiveButton(R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->

                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog()!!.cancel()
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}