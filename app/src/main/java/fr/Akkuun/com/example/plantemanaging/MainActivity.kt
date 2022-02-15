package fr.Akkuun.com.example.plantemanaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.Akkuun.com.example.plantemanaging.fragments.AddPlantFragment
import fr.Akkuun.com.example.plantemanaging.fragments.CollctionFragment
import fr.Akkuun.com.example.plantemanaging.fragments.HomeFragments

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
val navigationView=findViewById<BottomNavigationView>(R.id.navigation_view)
navigationView.setOnNavigationItemSelectedListener {
    when(it.itemId){
        R.id.home_page->{
            loadFragment(HomeFragments(this))
            return@setOnNavigationItemSelectedListener true
        }
        R.id.collection_page->{
            loadFragment(CollctionFragment(this))
        return@setOnNavigationItemSelectedListener  true
        }
        R.id.add_plant_page ->{
            loadFragment(AddPlantFragment(this))
        return@setOnNavigationItemSelectedListener true
        }
        else -> false
    }
}

        loadFragment(HomeFragments(this))


    }

    private fun loadFragment(fragment: Fragment) {
//charger notre plante Repository
        val repo = PlantRepository()


        //mettre à jour  la liste  de plantes

        repo.updateData {
            //injecter le fragment dans notre boite
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

}