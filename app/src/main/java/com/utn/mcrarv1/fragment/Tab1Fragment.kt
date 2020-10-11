package com.utn.mcrarv1.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.renderscript.Sampler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar
import com.utn.mcrarv1.R
import com.utn.mcrarv1.database.appdatabase
import com.utn.mcrarv1.database.notebookDao
import com.utn.mcrarv1.entities.notebook
import kotlinx.android.synthetic.main.fragment_tab1.view.*


class Tab1Fragment : Fragment() {


    lateinit var tab1View : View
    var contactID : Int = 0
    var userID : Int = 0
    var index : Int = 0
    var keyValue : String ="key"

    //private val PREF_NAME = "TABPreferences"

    private var user: Int = 0
    private var contact: Int  = 0

    //Variables para UI

    lateinit var tab1Alias : EditText
    lateinit var tab1Nombre : EditText
    lateinit var tab1Apellido : EditText
    lateinit var tab1Telfono : EditText
    lateinit var tab1Email : EditText
    lateinit var tab1Direccion : EditText

    lateinit var tab1Title_len : TextView
    lateinit var tab1SubTitle_len : TextView
    lateinit var tab1Alias_len : TextView
    lateinit var tab1Nombre_len : TextView
    lateinit var tab1Apellido_len : TextView
    lateinit var tab1Telefono_len : TextView
    lateinit var tab1Email_len : TextView
    lateinit var tab1Direccion_len : TextView

    lateinit var tab1Update : Button

    //room database
    private var db: appdatabase? = null
    private var notebookDao: notebookDao? = null
    private var Contact : notebook? = null



    fun newInstance(user: Int, contact: Int): Tab1Fragment {
        val bundle = Bundle()
        bundle.putInt("user", user)
        bundle.putInt("contact", contact)
        val fragment = Tab1Fragment()
        fragment.arguments = bundle
        return fragment
    }

    private fun readBundle(bundle: Bundle?) {
        if (bundle != null) {
            user = bundle.getInt("user")
            contact = bundle.getInt("contact")
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setFragmentResultListener("requestKey") { key, bundle ->
//            val result = bundle.getInt("bundleKey")
//        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        tab1View = inflater.inflate(R.layout.fragment_tab1, container, false)

        tab1Alias = tab1View.findViewById(R.id.tab1EditAlias)!!
        tab1Nombre = tab1View.findViewById(R.id.tab1EditNombre)!!
        tab1Apellido = tab1View.findViewById(R.id.tab1EditApellido)!!
        tab1Telfono = tab1View.findViewById(R.id.tab1EditTelefono)!!
        tab1Email = tab1View.findViewById(R.id.tab1EditEmail)!!
        tab1Direccion = tab1View.findViewById(R.id.tab1EditDireccion)!!
        tab1Update = tab1View.findViewById(R.id.tab1Update)!!


        tab1Title_len = tab1View.findViewById(R.id.tab1Title)
        tab1SubTitle_len = tab1View.findViewById(R.id.tab1SubTitle)
        tab1Alias_len = tab1View.findViewById(R.id.tab1Alias)
        tab1Nombre_len = tab1View.findViewById(R.id.tab1Nombre)
        tab1Apellido_len = tab1View.findViewById(R.id.tab1Apellido)
        tab1Telefono_len = tab1View.findViewById(R.id.tab1Telefono)
        tab1Email_len = tab1View.findViewById(R.id.tab1Email)
        tab1Direccion_len = tab1View.findViewById(R.id.tab1Direccion)

        readBundle(arguments);
        return tab1View
    }

    override fun onStart() {
        super.onStart()

        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        var language : String? = pref.getString("len", "")

        if(language.equals("1")){

            tab1Title_len.text = getString(R.string.tab1Title)
            tab1SubTitle_len.text = getString(R.string.tab1SubTitle)
            tab1Alias_len.text = getString(R.string.tab1Alias)
            tab1Nombre_len.text = getString(R.string.tab1Nombre)
            tab1Apellido_len.text = getString(R.string.tab1Apellido)
            tab1Telefono_len.text = getString(R.string.tab1Telefono)
            tab1Email_len.text = getString(R.string.tab1Email)
            tab1Direccion_len.text = getString(R.string.tab1Direccion)

        }
        if(language.equals("2")){

            tab1Title_len.text = getString(R.string.tab1Title_In)
            tab1SubTitle_len.text = getString(R.string.tab1SubTitle_In)
            tab1Alias_len.text = getString(R.string.tab1Alias_In)
            tab1Nombre_len.text = getString(R.string.tab1Nombre_In)
            tab1Apellido_len.text = getString(R.string.tab1Apellido_In)
            tab1Telefono_len.text = getString(R.string.tab1Telefono_In)
            tab1Email_len.text = getString(R.string.tab1Email_In)
            tab1Direccion_len.text = getString(R.string.tab1Direccion_In)
        }

       // Snackbar.make(tab1View, "Lenaguaje" + language.toString(), Snackbar.LENGTH_LONG).show()

//        setFragmentResultListener("requestKey") { key, bundle ->
//            val result = bundle.getInt("bundleKey")
//            txtuserID.text = result.toString()
//            //Snackbar.make(tab1View,, Snackbar.LENGTH_SHORT).show()
//        }


//        keyValue += index.toString()
//        index ++
//
//        userID = sharedPref.getInt(keyValue, index)
//        //contactID = sharedPref.getInt("contactID", 0)

        db = appdatabase.getAppDataBase(tab1View.context)
        notebookDao = db?.notebookDao()

        Contact = notebookDao?.loadContactByUserIDContactID(user, contact)


        tab1Alias.setText(Contact?.alias.toString())
        tab1Nombre.setText(Contact?.nombre.toString())
        tab1Apellido.setText(Contact?.apellido.toString())
        tab1Telfono.setText(Contact?.telefono.toString())
        tab1Email.setText(Contact?.email.toString())
        tab1Direccion.setText(Contact?.direccion.toString())

        tab1Update.setOnClickListener {

            notebookDao?.insertContact(
                notebook(

                    contact, user,
                    tab1Alias.text.toString(),
                    tab1Nombre.text.toString(),
                    tab1Apellido.text.toString(),
                    tab1Telfono.text.toString(),
                    tab1Email.text.toString(),
                    tab1Direccion.text.toString(),
                    Contact?.trabajo.toString(),
                    Contact?.direccionlaboral.toString(),
                    Contact?.telefonolaboral.toString(),
                    Contact?.emaillaboral.toString(),
                    Contact?.imagen.toString(),
                    Contact?.notas.toString()
                )
            )

        }

    }


}