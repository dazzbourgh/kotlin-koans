package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return when {
            year > other.year -> 1
            year < other.year -> -1
            month > other.month -> 1
            month < other.month -> -1
            dayOfMonth > other.dayOfMonth -> 1
            dayOfMonth < other.dayOfMonth -> -1
            else -> 0
        }
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = {
    val self: DateRange = DateRange(this, other)
    return self
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate): ClosedRange<MyDate> {
    override fun contains(value: MyDate): Boolean {
        return value in start..endInclusive
    }
}