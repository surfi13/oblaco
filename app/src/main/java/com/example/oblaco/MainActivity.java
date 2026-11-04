package com.example.oblaco;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Context context;
    LayoutInflater layoutInflater;

    static UserListAdapter userListAdapter;

    static FrameLayout UserPanel;
    static TextView NameTextView, StateTextView, AgeTextView;

    int positionActiveUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutInflater = LayoutInflater.from(this);

        new UserStaticInfo();
        Init();

        ListView listView = findViewById(R.id.listView);
        userListAdapter = new UserListAdapter();
        listView.setAdapter(userListAdapter);
    }

    private void Init() {
        UserPanel = findViewById(R.id.userPanel);
        NameTextView = findViewById(R.id.NameTextView);
        StateTextView = findViewById(R.id.StateTextView);
        AgeTextView = findViewById(R.id.AgeTextView);
    }

    private void UserVisibility(boolean visible) {
        if (visible)
            UserPanel.setVisibility(View.VISIBLE);
        else
            UserPanel.setVisibility(View.GONE);
    }

    private static void InitPanel(User item) {
        NameTextView.setText(item.getName());
        StateTextView.setText(item.getState());
        AgeTextView.setText(String.valueOf(item.getAge()));
    }

    public void BackToList(View view) {
        UserVisibility(false);
    }

    public void EditUser(View view) {
        GoToUserProfile(positionActiveUser);
    }

    public void GoToUserProfile(int position) {
        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra(UserStaticInfo.POSITION, position);
        startActivity(intent);
    }

    public static void UpdateListAndUserPanel(User user) {
        userListAdapter.notifyDataSetChanged();
        InitPanel(user);
    }

    private class UserListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return UserStaticInfo.users.size();
        }

        @Override
        public User getItem(int position) {
            return UserStaticInfo.users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View currentView, ViewGroup parent) {
            User currentUser = getItem(position);
            currentView = layoutInflater.inflate(R.layout.item_user, parent, false);

            TextView nameView = currentView.findViewById(R.id.NameTextView);
            TextView stateView = currentView.findViewById(R.id.StateTextView);
            FrameLayout StateRound = currentView.findViewById(R.id.StateRound);

            nameView.setText(currentUser.getName());
            stateView.setText(currentUser.getState());

            switch (currentUser.getStateSignal())
            {
                case 0:
                    StateRound.setBackgroundResource(R.drawable.back_offline);
                    break;
                case 1:
                    StateRound.setBackgroundResource(R.drawable.back_online);
                    break;
                case 2:
                    StateRound.setBackgroundResource(R.drawable.back_departed);
                    break;
            }

            currentView.setOnClickListener((view) -> {
                positionActiveUser = position;
                InitPanel(getItem(position));
                UserVisibility(true);
            });

            return currentView;
        }
    }
}