package nowiwr01p.daily.doctor.database.tables.table.onboarding

import nowiwr01p.daily.doctor.database.tables.table.brand.BrandTable
import org.jetbrains.exposed.dao.id.UUIDTable

object OnboardingTable : UUIDTable("onboardings") {

    val brand = reference("brand_id", BrandTable)
    val languageCode = varchar("language_code", 5)
    val position = integer("position")
    val image = varchar("image", 255)
    val title = varchar("title", 255)
    val description = varchar("description", 1024)
    val firstButtonText = varchar("first_button_text", 255)
    val secondButtonText = varchar("second_button_text", 255)

    init {
        uniqueIndex(brand, position, languageCode)
    }
}