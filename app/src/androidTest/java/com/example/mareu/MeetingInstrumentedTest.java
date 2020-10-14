package com.example.mareu;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.mareu.Controler.Activity.ListMeetingActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MeetingInstrumentedTest {

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityTestRule = new ActivityTestRule<>(ListMeetingActivity.class);

    @Before
    public void setUp(){
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.delete_button),
                        childAtPosition(
                                allOf(withId(R.id.fragment_layout),
                                        childAtPosition(
                                                withId(R.id.list_meetings),
                                                0)),
                                10),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        onView(withId(R.id.add_metting_button)).perform(click());
        onView((withId(R.id.topic_edit))).perform(replaceText("Réunion Test"), closeSoftKeyboard());
        onView(withId(R.id.mail_edit)).perform(replaceText("Luca"), closeSoftKeyboard());
        onView((withId(R.id.add_mail_btn))).perform(click());
        onView((withId(R.id.date_btn))).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 10, 1));
        onView((withText(android.R.string.ok))).perform(click());
        onView((withId(R.id.hour_start_btn))).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(12, 0));
        onView((withText(android.R.string.ok))).perform(click());
        onView((withId(R.id.hour_end_btn))).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(14, 0));
        onView((withText(android.R.string.ok))).perform(click());
        onView(withId(R.id.room_spinner)).perform(click());
        onView(withText("Salle 1")).perform(click());
        onView((withId(R.id.valid_button))).perform(click());
    }

    @Test
    public void addMeetingAndCheckDetailTest() {
        onView(withId(R.id.list_meetings)).check(matches(isDisplayed()));
        onView(withId(R.id.list_meetings)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.detail_topic_text)).check(matches(isDisplayed()));
        onView(withId(R.id.detail_topic_text)).check(matches(withText("Réunion Test")));
        onView(withId(R.id.back_btn)).perform(click());
    }

    @Test
    public void availabilityAndDeleteMeetingTest() {
        onView(withId(R.id.add_metting_button)).perform(click());
        onView((withId(R.id.topic_edit))).perform(replaceText("Réunion A"), closeSoftKeyboard());
        onView(withId(R.id.mail_edit)).perform(replaceText("Luca"), closeSoftKeyboard());
        onView((withId(R.id.add_mail_btn))).perform(click());
        onView((withId(R.id.date_btn))).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 10, 1));
        onView((withText(android.R.string.ok))).perform(click());
        onView((withId(R.id.hour_start_btn))).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(12, 0));
        onView((withText(android.R.string.ok))).perform(click());
        onView((withId(R.id.hour_end_btn))).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(14, 0));
        onView((withText(android.R.string.ok))).perform(click());
        onView(withId(R.id.room_spinner)).perform(click());
        onView(withText("Salle 1")).perform(click());
        onView((withId(R.id.valid_button))).perform(click());
        onView(withId(R.id.valid_button)).check(matches(isDisplayed()));
        onView(withId(R.id.room_spinner)).perform(click());
        onView(withText("Salle 2")).perform(click());
        onView((withId(R.id.valid_button))).perform(click());
        onView(withId(R.id.list_meetings));
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.delete_button),
                        childAtPosition(
                                allOf(withId(R.id.fragment_layout),
                                        childAtPosition(
                                                withId(R.id.list_meetings),
                                                0)),
                                10),
                        isDisplayed()));
        appCompatImageButton.perform(click());
    }

    @Test
    public void filterByDateMeetingTest() {
        onView(withContentDescription("Plus d'options")).perform(click());
        onView(withText("filtrer par date")).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 10, 2));
        onView((withText(android.R.string.ok))).perform(click());
        onView(withContentDescription("Plus d'options")).perform(click());
        onView(withText("filtrer par date")).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 10, 1));
        onView((withText(android.R.string.ok))).perform(click());
        onView(withText("Réunion Test")).check(matches(isDisplayed()));
    }

    @Test
    public void filterByRoomMeetingTest() {
        onView(withContentDescription("Plus d'options")).perform(click());
        onView(withText("filtrer par salle")).perform(click());
        onView(withText("Salle 2")).perform(click());
        onView(withContentDescription("Plus d'options")).perform(click());
        onView(withText("filtrer par salle")).perform(click());
        onView(withText("Salle 1")).perform(click());
        onView(withText("Réunion Test")).check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}