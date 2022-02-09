package fr.Akkuun.com.example.plantemanaging.manager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import fr.Akkuun.com.example.plantemanaging.PlantModel
import fr.Akkuun.com.example.plantemanaging.R

class PlantAdapter(
    private val plantelist:List<PlantModel>,
    private val layoutId:Int) :RecyclerView.Adapter<PlantAdapter.ViewHolder>(){
    //boite pour ranger tout les composants à controler

    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
//image de la plante
val planteImage=view.findViewById<ImageView>(R.id.image_item)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
val view = LayoutInflater.from(parent.context).inflate(layoutId ,parent,false)

    return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//recuprer le info de la plante

        val currentplant=plantelist[position]


    }

    override fun getItemCount(): Int {
        return  plantelist.size
    }
}