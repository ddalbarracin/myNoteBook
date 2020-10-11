package com.utn.mcrarv1.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.utn.mcrarv1.R
import com.utn.mcrarv1.database.appdatabase
import com.utn.mcrarv1.database.notebookDao
import com.utn.mcrarv1.entities.notebook
import kotlinx.android.synthetic.main.fragment_add.*

class addFragment : Fragment() {

    lateinit var v : View
    lateinit var addName : EditText
    lateinit var addLastname : EditText
    lateinit var addPhone : EditText
    lateinit var addImage : EditText
    lateinit var acceptButton : Button
    lateinit var cancelButton : Button


    //room database
    private var db: appdatabase? = null
    private var notebookDao: notebookDao? = null
    lateinit var notebookList: MutableList<notebook>





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_add, container, false)

        addName = v.findViewById(R.id.usernameAdd)!!
        addLastname = v.findViewById(R.id.lastnameAdd)!!
        addPhone = v.findViewById(R.id.phoneAdd)!!
        addImage = v.findViewById(R.id.imageAdd)!!
        acceptButton = v.findViewById(R.id.AcceptButtonAdd)!!
        cancelButton = v.findViewById(R.id.cancelbuttonAdd)!!

        return  v

    }

    override fun onStart() {
        super.onStart()
        //newContact

        var userLoged : Int = 0
        var alias : String = ""
        var nombre : String = ""
        var apellido : String = ""
        var telefono : String = ""
        var email : String = ""
        var direccion : String = ""
        var trabajo : String = ""
        var direccionlaboral : String = ""
        var telefonolaboral : String = ""
        var emaillaboral : String = ""
        var imagen : String = " "
        var notas : String = ""


        db = appdatabase.getAppDataBase(v.context)
        notebookDao = db?.notebookDao()

        //notebookList = notebookDao?.loadContact() as MutableList<notebook>

        userLoged = addFragmentArgs.fromBundle(requireArguments()).userID

        //Snackbar.make(v, userLoged.toString(), Snackbar.LENGTH_SHORT).show()

        //set new user




        acceptButton.setOnClickListener {

            //Snackbar.make(v, "Accept", Snackbar.LENGTH_SHORT).show()
            nombre = addName.text.toString()
            apellido = addLastname.text.toString()
            telefono = addPhone.text.toString()
            imagen = addImage.text.toString()

            notebookList = notebookDao?.loadContact() as MutableList<notebook>
            var index = notebookList[notebookList.size -1 ].id +1


            notebookDao?.insertContact(notebook(index, userLoged,alias,nombre,apellido,telefono,email,direccion,trabajo,direccionlaboral,telefonolaboral,emaillaboral,imagen,notas))

            val action2listOK = addFragmentDirections.actionAddFragmentToAppListFragment2(userLoged)
            findNavController().navigate(action2listOK)

        }

        cancelButton.setOnClickListener{

            Snackbar.make(v, "Cancel", Snackbar.LENGTH_SHORT).show()


            val action2listCancel = addFragmentDirections.actionAddFragmentToAppListFragment2(userLoged)
            findNavController().navigate(action2listCancel)

        }

    }
}