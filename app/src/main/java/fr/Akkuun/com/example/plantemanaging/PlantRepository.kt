package fr.Akkuun.com.example.plantemanaging

import android.net.Uri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import fr.Akkuun.com.example.plantemanaging.PlantRepository.Singleton.databaseRef
import fr.Akkuun.com.example.plantemanaging.PlantRepository.Singleton.downloadUri
import fr.Akkuun.com.example.plantemanaging.PlantRepository.Singleton.plantList
import fr.Akkuun.com.example.plantemanaging.PlantRepository.Singleton.storageReference
import java.net.URI
import java.util.*

class PlantRepository {

    object Singleton {
//se connecter a notre espace de stockage
//donner le lien de notre storage du bucket
        private val BUCKET_URL: String="gs://plantemanaging.appspot.com"
        val storageReference=FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)


        //se connecter a la reference plante
        val databaseRef = FirebaseDatabase.getInstance().getReference("Plants")
        //créer une liste contenant nos plantes

        val plantList = arrayListOf<PlantModel>()

        //contnir le lien de l'image courrante

        var downloadUri:Uri?=null
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
    //creer une fonction pour envoyer des fichiers dans le storage
    fun uploadedImage(file:Uri,callback: () -> Unit){
//verifier que ce fichier n'est pas null
        if (file!=null){
            val fileName=UUID.randomUUID().toString()+"jpg"
            val ref= storageReference.child(fileName)
            val uploadTask=ref.putFile(file)

            //demarer la tâche d'envoie
            uploadTask.continueWithTask (Continuation<UploadTask.   TaskSnapshot, Task<Uri>>{
                 task ->

                if (!task.isSuccessful){
                    task.exception?.let { throw it  }
                }

                return@Continuation ref.downloadUrl
            }).addOnCompleteListener { task->

                if (task.isSuccessful){
                     downloadUri=task.result
                    callback()
                }

            }
        }
    }


    //mettre un jour un objet en base de donnee
    fun updatePlant(plant: PlantModel) = databaseRef.child(plant.id).setValue(plant)

    //inserer une nouvelle plante en bdd
    fun insertPlant(plant: PlantModel) = databaseRef.child(plant.id).setValue(plant)


    //supprimer element dans la base

    fun deletePlant(plant:PlantModel)= databaseRef.child(plant.id).removeValue()

}