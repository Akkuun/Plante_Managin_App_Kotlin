package fr.Akkuun.com.example.plantemanaging.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.Akkuun.com.example.plantemanaging.R
import fr.Akkuun.com.example.plantemanaging.manager.PlantAdapter

class HomeFragments :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater?.inflate(R.layout.fragment_home,container,false)
    //recuper le recycle view
val horizontalRecycleView=view.findViewById<RecyclerView>(R.id.horizontal_recycle_View)
        horizontalRecycleView.adapter=PlantAdapter()
        return view
    }
}