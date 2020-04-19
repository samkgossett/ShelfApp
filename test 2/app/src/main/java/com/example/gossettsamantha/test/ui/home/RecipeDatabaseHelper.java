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

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "ID, "
                    + "NAME, "
                    + "DESCRIPTION, "
                    + "IMAGE_RESOURCE_ID, "
                    + "MATCH_PERCENTAGE, "
                    + "LINK);");

            double match = ThreadLocalRandom.current().nextDouble(0.00, 100);

            insertDrink(db, 1,"Air Fryer Chicken Taquitos", "These taquitos are air fried, which makes them healthier than deep fried ones. Using shredded rotisserie chicken provides a quick prep but you can always use home-cooked chicken. Serve with sides of sour cream, Mexican tomato sauce, and guacamole, or your choice of sides.", R.drawable.airfryerchickentaquitos, 95.2, "https://www.allrecipes.com/recipe/279015/air-fryer-chicken-taquitos/?internalSource=streams&referringId=201&referringContentType=Recipe%20Hub&clickId=st_recipes_mades");
            insertDrink(db, 2, "Rosemary-Roasted Chicken with Apples and Potatoes", "The beginning of fall comes with an abundance of apples, which means it's time to start baking! But more than pies, apples roast extremely well with chicken! This dish makes a fantastic sweater-weather meal, with beautifully roasted chicken and an apple, potato, and onion bake that takes advantage of the delicious roasted chicken drippings.", R.drawable.rosemarychicken, 94.5, "https://www.allrecipes.com/recipe/276263/rosemary-roasted-chicken-with-apples-and-potatoes/?internalSource=staff%20pick&referringId=201&referringContentType=Recipe%20Hub");
            insertDrink(db, 3, "Easy Spinach and Arugula Chicken", "Arugula was on sale and I wanted to find new ways to use it. This became a quick favorite. I've found that there's no need to preheat the oven; a little longer cooking time produces a juicy thigh with crispy skin.",R.drawable.easyspinach, 93.5, "https://www.allrecipes.com/recipe/222907/easy-spinach-and-arugula-chicken/?internalSource=rotd&referringId=201&referringContentType=Recipe%20Hub");
            insertDrink(db, 4, "Keto Smothered Chicken Thighs", "Topped with bacon, mushrooms, green onions, and a creamy sauce, these chicken thighs are sure to become a favorite on your keto menu.", R.drawable.keto, 93.3, "https://www.allrecipes.com/recipe/272437/keto-smothered-chicken-thighs/");
            insertDrink(db, 5, "Bacon-Ranch Chicken Enchiladas", "Not authentically Mexican in the slightest, but holy buckets are they good! Great way to use up leftover rotisserie chicken.", R.drawable.brchicken, 90.1, "https://www.allrecipes.com/recipe/272457/bacon-ranch-chicken-enchiladas/");
            insertDrink(db, 6, "Chicken Enchiladas with Green Chile Sauce (Salsa Verde)", "Enchiladas made easy! Garnish with diced tomatoes, shredded lettuce, and any of your favorites!", R.drawable.chickenench, 90, "https://www.allrecipes.com/recipe/270862/chicken-enchiladas-with-green-chile-sauce-salsa-verde/");
            insertDrink(db, 7, "Simple Nashville Hot Chicken Biscuits", "This is my rendition of Nashville hot chicken, served as a sandwich with biscuits. To save time, I've used refrigerator biscuits. I'm not a fan of spicy hot, but this still packs some heat!", R.drawable.nashville, 87.4, "https://www.allrecipes.com/recipe/270031/simple-nashville-hot-chicken-biscuits/");
            insertDrink(db, 8, "Chicken Breasts with Tomatoes and Olives", "A classic take-out dish made simple in your Instant Pot®. Don't forget to pick up some fortune cookies and feel free to leave out the sriracha if sensitive to heat. Serve over rice and garnish with green onions and sesame seeds.", R.drawable.oneygar, 85.3, "https://www.allrecipes.com/recipe/270944/instant-pot-honey-garlic-chicken/");
            insertDrink(db, 9,"Chicken Puttanesca with Angel Hair Pasta", "Our best drip coffee", R.drawable.instantpot, match, "2213");
            insertDrink(db, 10, "Chicken Tetrazzini", "Our best drip coffee", R.drawable.instantpot, match, "2213");
            insertDrink(db, 11,"Chicken Shawarma", "Our best drip coffee", R.drawable.instantpot, match, "2213");
            insertDrink(db, 12, "Three-Cheese Chicken Penne Florentine", "Our best drip coffee", R.drawable.instantpot, match, "2213");
            insertDrink(db, 13,"Chicken Strips with Blue Cheese Dressing", "Our best drip coffee", R.drawable.instantpot, match, "2213");
            insertDrink(db, 14, "Potato, Chicken, and Fresh Pea Salad", "Our best drip coffee", R.drawable.instantpot, match, "2213");
            insertDrink(db, 15, "Hot Chicken and Chips Retro", "Our best drip coffee", R.drawable.instantpot, match, "2213");
            insertDrink(db, 16, "Biscuit-Topped Chicken Potpie", "Our best drip coffee", R.drawable.instantpot, match, "2213");
            insertDrink(db, 17, "Spiedini of Chicken and Zucchini with Almond Salsa Verde", "Our best drip coffee", R.drawable.instantpot, match, "2213");

        }
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
        }
    }
}
