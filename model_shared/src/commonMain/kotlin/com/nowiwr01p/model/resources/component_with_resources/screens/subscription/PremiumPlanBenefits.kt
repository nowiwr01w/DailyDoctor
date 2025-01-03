package com.nowiwr01p.model.resources.component_with_resources.screens.subscription

import com.nowiwr01p.model.model.subscription.benefits.SubscriptionBenefit
import com.nowiwr01p.model.model.subscription.benefits.SubscriptionBenefits.CommonSubscriptionPlanBenefits
import com.nowiwr01p.model.model.subscription.type.SubscriptionPlanType.Premium
import com.nowiwr01p.model.resources.language.Language

abstract class PremiumPlanBenefits(override val language: Language): CommonSubscriptionPlanBenefits(
    type = Premium,
    language = language
)

/**
 * GEORGIAN
 */
internal data class PremiumPlanBenefitsGeorgian(
    override val firstVisitDiscount: SubscriptionBenefit = SubscriptionBenefit(
        order = 1,
        title = "პირველი ვიზიტი იაფია",
        description = "15%-იანი ფასდაკლება პირველ ვიზიტზე"
    ),
    override val appointmentsCount: SubscriptionBenefit = SubscriptionBenefit(
        order = 2,
        title = "ჩანაწერების რაოდენობა",
        description = "თვეში ხუთი ექიმის მიღება"
    ),
    override val chatDescription: SubscriptionBenefit = SubscriptionBenefit(
        order = 3,
        title = "ჩატი ექიმთან",
        description = "ექიმთან ჩატი იხსნება ვიზიტის დაჯავშნისთანავე და არასოდეს ქრება, თუ თავად არ წაშლით"
    ),
    override val welcomePaper: SubscriptionBenefit = SubscriptionBenefit(
        order = 4,
        title = "არანაირი აურზაური ქაღალდებით",
        description = "ანამნეზის შევსება და პიროვნების იდენტიფიკაცია ჩატში ნებისმიერ დროს ვიზიტის დაჯავშნის შემდეგ"
    ),
    override val favoriteClinics: SubscriptionBenefit = SubscriptionBenefit(
        order = 5,
        title = "რჩეული კლინიკები",
        description = "15%-იანი ფასდაკლება საყვარელ კლინიკებში"
    ),
    override val decodingAnalysis: SubscriptionBenefit = SubscriptionBenefit(
        order = 6,
        title = "დოკუმენტების დეკოდირება",
        description = "სამი ანალიზის დეკოდირება თვეში. დააჭირეთ, რომ გაიგოთ მეტი, თუ რა მომსახურებაა ხელმისაწვდომი ამ გეგმაში"
    ),
    override val paidQuestions: SubscriptionBenefit = SubscriptionBenefit(
        order = 7,
        title = "კითხვა ნებისმიერ ექიმს",
        description = "თქვენი ჩატი ექიმისთვის პირველ პოზიციებზეა და მკვეთრად გამოყოფილია. ექიმი პასუხს გაგცემთ იმავე დღეს"
    )
): PremiumPlanBenefits(language = Language.Georgian)

/**
 * ENGLISH
 */
internal data class PremiumPlanBenefitsEnglish(
    override val firstVisitDiscount: SubscriptionBenefit = SubscriptionBenefit(
        order = 1,
        title = "First time cheaper",
        description = "15% discount on the first visit"
    ),
    override val appointmentsCount: SubscriptionBenefit = SubscriptionBenefit(
        order = 2,
        title = "Number of appointments",
        description = "Appointment with five doctors per month"
    ),
    override val chatDescription: SubscriptionBenefit = SubscriptionBenefit(
        order = 3,
        title = "Chat with the doctor",
        description = "The doctor chat opens immediately after booking an appointment and never disappears unless you delete it"
    ),
    override val welcomePaper: SubscriptionBenefit = SubscriptionBenefit(
        order = 4,
        title = "No hassle with paperwork",
        description = "Filling out the medical history form and identity verification in chat anytime after booking an appointment"
    ),
    override val favoriteClinics: SubscriptionBenefit = SubscriptionBenefit(
        order = 5,
        title = "Favorite clinics",
        description = "15% discount at favorite clinics"
    ),
    override val decodingAnalysis: SubscriptionBenefit = SubscriptionBenefit(
        order = 6,
        title = "Document decoding",
        description = "Three analysis decodings per month. Click to learn more about the decoding services available in this plan"
    ),
    override val paidQuestions: SubscriptionBenefit = SubscriptionBenefit(
        order = 7,
        title = "A question to any doctor",
        description = "Your chat is at the top positions, highlighted brightly for the doctor. The doctor will respond on the same day"
    )
): PremiumPlanBenefits(language = Language.English)

/**
 * RUSSIAN
 */
internal data class PremiumPlanBenefitsRussian(
    override val firstVisitDiscount: SubscriptionBenefit = SubscriptionBenefit(
        order = 1,
        title = "Первый раз дешевле",
        description = "Скидка 15% на первый приём"
    ),
    override val appointmentsCount: SubscriptionBenefit = SubscriptionBenefit(
        order = 2,
        title = "Количество записей",
        description = "Запись к пяти врачам в месяц"
    ),
    override val chatDescription: SubscriptionBenefit = SubscriptionBenefit(
        order = 3,
        title = "Чат с доктором",
        description = "Чат с доктором открывается сразу после записи на приём и никогда не исчезает, если только вы его не удалите"
    ),
    override val welcomePaper: SubscriptionBenefit = SubscriptionBenefit(
        order = 4,
        title = "Никакой возьни с бумажками",
        description = "Заполнение листа с анамнезом и верификация личности в чате в любое время после записи на приём"
    ),
    override val favoriteClinics: SubscriptionBenefit = SubscriptionBenefit(
        order = 5,
        title = "Избранные клиники",
        description = "Скидка 15% в любимых клиниках"
    ),
    override val decodingAnalysis: SubscriptionBenefit = SubscriptionBenefit(
        order = 6,
        title = "Расшифровка документов",
        description = "Три расшифровки в месяц. Нажмите, чтобы узнать подробнее, какие услуги расшифровки доступны в данном тарифе"
    ),
    override val paidQuestions: SubscriptionBenefit = SubscriptionBenefit(
        order = 7,
        title = "Вопрос любому доктору",
        description = "Ваш чат находится на первых позициях, выделение чата у доктора ярким цветом. Доктор ответит в этот же день"
    )
): PremiumPlanBenefits(language = Language.Russian)

/**
 * UKRAINIAN
 */
internal data class PremiumPlanBenefitsUkrainian(
    override val firstVisitDiscount: SubscriptionBenefit = SubscriptionBenefit(
        order = 1,
        title = "Перший раз дешевше",
        description = "Знижка 15% на перший візит"
    ),
    override val appointmentsCount: SubscriptionBenefit = SubscriptionBenefit(
        order = 2,
        title = "Кількість записів",
        description = "Запис до п'яти лікарів на місяць"
    ),
    override val chatDescription: SubscriptionBenefit = SubscriptionBenefit(
        order = 3,
        title = "Чат із лікарем",
        description = "Чат із лікарем відкривається одразу після запису на прийом і ніколи не зникає, якщо тільки ви його не видалите"
    ),
    override val welcomePaper: SubscriptionBenefit = SubscriptionBenefit(
        order = 4,
        title = "Ніякої тяганини з паперами",
        description = "Заповнення анкети анамнезу та верифікація особи в чаті в будь-який час після запису на прийом"
    ),
    override val favoriteClinics: SubscriptionBenefit = SubscriptionBenefit(
        order = 5,
        title = "Улюблені клініки",
        description = "Знижка 15% в улюблених клініках"
    ),
    override val decodingAnalysis: SubscriptionBenefit = SubscriptionBenefit(
        order = 6,
        title = "Розшифровка документів",
        description = "Три розшифровки аналізів на місяць. Натисніть, щоб дізнатися більше про доступні послуги розшифрування"
    ),
    override val paidQuestions: SubscriptionBenefit = SubscriptionBenefit(
        order = 7,
        title = "Запитання будь-якому лікарю",
        description = "Ваш чат знаходиться на перших позиціях, виділений яскравим кольором для лікаря. Лікар відповість у той же день"
    )
): PremiumPlanBenefits(language = Language.Ukrainian)
