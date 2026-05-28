package com.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.AbstractAction;

public class CategoryMapper {

    public static final Map<Category, Integer> TARGET_SPACE = new HashMap<>();

    static {
        TARGET_SPACE.put(Category.Technology, 0);
        TARGET_SPACE.put(Category.Geography, 1);
        TARGET_SPACE.put(Category.History, 2);
        TARGET_SPACE.put(Category.People, 3);
        TARGET_SPACE.put(Category.Mathematics, 4);
        TARGET_SPACE.put(Category.Religion, 5);
        TARGET_SPACE.put(Category.Philosophy, 6);
        TARGET_SPACE.put(Category.Society, 7);
        TARGET_SPACE.put(Category.Nature, 8);
        TARGET_SPACE.put(Category.Culture, 9);
        TARGET_SPACE.put(Category.Health, 10);
        TARGET_SPACE.put(Category.HumanActivities, 11);
    }

    public static Category map(String category) {

        String c = category.toLowerCase();

        if (c.contains("programming")
                || c.contains("software")
                || c.contains("computer")
                || c.contains("technology")
                || c.contains("jvm")
                || c.contains("compiled")
                || c.contains("object-oriented")) {
            return Category.Technology;
        }

        if (c.contains("city")
                || c.contains("country")
                || c.contains("geography")) {
            return Category.Geography;
        }

        if (c.contains("history")
                || c.contains("century")
                || c.contains("war")) {
            return Category.History;
        }

        if (c.contains("people")
                || c.contains("births")
                || c.contains("living people")) {
            return Category.People;
        }

        if (c.contains("mathematics")
                || c.contains("algorithm")) {
            return Category.Mathematics;
        }

        if (c.contains("religion")) {
            return Category.Religion;
        }
        if (c.contains("philosophy")) {
            return Category.Philosophy;
        }
        if (c.contains("society")) {
            return Category.Society;
        }
        if (c.contains("nature") || c.contains("biology")) {
            return Category.Nature;
        }
        if (c.contains("culture") || c.contains("art") || c.contains("film")) {
            return Category.Culture;
        }

        return null;
    }

}

enum Category {
    Culture,
    Geography,
    Health,
    History,
    HumanActivities,
    Mathematics,
    Nature,
    People,
    Philosophy,
    Religion,
    Society,
    Technology
}
