package com.nowiwr01p.model.resources.component_with_resources.screens.subscription.plans

import com.nowiwr01p.model.model.subscription.benefits.SubscriptionBenefit
import com.nowiwr01p.model.model.subscription.benefits.SubscriptionBenefits.CommonSubscriptionPlanBenefits
import com.nowiwr01p.model.model.subscription.type.SubscriptionPlanType.Standard
import com.nowiwr01p.model.resources.language.Language

abstract class StandardPlanBenefits(override val language: Language): CommonSubscriptionPlanBenefits(
    type = Standard,
    language = language
)

/**
 * GEORGIAN
 */
internal data class StandardPlanBenefitsGeorgian(
    override val firstVisitDiscount: SubscriptionBenefit = SubscriptionBenefit(
        order = 1,
        title = "პირველი ვიზიტი იაფია",
        description = "10%-იანი ფასდაკლება პირველ ვიზიტზე"
    ),
    override val appointmentsCount: SubscriptionBenefit = SubscriptionBenefit(
        order = 2,
        title = "ჩანაწერების რაოდენობა",
        description = "თვეში სამი ექიმის მიღება"
    ),
    override val chatDescription: SubscriptionBenefit = SubscriptionBenefit(
        order = 3,
        title = "ჩატი ექიმთან",
        description = "ექიმთან ჩატი იხსნება ვიზიტის დაჯავშნისთანავე, მაგრამ ქრება ვიზიტის დასრულების შემდეგ"
    ),
    override val welcomePaper: SubscriptionBenefit = SubscriptionBenefit(
        order = 4,
        title = "არანაირი აურზაური ქაღალდებით",
        description = "ანამნეზის შევსება და პიროვნების იდენტიფიკაცია ჩატში ვიზიტის დღეს"
    ),
    override val favoriteClinics: SubscriptionBenefit = SubscriptionBenefit(
        order = 5,
        title = "რჩეული კლინიკები",
        description = "10%-იანი ფასდაკლება საყვარელ კლინიკებში"
    ),
    override val decodingAnalysis: SubscriptionBenefit = SubscriptionBenefit(
        order = 6,
        title = "დოკუმენტების დეკოდირება",
        description = "ორი ანალიზის დეკოდირება თვეში. დააჭირეთ, რომ გაიგოთ მეტი, თუ რა მომსახურებაა ხელმისაწვდომი ამ გეგმაში"
    ),
    override val paidQuestions: SubscriptionBenefit = SubscriptionBenefit(
        order = 7,
        title = "კითხვა ნებისმიერ ექიმს",
        description = "თქვენი დიალოგის პრიორიტეტი მაღალია, თქვენი ჩატი გამოყოფილია ექიმისთვის. ექიმი პასუხს გაგცემთ სამ დღეში"
    )
): StandardPlanBenefits(language = Language.Georgian)

/**
 * ENGLISH
 */
internal data class StandardPlanBenefitsEnglish(
    override val firstVisitDiscount: SubscriptionBenefit = SubscriptionBenefit(
        order = 1,
        title = "First time cheaper",
        description = "10% discount on the first visit"
    ),
    override val appointmentsCount: SubscriptionBenefit = SubscriptionBenefit(
        order = 2,
        title = "Number of appointments",
        description = "Appointment with three doctors per month"
    ),
    override val chatDescription: SubscriptionBenefit = SubscriptionBenefit(
        order = 3,
        title = "Chat with the doctor",
        description = "The doctor chat opens immediately after booking an appointment but disappears after the appointment ends"
    ),
    override val welcomePaper: SubscriptionBenefit = SubscriptionBenefit(
        order = 4,
        title = "No hassle with paperwork",
        description = "Filling out the medical history form and identity verification in chat on the day of the appointment"
    ),
    override val favoriteClinics: SubscriptionBenefit = SubscriptionBenefit(
        order = 5,
        title = "Favorite clinics",
        description = "10% discount at favorite clinics"
    ),
    override val decodingAnalysis: SubscriptionBenefit = SubscriptionBenefit(
        order = 6,
        title = "Document decoding",
        description = "Two analysis decodings per month. Click to learn more about the decoding services available in this plan"
    ),
    override val paidQuestions: SubscriptionBenefit = SubscriptionBenefit(
        order = 7,
        title = "A question to any doctor",
        description = "Your dialogue priority is high, and your chat is highlighted for the doctor. The doctor will respond within three days"
    )
): StandardPlanBenefits(language = Language.English)

/**
 * RUSSIAN
 */
internal data class StandardPlanBenefitsRussian(
    override val firstVisitDiscount: SubscriptionBenefit = SubscriptionBenefit(
        order = 1,
        title = "Первый раз дешевле",
        description = "Скидка 10% на первый приём"
    ),
    override val appointmentsCount: SubscriptionBenefit = SubscriptionBenefit(
        order = 2,
        title = "Количество записей",
        description = "Запись к трём врачам в месяц"
    ),
    override val chatDescription: SubscriptionBenefit = SubscriptionBenefit(
        order = 3,
        title = "Чат с доктором",
        description = "Чат с доктором открывается сразу после записи на приём, но исчезает после того, как приём закончится"
    ),
    override val welcomePaper: SubscriptionBenefit = SubscriptionBenefit(
        order = 4,
        title = "Никакой возьни с бумажками",
        description = "Заполнение листа с анамнезом и верификация личности в чате в день приёма"
    ),
    override val favoriteClinics: SubscriptionBenefit = SubscriptionBenefit(
        order = 5,
        title = "Избранные клиники",
        description = "Скидка 10% в любимых клиниках"
    ),
    override val decodingAnalysis: SubscriptionBenefit = SubscriptionBenefit(
        order = 6,
        title = "Расшифровка документов",
        description = "Две расшифровки в месяц. Нажмите, чтобы узнать подробнее, какие услуги расшифровки доступны в данном тарифе"
    ),
    override val paidQuestions: SubscriptionBenefit = SubscriptionBenefit(
        order = 7,
        title = "Вопрос любому доктору",
        description = "Приоритет вашего диалога - высокий, выделение чата у доктора цветом. Доктор ответит в течение трёх дней"
    )
): StandardPlanBenefits(language = Language.Russian)

/**
 * UKRAINIAN
 */
internal data class StandardPlanBenefitsUkrainian(
    override val firstVisitDiscount: SubscriptionBenefit = SubscriptionBenefit(
        order = 1,
        title = "Перший раз дешевше",
        description = "Знижка 10% на перший візит"
    ),
    override val appointmentsCount: SubscriptionBenefit = SubscriptionBenefit(
        order = 2,
        title = "Кількість записів",
        description = "Запис до трьох лікарів на місяць"
    ),
    override val chatDescription: SubscriptionBenefit = SubscriptionBenefit(
        order = 3,
        title = "Чат із лікарем",
        description = "Чат із лікарем відкривається одразу після запису на прийом, але зникає після завершення прийому"
    ),
    override val welcomePaper: SubscriptionBenefit = SubscriptionBenefit(
        order = 4,
        title = "Ніякої тяганини з паперами",
        description = "Заповнення анкети анамнезу та верифікація особи в чаті в день прийому"
    ),
    override val favoriteClinics: SubscriptionBenefit = SubscriptionBenefit(
        order = 5,
        title = "Улюблені клініки",
        description = "Знижка 10% в улюблених клініках"
    ),
    override val decodingAnalysis: SubscriptionBenefit = SubscriptionBenefit(
        order = 6,
        title = "Розшифровка документів",
        description = "Дві розшифровки аналізів на місяць. Натисніть, щоб дізнатися більше про доступні послуги розшифрування"
    ),
    override val paidQuestions: SubscriptionBenefit = SubscriptionBenefit(
        order = 7,
        title = "Запитання будь-якому лікарю",
        description = "Пріоритет вашого діалогу - високий, лікар бачить виділення вашого чату кольором. Лікар відповість протягом трьох днів"
    )
): StandardPlanBenefits(language = Language.Ukrainian)
