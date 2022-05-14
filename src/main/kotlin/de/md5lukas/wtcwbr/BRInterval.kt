package de.md5lukas.wtcwbr

import java.time.LocalDate

data class BRInterval(val br: String, val start: LocalDate, val end: LocalDate) {
    val isActive: Boolean
        get() {
            val now = LocalDate.now()
            return now >= start && now <= end
        }
}