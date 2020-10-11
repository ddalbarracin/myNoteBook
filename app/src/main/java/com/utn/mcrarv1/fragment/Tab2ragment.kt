package com.utn.mcrarv1.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.preference.PreferenceManager
import com.utn.mcrarv1.R
import com.utn.mcrarv1.database.appdatabase
import com.utn.mcrarv1.database.notebookDao
import com.utn.mcrarv1.entities.notebook

class Tab2ragment : Fragment() {

    lateinit var tab2View : View

    private var user: Int = 0
    private var contact: Int  = 0

    //Variables para UI

    lateinit var tab2Empresa : EditText
    lateinit var tab2Direccion : EditText
    lateinit var tab2Telefono : EditText
    lateinit var tab2Email : EditText

    lateinit var tab2Title_len : TextView
    lateinit var tab2SubTitle_len : TextView
    lateinit var tab2Empresa_len : TextView
    lateinit var tab2Direccion_len : TextView
    lateinit var tab2Telefono_len : TextView
    lateinit var tab2Email_len : TextView


    lateinit var tab2Update : Button

    //room database
    private var db: appdatabase? = null
    private var notebookDao: notebookDao? = null
    private var Contact : notebook? = null



    fun newInstance(user: Int, contact: Int): Tab2ragment {
        val bundle = Bundle()
        bundle.putInt("user", user)
        bundle.putInt("contact", contact)
        val fragment = Tab2ragment()
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

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        tab2View = inflater.inflate(R.layout.fragment_tab2, container, false)

        tab2Empresa = tab2View.findViewById(R.id.tab2EditEmpresa)!!
        tab2Direccion = tab2View.findViewById(R.id.tab2EditDireccionLaboral)!!
        tab2Telefono = tab2View.findViewById(R.id.tab2EditTelefonoLaboral)!!
        tab2Email = tab2View.findViewById(R.id.tab2EditEmailLaboral)!!

        tab2Title_len = tab2View.findViewById(R.id.tab2Title)
        tab2SubTitle_len = tab2View.findViewById(R.id.tab2SubTitle)
        tab2Empresa_len = tab2View.findViewById(R.id.tab2Empresa)
        tab2Direccion_len = tab2View.findViewById(R.id.tab2DireccionLaboral)
        tab2Email_len = tab2View.findViewById(R.id.tab2EmailLaboral)
        tab2Telefono_len = tab2View.findViewById(R.id.tab2TelefonoLaboral)


        tab2Update = tab2View.findViewById(R.id.tab2Update)!!


        readBundle(arguments);


        return tab2View
    }

    override fun onStart() {
        super.onStart()

        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        var language : String? = pref.getString("len", "")

        if(language.equals("1")){

            tab2Title_len.text = getString(R.string.tab2Title)
            tab2SubTitle_len.text = getString(R.string.tab2SubTitle)
            tab2Empresa_len.text = getString(R.string.tab2Empresa)
            tab2Direccion_len.text = getString(R.string.tab2Direccion)
            tab2Email_len.text = getString(R.string.tab2Email)
            tab2Telefono_len.text = getString(R.string.tab2Telefono)
        }
        if(language.equals("2")){

            tab2Title_len.text = getString(R.string.tab2Title_In)
            tab2SubTitle_len.text = getString(R.string.tab2SubTitle_In)
            tab2Empresa_len.text = getString(R.string.tab2Empresa_In)
            tab2Direccion_len.text = getString(R.string.tab2Direccion_In)
            tab2Email_len.text = getString(R.string.tab2Email_In)
            tab2Telefono_len.text = getString(R.string.tab2Telefono_In)

        }

        db = appdatabase.getAppDataBase(tab2View.context)
        notebookDao = db?.notebookDao()

        Contact = notebookDao?.loadContactByUserIDContactID(user, contact)


        tab2Empresa.setText(Contact?.trabajo.toString())
        tab2Direccion.setText(Contact?.direccionlaboral.toString())
        tab2Telefono.setText(Contact?.telefonolaboral.toString())
        tab2Email.setText(Contact?.emaillaboral.toString())


        tab2Update.setOnClickListener{

            notebookDao?.insertContact(notebook(

                contact,user,
                Contact?.alias.toString(),
                Contact?.nombre.toString(),
                Contact?.apellido.toString(),
                Contact?.telefono.toString(),
                Contact?.email.toString(),
                Contact?.direccion.toString(),
                tab2Empresa.text.toString(),
                tab2Direccion.text.toString(),
                tab2Telefono.text.toString(),
                tab2Email.text.toString(),
                Contact?.imagen.toString(),
                Contact?.notas.toString()))
        }

    }
}