package nowiwr01p.daily.doctor.app_presentation.navigation.onboarding.model

import kotlinx.serialization.Contextual
import org.jetbrains.compose.resources.DrawableResource

interface OnboardingItemModel {
    @Contextual val image: DrawableResource
    val title: String
    val description: String
    val firstButtonText: String
    val secondButtonText: String
}