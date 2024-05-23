package onboarding.data

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import nowiwr01p.daily.doctor.app_presentation.navigation.onboarding.model.OnboardingItemModel
import onboarding.data.OnboardingItem.*
import org.jetbrains.compose.resources.DrawableResource

@Serializable
sealed class OnboardingItem(
    @Contextual override val image: DrawableResource,
    override val title: String,
    override val description: String,
    override val firstButtonText: String,
    override val secondButtonText: String = "",
): OnboardingItemModel {

    data class RemoteMeetingOnboardingItem(
        override val image: DrawableResource,
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
        override val image: DrawableResource,
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
        override val image: DrawableResource,
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
        override val image: DrawableResource,
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
        override val image: DrawableResource,
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