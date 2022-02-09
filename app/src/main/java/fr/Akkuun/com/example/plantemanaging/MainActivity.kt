 package fr.Akkuun.com.example.plantemanaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.Akkuun.com.example.plantemanaging.fragments.HomeFragments

 class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //injecter le fragment dans notre boite
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,HomeFragments())
        transaction.addToBackStack(null)
        transaction.commit()
    }

}