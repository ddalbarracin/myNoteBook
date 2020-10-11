package com.utn.mcrarv1.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.utn.mcrarv1.R
import com.utn.mcrarv1.entities.mascotas
import com.utn.mcrarv1.entities.notebook
import com.utn.mcrarv1.fragment.appListFragment
import org.w3c.dom.Text
import java.security.AccessController.getContext

class ListAdapter (private var notebookList: MutableList<notebook>, var context: Context, val onItemClick: (Int) -> Unit, val onLongItemClick: (Int) -> Boolean): RecyclerView.Adapter<ListAdapter.ListAdapterHolder>() {



    companion object {
        private val TAG = "listAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapterHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_lists, null, false )
        return (ListAdapterHolder(view))
    }


    override fun onBindViewHolder(holder: ListAdapterHolder, position: Int) {

        holder.setName(notebookList[position].nombre)
        holder.setLastName(notebookList[position].apellido)
        holder.setPhone(notebookList[position].telefono)

        if(notebookList[position].imagen.equals("")){
            Glide
                .with(context)
                .load(R.drawable.ic_baseline_android_24)
                .centerInside()
                .into(holder.getImageView())
        }
        else{
            Glide
                .with(context)
                .load(notebookList[position].imagen)
                .centerInside()
                .into(holder.getImageView())
        }
        if(notebookList[position].nombre.equals("Daniel David")) {
            Glide
                .with(context)
                .load("https://scontent.faep8-2.fna.fbcdn.net/v/t31.0-1/c47.14.173.172a/p200x200/903330_4746466945274_935666153_o.jpg?_nc_cat=109&_nc_sid=7206a8&_nc_ohc=aKP0EKpxSVcAX-nwMKb&_nc_ht=scontent.faep8-2.fna&_nc_tp=27&oh=518ed930d11fde1ce90de17eee87a671&oe=5F997735")
                .centerInside()
                .into(holder.getImageView());
        }

        holder.getCardLayout().setOnClickListener {
            onItemClick(position)
        }
        holder.getCardLayout().setOnLongClickListener{
            onLongItemClick(position)
        }

    }

    override fun getItemCount(): Int {

        return notebookList.size
    }


    fun setData(newData: ArrayList<notebook>){
        this.notebookList = newData
        this.notifyDataSetChanged()

    }


    class ListAdapterHolder (v : View) : RecyclerView.ViewHolder(v) {

        private var view : View

        init {
            this.view = v
        }

        fun setName(name:String) {
            val txt: TextView = view.findViewById(R.id.txt_name_item)
            txt.text = name
        }
        fun setPhone (phone: String){
            val txt : TextView = view.findViewById(R.id.txt_phone_item)
            txt.text = phone
        }
        fun setLastName (last : String){
            val txt : TextView = view.findViewById(R.id.txt_last_item)
            txt.text = last
        }

        fun getCardLayout (): CardView{
            return view.findViewById(R.id.cardviewList)
        }

        fun getImageView (): ImageView{
            return view.findViewById(R.id.img_item)
        }
    }




}


