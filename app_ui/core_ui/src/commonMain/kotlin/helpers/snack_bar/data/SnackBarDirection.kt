package helpers.snack_bar.data

sealed interface SnackBarDirection {
    data object Top: SnackBarDirection
    data object Bottom: SnackBarDirection
}