package fr.Akkuun.com.example.plantemanaging.manager

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.Akkuun.com.example.plantemanaging.*

class PlantAdapter(
    val context: MainActivity,
    private val plantelist: List<PlantModel>,
    private val layoutId: Int
) : RecyclerView.Adapter<PlantAdapter.ViewHolder>() {
    //boite pour ranger tout les composants à controler

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //image de la plante
        val planteImage = view.findViewById<ImageView>(R.id.image_item)
        val nomPlante: TextView? = view.findViewById(R.id.name_item)
        val DescriptionPlante: TextView? = view.findViewById(R.id.description_item)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//recuprer le info de la plante

        val currentplant = plantelist[position]

        //recuerer le repository

        val repo = PlantRepository()


        //utiliser glide pour recuperer l'image à partir de son lien--> composant
        Glide.with(context).load(Uri.parse(currentplant.imageUrl)).into(holder.planteImage)

        //mettre à jour le nom de la plante

        holder.nomPlante?.text = currentplant.name


        //mettre à jour la description de la plante

        holder.DescriptionPlante?.text = currentplant.desription

//verifier si la plante a ete like
        if (currentplant.liked) {
            holder.starIcon.setImageResource(R.drawable.ic_star)
        } else {
            holder.starIcon.setImageResource(R.drawable.ic_unstar)

        }

//rajouter une interaction sur cette étoile

        holder.starIcon.setOnClickListener {
            //inverser l'état du bouton

            currentplant.liked = !currentplant.liked
//mettre à jour l'objet en question

            repo.updatePlant(currentplant)

        }
//interaction lors du clic sur une plante
        holder.itemView.setOnClickListener{

            //afficher la popup

            PlantPopUp(this,currentplant).show()
        }

    }

    override fun getItemCount(): Int = plantelist.size
}