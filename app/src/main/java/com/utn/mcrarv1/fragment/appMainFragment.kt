package com.utn.mcrarv1.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar
import com.utn.mcrarv1.R
import com.utn.mcrarv1.R.menu.app_menu
import com.utn.mcrarv1.database.appdatabase
import com.utn.mcrarv1.database.notebookDao
import com.utn.mcrarv1.entities.notebook
import com.wajahatkarim3.roomexplorer.RoomExplorer


class appMainFragment : Fragment() {

    lateinit var v: View
    lateinit var vBar: View
   // private var mainlayout: DrawerLayout? = null

    lateinit var readyUser : TextView
    var logedUser : String = ""
    var userID : Int = 0

    lateinit var toList : Button
    lateinit var toTab : Button
    lateinit var toSettings : Button
    lateinit var toDebug : Button

    private var db: appdatabase? = null
    private var notebookDao: notebookDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // (activity as AppCompatActivity?)!!.setSupportActionBar(app_menu)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_appmain, container, false)


        toList = v.findViewById(R.id.buttonAppMain)
        toTab = v.findViewById(R.id.tabButton)
        toSettings = v.findViewById(R.id.settingsButton)
        readyUser = v.findViewById(R.id.txtReadyUser)
        toDebug = v.findViewById(R.id.debugButton)
        return v

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onStart() {
        super.onStart()

        logedUser = appMainFragmentArgs.fromBundle(requireArguments()).user
        //readyUser.text = logedUser
        userID = appMainFragmentArgs.fromBundle(requireArguments()).userid
        readyUser.text = logedUser + userID.toString()

        val preferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        db = appdatabase.getAppDataBase(v.context)
        notebookDao = db?.notebookDao()

        toList.setOnClickListener {

            //val action2List =
              //  fragmentAppMainFragmentDirections.actionAppMainFragmentToAppListFragment2(userID)
            //v.findNavController().navigate(action2List)
        }

        toTab.setOnClickListener{
            //val action2Tab = fragmentAppMainFragmentDirections.actionAppMainFragmentToContainerFragment()
            //v.findNavController().navigate(action2Tab)
        }

        toSettings.setOnClickListener{
           // val action2Settings = appMainFragmentDirections.actionAppMainFragmentToSettingsActivity()
            //v.findNavController().navigate(action2Settings)
        }
        toDebug.setOnClickListener {

            //RoomExplorer.show(context, appdatabase::class.java, "myDB")
        }


    }


}