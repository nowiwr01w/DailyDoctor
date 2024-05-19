package com.nowiwr01p.model.time

sealed class TimeInSeconds(
    open val seconds: Long
) {
    data class PeriodSeconds(
        override val seconds: Long
    ): TimeInSeconds(seconds)

    data class PeriodMinutes(
        val minutes: Long
    ): TimeInSeconds(ONE_MINUTE_IN_SEC * minutes)

    data class PeriodHours(
        val hours: Long
    ): TimeInSeconds(ONE_HOUR_IN_SEC * hours)

    data class PeriodDays(
        val days: Long
    ): TimeInSeconds(ONE_DAY_IN_SEC * days)

    data class PeriodWeeks(
        val weeks: Long
    ): TimeInSeconds(ONE_WEEK_IN_SEC * weeks)

    data class PeriodMonths(
        val months: Long
    ): TimeInSeconds(ONE_MONTH_IN_SEC * months)

    fun toMillis() = 1000 * seconds

    private companion object {
        const val ONE_MINUTE_IN_SEC = 60
        const val ONE_HOUR_IN_SEC = 60 * ONE_MINUTE_IN_SEC
        const val ONE_DAY_IN_SEC = 24 * ONE_HOUR_IN_SEC
        const val ONE_WEEK_IN_SEC = 7 * ONE_DAY_IN_SEC
        const val ONE_MONTH_IN_SEC = 30 * ONE_DAY_IN_SEC
    }
}