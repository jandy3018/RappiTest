package com.example.rappitest.ui

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.rappitest.R
import com.example.rappitest.dispacher.DetailMovieMockServerDispatcher
import com.example.rappitest.movies.view.DetailMovieActivity
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
class DetailMovieActivityTest {

    private lateinit var mockServer: MockWebServer

    @Rule
    @JvmField
    val rule = ActivityTestRule(DetailMovieActivity::class.java, false, false)

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start(8080)
    }

    @After
    fun tearDown() = mockServer.shutdown()

    @Test
    fun happyCase() {
        mockServer.dispatcher = DetailMovieMockServerDispatcher.ResponseDispatcher()
        val intent = Intent(InstrumentationRegistry.getInstrumentation().targetContext, DetailMovieActivity::class.java)
        intent.putExtra(
            BASE_URL_MOVIE_DB,
            mockServer.url("{id}?api_key=$API_KEY_TMDB&language=en-US").toString()
        )
        rule.launchActivity(intent)

        //  snackbar_action view - not visible on success response
        Espresso.onView(ViewMatchers.withId(R.id.snackbar_action))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //  action_mode_bar_stub view - not visible on success response
        Espresso.onView(ViewMatchers.withId(R.id.action_mode_bar_stub))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun unHappyCase() {
        mockServer.dispatcher = DetailMovieMockServerDispatcher.ErrorDispatcher()
        val intent = Intent(InstrumentationRegistry.getInstrumentation().targetContext, DetailMovieActivity::class.java)
        intent.putExtra(
            BASE_URL_MOVIE_DB,
            mockServer.url("I'm a mock URL").toString()
        )
        rule.launchActivity(intent)
        //  rv_genre has not childs - visible on success response
        Espresso.onView(ViewMatchers.withId(R.id.rv_genre))
            .check(ViewAssertions.matches(ViewMatchers.hasMinimumChildCount(0)))
    }
}