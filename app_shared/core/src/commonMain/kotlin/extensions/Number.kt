package extensions

fun Int.withLeadingZero() = when {
    this >= 10 -> toString()
    else -> "0$this"
}

fun Int?.orZero() = this ?: 0
