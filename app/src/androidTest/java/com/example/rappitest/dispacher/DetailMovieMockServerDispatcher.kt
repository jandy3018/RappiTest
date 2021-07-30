package com.example.rappitest.dispacher

import com.example.rappitest.utils.API_KEY_TMDB
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class DetailMovieMockServerDispatcher {

    //  valid response dispatcher
    class ResponseDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            if (request.path.equals(
                    "{id}?api_key=$API_KEY_TMDB&language=en-US",
                    true
                )
            ) return MockResponse().setResponseCode(200).setBody(
                "{\n" +
                        "  \"adult\": false,\n" +
                        "  \"backdrop_path\": \"/srYya1ZlI97Au4jUYAktDe3avyA.jpg\",\n" +
                        "  \"belongs_to_collection\": {\n" +
                        "    \"id\": 468552,\n" +
                        "    \"name\": \"Wonder Woman Collection\",\n" +
                        "    \"poster_path\": \"/8AQRfTuTHeFTddZN4IUAqprN8Od.jpg\",\n" +
                        "    \"backdrop_path\": \"/n9KlvCOBFDmSyw3BgNrkUkxMFva.jpg\"\n" +
                        "  },\n" +
                        "  \"budget\": 200000000,\n" +
                        "  \"genres\": [\n" +
                        "    {\n" +
                        "      \"id\": 14,\n" +
                        "      \"name\": \"Fantasy\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 28,\n" +
                        "      \"name\": \"Action\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 12,\n" +
                        "      \"name\": \"Adventure\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"homepage\": \"https://www.warnerbros.com/movies/wonder-woman-1984\",\n" +
                        "  \"id\": 464052,\n" +
                        "  \"imdb_id\": \"tt7126948\",\n" +
                        "  \"original_language\": \"en\",\n" +
                        "  \"original_title\": \"Wonder Woman 1984\",\n" +
                        "  \"overview\": \"A botched store robbery places Wonder Woman in a global battle against a powerful and mysterious ancient force that puts her powers in jeopardy.\",\n" +
                        "  \"popularity\": 1564.092,\n" +
                        "  \"poster_path\": \"/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg\",\n" +
                        "  \"production_companies\": [\n" +
                        "    {\n" +
                        "      \"id\": 9993,\n" +
                        "      \"logo_path\": \"/2Tc1P3Ac8M479naPp1kYT3izLS5.png\",\n" +
                        "      \"name\": \"DC Entertainment\",\n" +
                        "      \"origin_country\": \"US\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 174,\n" +
                        "      \"logo_path\": \"/ky0xOc5OrhzkZ1N6KyUxacfQsCk.png\",\n" +
                        "      \"name\": \"Warner Bros. Pictures\",\n" +
                        "      \"origin_country\": \"US\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 114152,\n" +
                        "      \"logo_path\": null,\n" +
                        "      \"name\": \"The Stone Quarry\",\n" +
                        "      \"origin_country\": \"US\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 128064,\n" +
                        "      \"logo_path\": \"/13F3Jf7EFAcREU0xzZqJnVnyGXu.png\",\n" +
                        "      \"name\": \"DC Films\",\n" +
                        "      \"origin_country\": \"US\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 507,\n" +
                        "      \"logo_path\": \"/z7H707qUWigbjHnJDMfj6QITEpb.png\",\n" +
                        "      \"name\": \"Atlas Entertainment\",\n" +
                        "      \"origin_country\": \"US\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": 429,\n" +
                        "      \"logo_path\": \"/2Tc1P3Ac8M479naPp1kYT3izLS5.png\",\n" +
                        "      \"name\": \"DC Comics\",\n" +
                        "      \"origin_country\": \"US\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"production_countries\": [\n" +
                        "    {\n" +
                        "      \"iso_3166_1\": \"US\",\n" +
                        "      \"name\": \"United States of America\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"release_date\": \"2020-12-16\",\n" +
                        "  \"revenue\": 159533000,\n" +
                        "  \"runtime\": 151,\n" +
                        "  \"spoken_languages\": [\n" +
                        "    {\n" +
                        "      \"english_name\": \"English\",\n" +
                        "      \"iso_639_1\": \"en\",\n" +
                        "      \"name\": \"English\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"Released\",\n" +
                        "  \"tagline\": \"A new era of wonder begins.\",\n" +
                        "  \"title\": \"Wonder Woman 1984\",\n" +
                        "  \"video\": false,\n" +
                        "  \"vote_average\": 6.9,\n" +
                        "  \"vote_count\": 4182\n" +
                        "}"
            )
            return MockResponse().setResponseCode(400)
        }
    }

    //  error dispatcher
    class ErrorDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse = MockResponse().setResponseCode(400)
    }
}