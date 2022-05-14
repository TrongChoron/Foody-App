package vn.hcmute.nhom02.foody;

import android.widget.ImageView;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import vn.hcmute.nhom02.foody.Domain.Restaurant;
import vn.hcmute.nhom02.foody.activity.SplashActivity;
import vn.hcmute.nhom02.foody.common.Utils;
import vn.hcmute.nhom02.foody.database.Database;
import vn.hcmute.nhom02.foody.database.FoodQuery;
import vn.hcmute.nhom02.foody.database.IFoodQuery;
import vn.hcmute.nhom02.foody.database.IRestaurantQuery;
import vn.hcmute.nhom02.foody.database.RestaurantQuery;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Fri, 5/13/2022
 * Time     : 23:12
 * Filename : RestaurantSQLTest
 */
public class RestaurantSQLTest {
    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<SplashActivity>(SplashActivity.class);

    private SplashActivity mainActivity = null;

    private Database database = null;

    private IRestaurantQuery restaurantQuery = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = mActivityTestRule.getActivity();
        database = new Database(mainActivity);
        restaurantQuery = RestaurantQuery.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }


    @Test
    public void testCreateFood() {
        ImageView imageView = new ImageView(mainActivity);
        imageView.setImageResource(R.drawable.bunmam);

        Restaurant foodModel = new Restaurant();
        foodModel.setRestaurantImage(Utils.convertImageViewToBytes(imageView));
        foodModel.setName("Bún mắm Lê Đức Thọ");
        foodModel.setAddress("143/35 Pham Huy Thông, Le Duc Tho, Phuong 7,tiep theop");
        foodModel.setCategoryId(1);
        Long foodInsert = restaurantQuery.insert(foodModel);
        Assert.assertNotNull(foodInsert);
    }

    @Test
    public void testFindByCategory(){
        final String code = "DU";
        List<Restaurant> foods = restaurantQuery.findByCategory(1);
        System.out.println(foods);
        Assert.assertTrue(foods.size() > 0);

    }

}
