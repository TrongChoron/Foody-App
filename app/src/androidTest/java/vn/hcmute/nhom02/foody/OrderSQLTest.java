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
import vn.hcmute.nhom02.foody.Domain.OrderModel;
import vn.hcmute.nhom02.foody.activity.SplashActivity;
import vn.hcmute.nhom02.foody.common.Utils;
import vn.hcmute.nhom02.foody.database.Database;
import vn.hcmute.nhom02.foody.database.FoodQuery;
import vn.hcmute.nhom02.foody.database.IOrderQuery;
import vn.hcmute.nhom02.foody.database.OrderQuery;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Tue, 5/17/2022
 * Time     : 21:07
 * Filename : OrderSQLTest
 */
public class OrderSQLTest {
    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<SplashActivity>(SplashActivity.class);

    private SplashActivity mainActivity = null;

    private Database database = null;

    private IOrderQuery orderQuery;

    @Before
    public void setUp() throws Exception {
        mainActivity = mActivityTestRule.getActivity();
        database = new Database(mainActivity);
        orderQuery = OrderQuery.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }

    @Test
    public void testCreateTableOrder() {
        String sql = "CREATE TABLE IF NOT EXISTS orders(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "quantity INTEGER, " +
                "status INTEGER CHECK (status IN (0, 1)) ,"+
                "food_id INTEGER, " +
                "user_id INTEGER)";
        database.QueryData(sql);
    }

    @Test
    public void updateOrder(){
        List<OrderModel> orderModels = orderQuery.findOrderByUserId(1);
        for(OrderModel item : orderModels){
            item.setFoodID(19);
            orderQuery.update(item);
        }
    }

    @Test
    public void testCreateFood() {
        OrderModel orderModel = new OrderModel();
        orderModel.setQuantity(2);
        orderModel.setStatus(0);
        orderModel.setFoodID(1);
        orderModel.setUserId(1);

        Long orderinsert = orderQuery.insert(orderModel);
        Assert.assertNotNull(orderinsert);
    }

    @Test
    public void testFindByUser(){
        List<OrderModel> orderModels = orderQuery.findOrderByUserId(1);
        System.out.println(orderModels.size());
    }

    @Test
    public void testFindAll(){
        List<OrderModel> orderModels = orderQuery.findAll();
        System.out.println(orderModels.size());
    }
}
