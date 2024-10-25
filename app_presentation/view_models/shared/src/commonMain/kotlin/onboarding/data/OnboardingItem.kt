package onboarding.data

import nowiwr01p.daily.doctor.app_presentation.navigation.onboarding.model.OnboardingItemModel
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.onboarding_for_whole_family_button_text
import nowiwr01p.daily.doctor.resources.onboarding_for_whole_family_title
import nowiwr01p.daily.doctor.resources.onboarding_notifications_button_text_no
import nowiwr01p.daily.doctor.resources.onboarding_notifications_button_text_ok
import nowiwr01p.daily.doctor.resources.onboarding_notifications_description
import nowiwr01p.daily.doctor.resources.onboarding_notifications_title
import nowiwr01p.daily.doctor.resources.onboarding_remote_meeting_button_text
import nowiwr01p.daily.doctor.resources.onboarding_remote_meeting_description
import nowiwr01p.daily.doctor.resources.onboarding_remote_meeting_title
import nowiwr01p.daily.doctor.resources.onboarding_savings_button_text
import nowiwr01p.daily.doctor.resources.onboarding_savings_description
import nowiwr01p.daily.doctor.resources.onboarding_savings_title
import nowiwr01p.daily.doctor.resources.onboarding_unlimited_communication_description
import nowiwr01p.daily.doctor.resources.onboarding_unlimited_communication_title
import nowiwr01p.daily.doctor.resources.onboarding_unlimited_communication_button_text
import nowiwr01p.daily.doctor.resources.onboarding_for_whole_family_description
import onboarding.data.OnboardingItem.ForWholeFamilyOnboardingItem
import onboarding.data.OnboardingItem.NotificationsOnboardingItem
import onboarding.data.OnboardingItem.RemoteMeetingOnboardingItem
import onboarding.data.OnboardingItem.SavingsOnboardingItem
import onboarding.data.OnboardingItem.UnlimitedCommunicationOnboardingItem
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed class OnboardingItem(
    override val image: DrawableResource,
    override val title: StringResource,
    override val description: StringResource,
    override val firstButtonText: StringResource,
    override val secondButtonText: StringResource? = null,
): OnboardingItemModel {

    data class RemoteMeetingOnboardingItem(
        override val image: DrawableResource,
        override val title: StringResource = Res.string.onboarding_remote_meeting_title,
        override val description: StringResource = Res.string.onboarding_remote_meeting_description,
        override val firstButtonText: StringResource = Res.string.onboarding_remote_meeting_button_text
    ) : OnboardingItem(
        image = image,
        title = title,
        description = description,
        firstButtonText = firstButtonText
    )

    data class UnlimitedCommunicationOnboardingItem(
        override val image: DrawableResource,
        override val title: StringResource = Res.string.onboarding_unlimited_communication_title,
        override val description: StringResource = Res.string.onboarding_unlimited_communication_description,
        override val firstButtonText: StringResource = Res.string.onboarding_unlimited_communication_button_text
    ) : OnboardingItem(
        image = image,
        title = title,
        description = description,
        firstButtonText = firstButtonText
    )

    data class ForWholeFamilyOnboardingItem(
        override val image: DrawableResource,
        override val title: StringResource = Res.string.onboarding_for_whole_family_title,
        override val description: StringResource = Res.string.onboarding_for_whole_family_description,
        override val firstButtonText: StringResource = Res.string.onboarding_for_whole_family_button_text
    ) : OnboardingItem(
        image = image,
        title = title,
        description = description,
        firstButtonText = firstButtonText
    )

    data class SavingsOnboardingItem(
        override val image: DrawableResource,
        override val title: StringResource = Res.string.onboarding_savings_title,
        override val description: StringResource = Res.string.onboarding_savings_description,
        override val firstButtonText: StringResource = Res.string.onboarding_savings_button_text
    ) : OnboardingItem(
        image = image,
        title = title,
        description = description,
        firstButtonText = firstButtonText
    )

    data class NotificationsOnboardingItem(
        override val image: DrawableResource,
        override val title: StringResource = Res.string.onboarding_notifications_title,
        override val description: StringResource = Res.string.onboarding_notifications_description,
        override val firstButtonText: StringResource = Res.string.onboarding_notifications_button_text_ok,
        override val secondButtonText: StringResource = Res.string.onboarding_notifications_button_text_no
    ) : OnboardingItem(
        image = image,
        title = title,
        description = description,
        firstButtonText = firstButtonText,
        secondButtonText = secondButtonText
    )
}

fun getOnboardingItems(
    remoteMeetingOnboardingImage: DrawableResource,
    unlimitedCommunicationOnboardingImage: DrawableResource,
    forWholeFamilyOnboardingImage: DrawableResource,
    savingsOnboardingImage: DrawableResource,
    notificationsOnboardingImage: DrawableResource
): List<OnboardingItem> {
    return listOf(
        RemoteMeetingOnboardingItem(remoteMeetingOnboardingImage),
        UnlimitedCommunicationOnboardingItem(unlimitedCommunicationOnboardingImage),
        ForWholeFamilyOnboardingItem(forWholeFamilyOnboardingImage),
        SavingsOnboardingItem(savingsOnboardingImage),
        NotificationsOnboardingItem(notificationsOnboardingImage)
    )
}