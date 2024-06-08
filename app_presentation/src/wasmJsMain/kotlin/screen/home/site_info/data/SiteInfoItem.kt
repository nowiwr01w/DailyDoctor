package screen.home.site_info.data

internal data class SiteInfoItem(
    val title: String,
    val items: List<String>
)

private val aboutUsInfo = SiteInfoItem(
    title = "О нас",
    items = listOf(
        "О компании",
        "Карьера",
        "Партнёры",
        "Отзывы о нас",
        "Пресса",
        "Контакты"
    )
)

private val forClinicsAndDoctorsInfo = SiteInfoItem(
    title = "Клиникам и врачам",
    items = listOf(
        "Привлечение паниентов в клинику",
        "Сотрудничество с врачами",
        "Сотрудничество с клиниками",
        "Правила сотрудничества с клиниками",
        "Персональные данные врачей",
        "Вакансии для онлайн клиники"
    )
)

private val serviceRulesInfo = SiteInfoItem(
    title = "Правила сервиса",
    items = listOf(
        "Правила использования сервиса",
        "Персональные данные пользователей",
        "Использование материалов сайта",
        "Ответственность за информацию",
        "Программа лояльности",
        "Контакты"
    )
)

private val forUsersInfo = SiteInfoItem(
    title = "Пользователям",
    items = listOf(
        "Как записаться на приём",
        "Почему лечиться с нами выгодно",
        "Как мы проверяем отзывы",
        "Защита ваших данных",
        "Подарочная карта",
        "Преимущества подписки"
    )
)

internal val information = listOf(
    aboutUsInfo,
    forClinicsAndDoctorsInfo,
    forUsersInfo,
    serviceRulesInfo
)