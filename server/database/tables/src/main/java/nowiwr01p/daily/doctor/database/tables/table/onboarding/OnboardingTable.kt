package nowiwr01p.daily.doctor.database.tables.table.onboarding

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

object OnboardingTable : UUIDTable("onboardings") {
    val languageCode = varchar("language_code", 5)
    val position = integer("position")
    val image = varchar("image", 255)
    val title = varchar("title", 255)
    val description = varchar("description", 1024)
    val firstButtonText = varchar("first_button_text", 255)
    val secondButtonText = varchar("second_button_text", 255)

    init {
        uniqueIndex(position, languageCode)
    }
}

class OnboardingEntity(id: EntityID<UUID>): UUIDEntity(id) {
    var languageCode by OnboardingTable.languageCode
    var position by OnboardingTable.position
    var image by OnboardingTable.image
    var title by OnboardingTable.title
    var description by OnboardingTable.description
    var firstButtonText by OnboardingTable.firstButtonText
    var secondButtonText by OnboardingTable.secondButtonText

    companion object: UUIDEntityClass<OnboardingEntity>(OnboardingTable)
}