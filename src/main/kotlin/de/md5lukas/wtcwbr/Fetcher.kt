package de.md5lukas.wtcwbr

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.jsoup.Jsoup
import org.jsoup.select.Evaluator

object Fetcher {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private fun getURL(page: Int) = "https://forum.warthunder.com/index.php?/topic/408054-season-schedule-for-squadron-battles/page/$page"

    fun getLatestComment(): String {
        var pageData = getPageData(1)

        if (pageData.pageEnd > 1) {
            pageData = getPageData(pageData.pageEnd)
        }

        return pageData.comment.last().text
    }

    private fun getPageData(page: Int): DiscussionForumPostingLDJson {
        val document = Jsoup.connect(getURL(page)).get()

        document.head().select(Evaluator.AttributeWithValue("type", "application/ld+json")).forEach {
            val content = it.data()
            if (json.decodeFromString<GenericLDJson>(content).`@type` == "DiscussionForumPosting") {
                return json.decodeFromString(content)
            }
        }

        throw IllegalStateException("Couldn't find proper ld+json tag")
    }
}