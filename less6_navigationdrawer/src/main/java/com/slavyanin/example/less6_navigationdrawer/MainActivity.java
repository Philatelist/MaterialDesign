package com.slavyanin.example.less6_navigationdrawer;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity {

    private Drawer drawerResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initializeNavigationDrawer(toolbar);
    }

    //Метод позволяет хардварной кнопкой не выходить из приложения, а убрать Drawer
    @Override
    public void onBackPressed() {
        if (drawerResult != null && drawerResult.isDrawerOpen()) {
            drawerResult.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private void initializeNavigationDrawer(Toolbar toolbar) {
        AccountHeader accHeaderResult = createAccountHeader();

        drawerResult = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withSliderBackgroundColor(Color.CYAN)
                .withAccountHeader(accHeaderResult)
                .addDrawerItems(
                        initializeDrawerItems()
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intent);
                        return false;
                    }
                })
                .build();

    }

    @NonNull
    private IDrawerItem[] initializeDrawerItems() {
        return new IDrawerItem[]{new PrimaryDrawerItem()
                .withName(R.string.nav_menu_item_home)
                .withIdentifier(1)
                .withIcon(R.drawable.ic_home_black_18dp),
                new DividerDrawerItem(),
                new SecondaryDrawerItem()
                        .withName(R.string.nav_menu_item_contacts)
                        .withIcon(R.drawable.ic_perm_contact_calendar_black_18dp),
                new SecondaryDrawerItem()
                        .withName(R.string.item1)
                        .withIcon(R.drawable.ic_3d_rotation_black_18dp),
                new SecondaryDrawerItem()
                        .withName(R.string.item2)
                        .withIcon(R.drawable.ic_accessibility_black_18dp)};
    }

    private AccountHeader createAccountHeader() {
        IProfile profile = new ProfileDrawerItem()
                .withName("Alexnder")
                .withEmail("a.usoff@gmail.com")
                .withIcon(R.drawable.ic_verified_user_black_18dp);

        return new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.wall)
                .addProfiles(profile)
                .build();
    }
}
