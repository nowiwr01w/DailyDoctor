package screen.home.populars.data

internal open class Category(
    val name: String,
    val value: String,
    val categories: List<CategoryData>
)

internal data class CategoryData(
    val name: String,
    val value: Int
)

internal class DoctorsCategory: Category(
    name = "Врачи",
    value = "113 149",
    categories = listOf(
        CategoryData("Гинеколог", 6233),
        CategoryData("Дерматолог", 3409),
        CategoryData("Невролог", 3957),
        CategoryData("Педиатр", 6443),
        CategoryData("Стоматолог",8509)
    )
)

internal class ClinicsCategory: Category(
    name = "Клиники",
    value = "6 973",
    categories = listOf(
        CategoryData("Клиника", 899),
        CategoryData("Стомотология", 2146),
        CategoryData("Больница", 131),
        CategoryData("Поликлиника", 309),
        CategoryData("Роддом",21)
    )
)

internal class ServicesCategory: Category(
    name = "Услуги",
    value = "173 927",
    categories = listOf(
        CategoryData("ЭКО", 76),
        CategoryData("Ринопластика", 151),
        CategoryData("Прививки", 480),
        CategoryData("Импланты", 1970),
        CategoryData("Брекеты",1401)
    )
)

internal class ProceduresCategory: Category(
    name = "Процедуры",
    value = "109 252",
    categories = listOf(
        CategoryData("МРТ", 316),
        CategoryData("КТ", 509),
        CategoryData("УЗИ", 1814),
        CategoryData("Рентген", 661),
        CategoryData("Гастроскопия",475)
    )
)

internal val categories = listOf(
    DoctorsCategory(),
    ClinicsCategory(),
    ServicesCategory(),
    ProceduresCategory()
)