package com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources

import com.nowiwr01p.model.model.subscription.type.SubscriptionType
import com.nowiwr01p.model.model.subscription.type.SubscriptionType.Standard
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.base.BaseSubscriptionBenefits
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.base.SubscriptionBenefitData

abstract class StandardPlanResources: BaseSubscriptionBenefits {
    override val type: SubscriptionType = Standard
}

/**
 * GEORGIAN
 */
internal data class StandardPlanResourcesGeorgian(
    override val firstVisitDiscount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "პირველი ვიზიტი იაფია",
        description = "10%-იანი ფასდაკლება პირველ ვიზიტზე"
    ),
    override val appointmentsCount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "ჩანაწერების რაოდენობა",
        description = "თვეში სამი ექიმის მიღება"
    ),
    override val chatDescription: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "ჩატი ექიმთან",
        description = "ექიმთან ჩატი იხსნება ვიზიტის დაჯავშნისთანავე, მაგრამ ქრება ვიზიტის დასრულების შემდეგ"
    ),
    override val welcomePaper: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "არანაირი აურზაური ქაღალდებით",
        description = "ანამნეზის შევსება და პიროვნების იდენტიფიკაცია ჩატში ვიზიტის დღეს"
    ),
    override val favoriteClinics: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "რჩეული კლინიკები",
        description = "10%-იანი ფასდაკლება საყვარელ კლინიკებში"
    ),
    override val decodingAnalysis: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "დოკუმენტების დეკოდირება",
        description = "ორი ანალიზის დეკოდირება თვეში. დააჭირეთ, რომ გაიგოთ მეტი, თუ რა მომსახურებაა ხელმისაწვდომი ამ გეგმაში"
    ),
    override val paidQuestions: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "კითხვა ნებისმიერ ექიმს",
        description = "თქვენი დიალოგის პრიორიტეტი მაღალია, თქვენი ჩატი გამოყოფილია ექიმისთვის. ექიმი პასუხს გაგცემთ სამ დღეში"
    )
): StandardPlanResources()

/**
 * ENGLISH
 */
internal data class StandardPlanResourcesEnglish(
    override val firstVisitDiscount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "First time cheaper",
        description = "10% discount on the first visit"
    ),
    override val appointmentsCount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Number of appointments",
        description = "Appointment with three doctors per month"
    ),
    override val chatDescription: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Chat with the doctor",
        description = "The doctor chat opens immediately after booking an appointment but disappears after the appointment ends"
    ),
    override val welcomePaper: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "No hassle with paperwork",
        description = "Filling out the medical history form and identity verification in chat on the day of the appointment"
    ),
    override val favoriteClinics: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Favorite clinics",
        description = "10% discount at favorite clinics"
    ),
    override val decodingAnalysis: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Document decoding",
        description = "Two analysis decodings per month. Click to learn more about the decoding services available in this plan"
    ),
    override val paidQuestions: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "A question to any doctor",
        description = "Your dialogue priority is high, and your chat is highlighted for the doctor. The doctor will respond within three days"
    )
): StandardPlanResources()

/**
 * RUSSIAN
 */
internal data class StandardPlanResourcesRussian(
    override val firstVisitDiscount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Первый раз дешевле",
        description = "Скидка 10% на первый приём"
    ),
    override val appointmentsCount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Количество записей",
        description = "Запись к трём врачам в месяц"
    ),
    override val chatDescription: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Чат с доктором",
        description = "Чат с доктором открывается сразу после записи на приём, но исчезает после того, как приём закончится"
    ),
    override val welcomePaper: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Никакой возьни с бумажками",
        description = "Заполнение листа с анамнезом и верификация личности в чате в день приёма"
    ),
    override val favoriteClinics: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Избранные клиники",
        description = "Скидка 10% в любимых клиниках"
    ),
    override val decodingAnalysis: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Расшифровка документов",
        description = "Две расшифровки в месяц. Нажмите, чтобы узнать подробнее, какие услуги расшифровки доступны в данном тарифе"
    ),
    override val paidQuestions: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Вопрос любому доктору",
        description = "Приоритет вашего диалога - высокий, выделение чата у доктора цветом. Доктор ответит в течение трёх дней"
    )
): StandardPlanResources()

/**
 * UKRAINIAN
 */
internal data class StandardPlanResourcesUkrainian(
    override val firstVisitDiscount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Перший раз дешевше",
        description = "Знижка 10% на перший візит"
    ),
    override val appointmentsCount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Кількість записів",
        description = "Запис до трьох лікарів на місяць"
    ),
    override val chatDescription: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Чат із лікарем",
        description = "Чат із лікарем відкривається одразу після запису на прийом, але зникає після завершення прийому"
    ),
    override val welcomePaper: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Ніякої тяганини з паперами",
        description = "Заповнення анкети анамнезу та верифікація особи в чаті в день прийому"
    ),
    override val favoriteClinics: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Улюблені клініки",
        description = "Знижка 10% в улюблених клініках"
    ),
    override val decodingAnalysis: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Розшифровка документів",
        description = "Дві розшифровки аналізів на місяць. Натисніть, щоб дізнатися більше про доступні послуги розшифрування"
    ),
    override val paidQuestions: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Запитання будь-якому лікарю",
        description = "Пріоритет вашого діалогу - високий, лікар бачить виділення вашого чату кольором. Лікар відповість протягом трьох днів"
    )
): StandardPlanResources()
