package com.example.oblaco;

import java.util.ArrayList;
import java.util.List;

public class UserStaticInfo {

    public static List<User> users = new ArrayList<>();

    public final static String POSITION = "position";

    public UserStaticInfo() {
        if (users.size() == 0)
            AddUsersInList();
    }

    private void AddUsersInList() {
        users.add(new User("Тумба", "Я дединсайд мне 9 лет и я хочу в психокидс", 9, 0));
        users.add(new User("Виктор", "Представитель группы лес", 21, 1));
        users.add(new User("Тед", "У меня нет рта, но я должен кричать", 158, 2));
        users.add(new User("Текила Сансет", "Грусть FM", 43, 0));
    }
}