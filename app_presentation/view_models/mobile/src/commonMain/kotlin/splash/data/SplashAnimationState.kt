package splash.data

const val SPLASH_ANIMATION_DURATION = 1500
const val SPLASH_VISIBILITY_DURATION = 1250
const val SPLASH_PROGRESS_ANIMATION_DURATION = 3000
const val SPLASH_PROGRESS_VISIBILITY_DURATION = 300

enum class SplashAnimationState(
    val value: Int,
    val showUntilAtMillis: Int,
    val animationDuration: Int
) {
    ICON(
        value = 0,
        showUntilAtMillis = SPLASH_ANIMATION_DURATION,
        animationDuration = SPLASH_VISIBILITY_DURATION
    ),
    FIRST_TEXT(
        value = 1,
        showUntilAtMillis = 2 * SPLASH_ANIMATION_DURATION,
        animationDuration = SPLASH_VISIBILITY_DURATION
    ),
    SECOND_TEXT(
        value = 2,
        showUntilAtMillis = 3 * SPLASH_ANIMATION_DURATION,
        animationDuration = SPLASH_VISIBILITY_DURATION
    ),
    PROGRESS(
        value = 3,
        showUntilAtMillis = 3 * SPLASH_ANIMATION_DURATION + SPLASH_PROGRESS_ANIMATION_DURATION,
        animationDuration = SPLASH_PROGRESS_VISIBILITY_DURATION
    )
}