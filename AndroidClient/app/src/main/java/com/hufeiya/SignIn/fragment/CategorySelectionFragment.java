/*
 * Copyright 2015 Google Inc.
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

package com.hufeiya.SignIn.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hufeiya.SignIn.R;
import com.hufeiya.SignIn.activity.CategorySelectionActivity;
import com.hufeiya.SignIn.activity.QuizActivity;
import com.hufeiya.SignIn.adapter.CategoryAdapter;
import com.hufeiya.SignIn.helper.TransitionHelper;
import com.hufeiya.SignIn.model.Category;
import com.hufeiya.SignIn.model.JsonAttributes;
import com.hufeiya.SignIn.model.User;
import com.hufeiya.SignIn.net.AsyncHttpHelper;
import com.hufeiya.SignIn.widget.OffsetDecoration;


public class CategorySelectionFragment extends Fragment {

    private static final int REQUEST_CATEGORY = 0x2300;
    private CategoryAdapter mAdapter;
    public SwipeRefreshLayout swipeRefreshLayout;

    public static CategorySelectionFragment newInstance() {
        return new CategorySelectionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        final User user = ((CategorySelectionActivity)getActivity()).getUser();
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                AsyncHttpHelper.refrash(user.getPhone(), user.getPass(), CategorySelectionFragment.this);
            }
        });
        setUpQuizGrid((RecyclerView) view.findViewById(R.id.categories));
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        AsyncHttpHelper.refrash(user.getPhone(), user.getPass(), CategorySelectionFragment.this);
        super.onViewCreated(view, savedInstanceState);
    }

    private void setUpQuizGrid(RecyclerView categoriesView) {
        final int spacing = getContext().getResources()
                .getDimensionPixelSize(R.dimen.spacing_nano);
        categoriesView.addItemDecoration(new OffsetDecoration(spacing));
        mAdapter = new CategoryAdapter(getActivity());
        mAdapter.setOnItemClickListener(
                new CategoryAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                        Activity activity = getActivity();
                        startQuizActivityWithTransition(activity,
                                v.findViewById(R.id.category_title),
                                mAdapter.getItem(position));
                    }
                });
        categoriesView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        getActivity().supportStartPostponedEnterTransition();
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CATEGORY && resultCode == R.id.solved) {
            mAdapter.notifyItemChanged(data.getStringExtra(JsonAttributes.ID));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void startQuizActivityWithTransition(Activity activity, View toolbar,
                                                 Category category) {

        final Pair[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, false,
                new Pair<>(toolbar, activity.getString(R.string.transition_toolbar)));
        @SuppressWarnings("unchecked")
        ActivityOptionsCompat sceneTransitionAnimation = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, pairs);

        // Start the activity with the participants, animating from one to the other.
        final Bundle transitionBundle = sceneTransitionAnimation.toBundle();
        Intent startIntent = QuizActivity.getStartIntent(activity, category);
        ActivityCompat.startActivityForResult(activity,
                startIntent,
                REQUEST_CATEGORY,
                transitionBundle);
    }
    public void toastLoginFail(String failType){
        if (failType.equals("account")){
            Toast.makeText(getActivity(), "蛤 (@[]@!!),你需要重新登录", Toast.LENGTH_SHORT).show();
        }else if(failType.equals("unknown")){
            Toast.makeText(getActivity(),"刷新失败, ( ° △ °|||)︴ ,主人请检查下网络",Toast.LENGTH_SHORT).show();
        }else if (failType.equals("json")){
            Toast.makeText(getActivity(),"json解析错误,这是个bug额(⊙o⊙)…",Toast.LENGTH_SHORT).show();
        }

    }
    public CategoryAdapter getmAdapter(){
        return mAdapter;
    }
}
