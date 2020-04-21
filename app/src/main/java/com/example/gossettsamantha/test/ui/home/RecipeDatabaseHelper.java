package com.example.gossettsamantha.test.ui.home;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gossettsamantha.test.R;

import java.util.concurrent.ThreadLocalRandom;

public class RecipeDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "RECIPE"; // the name of our database
    private static final int DB_VERSION = 1; // the version of the database

    public RecipeDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        updateMyDatabase(db, oldVersion, newVersion);
    }

    private static void insertDrink(SQLiteDatabase db, int id, String name, String description,
                                    int resourceId, double match, String link) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("ID", id);
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
        drinkValues.put("MATCH_PERCENTAGE", match);
        drinkValues.put("LINK", link);
        db.insert("DRINK", null, drinkValues);
    }

    private static void insertIngredient(SQLiteDatabase db, int ingredientId, boolean userOwns, String ingredient) {
        ContentValues ingredientValues = new ContentValues();
        ingredientValues.put("INGREDIENT_ID", ingredientId);
        ingredientValues.put("INGREDIENT", ingredient);
        ingredientValues.put("USER_OWNS", userOwns);
        db.insert("INGREDIENT", null, ingredientValues);

    }

    private void insertAmount(SQLiteDatabase db, int amount_id, String amount) {
        ContentValues amountValues = new ContentValues();
        amountValues.put("AMOUNT_ID", amount_id);
        amountValues.put("AMOUNT", amount);
        db.insert("AMOUNT", null, amountValues);
    }

    private static void insertIngredientRecipeTable(SQLiteDatabase db, int recipeId, int ingredientId,  int amountId) {
        ContentValues ingredientRecipeValues = new ContentValues();
        ingredientRecipeValues.put("INGREDIENT_ID", ingredientId);
        ingredientRecipeValues.put("RECIPE_ID", recipeId);
        ingredientRecipeValues.put("AMOUNT_ID", amountId);
        db.insert("INGREDIENT_RECIPE", null, ingredientRecipeValues);
    }


    private static void insertInstruction(SQLiteDatabase db, int idInstruction, int idRecipe, String instruction) {
        ContentValues instructionValues = new ContentValues();
        instructionValues.put("ID", idInstruction);
        instructionValues.put("RECIPE_ID", idRecipe);
        instructionValues.put("INSTRUCTION", instruction);
        db.insert("INSTRUCTION", null, instructionValues);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {

            //Is the Recipe table, but if i rename every drink instance to recipe it wont work lmao
            db.execSQL("CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "ID, "
                    + "NAME, "
                    + "DESCRIPTION, "
                    + "IMAGE_RESOURCE_ID, "
                    + "MATCH_PERCENTAGE, "
                    + "LINK);");

            insertDrink(db, 0,"Air Fryer Chicken Taquitos", "These taquitos are air fried, which makes them healthier than deep fried ones. Using shredded rotisserie chicken provides a quick prep but you can always use home-cooked chicken. Serve with sides of sour cream, Mexican tomato sauce, and guacamole, or your choice of sides.", R.drawable.airfryerchickentaquitos, 95.2, "https://www.allrecipes.com/recipe/279015/air-fryer-chicken-taquitos/?internalSource=streams&referringId=201&referringContentType=Recipe%20Hub&clickId=st_recipes_mades");
            insertDrink(db, 1, "Rosemary-Roasted Chicken with Apples and Potatoes", "The beginning of fall comes with an abundance of apples, which means it's time to start baking! But more than pies, apples roast extremely well with chicken! This dish makes a fantastic sweater-weather meal, with beautifully roasted chicken and an apple, potato, and onion bake that takes advantage of the delicious roasted chicken drippings.", R.drawable.rosemarychicken, 94.5, "https://www.allrecipes.com/recipe/276263/rosemary-roasted-chicken-with-apples-and-potatoes/?internalSource=staff%20pick&referringId=201&referringContentType=Recipe%20Hub");
            insertDrink(db, 2, "Easy Spinach and Arugula Chicken", "Arugula was on sale and I wanted to find new ways to use it. This became a quick favorite. I've found that there's no need to preheat the oven; a little longer cooking time produces a juicy thigh with crispy skin.",R.drawable.easyspinach, 93.5, "https://www.allrecipes.com/recipe/222907/easy-spinach-and-arugula-chicken/?internalSource=rotd&referringId=201&referringContentType=Recipe%20Hub");
            insertDrink(db, 3, "Keto Smothered Chicken Thighs", "Topped with bacon, mushrooms, green onions, and a creamy sauce, these chicken thighs are sure to become a favorite on your keto menu.", R.drawable.keto, 93.3, "https://www.allrecipes.com/recipe/272437/keto-smothered-chicken-thighs/");
            insertDrink(db, 4, "Bacon-Ranch Chicken Enchiladas", "Not authentically Mexican in the slightest, but holy buckets are they good! Great way to use up leftover rotisserie chicken.", R.drawable.brchicken, 90.1, "https://www.allrecipes.com/recipe/272457/bacon-ranch-chicken-enchiladas/");
            insertDrink(db, 5, "Chicken Enchiladas with Green Chile Sauce (Salsa Verde)", "Enchiladas made easy! Garnish with diced tomatoes, shredded lettuce, and any of your favorites!", R.drawable.chickenench, 90, "https://www.allrecipes.com/recipe/270862/chicken-enchiladas-with-green-chile-sauce-salsa-verde/");
            insertDrink(db, 6, "Simple Nashville Hot Chicken Biscuits", "This is my rendition of Nashville hot chicken, served as a sandwich with biscuits. To save time, I've used refrigerator biscuits. I'm not a fan of spicy hot, but this still packs some heat!", R.drawable.nashville, 87.4, "https://www.allrecipes.com/recipe/270031/simple-nashville-hot-chicken-biscuits/");
            insertDrink(db, 7, "Chicken Breasts with Tomatoes and Olives", "A classic take-out dish made simple in your Instant Pot®. Don't forget to pick up some fortune cookies and feel free to leave out the sriracha if sensitive to heat. Serve over rice and garnish with green onions and sesame seeds.", R.drawable.oneygar, 85.3, "https://www.allrecipes.com/recipe/270944/instant-pot-honey-garlic-chicken/");
            insertDrink(db, 8,"Chicken Puttanesca with Angel Hair Pasta", "Our best drip coffee", R.drawable.instantpot, 30.2, "2213");


            db.execSQL("CREATE TABLE INGREDIENT_RECIPE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "INGREDIENT_ID, "
                    + "RECIPE_ID, "
                    + "AMOUNT_ID);");

            //recipe, ingredient, amount ids

            //Air Fryer Chicken
            insertIngredientRecipeTable(db, 0, 0, 0);
            insertIngredientRecipeTable(db, 0, 1, 6);
            insertIngredientRecipeTable(db, 0, 2, 10);
            insertIngredientRecipeTable(db, 0, 3, 6);
            insertIngredientRecipeTable(db, 0, 4, 6);
            insertIngredientRecipeTable(db, 0, 5, 14);
            insertIngredientRecipeTable(db, 0, 6, 6);
            insertIngredientRecipeTable(db, 0, 7, 12);
            insertIngredientRecipeTable(db, 0, 8, 16);
            insertIngredientRecipeTable(db, 0, 9, 16);
            insertIngredientRecipeTable(db, 0, 10, 26);
            insertIngredientRecipeTable(db, 0, 11, 15);

            //Rosemary Roasted
            insertIngredientRecipeTable(db, 1, 12, 15);
            insertIngredientRecipeTable(db, 1, 13, 6);
            insertIngredientRecipeTable(db, 1, 14, 31);
            insertIngredientRecipeTable(db, 1, 8,16);
            insertIngredientRecipeTable(db, 1, 15,3);
            insertIngredientRecipeTable(db, 1, 16,32);
            insertIngredientRecipeTable(db, 1, 17,33);
            insertIngredientRecipeTable(db, 1, 18,1);
            insertIngredientRecipeTable(db, 1, 19,21);
            insertIngredientRecipeTable(db, 1, 16,32);
            insertIngredientRecipeTable(db, 1, 20,6);
            insertIngredientRecipeTable(db, 1, 9,15);

            //Easy Spinach
            insertIngredientRecipeTable(db, 2, 21,12);
            insertIngredientRecipeTable(db, 2, 22,12);
            insertIngredientRecipeTable(db, 2, 23,35);
            insertIngredientRecipeTable(db, 2, 19,21);
            insertIngredientRecipeTable(db, 2, 8,34);

            insertIngredientRecipeTable(db, 3, 8,34);

            db.execSQL("CREATE TABLE INGREDIENT (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "INGREDIENT_ID, "
                    + "USER_OWNS, "
                    + "INGREDIENT);");

            //ingredient id to name
            insertIngredient(db, 0, false,"vegetable oil");
            insertIngredient(db, 1, false,"diced onion");
            insertIngredient(db, 2,  false,"minced garlic");
            insertIngredient(db, 3,  false,"chopped green chilies");
            insertIngredient(db, 4,  false,"Mexican-style hot tomato sauce");
            insertIngredient(db, 5,  false,"rotisserie chicken");
            insertIngredient(db, 6,  false,"Neufchatel cheese");
            insertIngredient(db, 7,  false,"Mexican cheese blend");
            insertIngredient(db, 8,  false,"salt");
            insertIngredient(db, 9,  false,"pepper");
            insertIngredient(db, 10,  false,"corn tortillas");
            insertIngredient(db, 11,  false,"avocado oil cooking spray");
            insertIngredient(db, 12,  false,"cooking spray");
            insertIngredient(db, 13,  false,"butter");
            insertIngredient(db, 14,  false,"large onion");
            insertIngredient(db, 15,  false,"white sugar");
            insertIngredient(db, 16,  false,"medium apples");
            insertIngredient(db, 17,  false,"Yukon Gold potatoes");
            insertIngredient(db, 18,  false,"olive oil");
            insertIngredient(db, 19,  false,"bone-in chicken thighs with skin");
            insertIngredient(db, 20,  false,"chopped rosemary");
            insertIngredient(db, 21,  false,"baby spinach leaves");
            insertIngredient(db, 22,  false,"arugula");
            insertIngredient(db, 23,  false,"garlic");


            db.execSQL("CREATE TABLE AMOUNT (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "AMOUNT_ID, "
                    + "AMOUNT);");

            //amount id has amount
            insertAmount(db, 15,  "");
            insertAmount(db, 16,  "to taste, ");

            insertAmount(db, 0, "one teaspoon");
            insertAmount(db, 1, "two teaspoons");
            insertAmount(db, 2, "three teaspoons");
            insertAmount(db, 3, "four teaspoons");
            insertAmount(db, 4,  "five teaspoons");

            insertAmount(db, 5,  "one tablespoon");
            insertAmount(db, 6,  "two tablespoons");
            insertAmount(db, 7,  "three tablespoons");
            insertAmount(db, 8,  "four tablespoons");
            insertAmount(db, 9,  "five tablespoons");

            insertAmount(db, 10,  "1 clove");

            insertAmount(db, 11,  "1/4 cup");
            insertAmount(db, 12,  "1/2 cup");
            insertAmount(db, 13,  "3/4 cup");
            insertAmount(db, 14,  "1 cup");

            insertAmount(db, 20,  "0");
            insertAmount(db, 21,  "1");
            insertAmount(db, 22,  "2");
            insertAmount(db, 23,  "3");
            insertAmount(db, 24,  "4");
            insertAmount(db, 25,  "5");
            insertAmount(db, 26,  "6");
            insertAmount(db, 27,  "7");
            insertAmount(db, 28,  "8");
            insertAmount(db, 29,  "9");
            insertAmount(db, 30,  "10");

            insertAmount(db, 31,  "1 sliced");
            insertAmount(db, 32,  "5 peeled, cored, and cut into 1/4th in slices");
            insertAmount(db, 33,  "1 pound cut into 1/4th in slices");

            insertAmount(db, 34, "1/4 teaspoon");

            insertAmount(db, 35,  "1 clove, slithered");



            db.execSQL("CREATE TABLE INSTRUCTION (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "ID, "
                    + "RECIPE_ID, "
                    + "INSTRUCTION);");

            insertInstruction(db, 1, 0, "1. Heat oil in a skillet. Add onion and cook until soft and translucent, 3 to 5 minutes. Add garlic and cook until fragrant, about 1 minute. Add green chiles and Mexican tomato sauce; stir to combine. Add chicken, Neufchatel cheese, and Mexican cheese blend. Cook and stir until cheeses have melted and mixture is completely warmed, about 3 minutes. Season with salt and pepper.");
            insertInstruction(db, 2, 0, "2. Heat tortillas in a skillet or directly on the grates of a gas stove until soft and pliable. Place 3 tablespoons of chicken mixture down the center of each tortilla. Fold over and roll into taquitos.");
            insertInstruction(db, 3, 0, "3. Preheat an air fryer to 400 degrees F (200 degrees C).");
            insertInstruction(db, 4, 0, "4. Place taquitos in the air fryer basket, making sure they are not touching, and mist with avocado oil. Cook in batches if necessary. Cook until golden brown and crispy, 6 to 9 minutes. Turn taquitos over, mist with avocado oil, and air fry for an additional 3 to 5 minutes.");

            insertInstruction(db, 5, 1, "1. Preheat the oven to 400 degrees F (200 degrees C). Spray the top and bottom parts of a broiler pan with cooking spray.");
            insertInstruction(db, 6, 1, "2. Melt butter in a skillet over medium heat and add onion slices. Sprinkle onions with 1 teaspoon salt and saute for 2 minutes. Add sugar and continue cooking until onions are translucent but not browned, 6 to 8 minutes. Remove from heat");
            insertInstruction(db, 7, 1, "3. Layer apples, potatoes, and sauteed onion slices in the bottom part of the broiler pan. Cover with the part top of the broiler pan");
            insertInstruction(db, 8, 1, "4. Drizzle olive oil over chicken thighs. Sprinkle with fresh rosemary, salt, and pepper. Rub olive oil and spices thoroughly all over each thigh and place on the top part of the broiler pan.");
            insertInstruction(db, 9, 1, "5. Roast in the preheated oven until juices run clear and an instant-read thermometer inserted into the thickest part of the thigh, near the bone, reads 165 degrees F (74 degrees C), about 1 hour. Serve apples, potatoes, and onion alongside chicken thighs.");

            insertInstruction(db, 10, 2, "1. Stuff spinach leaves, arugula, and garlic under the skin of the chicken thigh and place on a baking dish. Sprinkle with seasoned salt.");
            insertInstruction(db, 11, 2, "2. Bake chicken thigh in the oven set at 350 degrees F (175 degrees C) until no longer pink at the bone and the juices run clear, about 50 minutes. An instant-read thermometer inserted near the bone should read 165 degrees F (74 degrees C)");

            insertInstruction(db, 12, 3, "1. ");


        }


        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
            db.execSQL("ALTER TABLE INGREDIENT ADD COLUMN FAVORITE NUMERIC;");
            db.execSQL("ALTER TABLE INGREDIENT_RECIPE ADD COLUMN FAVORITE NUMERIC;");
            db.execSQL("ALTER TABLE AMOUNT ADD COLUMN FAVORITE NUMERIC;");
            db.execSQL("ALTER TABLE INSTRUCTION ADD COLUMN FAVORITE NUMERIC;");
        }
    }



}
