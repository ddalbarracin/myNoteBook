package com.utn.mcrarv1.fragment

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.Notification
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.utn.mcrarv1.R
import com.utn.mcrarv1.adapters.ListAdapter
import com.utn.mcrarv1.database.appdatabase
import com.utn.mcrarv1.database.notebookDao
import com.utn.mcrarv1.entities.notebook
import kotlinx.android.synthetic.main.adduser_dialog.*


class appListFragment : Fragment(){

    lateinit var v: View
    var userID: Int = 0
    lateinit var addUser: FloatingActionButton


    lateinit var recNotebook: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    var notebookList: MutableList<notebook> = ArrayList<notebook>()
    private lateinit var ListAdapter: ListAdapter

    //room database
    private var db: appdatabase? = null
    private var notebookDao: notebookDao? = null
    lateinit var notebooks: MutableList<notebook>



    companion object {
        fun newInstance() = appListFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_applist, container, false)

        recNotebook = v.findViewById(R.id.recAppList)
        addUser = v.findViewById(R.id.floatingButton)

        setHasOptionsMenu(true)

        userID = appListFragmentArgs.fromBundle(requireArguments()).userLoged

        return v
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.app_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onStart() {
        super.onStart()

        db = appdatabase.getAppDataBase(v.context)
        notebookDao = db?.notebookDao()

        userID = appListFragmentArgs.fromBundle(requireArguments()).userLoged

        recNotebook.setHasFixedSize(true)

        linearLayoutManager = LinearLayoutManager(context)

        recNotebook.layoutManager = linearLayoutManager

        notebookList = notebookDao?.loadContactByUserId(userID) as MutableList<notebook>



        ListAdapter = ListAdapter(notebookList, requireContext(), { position -> onItemClick(position) }, { position -> onLongItemClick(position) })

        recNotebook.adapter = ListAdapter

        addUser.setOnClickListener {

            adduserNew()
            Snackbar.make(v, "New User Added", Snackbar.LENGTH_SHORT).show()
        }
    }

    fun onItemClick(position: Int) {

        var contactID = notebookList[position].id

        val action2contact = appListFragmentDirections.actionAppListFragment2ToContainerFragment(userID, contactID)
        Snackbar.make(v, position.toString(), Snackbar.LENGTH_SHORT).show()
        v.findNavController().navigate(action2contact)
    }

    fun onLongItemClick(position: Int): Boolean {

        val builder = AlertDialog.Builder(v.context)
        builder.setTitle("Remove User")
        builder.setMessage("Are you sure remove user?")
        builder.setPositiveButton("Accept") { dialog, which -> removeUser(position) }

        builder.setNegativeButton("Cancel") { dialog,
                                              which ->
            Snackbar.make(
                v,
                "Canceled",
                Snackbar.LENGTH_SHORT
            ).show()

        }
        val dialog: AlertDialog = builder.create()
        dialog.show()

        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = when (item.itemId) {

            R.id.action_add -> adduserNew()//Snackbar.make(v, "add", Snackbar.LENGTH_SHORT).show()
            //R.id.action_remove -> Snackbar.make(v, "remove", Snackbar.LENGTH_SHORT).show()
            R.id.action_settings -> toSettings() //Snackbar.make(v, "settings", Snackbar.LENGTH_SHORT).show()

            else -> ""
        }
        return super.onOptionsItemSelected(item)
    }

    fun adduserNew(){


        userID = appListFragmentArgs.fromBundle(requireArguments()).userLoged

        val action2add = appListFragmentDirections.actionAppListFragment2ToAddFragment2(userID)
        findNavController().navigate(action2add)
        //val newFragment = notifyDialog()
        //newFragment.show(childFragmentManager, "adduser")
        //newFragment.listener.onDialogPositiveClick(newFragment)
    }

    fun removeUser(position : Int){

        userID = appListFragmentArgs.fromBundle(requireArguments()).userLoged

        notebookList = notebookDao?.loadContactByUserId(userID) as MutableList<notebook>

        notebookDao?.deleteContact(notebookList[position])

    }
    fun toSettings() {

        val action2Settings = appListFragmentDirections.actionAppListFragment2ToSettingsActivity()
        findNavController().navigate(action2Settings)
    }

}