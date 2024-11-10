package nowiwr01p.daily.doctor.database.init_values.data.onboarding.onboardings

import com.nowiwr01p.model.model.app_config.config.BrandConfigType.CALL_DOCTOR_CONFIG_TYPE
import nowiwr01p.daily.doctor.database.init_values.domain.onboarding.onboardings.DatabaseInitCallDoctorOnboardingDataUseCase
import nowiwr01p.daily.doctor.database.tables.table.brand.BrandTable
import nowiwr01p.daily.doctor.database.tables.table.onboarding.OnboardingTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

class DatabaseInitCallDoctorOnboardingDataUseCaseImpl : DatabaseInitCallDoctorOnboardingDataUseCase {

    override suspend fun execute(input: Unit) {
        val brandId = getBrandId(CALL_DOCTOR_CONFIG_TYPE.type)
        val newData = buildOnboardingData(brandId).sortWithUniqueKey()
        val existingData = getExistingOnboardingData(brandId).sortWithUniqueKey()
        if (existingData != newData) {
            deleteExistingOnboardingData(brandId)
            insertOnboardingData(newData)
        }
    }

    /**
     * BRAND
     */
    private fun getBrandId(brandName: String) = transaction {
        BrandTable.selectAll()
            .where { BrandTable.brandName eq brandName }
            .single()[BrandTable.id].value
    }

    private fun List<OnboardingItemData>.sortWithUniqueKey() = sortedBy { item ->
        with(item) { "$brandId|$languageCode|$position" }
    }

    /**
     * EXISTING DATA
     */
    private fun getExistingOnboardingData(brandId: UUID) = transaction {
        OnboardingTable.selectAll()
            .where { OnboardingTable.brand eq brandId }
            .map { row ->
                OnboardingItemData(
                    brandId = row[OnboardingTable.brand].value,
                    languageCode = row[OnboardingTable.languageCode],
                    position = row[OnboardingTable.position],
                    image = row[OnboardingTable.image],
                    title = row[OnboardingTable.title],
                    description = row[OnboardingTable.description],
                    firstButtonText = row[OnboardingTable.firstButtonText],
                    secondButtonText = row[OnboardingTable.secondButtonText]
                )
            }
    }

    private fun deleteExistingOnboardingData(brandId: UUID) = transaction {
        OnboardingTable.deleteWhere { brand eq brandId }
    }

    private fun insertOnboardingData(data: List<OnboardingItemData>) = transaction {
        data.forEach { item ->
            OnboardingTable.insert {
                it[brand] = item.brandId
                it[languageCode] = item.languageCode
                it[position] = item.position
                it[image] = item.image
                it[title] = item.title
                it[description] = item.description
                it[firstButtonText] = item.firstButtonText
                it[secondButtonText] = item.secondButtonText
            }
        }
    }

    /**
     * RELEVANT DATA
     */
    private fun buildOnboardingData(brandId: UUID) = buildList {
        addAll(getRussianOnboardingData(brandId))
        addAll(getEnglishOnboardingData(brandId))
        addAll(getGeorgianOnboardingData(brandId))
    }

    /**
     * RUSSIAN
     */
    private fun getRussianOnboardingData(brandId: UUID) = listOf(
        OnboardingItemData(
            brandId = brandId,
            languageCode = "ru",
            position = 1,
            image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
            title = "Онлайн-консультации с врачами в чате",
            description = "Дома, на даче, в отпуске или даже на работе",
            firstButtonText = "Очень интересно!",
            secondButtonText = ""
        ),
        OnboardingItemData(
            brandId = brandId,
            languageCode = "ru",
            position = 2,
            image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
            title = "Безлимитные обращения - мы на связи 24/7",
            description = "Пишите, когда болит или когда нужно просто спросить",
            firstButtonText = "Класс, а что еще?",
            secondButtonText = ""
        ),
        OnboardingItemData(
            brandId = brandId,
            languageCode = "ru",
            position = 3,
            image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
            title = "Для всей семьи - себе, детям и родителям",
            description = "И мужу, который сам бы не пошел к врачу",
            firstButtonText = "Это мне надо",
            secondButtonText = ""
        ),
        OnboardingItemData(
            brandId = brandId,
            languageCode = "ru",
            position = 4,
            image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
            title = "Экономия на очных визитах к врачу",
            description = "А еще эксклюзивные скидки на анализы до 25%",
            firstButtonText = "Отлично!",
            secondButtonText = ""
        ),
        OnboardingItemData(
            brandId = brandId,
            languageCode = "ru",
            position = 5,
            image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
            title = "Не пропустите ответ врача на ваше сообщение",
            description = "И спокойно занимайтесь другими делами",
            firstButtonText = "Разрешить уведомления",
            secondButtonText = "Не сейчас"
        )
    )

    /**
     * ENGLISH
     */
    private fun getEnglishOnboardingData(brandId: UUID) = listOf(
        OnboardingItemData(
            brandId = brandId,
            languageCode = "en",
            position = 1,
            image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
            title = "Online consultations with doctors in chat",
            description = "At home, at the cottage, on vacation, or even at work",
            firstButtonText = "Very interesting!",
            secondButtonText = ""
        ),
        OnboardingItemData(
            brandId = brandId,
            languageCode = "en",
            position = 2,
            image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
            title = "Unlimited requests - we are available 24/7",
            description = "Write when it hurts or when you just need to ask",
            firstButtonText = "Great, what else?",
            secondButtonText = ""
        ),
        OnboardingItemData(
            brandId = brandId,
            languageCode = "en",
            position = 3,
            image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
            title = "For the whole family - yourself, children, and parents",
            description = "And for the husband who wouldn't go to the doctor himself",
            firstButtonText = "I need this",
            secondButtonText = ""
        ),
        OnboardingItemData(
            brandId = brandId,
            languageCode = "en",
            position = 4,
            image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
            title = "Savings on in-person doctor visits",
            description = "And exclusive discounts on tests up to 25%",
            firstButtonText = "Excellent!",
            secondButtonText = ""
        ),
        OnboardingItemData(
            brandId = brandId,
            languageCode = "en",
            position = 5,
            image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
            title = "Don't miss the doctor's response to your message",
            description = "And calmly do other things",
            firstButtonText = "Allow notifications",
            secondButtonText = "Not now"
        )
    )

    /**
     * GEORGIAN
     */
    private fun getGeorgianOnboardingData(brandId: UUID) = listOf(
        OnboardingItemData(
            brandId = brandId,
            languageCode = "ka",
            position = 1,
            image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
            title = "ონლაინ კონსულტაციები ექიმებთან ჩათში",
            description = "სახლში, აგარაკზე, შვებულებაში ან სამსახურში",
            firstButtonText = "ძალიან საინტერესოა!",
            secondButtonText = ""
        ),
        OnboardingItemData(
            brandId = brandId,
            languageCode = "ka",
            position = 2,
            image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
            title = "შეუზღუდავი მოთხოვნები - ჩვენ 24/7 ხელმისაწვდომები ვართ",
            description = "დაწერეთ, როცა გტკივათ ან უბრალოდ გჭირდებათ კითხვა",
            firstButtonText = "ძალიან კარგი, კიდევ რა?",
            secondButtonText = ""
        ),
        OnboardingItemData(
            brandId = brandId,
            languageCode = "ka",
            position = 3,
            image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
            title = "მთელი ოჯახისთვის - თქვენთვის, ბავშვებისთვის და მშობლებისთვის",
            description = "და ქმრისთვის, რომელიც თავად ექიმთან არ წავიდოდა",
            firstButtonText = "ეს მჭირდება",
            secondButtonText = ""
        ),
        OnboardingItemData(
            brandId = brandId,
            languageCode = "ka",
            position = 4,
            image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
            title = "დაზოგვა ექიმთან პირადი ვიზიტებისას",
            description = "და ექსკლუზიური ფასდაკლებები ანალიზებზე 25%-მდე",
            firstButtonText = "შესანიშნავია!",
            secondButtonText = ""
        ),
        OnboardingItemData(
            brandId = brandId,
            languageCode = "ka",
            position = 5,
            image = "https://s1.coincarp.com/logo/1/toncoin.png?style=200&v=1672976475",
            title = "არ გამოტოვოთ ექიმის პასუხი თქვენს შეტყობინებაზე",
            description = "და მშვიდად დაკავდით სხვა საქმეებით",
            firstButtonText = "ნება დართეთ შეტყობინებებს",
            secondButtonText = "ახლა არა"
        )
    )
}

private data class OnboardingItemData(
    val brandId: UUID,
    val languageCode: String,
    val position: Int,
    val image: String,
    val title: String,
    val description: String,
    val firstButtonText: String,
    val secondButtonText: String
)