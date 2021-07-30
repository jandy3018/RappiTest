package com.example.rappitest.dispacher

import com.example.rappitest.utils.API_KEY_TMDB
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MoviesListMockServerDispatcher {
    //  valid response dispatcher
    class ResponseDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            if (request.path.equals(
                    "now_playing?language=en-US&page=undefined&api_key=$API_KEY_TMDB",
                    true
                )
            ) return MockResponse().setResponseCode(200).setBody(
                "{\n" +
                        "  \"dates\": {\n" +
                        "    \"maximum\": \"2021-03-18\",\n" +
                        "    \"minimum\": \"2021-01-29\"\n" +
                        "  },\n" +
                        "  \"page\": 1,\n" +
                        "  \"results\": [\n" +
                        "    {\n" +
                        "      \"adult\": false,\n" +
                        "      \"backdrop_path\": \"/rcUcYzGGicDvhDs58uM44tJKB9F.jpg\",\n" +
                        "      \"genre_ids\": [\n" +
                        "        16,\n" +
                        "        12,\n" +
                        "        14,\n" +
                        "        10751,\n" +
                        "        28,\n" +
                        "        18\n" +
                        "      ],\n" +
                        "      \"id\": 527774,\n" +
                        "      \"original_language\": \"en\",\n" +
                        "      \"original_title\": \"Raya and the Last Dragon\",\n" +
                        "      \"overview\": \"Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.\",\n" +
                        "      \"popularity\": 4717.087,\n" +
                        "      \"poster_path\": \"/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg\",\n" +
                        "      \"release_date\": \"2021-03-03\",\n" +
                        "      \"title\": \"Raya and the Last Dragon\",\n" +
                        "      \"video\": false,\n" +
                        "      \"vote_average\": 8.6,\n" +
                        "      \"vote_count\": 956\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"adult\": false,\n" +
                        "      \"backdrop_path\": \"/fev8UFNFFYsD5q7AcYS8LyTzqwl.jpg\",\n" +
                        "      \"genre_ids\": [\n" +
                        "        28,\n" +
                        "        35,\n" +
                        "        10751,\n" +
                        "        16,\n" +
                        "        12\n" +
                        "      ],\n" +
                        "      \"id\": 587807,\n" +
                        "      \"original_language\": \"en\",\n" +
                        "      \"original_title\": \"Tom & Jerry\",\n" +
                        "      \"overview\": \"Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can’t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse.\",\n" +
                        "      \"popularity\": 2564.583,\n" +
                        "      \"poster_path\": \"/6KErczPBROQty7QoIsaa6wJYXZi.jpg\",\n" +
                        "      \"release_date\": \"2021-02-11\",\n" +
                        "      \"title\": \"Tom & Jerry\",\n" +
                        "      \"video\": false,\n" +
                        "      \"vote_average\": 7.7,\n" +
                        "      \"vote_count\": 767\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"adult\": false,\n" +
                        "      \"backdrop_path\": \"/8tNX8s3j1O0eqilOQkuroRLyOZA.jpg\",\n" +
                        "      \"genre_ids\": [\n" +
                        "        14,\n" +
                        "        28,\n" +
                        "        12\n" +
                        "      ],\n" +
                        "      \"id\": 458576,\n" +
                        "      \"original_language\": \"en\",\n" +
                        "      \"original_title\": \"Monster Hunter\",\n" +
                        "      \"overview\": \"A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.\",\n" +
                        "      \"popularity\": 1883.895,\n" +
                        "      \"poster_path\": \"/1UCOF11QCw8kcqvce8LKOO6pimh.jpg\",\n" +
                        "      \"release_date\": \"2020-12-03\",\n" +
                        "      \"title\": \"Monster Hunter\",\n" +
                        "      \"video\": false,\n" +
                        "      \"vote_average\": 7.3,\n" +
                        "      \"vote_count\": 1080\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"adult\": false,\n" +
                        "      \"backdrop_path\": \"/srYya1ZlI97Au4jUYAktDe3avyA.jpg\",\n" +
                        "      \"genre_ids\": [\n" +
                        "        14,\n" +
                        "        28,\n" +
                        "        12\n" +
                        "      ],\n" +
                        "      \"id\": 464052,\n" +
                        "      \"original_language\": \"en\",\n" +
                        "      \"original_title\": \"Wonder Woman 1984\",\n" +
                        "      \"overview\": \"A botched store robbery places Wonder Woman in a global battle against a powerful and mysterious ancient force that puts her powers in jeopardy.\",\n" +
                        "      \"popularity\": 1564.092,\n" +
                        "      \"poster_path\": \"/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg\",\n" +
                        "      \"release_date\": \"2020-12-16\",\n" +
                        "      \"title\": \"Wonder Woman 1984\",\n" +
                        "      \"video\": false,\n" +
                        "      \"vote_average\": 6.9,\n" +
                        "      \"vote_count\": 4178\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"adult\": false,\n" +
                        "      \"backdrop_path\": \"/vfuzELmhBjBTswXj2Vqxnu5ge4g.jpg\",\n" +
                        "      \"genre_ids\": [\n" +
                        "        53,\n" +
                        "        80\n" +
                        "      ],\n" +
                        "      \"id\": 602269,\n" +
                        "      \"original_language\": \"en\",\n" +
                        "      \"original_title\": \"The Little Things\",\n" +
                        "      \"overview\": \"Deputy Sheriff Joe \\\"Deke\\\" Deacon joins forces with Sgt. Jim Baxter to search for a serial killer who's terrorizing Los Angeles. As they track the culprit, Baxter is unaware that the investigation is dredging up echoes of Deke's past, uncovering disturbing secrets that could threaten more than his case.\",\n" +
                        "      \"popularity\": 944.103,\n" +
                        "      \"poster_path\": \"/c7VlGCCgM9GZivKSzBgzuOVxQn7.jpg\",\n" +
                        "      \"release_date\": \"2021-01-28\",\n" +
                        "      \"title\": \"The Little Things\",\n" +
                        "      \"video\": false,\n" +
                        "      \"vote_average\": 6.5,\n" +
                        "      \"vote_count\": 535\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"adult\": false,\n" +
                        "      \"backdrop_path\": \"/u5WUCO6irZoq27qbYYrtLUrCGDV.jpg\",\n" +
                        "      \"genre_ids\": [\n" +
                        "        27,\n" +
                        "        53\n" +
                        "      ],\n" +
                        "      \"id\": 630586,\n" +
                        "      \"original_language\": \"en\",\n" +
                        "      \"original_title\": \"Wrong Turn\",\n" +
                        "      \"overview\": \"Jen and a group of friends set out to hike the Appalachian Trail. Despite warnings to stick to the trail, the hikers stray off course—and cross into land inhabited by The Foundation, a hidden community of mountain dwellers who use deadly means to protect their way of life.\",\n" +
                        "      \"popularity\": 846.837,\n" +
                        "      \"poster_path\": \"/4U1SBHmwHkNA0eHZ2n1CuiC1K1g.jpg\",\n" +
                        "      \"release_date\": \"2021-01-26\",\n" +
                        "      \"title\": \"Wrong Turn\",\n" +
                        "      \"video\": false,\n" +
                        "      \"vote_average\": 6.4,\n" +
                        "      \"vote_count\": 229\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"adult\": false,\n" +
                        "      \"backdrop_path\": \"/3ombg55JQiIpoPnXYb2oYdr6DtP.jpg\",\n" +
                        "      \"genre_ids\": [\n" +
                        "        878,\n" +
                        "        28\n" +
                        "      ],\n" +
                        "      \"id\": 560144,\n" +
                        "      \"original_language\": \"en\",\n" +
                        "      \"original_title\": \"Skylines\",\n" +
                        "      \"overview\": \"When a virus threatens to turn the now earth-dwelling friendly alien hybrids against humans, Captain Rose Corley must lead a team of elite mercenaries on a mission to the alien world in order to save what's left of humanity.\",\n" +
                        "      \"popularity\": 679.771,\n" +
                        "      \"poster_path\": \"/2W4ZvACURDyhiNnSIaFPHfNbny3.jpg\",\n" +
                        "      \"release_date\": \"2020-10-25\",\n" +
                        "      \"title\": \"Skylines\",\n" +
                        "      \"video\": false,\n" +
                        "      \"vote_average\": 6.1,\n" +
                        "      \"vote_count\": 213\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"adult\": false,\n" +
                        "      \"backdrop_path\": \"/fX8e94MEWSuTJExndVYxKsmA4Hw.jpg\",\n" +
                        "      \"genre_ids\": [\n" +
                        "        28,\n" +
                        "        12,\n" +
                        "        80\n" +
                        "      ],\n" +
                        "      \"id\": 604822,\n" +
                        "      \"original_language\": \"zh\",\n" +
                        "      \"original_title\": \"急先锋\",\n" +
                        "      \"overview\": \"Covert security company Vanguard is the last hope of survival for an accountant after he is targeted by the world's deadliest mercenary organization.\",\n" +
                        "      \"popularity\": 636.728,\n" +
                        "      \"poster_path\": \"/vYvppZMvXYheYTWVd8Rnn9nsmNp.jpg\",\n" +
                        "      \"release_date\": \"2020-09-30\",\n" +
                        "      \"title\": \"Vanguard\",\n" +
                        "      \"video\": false,\n" +
                        "      \"vote_average\": 6.4,\n" +
                        "      \"vote_count\": 227\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"adult\": false,\n" +
                        "      \"backdrop_path\": \"/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg\",\n" +
                        "      \"genre_ids\": [\n" +
                        "        10751,\n" +
                        "        16,\n" +
                        "        35,\n" +
                        "        18,\n" +
                        "        10402,\n" +
                        "        14\n" +
                        "      ],\n" +
                        "      \"id\": 508442,\n" +
                        "      \"original_language\": \"en\",\n" +
                        "      \"original_title\": \"Soul\",\n" +
                        "      \"overview\": \"Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.\",\n" +
                        "      \"popularity\": 648.303,\n" +
                        "      \"poster_path\": \"/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg\",\n" +
                        "      \"release_date\": \"2020-12-25\",\n" +
                        "      \"title\": \"Soul\",\n" +
                        "      \"video\": false,\n" +
                        "      \"vote_average\": 8.3,\n" +
                        "      \"vote_count\": 5192\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"adult\": false,\n" +
                        "      \"backdrop_path\": \"/cjaOSjsjV6cl3uXdJqimktT880L.jpg\",\n" +
                        "      \"genre_ids\": [\n" +
                        "        10751,\n" +
                        "        14,\n" +
                        "        16,\n" +
                        "        35\n" +
                        "      ],\n" +
                        "      \"id\": 529203,\n" +
                        "      \"original_language\": \"en\",\n" +
                        "      \"original_title\": \"The Croods: A New Age\",\n" +
                        "      \"overview\": \"Searching for a safer habitat, the prehistoric Crood family discovers an idyllic, walled-in paradise that meets all of its needs. Unfortunately, they must also learn to live with the Bettermans -- a family that's a couple of steps above the Croods on the evolutionary ladder. As tensions between the new neighbors start to rise, a new threat soon propels both clans on an epic adventure that forces them to embrace their differences, draw strength from one another, and survive together.\",\n" +
                        "      \"popularity\": 608.034,\n" +
                        "      \"poster_path\": \"/tbVZ3Sq88dZaCANlUcewQuHQOaE.jpg\",\n" +
                        "      \"release_date\": \"2020-11-25\",\n" +
                        "      \"title\": \"The Croods: A New Age\",\n" +
                        "      \"video\": false,\n" +
                        "      \"vote_average\": 7.5,\n" +
                        "      \"vote_count\": 1687\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"adult\": false,\n" +
                        "      \"backdrop_path\": \"/wzJRB4MKi3yK138bJyuL9nx47y6.jpg\",\n" +
                        "      \"genre_ids\": [\n" +
                        "        28,\n" +
                        "        53,\n" +
                        "        878\n" +
                        "      ],\n" +
                        "      \"id\": 577922,\n" +
                        "      \"original_language\": \"en\",\n" +
                        "      \"original_title\": \"Tenet\",\n" +
                        "      \"overview\": \"Armed with only one word - Tenet - and fighting for the survival of the entire world, the Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.\",\n" +
                        "      \"popularity\": 490.658,\n" +
                        "      \"poster_path\": \"/k68nPLbIST6NP96JmTxmZijEvCA.jpg\",\n" +
                        "      \"release_date\": \"2020-08-22\",\n" +
                        "      \"title\": \"Tenet\",\n" +
                        "      \"video\": false,\n" +
                        "      \"vote_average\": 7.3,\n" +
                        "      \"vote_count\": 4652\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"adult\": false,\n" +
                        "      \"backdrop_path\": \"/2M2JxEv3HSpjnZWjY9NOdGgfUd.jpg\",\n" +
                        "      \"genre_ids\": [\n" +
                        "        53,\n" +
                        "        28,\n" +
                        "        80,\n" +
                        "        18\n" +
                        "      ],\n" +
                        "      \"id\": 553604,\n" +
                        "      \"original_language\": \"en\",\n" +
                        "      \"original_title\": \"Honest Thief\",\n" +
                        "      \"overview\": \"A bank robber tries to turn himself in because he's falling in love and wants to live an honest life...but when he realizes the Feds are more corrupt than him, he must fight back to clear his name.\",\n" +
                        "      \"popularity\": 474.932,\n" +
                        "      \"poster_path\": \"/zeD4PabP6099gpE0STWJrJrCBCs.jpg\",\n" +
                        "      \"release_date\": \"2020-09-03\",\n" +
                        "      \"title\": \"Honest Thief\",\n" +
                        "      \"video\": false,\n" +
                        "      \"vote_average\": 6.6,\n" +
                        "      \"vote_count\": 657\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"adult\": false,\n" +
                        "      \"backdrop_path\": \"/vQJ3yBdF91tzd73G8seL5bOxfvG.jpg\",\n" +
                        "      \"genre_ids\": [\n" +
                        "        53,\n" +
                        "        27,\n" +
                        "        18\n" +
                        "      ],\n" +
                        "      \"id\": 599281,\n" +
                        "      \"original_language\": \"en\",\n" +
                        "      \"original_title\": \"Fear of Rain\",\n" +
                        "      \"overview\": \"A teenage girl living with schizophrenia begins to suspect her neighbor has kidnapped a child. Her parents try desperately to help her live a normal life, without exposing their own tragic secrets, and the only person who believes her is Caleb – a boy she isn’t even sure exists.\",\n" +
                        "      \"popularity\": 408.35,\n" +
                        "      \"poster_path\": \"/b2shaNA4F8zNIwoRYr33lPTiFfl.jpg\",\n" +
                        "      \"release_date\": \"2021-02-12\",\n" +
                        "      \"title\": \"Fear of Rain\",\n" +
                        "      \"video\": false,\n" +
                        "      \"vote_average\": 7.5,\n" +
                        "      \"vote_count\": 139\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"adult\": false,\n" +
                        "      \"backdrop_path\": \"/qIeFiiT0IfkcXmfHG2KvFxwsttw.jpg\",\n" +
                        "      \"genre_ids\": [\n" +
                        "        18,\n" +
                        "        10749\n" +
                        "      ],\n" +
                        "      \"id\": 506281,\n" +
                        "      \"original_language\": \"en\",\n" +
                        "      \"original_title\": \"The World to Come\",\n" +
                        "      \"overview\": \"Two women who forge a close connection despite their isolation in the mid-19th-century American frontier.\",\n" +
                        "      \"popularity\": 441.744,\n" +
                        "      \"poster_path\": \"/j64CnpmbaKB90Cpprk5hM9kHyJI.jpg\",\n" +
                        "      \"release_date\": \"2021-02-12\",\n" +
                        "      \"title\": \"The World to Come\",\n" +
                        "      \"video\": false,\n" +
                        "      \"vote_average\": 7,\n" +
                        "      \"vote_count\": 24\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"adult\": false,\n" +
                        "      \"backdrop_path\": \"/las0P4Dua54XrZ73VQmGUaH1z0U.jpg\",\n" +
                        "      \"genre_ids\": [\n" +
                        "        16,\n" +
                        "        28,\n" +
                        "        18,\n" +
                        "        878\n" +
                        "      ],\n" +
                        "      \"id\": 283566,\n" +
                        "      \"original_language\": \"ja\",\n" +
                        "      \"original_title\": \"シン・エヴァンゲリオン劇場版:||\",\n" +
                        "      \"overview\": \"In the aftermath of the Fourth Impact, stranded without their Evangelions, Shinji, Asuka, and Rei search for refuge in the desolate red remains of Tokyo-3. But the danger to the world is far from over. A new impact is looming on the horizon—one that will prove to be the true end of Evangelion.\",\n" +
                        "      \"popularity\": 347.943,\n" +
                        "      \"poster_path\": \"/jDwZavHo99JtGsCyRzp4epeeBHx.jpg\",\n" +
                        "      \"release_date\": \"2021-03-08\",\n" +
                        "      \"title\": \"Evangelion: 3.0+1.0 Thrice Upon a Time\",\n" +
                        "      \"video\": false,\n" +
                        "      \"vote_average\": 10,\n" +
                        "      \"vote_count\": 2\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"adult\": false,\n" +
                        "      \"backdrop_path\": \"/4qu4kO5HVTKMK2hvmCXeviZ233l.jpg\",\n" +
                        "      \"genre_ids\": [\n" +
                        "        35,\n" +
                        "        80,\n" +
                        "        18\n" +
                        "      ],\n" +
                        "      \"id\": 741228,\n" +
                        "      \"original_language\": \"en\",\n" +
                        "      \"original_title\": \"Locked Down\",\n" +
                        "      \"overview\": \"During a COVID-19 lockdown, sparring couple Linda and Paxton call a truce to attempt a high-risk jewellery heist at one of the world's most exclusive department stores, Harrods.\",\n" +
                        "      \"popularity\": 346.353,\n" +
                        "      \"poster_path\": \"/svHelD0Hb3TXPAQoPsoBwdDMTvf.jpg\",\n" +
                        "      \"release_date\": \"2021-02-25\",\n" +
                        "      \"title\": \"Locked Down\",\n" +
                        "      \"video\": false,\n" +
                        "      \"vote_average\": 5.5,\n" +
                        "      \"vote_count\": 11\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"adult\": false,\n" +
                        "      \"backdrop_path\": \"/y7b78soqvJq4aQ8G0Zt72inSQoE.jpg\",\n" +
                        "      \"genre_ids\": [\n" +
                        "        35,\n" +
                        "        10749\n" +
                        "      ],\n" +
                        "      \"id\": 778730,\n" +
                        "      \"original_language\": \"es\",\n" +
                        "      \"original_title\": \"Loco por ella\",\n" +
                        "      \"overview\": \"After a magical night together, Adri voluntarily turns himself into the psychiatric institution where Carla lives.\",\n" +
                        "      \"popularity\": 308.402,\n" +
                        "      \"poster_path\": \"/hPBJckYsL1UOsz44InZ2wYJyJTy.jpg\",\n" +
                        "      \"release_date\": \"2021-02-26\",\n" +
                        "      \"title\": \"Crazy About Her\",\n" +
                        "      \"video\": false,\n" +
                        "      \"vote_average\": 7.3,\n" +
                        "      \"vote_count\": 150\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"adult\": false,\n" +
                        "      \"backdrop_path\": \"/8rIoyM6zYXJNjzGseT3MRusMPWl.jpg\",\n" +
                        "      \"genre_ids\": [\n" +
                        "        10751,\n" +
                        "        14,\n" +
                        "        12,\n" +
                        "        35,\n" +
                        "        27\n" +
                        "      ],\n" +
                        "      \"id\": 531219,\n" +
                        "      \"original_language\": \"en\",\n" +
                        "      \"original_title\": \"Roald Dahl's The Witches\",\n" +
                        "      \"overview\": \"In late 1967, a young orphaned boy goes to live with his loving grandma in the rural Alabama town of Demopolis. As the boy and his grandmother encounter some deceptively glamorous but thoroughly diabolical witches, she wisely whisks him away to a seaside resort. Regrettably, they arrive at precisely the same time that the world's Grand High Witch has gathered.\",\n" +
                        "      \"popularity\": 287.276,\n" +
                        "      \"poster_path\": \"/b1C0FuXp4wiPmHLVKq4kwtDMgK6.jpg\",\n" +
                        "      \"release_date\": \"2020-10-25\",\n" +
                        "      \"title\": \"Roald Dahl's The Witches\",\n" +
                        "      \"video\": false,\n" +
                        "      \"vote_average\": 6.7,\n" +
                        "      \"vote_count\": 1360\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"adult\": false,\n" +
                        "      \"backdrop_path\": \"/vX5JtEcumMQvMCLVcIqfetc7hdg.jpg\",\n" +
                        "      \"genre_ids\": [\n" +
                        "        35,\n" +
                        "        80,\n" +
                        "        53\n" +
                        "      ],\n" +
                        "      \"id\": 601666,\n" +
                        "      \"original_language\": \"en\",\n" +
                        "      \"original_title\": \"I Care a Lot\",\n" +
                        "      \"overview\": \"A court-appointed legal guardian defrauds her older clients and traps them under her care. But her latest mark comes with some unexpected baggage.\",\n" +
                        "      \"popularity\": 266.118,\n" +
                        "      \"poster_path\": \"/gKnhEsjNefpKnUdAkn7INzIFLSu.jpg\",\n" +
                        "      \"release_date\": \"2021-02-19\",\n" +
                        "      \"title\": \"I Care a Lot\",\n" +
                        "      \"video\": false,\n" +
                        "      \"vote_average\": 6.8,\n" +
                        "      \"vote_count\": 839\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"adult\": false,\n" +
                        "      \"backdrop_path\": \"/5TbtcmRySXPAEXBzwhiOYYDQmgv.jpg\",\n" +
                        "      \"genre_ids\": [\n" +
                        "        27,\n" +
                        "        9648\n" +
                        "      ],\n" +
                        "      \"id\": 723072,\n" +
                        "      \"original_language\": \"en\",\n" +
                        "      \"original_title\": \"Host\",\n" +
                        "      \"overview\": \"Six friends hire a medium to hold a séance via Zoom during lockdown — but they get far more than they bargained for as things quickly go wrong. When an evil spirit starts invading their homes, they begin to realize they might not survive the night.\",\n" +
                        "      \"popularity\": 245.462,\n" +
                        "      \"poster_path\": \"/h7dZpJDORYs5c56dydbrLFkEXpE.jpg\",\n" +
                        "      \"release_date\": \"2020-12-04\",\n" +
                        "      \"title\": \"Host\",\n" +
                        "      \"video\": false,\n" +
                        "      \"vote_average\": 6.7,\n" +
                        "      \"vote_count\": 194\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"total_pages\": 40,\n" +
                        "  \"total_results\": 799\n" +
                        "}"
            )
            return MockResponse().setResponseCode(400)
        }
    }

    //  error dispatcher
    class ErrorDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse =
            MockResponse().setResponseCode(400)
    }
}