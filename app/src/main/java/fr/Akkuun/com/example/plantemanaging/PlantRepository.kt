package fr.Akkuun.com.example.plantemanaging

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.Akkuun.com.example.plantemanaging.PlantRepository.Singleton.databaseRef
import fr.Akkuun.com.example.plantemanaging.PlantRepository.Singleton.plantList

class PlantRepository {

    object Singleton {

        //se connecter a la reference plante
        val databaseRef = FirebaseDatabase.getInstance().getReference("Plants")
        //créer une liste contenant nos plantes

        val plantList = arrayListOf<PlantModel>()
    }


    fun updateData(callback: () -> Unit) {
        //absorber les donnes  depuis la dataRef  -> liste de plantes

        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                //retirer les anciennes
                plantList.clear()


                //recolter la liste

                for (ds in p0.children) {
                    //construire un obejt plante

                    val Plant = ds.getValue(PlantModel::class.java)

                    //verifier que la plante n'est pas null

                    if (Plant != null) {
                        plantList.add(Plant)
                    }
                }
                //actionner le callback
                callback()
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }

    //mettre un jour un objet en base de donnee
    fun updatePlant(plant: PlantModel) = databaseRef.child(plant.id).setValue(plant)

}