package nowiwr01p.daily.doctor.app_presentation.navigation.onboarding.model

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

interface OnboardingItemModel {
    val image: DrawableResource
    val title: StringResource
    val description: StringResource
    val firstButtonText: StringResource
    val secondButtonText: StringResource?
}