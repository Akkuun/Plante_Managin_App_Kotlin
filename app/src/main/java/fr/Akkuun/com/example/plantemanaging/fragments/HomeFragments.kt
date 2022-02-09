package fr.Akkuun.com.example.plantemanaging.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.Akkuun.com.example.plantemanaging.PlantModel
import fr.Akkuun.com.example.plantemanaging.R
import fr.Akkuun.com.example.plantemanaging.manager.PlantAdapter
import fr.Akkuun.com.example.plantemanaging.manager.PlantItemDecoration

class HomeFragments :Fragment() {
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
             "https://pixabay.com/fr/photos/pissenlit-des-graines-2266558/",
            false
        ))
        //enregistrer deuxieme plante
        PlanteListe.add(PlantModel(
            "Banboo",
            "Originaire d'Asie",
            "https://pixabay.com/link/?ua=t%3Devent%26ec%3Dapi_ad%26ea%3Dnavigate%26el%3Dgetty%26v%3D1%26tid%3DUA-20223345-1&next=https%3A%2F%2Fwww.istockphoto.com%2Fphoto%2Fbonsai-bambootree-in-a-green-glazed-square-vertical-pot-gm470979432-62511994%3Futm_source%3Dpixabay%26utm_medium%3Daffiliate%26utm_campaign%3DSRP_image_sponsored%26utm_content%3Dhttp%253A%252F%252Fpixabay.com%252Ffr%252Fimages%252Fsearch%252Fbanboo%252F%26utm_term%3Dbanboo&hash=eac50b199c2c892e9e4136b8b77442fb205b2c0f/",
            false
        ))
//enregistrer troiseieme plante
        PlanteListe.add(PlantModel(
            "Rose",
            "Plante à épines",
            "https://pixabay.com/photos/rose-flower-love-romance-beautiful-729509/",
            false
        ))
        //enregistrer quatrieme plante
        PlanteListe.add(PlantModel(
            "Cactus",
            "Plante du désert",
            "https://pixabay.com/fr/photos/cactus-arrosoir-plante-d-appartement-4161380/",
            false
        ))


        //recuper le recycle view
val horizontalRecycleView=view.findViewById<RecyclerView>(R.id.horizontal_recycle_View)
        horizontalRecycleView.adapter=PlantAdapter(PlanteListe,R.layout.item_horizontal_plant)



        //recuperer le deuxieme recycle view

         val verticalRecycleView=view.findViewById<RecyclerView>(R.id.vertical_recycle_View)
        verticalRecycleView.adapter=PlantAdapter(PlanteListe, R.layout.item_vertical_plant)
        verticalRecycleView.addItemDecoration(PlantItemDecoration())

        return view
    }
}