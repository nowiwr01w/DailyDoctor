package com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources

import com.nowiwr01p.model.model.subscription.type.SubscriptionType
import com.nowiwr01p.model.model.subscription.type.SubscriptionType.Premium
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.base.BaseSubscriptionBenefits
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.base.SubscriptionBenefitData

abstract class PremiumPlanResources: BaseSubscriptionBenefits {
    override val type: SubscriptionType = Premium
}

/**
 * GEORGIAN
 */
internal data class PremiumPlanResourcesGeorgian(
    override val firstVisitDiscount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "პირველი ვიზიტი იაფია",
        description = "15%-იანი ფასდაკლება პირველ ვიზიტზე"
    ),
    override val appointmentsCount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "ჩანაწერების რაოდენობა",
        description = "თვეში ხუთი ექიმის მიღება"
    ),
    override val chatDescription: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "ჩატი ექიმთან",
        description = "ექიმთან ჩატი იხსნება ვიზიტის დაჯავშნისთანავე და არასოდეს ქრება, თუ თავად არ წაშლით"
    ),
    override val welcomePaper: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "არანაირი აურზაური ქაღალდებით",
        description = "ანამნეზის შევსება და პიროვნების იდენტიფიკაცია ჩატში ნებისმიერ დროს ვიზიტის დაჯავშნის შემდეგ"
    ),
    override val favoriteClinics: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "რჩეული კლინიკები",
        description = "15%-იანი ფასდაკლება საყვარელ კლინიკებში"
    ),
    override val decodingAnalysis: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "დოკუმენტების დეკოდირება",
        description = "სამი ანალიზის დეკოდირება თვეში. დააჭირეთ, რომ გაიგოთ მეტი, თუ რა მომსახურებაა ხელმისაწვდომი ამ გეგმაში"
    ),
    override val paidQuestions: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "კითხვა ნებისმიერ ექიმს",
        description = "თქვენი ჩატი ექიმისთვის პირველ პოზიციებზეა და მკვეთრად გამოყოფილია. ექიმი პასუხს გაგცემთ იმავე დღეს"
    )
): PremiumPlanResources()

/**
 * ENGLISH
 */
internal data class PremiumPlanResourcesEnglish(
    override val firstVisitDiscount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "First time cheaper",
        description = "15% discount on the first visit"
    ),
    override val appointmentsCount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Number of appointments",
        description = "Appointment with five doctors per month"
    ),
    override val chatDescription: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Chat with the doctor",
        description = "The doctor chat opens immediately after booking an appointment and never disappears unless you delete it"
    ),
    override val welcomePaper: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "No hassle with paperwork",
        description = "Filling out the medical history form and identity verification in chat anytime after booking an appointment"
    ),
    override val favoriteClinics: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Favorite clinics",
        description = "15% discount at favorite clinics"
    ),
    override val decodingAnalysis: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Document decoding",
        description = "Three analysis decodings per month. Click to learn more about the decoding services available in this plan"
    ),
    override val paidQuestions: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "A question to any doctor",
        description = "Your chat is at the top positions, highlighted brightly for the doctor. The doctor will respond on the same day"
    )
): PremiumPlanResources()

/**
 * RUSSIAN
 */
internal data class PremiumPlanResourcesRussian(
    override val firstVisitDiscount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Первый раз дешевле",
        description = "Скидка 15% на первый приём"
    ),
    override val appointmentsCount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Количество записей",
        description = "Запись к пяти врачам в месяц"
    ),
    override val chatDescription: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Чат с доктором",
        description = "Чат с доктором открывается сразу после записи на приём и никогда не исчезает, если только вы его не удалите"
    ),
    override val welcomePaper: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Никакой возьни с бумажками",
        description = "Заполнение листа с анамнезом и верификация личности в чате в любое время после записи на приём"
    ),
    override val favoriteClinics: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Избранные клиники",
        description = "Скидка 15% в любимых клиниках"
    ),
    override val decodingAnalysis: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Расшифровка документов",
        description = "Три расшифровки в месяц. Нажмите, чтобы узнать подробнее, какие услуги расшифровки доступны в данном тарифе"
    ),
    override val paidQuestions: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Вопрос любому доктору",
        description = "Ваш чат находится на первых позициях, выделение чата у доктора ярким цветом. Доктор ответит в этот же день"
    )
): PremiumPlanResources()

/**
 * UKRAINIAN
 */
internal data class PremiumPlanResourcesUkrainian(
    override val firstVisitDiscount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Перший раз дешевше",
        description = "Знижка 15% на перший візит"
    ),
    override val appointmentsCount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Кількість записів",
        description = "Запис до п'яти лікарів на місяць"
    ),
    override val chatDescription: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Чат із лікарем",
        description = "Чат із лікарем відкривається одразу після запису на прийом і ніколи не зникає, якщо тільки ви його не видалите"
    ),
    override val welcomePaper: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Ніякої тяганини з паперами",
        description = "Заповнення анкети анамнезу та верифікація особи в чаті в будь-який час після запису на прийом"
    ),
    override val favoriteClinics: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Улюблені клініки",
        description = "Знижка 15% в улюблених клініках"
    ),
    override val decodingAnalysis: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Розшифровка документів",
        description = "Три розшифровки аналізів на місяць. Натисніть, щоб дізнатися більше про доступні послуги розшифрування"
    ),
    override val paidQuestions: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Запитання будь-якому лікарю",
        description = "Ваш чат знаходиться на перших позиціях, виділений яскравим кольором для лікаря. Лікар відповість у той же день"
    )
): PremiumPlanResources()
