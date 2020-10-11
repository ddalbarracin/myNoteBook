package com.utn.mcrarv1.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.utn.mcrarv1.R
import com.utn.mcrarv1.database.accountDao
import com.utn.mcrarv1.database.appdatabase
import com.utn.mcrarv1.entities.useraccount
import com.wajahatkarim3.roomexplorer.RoomExplorer


class loginFragment : Fragment() {

    //Variables de la base de datos
    private var db: appdatabase? =null
    private var userDao: accountDao? = null
    lateinit var accounts: MutableList<useraccount>

    //Variables UI

    lateinit var inputNic: EditText
    lateinit var inputPass: EditText
    lateinit var Login: Button
    lateinit var nicname: String
    lateinit var password: String
    lateinit var InputReg: TextView
    var index: Int = 0
    var insertUser: Int = 0

    //View
    lateinit var v: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_login, container, false)

        Login = v.findViewById(R.id.buttonLogin)!!
        inputNic = v.findViewById(R.id.txtUserInput)!!
        inputPass = v.findViewById(R.id.txtPasswordInput)!!
        InputReg = v.findViewById(R.id.txtRegister)!!



        return v
    }

    override fun onStart() {
        super.onStart()
        index = 0
        db = appdatabase.getAppDataBase(v.context)
        userDao = db?.accountDao()

        Login.setOnClickListener {

            nicname = inputNic.text.toString()
            password = inputPass.text.toString()


            if (nicname.isEmpty()) {

                inputNic.text.clear()
                inputPass.text.clear()
                Snackbar.make(v, "User Name cannot be empty", Snackbar.LENGTH_LONG).show()
            }
            if (password.isEmpty()) {

                inputNic.text.clear()
                inputPass.text.clear()
                Snackbar.make(v, "Password cannot be empty", Snackbar.LENGTH_LONG).show()
            }
             //RoomExplorer.show(context, appdatabase::class.java, "myDB")
             accounts = userDao?.loadAllPersons() as MutableList<useraccount>

            for (user in accounts) {

                if (nicname.equals(user.nic) && password.equals(user.password)) {
                    index = user.id
                    break
                }

            }

            if (index.equals(0)) {

                Snackbar.make(v, "User don't exist", Snackbar.LENGTH_LONG).show()
            } else {

                Snackbar.make(v, "Loged", Snackbar.LENGTH_LONG).show()

                val actionLogin = loginFragmentDirections.actionLoginFragmentToAppListFragment2(index)
                v.findNavController().navigate(actionLogin)

            }

            inputNic.text.clear()
            inputPass.text.clear()

        }


        InputReg.setOnClickListener {

            val actionReg =
                loginFragmentDirections.actionLoginFragmentToRegFragment()
            v.findNavController().navigate(actionReg)

        }


    }

}