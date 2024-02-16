package com.example.foodapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
@Entity(tableName = "favorite_meal")
public class Meal {
    @PrimaryKey
    @NonNull
    String idMeal;
    String email;
    String strMeal;
    String strDrinkAlternate;
    String strCategory;
    String strArea;
    String strInstructions;
    String strMealThumb;
    String strTags;
    String strYoutube;
    String strIngredient1;
    String strIngredient2;
    String strIngredient3;
    String strIngredient4;
    String strIngredient5;
    String strIngredient6;
    String strIngredient7;
    String strIngredient8;
    String strIngredient9;
    String strIngredient10;
    String strIngredient11;
    String strIngredient12;
    String strIngredient13;
    String strIngredient14;
    String strIngredient15;
    String strIngredient16;
    String strIngredient17;
    String strIngredient18;
    String strIngredient19;
    String strIngredient20;
    @Ignore
    ArrayList<String> ingredientsList ;
    String strMeasure1;
    String strMeasure2;
    String strMeasure3;
    String strMeasure4;
    String strMeasure5;
    String strMeasure6;
    String strMeasure7;
    String strSource;
    String strImageSource;
    String strCreativeCommonsConfirmed;
    public Meal()
    {

    }


    public ArrayList<String> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList() {
//        ingredientsList=new ArrayList<String>();
//        ingredientsList.add(strIngredient1);
//        ingredientsList.add(strIngredient2);
//        ingredientsList.add(strIngredient3);
//        ingredientsList.add(strIngredient4);
//        ingredientsList.add(strIngredient5);
//        ingredientsList.add(strIngredient6);
//        ingredientsList.add(strIngredient7);
//        ingredientsList.add(strIngredient8);
//        ingredientsList.add(strIngredient9);
//        ingredientsList.add(strIngredient10);
//        ingredientsList.add(strIngredient11);
//        ingredientsList.add(strIngredient12);
//        ingredientsList.add(strIngredient13);
//        ingredientsList.add(strIngredient14);
//        ingredientsList.add(strIngredient15);
//        ingredientsList.add(strIngredient16);
//        ingredientsList.add(strIngredient17);
//        ingredientsList.add(strIngredient18);
//        ingredientsList.add(strIngredient19);
//        ingredientsList.add(strIngredient20);
//        ingredientsList.removeIf(ing -> ing.equals(""));
        ingredientsList = new ArrayList<String>();

        // Add non-null ingredients to the list
        if (strIngredient1 != null && !strIngredient1.isEmpty()) {
            ingredientsList.add(strIngredient1);
        }
        if (strIngredient2 != null && !strIngredient2.isEmpty()) {
            ingredientsList.add(strIngredient2);
        }
        if (strIngredient3 != null && !strIngredient3.isEmpty()) {
            ingredientsList.add(strIngredient3);
        }
        if (strIngredient4 != null && !strIngredient4.isEmpty()) {
            ingredientsList.add(strIngredient4);
        }
        if (strIngredient5 != null && !strIngredient5.isEmpty()) {
            ingredientsList.add(strIngredient5);
        }
        if (strIngredient6 != null && !strIngredient6.isEmpty()) {
            ingredientsList.add(strIngredient6);
        }
        if (strIngredient7 != null && !strIngredient7.isEmpty()) {
            ingredientsList.add(strIngredient7);
        }
        if (strIngredient8 != null && !strIngredient8.isEmpty()) {
            ingredientsList.add(strIngredient8);
        }
        if (strIngredient9 != null && !strIngredient9.isEmpty()) {
            ingredientsList.add(strIngredient9);
        }
        if (strIngredient10 != null && !strIngredient10.isEmpty()) {
            ingredientsList.add(strIngredient10);
        }
        if (strIngredient11 != null && !strIngredient11.isEmpty()) {
            ingredientsList.add(strIngredient11);
        }
        if (strIngredient12 != null && !strIngredient12.isEmpty()) {
            ingredientsList.add(strIngredient12);
        }
        if (strIngredient13 != null && !strIngredient13.isEmpty()) {
            ingredientsList.add(strIngredient13);
        }
        if (strIngredient14 != null && !strIngredient14.isEmpty()) {
            ingredientsList.add(strIngredient14);
        }
        if (strIngredient15 != null && !strIngredient15.isEmpty()) {
            ingredientsList.add(strIngredient15);
        }
        if (strIngredient16 != null && !strIngredient16.isEmpty()) {
            ingredientsList.add(strIngredient16);
        }
        if (strIngredient17 != null && !strIngredient17.isEmpty()) {
            ingredientsList.add(strIngredient17);
        }
        if (strIngredient18 != null && !strIngredient18.isEmpty()) {
            ingredientsList.add(strIngredient18);
        }
        if (strIngredient19 != null && !strIngredient19.isEmpty()) {
            ingredientsList.add(strIngredient19);
        }
        if (strIngredient20 != null && !strIngredient20.isEmpty()) {
            ingredientsList.add(strIngredient20);
        }
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrDrinkAlternate() {
        return strDrinkAlternate;
    }

    public void setStrDrinkAlternate(String strDrinkAlternate) {
        this.strDrinkAlternate = strDrinkAlternate;
    }

    @NonNull
    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(@NonNull String idMeal) {
        this.idMeal = idMeal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIngredientsList(ArrayList<String> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrTags() {
        return strTags;
    }

    public void setStrTags(String strTags) {
        this.strTags = strTags;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
    }

    public String getStrIngredient4() {
        return strIngredient4;
    }

    public void setStrIngredient4(String strIngredient4) {
        this.strIngredient4 = strIngredient4;
    }

    public String getStrIngredient5() {
        return strIngredient5;
    }

    public void setStrIngredient5(String strIngredient5) {
        this.strIngredient5 = strIngredient5;
    }

    public String getStrIngredient6() {
        return strIngredient6;
    }

    public void setStrIngredient6(String strIngredient6) {
        this.strIngredient6 = strIngredient6;
    }

    public String getStrIngredient7() {
        return strIngredient7;
    }

    public void setStrIngredient7(String strIngredient7) {
        this.strIngredient7 = strIngredient7;
    }

    public String getStrMeasure1() {
        return strMeasure1;
    }

    public void setStrMeasure1(String strMeasure1) {
        this.strMeasure1 = strMeasure1;
    }

    public String getStrMeasure2() {
        return strMeasure2;
    }

    public void setStrMeasure2(String strMeasure2) {
        this.strMeasure2 = strMeasure2;
    }

    public String getStrMeasure3() {
        return strMeasure3;
    }

    public void setStrMeasure3(String strMeasure3) {
        this.strMeasure3 = strMeasure3;
    }

    public String getStrMeasure4() {
        return strMeasure4;
    }

    public void setStrMeasure4(String strMeasure4) {
        this.strMeasure4 = strMeasure4;
    }

    public String getStrMeasure5() {
        return strMeasure5;
    }

    public void setStrMeasure5(String strMeasure5) {
        this.strMeasure5 = strMeasure5;
    }

    public String getStrMeasure6() {
        return strMeasure6;
    }

    public void setStrMeasure6(String strMeasure6) {
        this.strMeasure6 = strMeasure6;
    }

    public String getStrMeasure7() {
        return strMeasure7;
    }

    public void setStrMeasure7(String strMeasure7) {
        this.strMeasure7 = strMeasure7;
    }

    public String getStrSource() {
        return strSource;
    }

    public void setStrSource(String strSource) {
        this.strSource = strSource;
    }

    public String getStrImageSource() {
        return strImageSource;
    }

    public void setStrImageSource(String strImageSource) {
        this.strImageSource = strImageSource;
    }

    public String getStrCreativeCommonsConfirmed() {
        return strCreativeCommonsConfirmed;
    }

    public String getStrIngredient8() {
        return strIngredient8;
    }

    public void setStrIngredient8(String strIngredient8) {
        this.strIngredient8 = strIngredient8;
    }

    public String getStrIngredient9() {
        return strIngredient9;
    }

    public void setStrIngredient9(String strIngredient9) {
        this.strIngredient9 = strIngredient9;
    }

    public String getStrIngredient10() {
        return strIngredient10;
    }

    public void setStrIngredient10(String strIngredient10) {
        this.strIngredient10 = strIngredient10;
    }

    public String getStrIngredient11() {
        return strIngredient11;
    }

    public void setStrIngredient11(String strIngredient11) {
        this.strIngredient11 = strIngredient11;
    }

    public String getStrIngredient12() {
        return strIngredient12;
    }

    public void setStrIngredient12(String strIngredient12) {
        this.strIngredient12 = strIngredient12;
    }

    public String getStrIngredient13() {
        return strIngredient13;
    }

    public void setStrIngredient13(String strIngredient13) {
        this.strIngredient13 = strIngredient13;
    }

    public String getStrIngredient14() {
        return strIngredient14;
    }

    public void setStrIngredient14(String strIngredient14) {
        this.strIngredient14 = strIngredient14;
    }

    public String getStrIngredient15() {
        return strIngredient15;
    }

    public void setStrIngredient15(String strIngredient15) {
        this.strIngredient15 = strIngredient15;
    }

    public String getStrIngredient16() {
        return strIngredient16;
    }

    public void setStrIngredient16(String strIngredient16) {
        this.strIngredient16 = strIngredient16;
    }

    public String getStrIngredient17() {
        return strIngredient17;
    }

    public void setStrIngredient17(String strIngredient17) {
        this.strIngredient17 = strIngredient17;
    }

    public String getStrIngredient18() {
        return strIngredient18;
    }

    public void setStrIngredient18(String strIngredient18) {
        this.strIngredient18 = strIngredient18;
    }

    public String getStrIngredient19() {
        return strIngredient19;
    }

    public void setStrIngredient19(String strIngredient19) {
        this.strIngredient19 = strIngredient19;
    }

    public String getStrIngredient20() {
        return strIngredient20;
    }

    public void setStrIngredient20(String strIngredient20) {
        this.strIngredient20 = strIngredient20;
    }

    public void setStrCreativeCommonsConfirmed(String strCreativeCommonsConfirmed) {
        this.strCreativeCommonsConfirmed = strCreativeCommonsConfirmed;
    }
}
