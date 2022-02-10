package fr.Akkuun.com.example.plantemanaging.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.Akkuun.com.example.plantemanaging.MainActivity
import fr.Akkuun.com.example.plantemanaging.PlantModel
import fr.Akkuun.com.example.plantemanaging.R
import fr.Akkuun.com.example.plantemanaging.manager.PlantAdapter
import fr.Akkuun.com.example.plantemanaging.manager.PlantItemDecoration

class HomeFragments(
    private val context : MainActivity
) :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater?.inflate(R.layout.fragment_home,container,false)

        //créer une liste

        val PlanteListe= arrayListOf<PlantModel>()

        //enresgistrer premier plante

        PlanteListe.add(PlantModel(
            "Pissenlit",
            "jaune soleil",
             "https://cdn.pixabay.com/photo/2014/09/27/17/35/dandelion-463928_1280.jpg" ))
        //enregistrer deuxieme plante
        PlanteListe.add(PlantModel(
            "Banboo",
            "Originaire d'Asie",
            "https://cdn.pixabay.com/photo/2020/04/04/13/34/banboo-5002308_1280.jpg"))
//enregistrer troiseieme plante
        PlanteListe.add(PlantModel(
            "Rose",
            "Plante à épines",
            "https://cdn.pixabay.com/photo/2015/04/19/08/32/rose-729509_1280.jpg",false
        ))
        //enregistrer quatrieme plante
        PlanteListe.add(PlantModel(
            "Cactus",
            "Plante du désert",
            "https://cdn.pixabay.com/photo/2019/04/27/21/56/cactus-4161380_1280.jpg",false
        ))


        //recuper le recycle view
val horizontalRecycleView=view.findViewById<RecyclerView>(R.id.horizontal_recycle_View)
        horizontalRecycleView.adapter=PlantAdapter(context ,PlanteListe,R.layout.item_horizontal_plant)



        //recuperer le deuxieme recycle view

         val verticalRecycleView=view.findViewById<RecyclerView>(R.id.vertical_recycle_View)
        verticalRecycleView.adapter=PlantAdapter(context,PlanteListe, R.layout.item_vertical_plant)
        verticalRecycleView.addItemDecoration(PlantItemDecoration())

        return view
    }
}