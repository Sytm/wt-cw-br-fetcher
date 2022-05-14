package de.md5lukas.wtcwbr

import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.regex.Pattern

object Parser {

    private val pattern = Pattern.compile("^.*?(\\d\\d?\\.[037])\\s*?\\((\\d\\d\\.\\d\\d).*?(\\d\\d\\.\\d\\d)\\)\\s*?\$", Pattern.MULTILINE)

    fun parseComment(comment: String): List<BRInterval> {
        val result = ArrayList<BRInterval>()

        val matcher = pattern.matcher(comment)

        val now = OffsetDateTime.now(ZoneOffset.UTC)

        while (matcher.find()) {

            val br = matcher.group(1)
            val start = matcher.group(2).split('.')
            val end = matcher.group(3).split('.')

            val parsedStart = LocalDate.of(now.year, start[1].toInt(), start[0].toInt())
            val parsedEnd = LocalDate.of(now.year, end[1].toInt(), end[0].toInt())

            result.add(BRInterval(br, parsedStart, parsedEnd))
        }

        return result
    }
}