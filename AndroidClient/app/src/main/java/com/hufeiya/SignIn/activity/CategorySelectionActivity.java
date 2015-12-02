/*
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hufeiya.SignIn.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hufeiya.SignIn.R;
import com.hufeiya.SignIn.fragment.CategorySelectionFragment;
import com.hufeiya.SignIn.helper.ApiLevelHelper;
import com.hufeiya.SignIn.helper.PreferencesHelper;
import com.hufeiya.SignIn.jsonObject.JsonUser;
import com.hufeiya.SignIn.model.User;
import com.hufeiya.SignIn.net.AsyncHttpHelper;
import com.hufeiya.SignIn.widget.AvatarView;


public class CategorySelectionActivity extends AppCompatActivity {

    private static final String EXTRA_PLAYER = "player";
    private User user;

    public static void start(Activity activity, User user, ActivityOptionsCompat options) {
        Intent starter = getStartIntent(activity, user);
        ActivityCompat.startActivity(activity, starter, options.toBundle());
    }

    public static void start(Context context, User user) {
        Intent starter = getStartIntent(context, user);
        context.startActivity(starter);
    }

    @NonNull
    static Intent getStartIntent(Context context, User user) {
        Intent starter = new Intent(context, CategorySelectionActivity.class);
        starter.putExtra(EXTRA_PLAYER, user);
        return starter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category_selection);
        user = getIntent().getParcelableExtra(EXTRA_PLAYER);
        if (!PreferencesHelper.isSignedIn(this) && user != null) {
            PreferencesHelper.writeToPreferences(this, user);
        }
        setUpToolbar(user);
        if (savedInstanceState == null) {
            attachCategoryGridFragment();
        } else {
            setProgressBarVisibility(View.GONE);
        }
        supportPostponeEnterTransition();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setUpToolbar(User user) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_player);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final AvatarView avatarView = (AvatarView) toolbar.findViewById(R.id.avatar);
        avatarView.setAvatar(user.getAvatar().getDrawableId());
        //noinspection PrivateResource
        ((TextView) toolbar.findViewById(R.id.title)).setText(getDisplayName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_category, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.category_container);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out: {
                signOut();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NewApi")
    private void signOut() {
        PreferencesHelper.signOut(this);
        if (ApiLevelHelper.isAtLeast(Build.VERSION_CODES.LOLLIPOP)) {
            getWindow().setExitTransition(TransitionInflater.from(this)
                    .inflateTransition(R.transition.category_enter));
        }
        SignInActivity.start(this, false);
        ActivityCompat.finishAfterTransition(this);
    }

    private String getDisplayName() {
        JsonUser user = AsyncHttpHelper.user;
        if (user == null){
            //TODO
            return null;
        }
        String type;
        if (user.getUserType()){
            type = "同学";
        }else{
            type = "老师";
        }
        return  user.getUsername() + "  " + type;
    }

    private void attachCategoryGridFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment fragment = supportFragmentManager.findFragmentById(R.id.category_container);
        if (!(fragment instanceof CategorySelectionFragment)) {
            fragment = CategorySelectionFragment.newInstance();
        }
        supportFragmentManager.beginTransaction()
                .replace(R.id.category_container, fragment)
                .commit();
        setProgressBarVisibility(View.GONE);
    }

    private void setProgressBarVisibility(int visibility) {
        findViewById(R.id.progress).setVisibility(visibility);
    }

    public User getUser(){
        return user;
    }
}

