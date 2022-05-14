package vn.hcmute.nhom02.foody;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import vn.hcmute.nhom02.foody.Domain.CategoryModel;
import vn.hcmute.nhom02.foody.activity.MainActivity;
import vn.hcmute.nhom02.foody.activity.SplashActivity;
import vn.hcmute.nhom02.foody.common.Constants;
import vn.hcmute.nhom02.foody.database.CategoryQuery;
import vn.hcmute.nhom02.foody.database.Database;
import vn.hcmute.nhom02.foody.database.ICategoryQuery;
import vn.hcmute.nhom02.foody.signup.User;

/**
 * Create by: IntelliJ IDEA
 * User     : trongnt
 * Date     : Fri, 5/13/2022
 * Time     : 17:24
 * Filename : TestCategory
 */
public class TestCategory {
    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<SplashActivity>(SplashActivity.class);


    private SplashActivity mainActivity = null;

//    private Database database = null;

    private ICategoryQuery categoryQuery;

    @Before
    public void setUp() throws Exception {
        mainActivity = mActivityTestRule.getActivity();
//        database = new Database(mainActivity);
        categoryQuery = CategoryQuery.getInstance();
    }

    @After
    public void testDown() throws Exception {
        mainActivity = null;
    }

    @Test
    public void createCategory(){
        CategoryModel categoryModel = new CategoryModel(null, "Food", "DA");
        Long insert = categoryQuery.insert(categoryModel);
    }


    @Test
    public void testFindByCode() {
        CategoryModel testDb = categoryQuery.findByCode("DA");
        System.out.println(testDb);
    }

    @Test
    public void testFindAllCategory() {
        List<CategoryModel> categoryModels = new ArrayList<>();
        List<CategoryModel> results = categoryQuery.findAllCategory();
        if(results !=null)
            categoryModels.addAll(results);
        System.out.println(categoryModels);
    }


}
