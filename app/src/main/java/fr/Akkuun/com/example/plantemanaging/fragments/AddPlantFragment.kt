package fr.Akkuun.com.example.plantemanaging.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import fr.Akkuun.com.example.plantemanaging.MainActivity
import fr.Akkuun.com.example.plantemanaging.PlantModel
import fr.Akkuun.com.example.plantemanaging.PlantRepository
import fr.Akkuun.com.example.plantemanaging.PlantRepository.Singleton.downloadUri
import fr.Akkuun.com.example.plantemanaging.R
import java.util.*

class AddPlantFragment(

    private val context: MainActivity
) : Fragment() {
    private var file: Uri? = null
    private var unploadedImage: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_add_plant, container, false)

        //recuper uploadedImage
        unploadedImage = view.findViewById(R.id.preview_image)

        //recuperer le boutton pour charger l'image

        val pickUpImageButton = view.findViewById<Button>(R.id.uploadButton)

        //click event ==> choisir les photo

        pickUpImageButton.setOnClickListener {
            pickUpImage()
        }

        //recuperer le bouton confirmer

        val confirmButton = view.findViewById<Button>(R.id.confirme_button)
        confirmButton.setOnClickListener { sendForm(view) }

        return view
    }

    private fun sendForm(view: View) {

        val repo = PlantRepository()
        repo.uploadedImage(file!!) {
            val PlantName = view.findViewById<EditText>(R.id.name_input).text.toString()
            val Plantdescription =
                view.findViewById<EditText>(R.id.description_input).text.toString()
            val grow = view.findViewById<Spinner>(R.id.grow_spinner).selectedItem.toString()
            val water = view.findViewById<Spinner>(R.id.water_spinner).selectedItem.toString()
            val downloadedImageUrl = downloadUri

            //creer un nouvelle objet plant model

            val plant = PlantModel(

                UUID.randomUUID().toString(),
                PlantName,
                Plantdescription,
                downloadedImageUrl.toString(),
                grow,
                water


            )

            //envoyer vers la bdd

            repo.insertPlant(plant)

        }


    }

    private fun pickUpImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "select picture"), 47)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 47 && resultCode == Activity.RESULT_OK) {
            //verifier si les donnes sont null
            if (data == null || data.data == null) return

            //recuperer l'image
            file = data.data

            //mettre a jour l'image

            unploadedImage?.setImageURI(file)

        }
    }
}