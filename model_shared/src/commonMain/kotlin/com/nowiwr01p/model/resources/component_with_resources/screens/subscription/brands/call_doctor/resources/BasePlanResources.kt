package com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources

import com.nowiwr01p.model.model.subscription.type.SubscriptionType
import com.nowiwr01p.model.model.subscription.type.SubscriptionType.Base
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.base.BaseSubscriptionBenefits
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.base.SubscriptionBenefitData

abstract class BasePlanResources: BaseSubscriptionBenefits {
    override val type: SubscriptionType = Base
}

/**
 * GEORGIAN
 */
internal data class BasePlanResourcesGeorgian(
    override val firstVisitDiscount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "პირველი ვიზიტი იაფია",
        description = "5%-იანი ფასდაკლება პირველ ვიზიტზე"
    ),
    override val appointmentsCount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "ჩანაწერების რაოდენობა",
        description = "თვეში ორი ექიმის მიღება"
    ),
    override val chatDescription: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "ჩატი ექიმთან",
        description = "ექიმთან ჩატი იხსნება ვიზიტის დღეს და ქრება ვიზიტის დასრულების შემდეგ"
    ),
    override val welcomePaper: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "არანაირი აურზაური ქაღალდებით",
        description = "ანამნეზის შევსება ჩატში ვიზიტის დღეს, მაგრამ პიროვნების იდენტიფიკაცია კლინიკაში"
    ),
    override val favoriteClinics: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "რჩეული კლინიკები",
        description = "5%-იანი ფასდაკლება საყვარელ კლინიკებში"
    ),
    override val decodingAnalysis: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "დოკუმენტების დეკოდირება",
        description = "ერთჯერადი ანალიზის დეკოდირება თვეში. დააჭირეთ, რომ გაიგოთ მეტი, თუ რა მომსახურებაა ხელმისაწვდომი ამ გეგმაში"
    ),
    override val paidQuestions: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "კითხვა ნებისმიერ ექიმს",
        description = "თქვენი დიალოგის პრიორიტეტი - ნორმალური, ექიმს არ აქვს თქვენი ჩატი გამოყოფილი. ექიმი პასუხს გაგცემთ კვირის განმავლობაში"
    )
): BasePlanResources()

/**
 * ENGLISH
 */
internal data class BasePlanResourcesEnglish(
    override val firstVisitDiscount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "First time cheaper",
        description = "5% discount on the first visit"
    ),
    override val appointmentsCount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Number of appointments",
        description = "Appointment with two doctors per month"
    ),
    override val chatDescription: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Chat with the doctor",
        description = "The doctor chat opens on the day of the appointment and disappears after the appointment ends"
    ),
    override val welcomePaper: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "No hassle with paperwork",
        description = "Filling out the medical history form in chat on the day of the appointment, but identity verification at the clinic"
    ),
    override val favoriteClinics: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Favorite clinics",
        description = "5% discount at favorite clinics"
    ),
    override val decodingAnalysis: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Document decoding",
        description = "One analysis decoding per month. Click to learn more about the decoding services available in this plan"
    ),
    override val paidQuestions: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "A question to any doctor",
        description = "Your dialogue priority is normal, and the doctor does not have your chat highlighted. The doctor will respond within a week"
    )
): BasePlanResources()

/**
 * RUSSIAN
 */
internal data class BasePlanResourcesRussian(
    override val firstVisitDiscount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Первый раз дешевле",
        description = "Скидка 5% на первый приём"
    ),
    override val appointmentsCount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Количество записей",
        description = "Запись к двум врачам в месяц"
    ),
    override val chatDescription: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Чат с доктором",
        description = "Чат с доктором открывается в день приёма и исчезает после того, как приём закончится"
    ),
    override val welcomePaper: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Никакой возьни с бумажками",
        description = "Заполнение листа с анамнезом в чате в день приёма, но верификация личности в клинике"
    ),
    override val favoriteClinics: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Избранные клиники",
        description = "Скидка 5% в любимых клиниках"
    ),
    override val decodingAnalysis: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Расшифровка документов",
        description = "Одна расшифровка в месяц. Нажмите, чтобы узнать подробнее, какие услуги расшифровки доступны в данном тарифе"
    ),
    override val paidQuestions: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Вопрос любому доктору",
        description = "Приоритет вашего диалога - обычный, у доктора отсутствует выделение вашего чата цветом. Доктор ответит в течение недели"
    )
): BasePlanResources()

/**
 * UKRAINIAN
 */
internal data class BasePlanResourcesUkrainian(
    override val firstVisitDiscount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Перший раз дешевше",
        description = "Знижка 5% на перший візит"
    ),
    override val appointmentsCount: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Кількість записів",
        description = "Запис до двох лікарів на місяць"
    ),
    override val chatDescription: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Чат із лікарем",
        description = "Чат із лікарем відкривається в день прийому та зникає після завершення прийому"
    ),
    override val welcomePaper: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Ніякої тяганини з паперами",
        description = "Заповнення анкети анамнезу в чаті в день прийому, але верифікація особи в клініці"
    ),
    override val favoriteClinics: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Улюблені клініки",
        description = "Знижка 5% в улюблених клініках"
    ),
    override val decodingAnalysis: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Розшифровка документів",
        description = "Один розшифрування аналізу на місяць. Натисніть, щоб дізнатися більше про доступні послуги розшифрування"
    ),
    override val paidQuestions: SubscriptionBenefitData = SubscriptionBenefitData(
        title = "Запитання будь-якому лікарю",
        description = "Пріоритет вашого діалогу - звичайний, лікар не бачить виділення вашого чату кольором. Лікар відповість протягом тижня"
    )
): BasePlanResources()
