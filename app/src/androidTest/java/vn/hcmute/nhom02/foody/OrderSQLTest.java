package vn.hcmute.nhom02.foody;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import vn.hcmute.nhom02.foody.activity.SplashActivity;
import vn.hcmute.nhom02.foody.database.Database;
import vn.hcmute.nhom02.foody.database.FoodQuery;

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

    @Before
    public void setUp() throws Exception {
        mainActivity = mActivityTestRule.getActivity();
        database = new Database(mainActivity);
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }

    @Test
    public void testCreateTableFood() {
        String sql = "create table if not exists orders (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "quantity INTEGER, " +
                "status boolean,"+
                "food_id INTEGER NOT NULL, " +
                "user_id INTEGER NOT NULL," +
                "FOREIGN KEY (food_id) REFERENCES food(id),"+
                "FOREIGN KEY (user_id) REFERENCES user(id)"+
                ")";
        database.QueryData(sql);
    }
}
