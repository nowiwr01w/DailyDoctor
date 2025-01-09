package helpers.snack_bar.data

typealias LongType = Long

sealed class SnackBarDuration(
    open val timeMillis: LongType
) {
    data object Short: SnackBarDuration(3000L)
    data object Medium: SnackBarDuration(4000L)
    data object Long: SnackBarDuration(5000L)
    data object ExtraLong: SnackBarDuration(7000L)
    data class Custom(
        override val timeMillis: LongType
    ): SnackBarDuration(timeMillis)
}