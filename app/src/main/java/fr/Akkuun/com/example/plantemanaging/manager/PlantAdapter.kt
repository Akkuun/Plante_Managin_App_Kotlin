package fr.Akkuun.com.example.plantemanaging.manager

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.Akkuun.com.example.plantemanaging.MainActivity
import fr.Akkuun.com.example.plantemanaging.PlantModel
import fr.Akkuun.com.example.plantemanaging.R

class PlantAdapter(
    private val context: MainActivity,
    private val plantelist: List<PlantModel>,
    private val layoutId: Int
) : RecyclerView.Adapter<PlantAdapter.ViewHolder>() {
    //boite pour ranger tout les composants à controler

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //image de la plante
        val planteImage = view.findViewById<ImageView>(R.id.image_item)
        val nomPlante :TextView?= view.findViewById(R.id.name_item)
        val DescriptionPlante : TextView?= view.findViewById(R.id.description_item)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//recuprer le info de la plante

        val currentplant = plantelist[position]

        //utiliser glide pour recuperer l'image à partir de son lien--> composant
        Glide.with(context).load(Uri.parse(currentplant.imageUrl)).into(holder.planteImage)

        //mettre à jour le nom de la plante

        holder.nomPlante?.text=currentplant.name


        //mettre à jour la description de la plante

        holder.DescriptionPlante?.text=currentplant.desription


    }

    override fun getItemCount(): Int = plantelist.size
}