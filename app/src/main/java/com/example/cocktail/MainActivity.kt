package com.example.cocktail

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var drinkName: TextView
    lateinit var drinkContainer : ConstraintLayout
    lateinit var drinkImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drinkName = findViewById(R.id.drinkName)
        drinkContainer= findViewById(R.id.drinkContainer)

        getDrink()
            drinkContainer.setOnClickListener {
                getDrink()
            }
    }

    private fun getDrink() {
        lifecycleScope.launch{
            try {
                val response = requestDrinks()
                val drink = response.drinkRemoteEntities.random()
                drinkName.text = drink.strDrink
                Glide.with(this@MainActivity)
                    .load(drink.strDrinkThumb)
                    .into(drinkImage)

            }catch(e: Exception){
                Toast.makeText(this@MainActivity,e.message, Toast.LENGTH_LONG).show()
            }

        }
        //executar a requisição de rede apartr da resposta escolher um drink aleatorio
        //colocar o nome do drink aleatorio escolhido no textview
    }

    private suspend fun requestDrinks(): DrinksListRemoteEntity{
        return withContext(Dispatchers.IO){
            CocktailService.service.getDrinks()
        }
    }
}