package com.nowiwr01p.model.model.onboarding

import com.nowiwr01p.model.model.onboarding.item.OnboardingItemsData
import com.nowiwr01p.model.model.onboarding.type.OnboardingItemType
import com.nowiwr01p.model.model.onboarding.type.OnboardingItemType.ForWholeFamily
import com.nowiwr01p.model.model.onboarding.type.OnboardingItemType.Notifications
import com.nowiwr01p.model.model.onboarding.type.OnboardingItemType.OnlineAppointment
import com.nowiwr01p.model.model.onboarding.type.OnboardingItemType.Savings
import com.nowiwr01p.model.model.onboarding.type.OnboardingItemType.UnlimitedCommunication
import com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items.ForWholeFamilyDataEnglish
import com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items.ForWholeFamilyDataGeorgian
import com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items.ForWholeFamilyDataRussian
import com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items.ForWholeFamilyDataUkrainian
import com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items.NotificationsDataEnglish
import com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items.NotificationsDataGeorgian
import com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items.NotificationsDataRussian
import com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items.NotificationsDataUkrainian
import com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items.OnlineAppointmentDataEnglish
import com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items.OnlineAppointmentDataGeorgian
import com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items.OnlineAppointmentDataRussian
import com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items.OnlineAppointmentDataUkrainian
import com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items.SavingsDataEnglish
import com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items.SavingsDataGeorgian
import com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items.SavingsDataRussian
import com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items.SavingsDataUkrainian
import com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items.UnlimitedCommunicationDataEnglish
import com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items.UnlimitedCommunicationDataGeorgian
import com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items.UnlimitedCommunicationDataRussian
import com.nowiwr01p.model.resources.component_with_resources.screens.onboarding.items.UnlimitedCommunicationDataUkrainian
import kotlinx.serialization.Serializable

@Serializable
open class OnboardingItemServer(
    open val type: OnboardingItemType,
    open val items: List<OnboardingItemsData>
)

/**
 * ONLINE APPOINTMENT
 */
private data object OnlineAppointmentOnboardingItemServer: OnboardingItemServer(
    type = OnlineAppointment,
    items = listOf(
        OnlineAppointmentDataGeorgian(),
        OnlineAppointmentDataEnglish(),
        OnlineAppointmentDataRussian(),
        OnlineAppointmentDataUkrainian()
    )
)

/**
 * UNLIMITED COMMUNICATION
 */
private data object UnlimitedCommunicationOnboardingItemServer: OnboardingItemServer(
    type = UnlimitedCommunication,
    items = listOf(
        UnlimitedCommunicationDataGeorgian(),
        UnlimitedCommunicationDataEnglish(),
        UnlimitedCommunicationDataRussian(),
        UnlimitedCommunicationDataUkrainian()
    )
)

/**
 * FOR WHOLE FAMILY
 */
private data object ForWholeFamilyOnboardingItemServer: OnboardingItemServer(
    type = ForWholeFamily,
    items = listOf(
        ForWholeFamilyDataGeorgian(),
        ForWholeFamilyDataEnglish(),
        ForWholeFamilyDataRussian(),
        ForWholeFamilyDataUkrainian()
    )
)

/**
 * SAVINGS
 */
private data object SavingsOnboardingItemServer: OnboardingItemServer(
    type = Savings,
    items = listOf(
        SavingsDataGeorgian(),
        SavingsDataEnglish(),
        SavingsDataRussian(),
        SavingsDataUkrainian()
    )
)

/**
 * NOTIFICATIONS
 */
private data object NotificationsOnboardingItemServer: OnboardingItemServer(
    type = Notifications,
    items = listOf(
        NotificationsDataGeorgian(),
        NotificationsDataEnglish(),
        NotificationsDataRussian(),
        NotificationsDataUkrainian()
    )
)

/**
 * ALL
 */
val allOnboardingItemsWithTranslation = listOf(
    OnlineAppointmentOnboardingItemServer,
    UnlimitedCommunicationOnboardingItemServer,
    ForWholeFamilyOnboardingItemServer,
    SavingsOnboardingItemServer,
    NotificationsOnboardingItemServer
)