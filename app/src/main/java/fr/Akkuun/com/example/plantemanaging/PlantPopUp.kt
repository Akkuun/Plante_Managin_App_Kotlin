package fr.Akkuun.com.example.plantemanaging

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import fr.Akkuun.com.example.plantemanaging.manager.PlantAdapter

class PlantPopUp(
    private val adapter: PlantAdapter,
    private val currentPlant: PlantModel

) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_plants_details)
        setupComponents()
        setUpclosebutton()
        setUpDeleteButton()
    }

    private fun setUpDeleteButton() {
        findViewById<ImageView>(R.id.delete_button).setOnClickListener{

val repo=PlantRepository()
            repo.deletePlant(currentPlant)
            dismiss()
        }
    }



    private fun setUpclosebutton() {
        findViewById<ImageView>(R.id.close_button).setOnClickListener{

            dismiss()
        }
    }

    private fun setupComponents() {
        //actualiser l'image de la plante
        val plantImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentPlant.imageUrl)).into(plantImage)

        //actualiser le nom de la plante

        findViewById<TextView>(R.id.popup_plants_name).text = currentPlant.name

        //actualiser la description de la plante
        findViewById<TextView>(R.id.popup_plant_description_subtitle).text = currentPlant.desription

        //actualiser la croissance de la plante

        findViewById<TextView>(R.id.popup_plant_grow_description_subtitle).text=currentPlant.grow

        //actualiser la conso

        findViewById<TextView>(R.id.popup_plant_water_description_subtitle).text=currentPlant.water




    }

}