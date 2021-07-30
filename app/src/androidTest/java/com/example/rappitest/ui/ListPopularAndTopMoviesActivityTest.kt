package com.example.rappitest.ui

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.rappitest.R
import com.example.rappitest.dispacher.MoviesListMockServerDispatcher
import com.example.rappitest.movies.view.ListPopularAndTopMoviesActivity
import com.example.rappitest.utils.API_KEY_TMDB
import com.example.rappitest.utils.BASE_URL_MOVIE_DB
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListPopularAndTopMoviesActivityTest {

    private lateinit var mockServer: MockWebServer

    @Rule
    @JvmField
    val rule = ActivityTestRule(ListPopularAndTopMoviesActivity::class.java, false, false)

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start(8080)
    }

    @After
    fun tearDown() = mockServer.shutdown()

    @Test
    fun happyCase() {
        mockServer.dispatcher = MoviesListMockServerDispatcher.ResponseDispatcher()
        val intent = Intent(InstrumentationRegistry.getInstrumentation().targetContext, ListPopularAndTopMoviesActivity::class.java)
        intent.putExtra(
            BASE_URL_MOVIE_DB,
            mockServer.url("now_playing?language=en-US&page=undefined&api_key=$API_KEY_TMDB").toString()
        )
        rule.launchActivity(intent)

        //  rv_top_rated has childs - visible on success response
        Espresso.onView(ViewMatchers.withId(R.id.rv_top_rated))
            .check(ViewAssertions.matches(ViewMatchers.hasMinimumChildCount(1)))

        //  rv_most_popular has childs  - visible on success response
        Espresso.onView(ViewMatchers.withId(R.id.rv_most_popular))
            .check(ViewAssertions.matches(ViewMatchers.hasMinimumChildCount(1)))

        //  ProgressBar view - not visible on success response
        Espresso.onView(ViewMatchers.withId(R.id.pb_bar))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))

        //  Snackbar view - not visible on success response
        Espresso.onView(ViewMatchers.withId(R.id.action_mode_bar_stub))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun unHappyCase() {
        mockServer.dispatcher = MoviesListMockServerDispatcher.ErrorDispatcher()
        val intent = Intent(InstrumentationRegistry.getInstrumentation().targetContext, ListPopularAndTopMoviesActivity::class.java)
        intent.putExtra(
            BASE_URL_MOVIE_DB,
            mockServer.url("I'm a mock URL").toString()
        )
        rule.launchActivity(intent)
        //  title text offline visible
        Espresso.onView(ViewMatchers.withId(R.id.tv_title_toolbar))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.title_toolbar_offline)))
    }
}