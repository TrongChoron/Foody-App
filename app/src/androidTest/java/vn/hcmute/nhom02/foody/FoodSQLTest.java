package vn.hcmute.nhom02.foody;

import android.widget.ImageView;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import vn.hcmute.nhom02.foody.Domain.FoodModel;
import vn.hcmute.nhom02.foody.activity.SplashActivity;
import vn.hcmute.nhom02.foody.common.Utils;
import vn.hcmute.nhom02.foody.database.Database;
import vn.hcmute.nhom02.foody.database.FoodQuery;
import vn.hcmute.nhom02.foody.database.IFoodQuery;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Fri, 5/13/2022
 * Time     : 22:44
 * Filename : FoodSQLTest
 */
public class FoodSQLTest {
    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<SplashActivity>(SplashActivity.class);

    private SplashActivity mainActivity = null;

    private Database database = null;

    private IFoodQuery foodQuery = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = mActivityTestRule.getActivity();
        database = new Database(mainActivity);
        foodQuery = FoodQuery.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
    @Test
    public void testCreateFood() {
        ImageView imageView = new ImageView(mainActivity);
        imageView.setImageResource(R.drawable.bunbo);

        FoodModel foodModel = new FoodModel();
        foodModel.setPhotoFood(Utils.convertImageViewToBytes(imageView));
        foodModel.setFoodName("Bánh Tráng Trộn 123123");
        foodModel.setFoodDescription("Bánh tráng trà vinh");
        foodModel.setPrice(2.3F);
        foodModel.setRestaurantID(1);

        Long foodInsert = foodQuery.insert(foodModel);
        Assert.assertNotNull(foodInsert);
    }

    @Test
    public void deleteTest(){

        FoodModel foodModel = foodQuery.findById(3);

        Integer foodDelete = foodQuery.deleteFood(foodModel);
        System.out.println(foodDelete);
        Assert.assertNotNull(foodDelete);
    }

    @Test
    public void testFindAllFood() {
        List<FoodModel> foods = foodQuery.findAll();
        for (FoodModel item :foods){
            System.out.println(item.getPrice());
        }
        if (foods.size()==0) System.out.println("helooooo");

        Assert.assertTrue(foods.size() > 0);
    }

    @Test
    public void testFindFoodsByRestaurant() {
        List<FoodModel> foods = foodQuery.findFoodByRestaurant(1);
        for (FoodModel item:foods){
            System.out.println(item.getFoodName());
            System.out.println(item.getPrice());
        }
        System.out.println(foods.get(0).getFoodName());
        Assert.assertTrue(foods.size() > 0);
    }

    @Test
    public void findFoodById(){
        FoodModel foodModel = foodQuery.findById(3);
        System.out.println(foodModel.getPrice());
    }

    @Test
    public void testCreateTableFood() {
        String sql = "CREATE TABLE IF NOT EXISTS food(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name varchar(255), " +
                "description varchar(255)," +
                "price float,"+
                "pic blob,"+
                "restaurantID INTEGER NOT NULL," +
                " FOREIGN KEY (restaurantID) REFERENCES restaurant(id))";

        database.QueryData(sql);
    }
}
