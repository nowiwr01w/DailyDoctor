package ui.common.onboarding.data

import dailydoctor.composeapp.generated.resources.Res
import dailydoctor.composeapp.generated.resources.compose_multiplatform
import dailydoctor.composeapp.generated.resources.ic_onboarding_always_online
import dailydoctor.composeapp.generated.resources.ic_onboarding_chat_with_doctor
import dailydoctor.composeapp.generated.resources.ic_onboarding_for_whole_family
import dailydoctor.composeapp.generated.resources.ic_onboarding_notifications
import dailydoctor.composeapp.generated.resources.ic_onboarding_save_with_us
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.common.onboarding.data.OnboardingItem.*

@OptIn(ExperimentalResourceApi::class)
@Serializable
sealed class OnboardingItem(
    @Contextual open val image: DrawableResource,
    open val title: String,
    open val description: String,
    open val firstButtonText: String,
    open val secondButtonText: String = "",
) {
    data class RemoteMeetingOnboardingItem(
        override val image: DrawableResource = Res.drawable.ic_onboarding_chat_with_doctor,
        override val title: String = "Онлайн-консультации с врачами в чате",
        override val description: String = "Дома, на даче, в отпуске или даже на работе",
        override val firstButtonText: String = "Очень интересно!",
    ) : OnboardingItem(
        image = image,
        title = title,
        description = description,
        firstButtonText = firstButtonText
    )

    data class UnlimitedCommunicationOnboardingItem(
        override val image: DrawableResource = Res.drawable.ic_onboarding_always_online,
        override val title: String = "Безлимитные обращения - мы на связи 24/7",
        override val description: String = "Пишите, когда болит или когда нужно просто спросить",
        override val firstButtonText: String = "Класс, а что еще?",
    ) : OnboardingItem(
        image = image,
        title = title,
        description = description,
        firstButtonText = firstButtonText
    )

    data class ForWholeFamilyOnboardingItem(
        override val image: DrawableResource = Res.drawable.ic_onboarding_for_whole_family,
        override val title: String = "Для всей семьи - себе, детям и родителям",
        override val description: String = "И мужу, который сам бы не пошел к врачу",
        override val firstButtonText: String = "Это мне надо",
    ) : OnboardingItem(
        image = image,
        title = title,
        description = description,
        firstButtonText = firstButtonText
    )

    data class SavingsOnboardingItem(
        override val image: DrawableResource = Res.drawable.ic_onboarding_save_with_us,
        override val title: String = "Экономия на очных визитах к врачу",
        override val description: String = "А еще эксклюзивные скидки на анализы до 25%",
        override val firstButtonText: String = "Отлично!",
    ) : OnboardingItem(
        image = image,
        title = title,
        description = description,
        firstButtonText = firstButtonText
    )

    data class NotificationsOnboardingItem(
        override val image: DrawableResource = Res.drawable.ic_onboarding_notifications,
        override val title: String = "Не пропустите ответ врача на ваще сообщение",
        override val description: String = "И спокойно занимайтесь другими делами",
        override val firstButtonText: String = "Разрешить уведомления",
        override val secondButtonText: String = "Не сейчас"
    ) : OnboardingItem(
        image = image,
        title = title,
        description = description,
        firstButtonText = firstButtonText,
        secondButtonText = secondButtonText
    )
}

@OptIn(ExperimentalResourceApi::class)
internal fun getOnboardingItems() = listOf(
    RemoteMeetingOnboardingItem(),
    UnlimitedCommunicationOnboardingItem(),
    ForWholeFamilyOnboardingItem(),
    SavingsOnboardingItem(),
    NotificationsOnboardingItem()
)