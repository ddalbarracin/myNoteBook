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

class Tab3Fragment : Fragment() {

    lateinit var tab3View: View

    private var user: Int = 0
    private var contact: Int = 0

    //Variables para UI


    lateinit var tab3Notes: EditText

    lateinit var tab3Title_len : TextView
    lateinit var tab3SubTitle_len : TextView




    lateinit var tab3Update: Button

    //room database
    private var db: appdatabase? = null
    private var notebookDao: notebookDao? = null
    private var Contact: notebook? = null


    fun newInstance(user: Int, contact: Int): Tab3Fragment {
        val bundle = Bundle()
        bundle.putInt("user", user)
        bundle.putInt("contact", contact)
        val fragment = Tab3Fragment()
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
        tab3View = inflater.inflate(R.layout.fragment_tab3, container, false)

        tab3Notes = tab3View.findViewById(R.id.tab3EditNotes)!!
        tab3Update = tab3View.findViewById(R.id.tab3Update)!!

        tab3Title_len = tab3View.findViewById(R.id.tab3Title)
        tab3SubTitle_len = tab3View.findViewById(R.id.tab3SubTitle)



        readBundle(arguments);

        return tab3View
    }

    override fun onStart() {
        super.onStart()

        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        var language : String? = pref.getString("len", "")

        if(language.equals("1")){

            tab3Title_len.text = getString(R.string.tab3Title)
            tab3SubTitle_len.text = getString(R.string.tab3Subtitle)

        }

        if(language.equals("2")){

            tab3Title_len.text = getString(R.string.tab3Title_In)
            tab3SubTitle_len.text = getString(R.string.tab3Subtitle_In)
        }

        db = appdatabase.getAppDataBase(tab3View.context)
        notebookDao = db?.notebookDao()

        Contact = notebookDao?.loadContactByUserIDContactID(user, contact)

        tab3Notes.setText(Contact?.notas.toString())


        tab3Update.setOnClickListener {

            notebookDao?.insertContact(
                notebook(

                    contact, user,
                    Contact?.alias.toString(),
                    Contact?.nombre.toString(),
                    Contact?.apellido.toString(),
                    Contact?.telefono.toString(),
                    Contact?.email.toString(),
                    Contact?.direccion.toString(),
                    Contact?.trabajo.toString(),
                    Contact?.direccionlaboral.toString(),
                    Contact?.telefonolaboral.toString(),
                    Contact?.emaillaboral.toString(),
                    Contact?.imagen.toString(),
                    tab3Notes.text.toString()
                )
            )
        }
    }
}
