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

    private static void insertIngredient(SQLiteDatabase db, int ingredientId, int userOwns, String ingredient) {
        ContentValues ingredientValues = new ContentValues();
        ingredientValues.put("INGREDIENT_ID", ingredientId);
        ingredientValues.put("INGREDIENTNAME", ingredient);
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

            insertDrink(db, 0,"Air Fryer Chicken Taquitos", "These taquitos are air fried, which makes them healthier than deep fried ones. Using shredded rotisserie chicken provides a quick prep but you can always use home-cooked chicken. Serve with sides of sour cream, Mexican tomato sauce, and guacamole, or your choice of sides.", R.drawable.airfryerchickentaquitos, 0, "https://www.allrecipes.com/recipe/279015/air-fryer-chicken-taquitos/?internalSource=streams&referringId=201&referringContentType=Recipe%20Hub&clickId=st_recipes_mades");
            insertDrink(db, 1, "Rosemary-Roasted Chicken with Apples and Potatoes", "The beginning of fall comes with an abundance of apples, which means it's time to start baking! But more than pies, apples roast extremely well with chicken! This dish makes a fantastic sweater-weather meal, with beautifully roasted chicken and an apple, potato, and onion bake that takes advantage of the delicious roasted chicken drippings.", R.drawable.rosemarychicken, 0, "https://www.allrecipes.com/recipe/276263/rosemary-roasted-chicken-with-apples-and-potatoes/?internalSource=staff%20pick&referringId=201&referringContentType=Recipe%20Hub");
            insertDrink(db, 2, "Easy Spinach and Arugula Chicken", "Arugula was on sale and I wanted to find new ways to use it. This became a quick favorite. I've found that there's no need to preheat the oven; a little longer cooking time produces a juicy thigh with crispy skin.",R.drawable.easyspinach, 0, "https://www.allrecipes.com/recipe/222907/easy-spinach-and-arugula-chicken/?internalSource=rotd&referringId=201&referringContentType=Recipe%20Hub");
            insertDrink(db, 3, "Keto Smothered Chicken Thighs", "Topped with bacon, mushrooms, green onions, and a creamy sauce, these chicken thighs are sure to become a favorite on your keto menu.", R.drawable.keto, 0, "https://www.allrecipes.com/recipe/272437/keto-smothered-chicken-thighs/");
            insertDrink(db, 4, "Bacon-Ranch Chicken Enchiladas", "Not authentically Mexican in the slightest, but holy buckets are they good! Great way to use up leftover rotisserie chicken.", R.drawable.brchicken, 0, "https://www.allrecipes.com/recipe/272457/bacon-ranch-chicken-enchiladas/");
            insertDrink(db, 5, "Chicken Enchiladas with Green Chile Sauce (Salsa Verde)", "Enchiladas made easy! Garnish with diced tomatoes, shredded lettuce, and any of your favorites!", R.drawable.chickenench, 0, "https://www.allrecipes.com/recipe/270862/chicken-enchiladas-with-green-chile-sauce-salsa-verde/");
            insertDrink(db, 6, "Simple Nashville Hot Chicken Biscuits", "This is my rendition of Nashville hot chicken, served as a sandwich with biscuits. To save time, I've used refrigerator biscuits. I'm not a fan of spicy hot, but this still packs some heat!", R.drawable.nashville, 0, "https://www.allrecipes.com/recipe/270031/simple-nashville-hot-chicken-biscuits/");
            insertDrink(db, 7, "Instant Pot® Honey-Garlic Chicken", "A classic take-out dish made simple in your Instant Pot®. Don't forget to pick up some fortune cookies and feel free to leave out the sriracha if sensitive to heat. Serve over rice and garnish with green onions and sesame seeds.", R.drawable.oneygar, 0, "https://www.allrecipes.com/recipe/270944/instant-pot-honey-garlic-chicken/");
            insertDrink(db, 8,"3-Ingredient Cheesecake", "This is a Japanese cheesecake which is so light that it seems a little like a souffle. You only need cream cheese, eggs, and white chocolate to make this easy gluten-free dessert! This cheesecake tastes best after it has been chilled for a a few hours in the fridge. It keeps in the fridge for a few days. Serve as is or with a drizzle of pureed berries.", R.drawable.threeingcheesecake, 0, "https://www.allrecipes.com/recipe/263685/3-ingredient-cheesecake/?internalSource=hub%20recipe&referringContentType=Search&clickId=cardslot%201");
            insertDrink(db, 9,"Chicken Puttanesca with Angel Hair Pasta", "Our best drip coffee", R.drawable.instantpot, 0, "2213");


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

            //keto
            insertIngredientRecipeTable(db, 3, 15,24);   //Chicken
            insertIngredientRecipeTable(db, 3, 21,5);    //Paprika
            insertIngredientRecipeTable(db, 3, 8,15);    //Salt
            insertIngredientRecipeTable(db, 3, 9,15);    //Pepper
            insertIngredientRecipeTable(db, 3, 22,24);   //Bacon
            insertIngredientRecipeTable(db, 3, 23,50);   //Chicken Broth
            insertIngredientRecipeTable(db, 3, 24,44);   // Mushrooms
            insertIngredientRecipeTable(db, 3, 25,11);   //whipping cream
            insertIngredientRecipeTable(db, 3, 26,22);   //green onion

            //Bacon-Garlic-Chicken
            insertIngredientRecipeTable(db, 4, 11, 15); //cooking spray
            insertIngredientRecipeTable(db, 4, 16, 6); //olive oil
            insertIngredientRecipeTable(db, 4, 1, 11); //onion
            insertIngredientRecipeTable(db, 4, 5, 19); //Chicken
            insertIngredientRecipeTable(db, 4, 22, 14); //Bacon
            insertIngredientRecipeTable(db, 4, 2, 6); //garlic

            //Chicken Enchiladeas
            insertIngredientRecipeTable(db, 5, 5, 21); //Chicken
            insertIngredientRecipeTable(db, 5, 7, 14); //Mexian Cheese
            insertIngredientRecipeTable(db, 5, 29, 48); //Cream Cheese
            insertIngredientRecipeTable(db, 5, 30, 11); //Salsa Verde

            //Nashville Biscuits
            insertIngredientRecipeTable(db, 6, 31, 21); //canned biscuits
            insertIngredientRecipeTable(db, 6, 32, 12); //buttermilk
            insertIngredientRecipeTable(db, 6, 33, 21); //eggs
            insertIngredientRecipeTable(db, 6, 34, 6); //sriracha sauce
            insertIngredientRecipeTable(db, 6, 8, 15); //salt
            insertIngredientRecipeTable(db, 6, 9, 15); //pepper

            //Instant Pot
            insertIngredientRecipeTable(db, 7, 35, 50); //honey
            insertIngredientRecipeTable(db, 7, 36, 52); //soy sauce
            insertIngredientRecipeTable(db, 7, 3, 23); //garlic
            insertIngredientRecipeTable(db, 7, 37, 7); //ketchup
            insertIngredientRecipeTable(db, 7, 18, 35); //chicken
            insertIngredientRecipeTable(db, 7, 38, 5); //sesame seeds

            //3-Ingredient Cheesecake
            insertIngredientRecipeTable(db, 8, 39, 44); //white Chocolate
            insertIngredientRecipeTable(db, 8, 29, 19); //Cream cheese
            insertIngredientRecipeTable(db, 8, 33, 23); //eggs



            db.execSQL("CREATE TABLE INGREDIENT (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "INGREDIENT_ID, "
                    + "USER_OWNS, "
                    + "INGREDIENTNAME);");

            //ingredient id to name
            //Recipe 1
            insertIngredient(db, 0, 0,"vegetable oil");
            insertIngredient(db, 1, 0,"diced onion");
            insertIngredient(db, 2,  0,"garlic");
            insertIngredient(db, 3,  0,"chopped green chiles");
            insertIngredient(db, 4,  0,"Mexican-style hot tomato sauce");
            insertIngredient(db, 5,  0,"rotisserie chicken");
            insertIngredient(db, 6,  0,"Neufchatel cheese");
            insertIngredient(db, 7,  0,"Mexican cheese blend");
            insertIngredient(db, 8,  0,"salt");
            insertIngredient(db, 9,  0,"pepper");
            insertIngredient(db, 10,  0,"corn tortillas");
            insertIngredient(db, 11,  0,"avocado oil cooking spray");

            //Recipe2
            insertIngredient(db, 12,  0,"Butter");
            insertIngredient(db, 13,  0,"Sugar");
            insertIngredient(db, 14,  0,"Apples");
            insertIngredient(db, 15,  0,"Potatoes");
            insertIngredient(db, 16,  0,"Olive Oil");
            insertIngredient(db, 17,  0,"Rosemary");
            insertIngredient(db, 18,  0,"Frozen Chicken Thighs");

            //Recipe3
            insertIngredient(db, 19,  0,"Baby Spinach");
            insertIngredient(db, 20,  0,"Arugula");

            //Recipe4
            insertIngredient(db, 21,  0,"Paprika");
            insertIngredient(db, 22,  0,"Bacon");
            insertIngredient(db, 24,  0,"Chicken Broth");
            insertIngredient(db, 25,  0,"Mushrooms");
            insertIngredient(db, 26,  0,"Whipping Cream");
            insertIngredient(db, 27,  0,"Green Onion");

            //Recipe5
            insertIngredient(db, 29,  0,"Cream Cheese");
            insertIngredient(db, 30,  0,"Salsa verde");

            //Recipe6
            insertIngredient(db, 31,  0,"Canned Biscuits");
            insertIngredient(db, 32,  0,"buttermilk");
            insertIngredient(db, 33,  0,"Egg");
            insertIngredient(db, 34,  0,"Sririacha Sauce");

            insertIngredient(db, 35,  0,"Honey");
            insertIngredient(db, 36,  0,"Soy Sauce");
            insertIngredient(db, 37,  0,"Ketchup");
            insertIngredient(db, 38,  0,"Sesame Seeds");
            insertIngredient(db, 39,  0,"White chocolate");




            db.execSQL("CREATE TABLE AMOUNT (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "AMOUNT_ID, "
                    + "AMOUNT);");

            //only add if there is a new amount
            insertAmount(db, 15,  ""); // for null value
            insertAmount(db, 16,  "to taste");

            //teaspoons
            insertAmount(db, 31, "1/4 teaspoon");

            insertAmount(db, 33, "1/2 teaspoon");
            insertAmount(db, 34, "3/4 teaspoon");
            insertAmount(db, 0, "one teaspoon");
            insertAmount(db, 1, "two teaspoons");
            insertAmount(db, 2, "three teaspoons");
            insertAmount(db, 3, "four teaspoons");
            insertAmount(db, 4,  "five teaspoons");

            //tablespoons
            insertAmount(db, 5,  "one tablespoon");
            insertAmount(db, 6,  "two tablespoons");
            insertAmount(db, 7,  "three tablespoons");
            insertAmount(db, 8,  "four tablespoons");
            insertAmount(db, 9,  "five tablespoons");

            //Whole
            insertAmount(db, 10,  "1 clove");

            //Cup measurements
            insertAmount(db, 11,  "1/4 cup");
            insertAmount(db, 12,  "1/2 cup");
            insertAmount(db, 50,  "1/3 cup");
            insertAmount(db, 13,  "3/4 cup");
            insertAmount(db, 14,  "1 cup");

            insertAmount(db,52, "1 and 1/2 Cups");
            insertAmount(db, 53, "2 Cups");

            //Whole Measurements
            insertAmount(db, 19,  "1/2");
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

            //Pound Measurements
            insertAmount(db, 51,  "one pound");

            insertAmount(db, 35, "2 pounds");

            //ounces
            insertAmount(db, 44, "4 Ounces");
            insertAmount(db, 48, "8 Ounces");



            db.execSQL("CREATE TABLE INSTRUCTION (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "ID, "
                    + "RECIPE_ID, "
                    + "INSTRUCTION);");

            insertInstruction(db, 1, 0, "1. Heat oil in a skillet. Add onion and cook until soft and translucent, 3 to 5 minutes. Add garlic and cook until fragrant, about 1 minute. Add green chiles and Mexican tomato sauce; stir to combine. Add chicken, Neufchatel cheese, and Mexican cheese blend. Cook and stir until cheeses have melted and mixture is completely warmed, about 3 minutes. Season with salt and pepper.");
            insertInstruction(db, 2, 0, "2. Heat tortillas in a skillet or directly on the grates of a gas stove until soft and pliable. Place 3 tablespoons of chicken mixture down the center of each tortilla. Fold over and roll into taquitos.");
            insertInstruction(db, 3, 0, "3. Preheat an air fryer to 400 degrees F (200 degrees C).");
            insertInstruction(db, 4, 0, "4. Place taquitos in the air fryer basket, making sure they are not touching, and mist with avocado oil. Cook in batches if necessary. Cook until golden brown and crispy, 6 to 9 minutes. Turn taquitos over, mist with avocado oil, and air fry for an additional 3 to 5 minutes.");

            insertInstruction(db, 5, 1, "1. Preheat the oven to 400 degrees F (200 degrees C). Spray the top and bottom parts of a broiler pan with cooking spray.");
            insertInstruction(db, 6, 1, "2. Melt butter in a skillet over medium heat and add onion slices. Sprinkle onions with 1 teaspoon salt and saute for 2 minutes. Add sugar and continue cooking until onions are translucent but not browned, 6 to 8 minutes. Remove from heat.");
            insertInstruction(db, 7, 1, "3. Layer apples, potatoes, and sauteed onion slices in the bottom part of the broiler pan. Cover with the part top of the broiler pan.");
            insertInstruction(db, 8, 1, "4. Drizzle olive oil over chicken thighs. Sprinkle with fresh rosemary, salt, and pepper. Rub olive oil and spices thoroughly all over each thigh and place on the top part of the broiler pan.");
            insertInstruction(db, 9, 1, "5. Roast in the preheated oven until juices run clear and an instant-read thermometer inserted into the thickest part of the thigh, near the bone, reads 165 degrees F (74 degrees C), about 1 hour. Serve apples, potatoes, and onion alongside chicken thighs.");

            //Easy Spinach and Arugula Chicken
            insertInstruction(db, 10, 2, "1. Stuff spinach leaves, arugula, and garlic under the skin of the chicken thigh and place on a baking dish. Sprinkle with seasoned salt.");
            insertInstruction(db, 11, 2, "2. Bake chicken thigh in the oven set at 350 degrees F (175 degrees C) until no longer pink at the bone and the juices run clear, about 50 minutes. An instant-read thermometer inserted near the bone should read 165 degrees F (74 degrees C).");

            //Keto Smothered Chicken Thighs
            insertInstruction(db, 12, 3, "1. Season chicken thighs on all sides with paprika, salt, and pepper.");
            insertInstruction(db, 13, 3, "2. Cook bacon in a cast iron skillet or oven-safe pan over medium-high heat until browned, 4 to 5 minutes. Remove from skillet and drain on a paper towel-lined plate. Drain and discard excess grease from skillet.");
            insertInstruction(db, 14, 3, "3. Return skillet to medium heat and cook chicken thighs, skin-side down, for 3 to 4 minutes. Flip chicken over and place skillet in the preheated oven.");
            insertInstruction(db, 15, 3, "4. Bake until chicken thighs are no longer pink at the bone and juices run clear, about 30 minutes. An instant-read thermometer inserted near the bone should read 165 degrees F (74 degrees C). Remove chicken to a plate and cover with foil to keep warm. Remove all but 2 tablespoons drippings from skillet.");
            insertInstruction(db, 16, 3, "5. Return skillet to the stove over medium-high heat. Pour in chicken broth while whisking up brown bits from the bottom of the skillet. Add mushrooms and cook until soft, about 3 to 4 minutes. Pour in heavy whipping cream and whisk together until lightly simmering, then reduce heat to medium-low. Season with salt and pepper, if necessary.");
            insertInstruction(db, 17, 3, "6. Return chicken and any juices back into skillet; top with bacon and green onions. Serve immediately, spooning sauce over the chicken.");

            //Bacon-Ranch Chicken Enchiladas
            insertInstruction(db, 18, 4, "1. Preheat the oven to 450 degrees F (230 degrees C). Spray a 9x12-inch brownie pan with cooking spray.");
            insertInstruction(db, 19, 4, "2. Heat olive oil in a large skillet over medium heat. Add onion and cook until soft, about 5 minutes. Stir in chicken, bacon, and roasted garlic. Season with salt, pepper, and garlic powder. Stir in green onions.");
            insertInstruction(db, 20, 4, "3. Mix ranch dressing and sour cream together in a small bowl. Remove chicken mixture from heat and stir just enough ranch mixture into the skillet to barely coat chicken.");
            insertInstruction(db, 21, 4, "4. Hold 1 tortilla and spoon 2 tablespoons chicken mixture across the middle. Sprinkle 1 tablespoon Cheddar-Monterey Jack cheese blend over chicken and roll tortilla up. Place filled tortilla, seam-side down, against the short end of the pan so it does not unroll. Repeat with remaining tortillas and filling until pan is jammed full of enchiladas.");
            insertInstruction(db, 22, 4, "5. Bake in the preheated oven until bubbly on top, about 15 minutes.");
            insertInstruction(db, 23, 4, "6. Bake in the preheated oven until bubbly on top, about 15 minutes.");

            //Chicken Enchiladas with Green Chile Sauce (Salsa Verde)
            insertInstruction(db, 25, 5, "1. Preheat the oven to 375 degrees F (190 degrees C).");
            insertInstruction(db, 26, 5, "2. Heat olive oil in a saute pan over medium heat. Add onion; cook and stir until translucent, about 5 minutes. Add garlic and heat until fragrant, about 2 minutes. Remove from heat and let cool.");
            insertInstruction(db, 27, 5, "3. Combine chicken, onion mixture, cheese blend, cream cheese, salsa verde, olives, cilantro, and jalapenos in a large bowl for the filling. Mix well.");
            insertInstruction(db, 28, 5, "4. Melt butter in a saucepan over medium heat. Stir in flour, then chicken broth. Cook until sauce starts to thicken, 6 to 8 minutes. Stir in sour cream and salsa verde; cook until heated through. Spread a spoonful of sauce over the bottoms of two 9x11-inch casserole dishes.");
            insertInstruction(db, 29, 5, "5. Wrap a tortilla in a damp paper towel and microwave for about 30 seconds. Fill with the chicken mixture, roll up, and place seam-side down into a baking dish. Repeat with remaining tortillas until each dish holds 8 enchiladas.");
            insertInstruction(db, 29, 2, "6. Spoon remaining sauce over the enchiladas. Sprinkle cheese on top.");
            insertInstruction(db, 29, 5, "7. Bake in the preheated oven until heated through, about 20 minutes.");

            //Simple Nashville Hot Chicken Biscuits
            insertInstruction(db, 29, 6, "1. Preheat the oven to 350 degrees F (175 degrees C). Place biscuits 1 inch apart on an ungreased baking sheet.");
            insertInstruction(db, 30, 6, "2. Bake in the preheated oven until golden brown, 13 to 17 minutes. Set aside.");
            insertInstruction(db, 31, 6, "3. Mix buttermilk, egg, sriracha, salt, garlic powder, and cayenne pepper together in a shallow bowl. Mix flour and chicken and pork rub together in a separate shallow bowl. Dip chicken into buttermilk mixture then dredge in flour mixture; shake off excess.");
            insertInstruction(db, 32, 6, "4. Heat oil and butter in a cast iron skillet over medium heat. Fry coated chicken in the hot skillet until golden and cooked through, about 3 minutes per side.");
            insertInstruction(db, 33, 6, "5. Mix sriracha and agave nectar together in a bowl; adjust heat level to suit your preference.");
            insertInstruction(db, 34, 6, "6. Slice biscuits in half and place 1 piece of chicken on each bottom half. Place about 1 tablespoon sauce on each piece of chicken and top with the remaining biscuit halves.");

            //Instant Pot® Honey-Garlic Chicken
            insertInstruction(db, 35,7, "1. Combine honey, soy sauce, garlic, ketchup, and sriracha sauce in a bowl; mix well and set aside.");
            insertInstruction(db, 36, 7, "2. Place chicken pieces in a large bowl, add cornstarch, and toss to combine.");
            insertInstruction(db, 37, 7, "3. Turn on a multi-functional pressure cooker (such as Instant Pot®) and select the Saute function. Add oil until hot. Add 1/2 the chicken and cook for 3 minutes. Flip and cook 2 more minutes. Transfer chicken to a plate and repeat with remaining chicken. Pour chicken broth into the empty Instant Pot® and cook for 2 minutes, scraping up the brown bits with a wooden spoon to deglaze the pot. Turn Instant Pot® off. Return chicken to the pot and pour honey mixture on top. Stir to coat with sauce.");
            insertInstruction(db, 38, 7, "4. Close and lock the lid. Select high pressure according to manufacturer's instructions; set timer for 2 minutes. Allow 10 minutes for pressure to build.");
            insertInstruction(db, 39, 7, "5. Release pressure carefully using the quick-release method according to manufacturer's instructions, about 5 minutes. Unlock and remove the lid. Serve chicken sprinkled with green onions and sesame seeds.");

            //3-Ingredient Cheesecake
            insertInstruction(db, 52, 8, "1. Preheat the oven to 350 degrees F (175 degrees C). Line the bottom of a 9-inch springform pan with parchment paper.");
            insertInstruction(db, 53, 8, "2. Place white chocolate in top of a double boiler over simmering water. Stir frequently, scraping down the sides with a rubber spatula to avoid scorching, until chocolate is melted, about 5 minutes. Remove and allow to cool slightly. Mix in cream cheese and egg yolks.");
            insertInstruction(db, 54, 8, "3. Beat egg whites in a large glass, metal, or ceramic bowl with an electric mixer until stiff peaks form. Fold in the cooled white chocolate mixture. Pour into the prepared springform pan.");
            insertInstruction(db, 55, 8, "4. Bake in the preheated oven for 15 minutes. Reduce oven temperature to 300 degrees F (150 degrees C) and bake for an additional 15 minutes. Turn the oven off, leaving cheesecake inside for for a further 15 minutes.");
            insertInstruction(db, 56, 8, "5. Remove cheesecake from the oven and allow to cool completely at room temperature, about 1 hour. Chill before serving, about 3 hours.");



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
