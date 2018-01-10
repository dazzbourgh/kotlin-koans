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

    operator fun inc() = nextDay()
    operator fun plus(other: TimeInterval) = addTimeIntervals(other, 1)
    operator fun plus(pair: Pair<TimeInterval, Int>): MyDate = addTimeIntervals(pair.first, pair.second)
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    operator fun times(i: Int): Pair<TimeInterval, Int> = Pair(this, i)
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun contains(value: MyDate) = value >= start && value <= endInclusive

    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            var value = start

            override fun hasNext(): Boolean {
                return value <= endInclusive
            }

            override fun next(): MyDate {
                val ret = value
                value = value.nextDay()
                return ret
            }
        }
    }
}

