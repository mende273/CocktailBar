package com.jumrukovski.cocktailbar.data.model

import androidx.core.util.Pair
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Drink(
    @PrimaryKey
    val idDrink: Long?,
    val strDrink: String?,
    val strDrinkAlternate: String?,
    val strDrinkES: String?,
    val strDrinkDE: String?,
    val strDrinkFR: String?,
    val strTags: String?,
    val strVideo: String?,
    val strCategory: String?,
    val strIBA: String?,
    val strAlcoholic: String?,
    val strGlass: String?,
    val strInstructions: String?,
    val strInstructionsES: String?,
    val strInstructionsDE: String?,
    val strInstructionsFR: String?,
    val strDrinkThumb: String?,
    val strIngredient1: String? = "",
    val strIngredient2: String? = "",
    val strIngredient3: String? = "",
    val strIngredient4: String? = "",
    val strIngredient5: String? = "",
    val strIngredient6: String? = "",
    val strIngredient7: String? = "",
    val strIngredient8: String? = "",
    val strIngredient9: String? = "",
    val strIngredient10: String? = "",
    val strIngredient11: String? = "",
    val strIngredient12: String? = "",
    val strIngredient13: String? = "",
    val strIngredient14: String? = "",
    val strIngredient15: String? = "",
    val strMeasure1: String? = "",
    val strMeasure2: String? = "",
    val strMeasure3: String? = "",
    val strMeasure4: String? = "",
    val strMeasure5: String? = "",
    val strMeasure6: String? = "",
    val strMeasure7: String? = "",
    val strMeasure8: String? = "",
    val strMeasure9: String? = "",
    val strMeasure10: String? = "",
    val strMeasure11: String? = "",
    val strMeasure12: String? = "",
    val strMeasure13: String? = "",
    val strMeasure14: String? = "",
    val strMeasure15: String? = "",
    val strCreativeCommonsConfirmed: String?,
    val dateModified: String?
) : Serializable {

    fun getFilterTitle(): String {
        return if (!strAlcoholic.isNullOrEmpty()) {
            strAlcoholic
        } else if (!strIngredient1.isNullOrEmpty()) {
            strIngredient1
        } else if (!strGlass.isNullOrEmpty()) {
            strGlass
        } else if (!strCategory.isNullOrEmpty()) {
            strCategory
        } else {
            ""
        }
    }

    fun getIngredientsWithMeasurements() = mutableListOf<Pair<String, String>>(
        Pair(strIngredient1, strMeasure1),
        Pair(strIngredient2, strMeasure2),
        Pair(strIngredient3, strMeasure3),
        Pair(strIngredient4, strMeasure4),
        Pair(strIngredient5, strMeasure5),
        Pair(strIngredient6, strMeasure6),
        Pair(strIngredient7, strMeasure7),
        Pair(strIngredient8, strMeasure8),
        Pair(strIngredient9, strMeasure9),
        Pair(strIngredient10, strMeasure10),
        Pair(strIngredient11, strMeasure11),
        Pair(strIngredient12, strMeasure12),
        Pair(strIngredient13, strMeasure13),
        Pair(strIngredient14, strMeasure14),
        Pair(strIngredient15, strMeasure15)
    )
}
