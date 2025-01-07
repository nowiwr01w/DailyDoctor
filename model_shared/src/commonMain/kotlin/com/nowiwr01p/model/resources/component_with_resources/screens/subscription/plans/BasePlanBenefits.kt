package com.nowiwr01p.model.resources.component_with_resources.screens.subscription.plans

import com.nowiwr01p.model.model.subscription.benefits.SubscriptionBenefit
import com.nowiwr01p.model.model.subscription.benefits.SubscriptionBenefits.CommonSubscriptionPlanBenefits
import com.nowiwr01p.model.model.subscription.type.SubscriptionPlanType.Base
import com.nowiwr01p.model.resources.language.Language

abstract class BasePlanBenefits(override val language: Language): CommonSubscriptionPlanBenefits(
    type = Base,
    language = language
)

/**
 * GEORGIAN
 */
internal data class BasePlanBenefitsGeorgian(
    override val firstVisitDiscount: SubscriptionBenefit = SubscriptionBenefit(
        order = 1,
        title = "პირველი ვიზიტი იაფია",
        description = "5%-იანი ფასდაკლება პირველ ვიზიტზე"
    ),
    override val appointmentsCount: SubscriptionBenefit = SubscriptionBenefit(
        order = 2,
        title = "ჩანაწერების რაოდენობა",
        description = "თვეში ორი ექიმის მიღება"
    ),
    override val chatDescription: SubscriptionBenefit = SubscriptionBenefit(
        order = 3,
        title = "ჩატი ექიმთან",
        description = "ექიმთან ჩატი იხსნება ვიზიტის დღეს და ქრება ვიზიტის დასრულების შემდეგ"
    ),
    override val welcomePaper: SubscriptionBenefit = SubscriptionBenefit(
        order = 4,
        title = "არანაირი აურზაური ქაღალდებით",
        description = "ანამნეზის შევსება ჩატში ვიზიტის დღეს, მაგრამ პიროვნების იდენტიფიკაცია კლინიკაში"
    ),
    override val favoriteClinics: SubscriptionBenefit = SubscriptionBenefit(
        order = 5,
        title = "რჩეული კლინიკები",
        description = "5%-იანი ფასდაკლება საყვარელ კლინიკებში"
    ),
    override val decodingAnalysis: SubscriptionBenefit = SubscriptionBenefit(
        order = 6,
        title = "დოკუმენტების დეკოდირება",
        description = "ერთჯერადი ანალიზის დეკოდირება თვეში. დააჭირეთ, რომ გაიგოთ მეტი, თუ რა მომსახურებაა ხელმისაწვდომი ამ გეგმაში"
    ),
    override val paidQuestions: SubscriptionBenefit = SubscriptionBenefit(
        order = 7,
        title = "კითხვა ნებისმიერ ექიმს",
        description = "თქვენი დიალოგის პრიორიტეტი - ნორმალური, ექიმს არ აქვს თქვენი ჩატი გამოყოფილი. ექიმი პასუხს გაგცემთ კვირის განმავლობაში"
    )
): BasePlanBenefits(language = Language.Georgian)

/**
 * ENGLISH
 */
internal data class BasePlanBenefitsEnglish(
    override val firstVisitDiscount: SubscriptionBenefit = SubscriptionBenefit(
        order = 1,
        title = "First time cheaper",
        description = "5% discount on the first visit"
    ),
    override val appointmentsCount: SubscriptionBenefit = SubscriptionBenefit(
        order = 2,
        title = "Number of appointments",
        description = "Appointment with two doctors per month"
    ),
    override val chatDescription: SubscriptionBenefit = SubscriptionBenefit(
        order = 3,
        title = "Chat with the doctor",
        description = "The doctor chat opens on the day of the appointment and disappears after the appointment ends"
    ),
    override val welcomePaper: SubscriptionBenefit = SubscriptionBenefit(
        order = 4,
        title = "No hassle with paperwork",
        description = "Filling out the medical history form in chat on the day of the appointment, but identity verification at the clinic"
    ),
    override val favoriteClinics: SubscriptionBenefit = SubscriptionBenefit(
        order = 5,
        title = "Favorite clinics",
        description = "5% discount at favorite clinics"
    ),
    override val decodingAnalysis: SubscriptionBenefit = SubscriptionBenefit(
        order = 6,
        title = "Document decoding",
        description = "One analysis decoding per month. Click to learn more about the decoding services available in this plan"
    ),
    override val paidQuestions: SubscriptionBenefit = SubscriptionBenefit(
        order = 7,
        title = "A question to any doctor",
        description = "Your dialogue priority is normal, and the doctor does not have your chat highlighted. The doctor will respond within a week"
    )
): BasePlanBenefits(language = Language.English)

/**
 * RUSSIAN
 */
internal data class BasePlanBenefitsRussian(
    override val firstVisitDiscount: SubscriptionBenefit = SubscriptionBenefit(
        order = 1,
        title = "Первый раз дешевле",
        description = "Скидка 5% на первый приём"
    ),
    override val appointmentsCount: SubscriptionBenefit = SubscriptionBenefit(
        order = 2,
        title = "Количество записей",
        description = "Запись к двум врачам в месяц"
    ),
    override val chatDescription: SubscriptionBenefit = SubscriptionBenefit(
        order = 3,
        title = "Чат с доктором",
        description = "Чат с доктором открывается в день приёма и исчезает после того, как приём закончится"
    ),
    override val welcomePaper: SubscriptionBenefit = SubscriptionBenefit(
        order = 4,
        title = "Никакой возьни с бумажками",
        description = "Заполнение листа с анамнезом в чате в день приёма, но верификация личности в клинике"
    ),
    override val favoriteClinics: SubscriptionBenefit = SubscriptionBenefit(
        order = 5,
        title = "Избранные клиники",
        description = "Скидка 5% в любимых клиниках"
    ),
    override val decodingAnalysis: SubscriptionBenefit = SubscriptionBenefit(
        order = 6,
        title = "Расшифровка документов",
        description = "Одна расшифровка в месяц. Нажмите, чтобы узнать подробнее, какие услуги расшифровки доступны в данном тарифе"
    ),
    override val paidQuestions: SubscriptionBenefit = SubscriptionBenefit(
        order = 7,
        title = "Вопрос любому доктору",
        description = "Приоритет вашего диалога - обычный, у доктора отсутствует выделение вашего чата цветом. Доктор ответит в течение недели"
    )
): BasePlanBenefits(language = Language.Russian)

/**
 * UKRAINIAN
 */
internal data class BasePlanBenefitsUkrainian(
    override val firstVisitDiscount: SubscriptionBenefit = SubscriptionBenefit(
        order = 1,
        title = "Перший раз дешевше",
        description = "Знижка 5% на перший візит"
    ),
    override val appointmentsCount: SubscriptionBenefit = SubscriptionBenefit(
        order = 2,
        title = "Кількість записів",
        description = "Запис до двох лікарів на місяць"
    ),
    override val chatDescription: SubscriptionBenefit = SubscriptionBenefit(
        order = 3,
        title = "Чат із лікарем",
        description = "Чат із лікарем відкривається в день прийому та зникає після завершення прийому"
    ),
    override val welcomePaper: SubscriptionBenefit = SubscriptionBenefit(
        order = 4,
        title = "Ніякої тяганини з паперами",
        description = "Заповнення анкети анамнезу в чаті в день прийому, але верифікація особи в клініці"
    ),
    override val favoriteClinics: SubscriptionBenefit = SubscriptionBenefit(
        order = 5,
        title = "Улюблені клініки",
        description = "Знижка 5% в улюблених клініках"
    ),
    override val decodingAnalysis: SubscriptionBenefit = SubscriptionBenefit(
        order = 6,
        title = "Розшифровка документів",
        description = "Один розшифрування аналізу на місяць. Натисніть, щоб дізнатися більше про доступні послуги розшифрування"
    ),
    override val paidQuestions: SubscriptionBenefit = SubscriptionBenefit(
        order = 7,
        title = "Запитання будь-якому лікарю",
        description = "Пріоритет вашого діалогу - звичайний, лікар не бачить виділення вашого чату кольором. Лікар відповість протягом тижня"
    )
): BasePlanBenefits(language = Language.Ukrainian)
