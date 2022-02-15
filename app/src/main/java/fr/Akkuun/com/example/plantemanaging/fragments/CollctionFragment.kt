package fr.Akkuun.com.example.plantemanaging.fragments

import android.os.Bundle
import android.os.RecoverySystem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.Akkuun.com.example.plantemanaging.MainActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.Akkuun.com.example.plantemanaging.R
import fr.Akkuun.com.example.plantemanaging.manager.PlantAdapter
import fr.Akkuun.com.example.plantemanaging.PlantRepository.Singleton.plantList
import fr.Akkuun.com.example.plantemanaging.manager.PlantItemDecoration

class CollctionFragment (
    private  val context:MainActivity
    ):Fragment(){

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        val view =inflater?.inflate(R.layout.fragment_collection,container,false)

        //recuperer cylceview
        val collectionRecycleView=view.findViewById<RecyclerView>(R.id.collection_recycler_list)
        collectionRecycleView.adapter=PlantAdapter(context, plantList.filter { it.liked },R.layout.item_vertical_plant)
        collectionRecycleView.layoutManager=LinearLayoutManager(context)
        collectionRecycleView.addItemDecoration(PlantItemDecoration())

        return view
    }
    }


