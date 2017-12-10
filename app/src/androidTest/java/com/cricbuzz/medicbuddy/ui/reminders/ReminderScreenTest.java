package com.cricbuzz.medicbuddy.ui.reminders;

/**
 * Created by Rahil on 10/20/2017.
 */

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cricbuzz.medicbuddy.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Tests for the reminder screen, the main screen which contains a list of all reminders.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ReminderScreenTest {

    @Rule
    public ActivityTestRule<RemindersActivity> mNotesActivityTestRule =
            new ActivityTestRule<>(RemindersActivity.class);

    @Test
    public void clickAddReminderButton_opensNewReminderUi() throws Exception {
        // Click on the new reminder button
        onView(withId(R.id.fab)).perform(click());

        // Check if the new reminder screen is displayed
        onView(withText(R.string.set_reminder)).check(matches(isDisplayed()));
    }
}
