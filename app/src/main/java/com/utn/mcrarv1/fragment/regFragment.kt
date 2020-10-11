package com.utn.mcrarv1.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.utn.mcrarv1.R
import com.utn.mcrarv1.database.accountDao
import com.utn.mcrarv1.database.appdatabase
import com.utn.mcrarv1.entities.account
import com.utn.mcrarv1.entities.useraccount
import com.wajahatkarim3.roomexplorer.RoomExplorer


class regFragment : Fragment() {

    lateinit var v : View

    //Variables de la base de datos
    private var db: appdatabase? =null
    private var userDao: accountDao? = null
    lateinit var accounts: MutableList<useraccount>

    //Variables de UI
    lateinit var regAccounts : MutableList<useraccount>
    lateinit var regInputNIC : EditText
    lateinit var regInputFirstName: EditText
    lateinit var regInputLastName : EditText
    lateinit var regInputEmail : EditText
    lateinit var regInputPassword : EditText
    lateinit var regRegister : Button

    //Variables Campos de la Base
    var index : Int = 1
    var match : Int = 0
    lateinit var nic : String
    lateinit var firstname: String
    lateinit var lastname : String
    lateinit var email : String
    lateinit var pass : String

    /**
     * Funciones
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_reg, container, false)

        regInputNIC = v.findViewById(R.id.editRegInputNicName)!!
        regInputFirstName = v.findViewById(R.id.editRegInputFirstName)!!
        regInputLastName = v.findViewById(R.id.editRegInputLastName)!!
        regInputEmail = v.findViewById(R.id.editRegInputEmail)!!
        regInputPassword = v.findViewById(R.id.editRegInputPassword)!!
        regRegister = v.findViewById(R.id.buttonRegRegister)!!

        return v
    }

    override fun onStart() {
        super.onStart()

        db = appdatabase.getAppDataBase(v.context)
        userDao=db?.accountDao()

        regRegister.setOnClickListener {

            nic = regInputNIC.text.toString()
            firstname = regInputFirstName.text.toString()
            lastname = regInputLastName.text.toString()
            email= regInputEmail.text.toString()
            pass = regInputPassword.text.toString()

            if(nic.isEmpty()) {

                regInputNIC.text.clear()
                regInputFirstName.text.clear()
                regInputLastName.text.clear()
                regInputEmail.text.clear()
                regInputPassword.text.clear()
                Snackbar.make( v, "NIC Name cannot be empty", Snackbar.LENGTH_LONG).show()

            }

            if(firstname.isEmpty()) {

                regInputNIC.text.clear()
                regInputFirstName.text.clear()
                regInputLastName.text.clear()
                regInputEmail.text.clear()
                regInputPassword.text.clear()
                Snackbar.make( v, "User Name cannot be empty", Snackbar.LENGTH_LONG).show()

            }

            if(lastname.isEmpty()) {

                regInputNIC.text.clear()
                regInputFirstName.text.clear()
                regInputLastName.text.clear()
                regInputEmail.text.clear()
                regInputPassword.text.clear()
                Snackbar.make( v, "Last Name cannot be empty", Snackbar.LENGTH_LONG).show()

            }

            if(email.isEmpty()) {

                regInputNIC.text.clear()
                regInputFirstName.text.clear()
                regInputLastName.text.clear()
                regInputEmail.text.clear()
                regInputPassword.text.clear()
                Snackbar.make( v, "Email cannot be empty", Snackbar.LENGTH_LONG).show()

            }

            if(pass.isEmpty()) {

                regInputNIC.text.clear()
                regInputFirstName.text.clear()
                regInputLastName.text.clear()
                regInputEmail.text.clear()
                regInputPassword.text.clear()
                Snackbar.make( v, "Password cannot be empty", Snackbar.LENGTH_LONG).show()

            }

            regAccounts = userDao?.loadAllPersons() as MutableList<useraccount>

            if (regAccounts.size == 0){
                match=1
            }
            for (user in regAccounts) {

                if (nic.equals(user.nic.toString())){

                    regInputNIC.text.clear()
                    Snackbar.make(v, "The Nic Name is in use. Please try again.", Snackbar.LENGTH_LONG).show()
                }
                if (email.equals(user.email.toString())) {

                    regInputEmail.text.clear()
                    Snackbar.make(v, "The email is registered.", Snackbar.LENGTH_LONG).show()
                }
                if((!(nic.equals(user.nic))) && (!(email.equals(user.email)))) {
                    match = 1
                    break
                }

                else {

                    match =0
                    //Snackbar.make(v, "Please try again.", Snackbar.LENGTH_LONG).show()
                }


            }

            if (match == 0){

                Snackbar.make(v, "Please try again.", Snackbar.LENGTH_LONG).show()
            }
            else {

                index = regAccounts[regAccounts.size -1].id + 1

                userDao?.insertPerson(useraccount(index, nic,firstname,lastname,email,pass))
                Snackbar.make(v, "User Registered.", Snackbar.LENGTH_LONG).show()
                //index ++
                val regOk = regFragmentDirections.actionRegFragmentToLoginFragment()
                v.findNavController().navigate(regOk)

            }
            //RoomExplorer.show(context, appdatabase::class.java, "myDB")

        }
    }
}