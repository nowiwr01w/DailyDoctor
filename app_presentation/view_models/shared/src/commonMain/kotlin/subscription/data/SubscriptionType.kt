package subscription.data

import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.ic_subscription_base
import nowiwr01p.daily.doctor.resources.ic_subscription_free
import nowiwr01p.daily.doctor.resources.ic_subscription_premium
import nowiwr01p.daily.doctor.resources.ic_subscription_standard
import nowiwr01p.daily.doctor.resources.subscription_base_title
import nowiwr01p.daily.doctor.resources.subscription_benefit_appointments_count_base
import nowiwr01p.daily.doctor.resources.subscription_benefit_appointments_count_premium
import nowiwr01p.daily.doctor.resources.subscription_benefit_appointments_count_standard
import nowiwr01p.daily.doctor.resources.subscription_benefit_appointments_count_title
import nowiwr01p.daily.doctor.resources.subscription_benefit_chat_base
import nowiwr01p.daily.doctor.resources.subscription_benefit_chat_premium
import nowiwr01p.daily.doctor.resources.subscription_benefit_chat_standard
import nowiwr01p.daily.doctor.resources.subscription_benefit_chat_title
import nowiwr01p.daily.doctor.resources.subscription_benefit_decoding_analysis_base
import nowiwr01p.daily.doctor.resources.subscription_benefit_decoding_analysis_premium
import nowiwr01p.daily.doctor.resources.subscription_benefit_decoding_analysis_standard
import nowiwr01p.daily.doctor.resources.subscription_benefit_decoding_analysis_title
import nowiwr01p.daily.doctor.resources.subscription_benefit_favorite_clinics_base
import nowiwr01p.daily.doctor.resources.subscription_benefit_favorite_clinics_premium
import nowiwr01p.daily.doctor.resources.subscription_benefit_favorite_clinics_standard
import nowiwr01p.daily.doctor.resources.subscription_benefit_favorite_clinics_title
import nowiwr01p.daily.doctor.resources.subscription_benefit_first_visit_discounts_base
import nowiwr01p.daily.doctor.resources.subscription_benefit_first_visit_discounts_premium
import nowiwr01p.daily.doctor.resources.subscription_benefit_first_visit_discounts_standard
import nowiwr01p.daily.doctor.resources.subscription_benefit_first_visit_discounts_title
import nowiwr01p.daily.doctor.resources.subscription_benefit_paid_questions_base
import nowiwr01p.daily.doctor.resources.subscription_benefit_paid_questions_premium
import nowiwr01p.daily.doctor.resources.subscription_benefit_paid_questions_standard
import nowiwr01p.daily.doctor.resources.subscription_benefit_paid_questions_title
import nowiwr01p.daily.doctor.resources.subscription_benefit_welcome_paper_base
import nowiwr01p.daily.doctor.resources.subscription_benefit_welcome_paper_premium
import nowiwr01p.daily.doctor.resources.subscription_benefit_welcome_paper_standard
import nowiwr01p.daily.doctor.resources.subscription_benefit_welcome_paper_title
import nowiwr01p.daily.doctor.resources.subscription_free_title
import nowiwr01p.daily.doctor.resources.subscription_premium_title
import nowiwr01p.daily.doctor.resources.subscription_standard_title
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import subscription.data.SubscriptionType.*

sealed class SubscriptionType(
    val id: Int,
    val position: Int,
    val name: StringResource,
    val icon: DrawableResource,
    val yearlyPrice: Price,
    val monthlyPrice: Price,
    val benefits: List<BenefitData>
) {
    data object Free: SubscriptionType(
        id = 0,
        position = 0,
        name = Res.string.subscription_free_title,
        icon = Res.drawable.ic_subscription_free,
        monthlyPrice = Price(
            basePrice = 0f,
            discountedPrice = 0f
        ),
        yearlyPrice = Price(
            basePrice = 0f,
            discountedPrice = 0f
        ),
        benefits = listOf()
    )

    data object Base: SubscriptionType(
        id = 1,
        position = 1,
        name = Res.string.subscription_base_title,
        icon = Res.drawable.ic_subscription_base,
        monthlyPrice = Price(
            basePrice = 5.49f,
            discountedPrice = 3.99f
        ),
        yearlyPrice = Price(
            basePrice = 24.99f,
            discountedPrice = 19.99f
        ),
        benefits = listOf(
            BenefitData(
                title = Res.string.subscription_benefit_first_visit_discounts_title,
                description = Res.string.subscription_benefit_first_visit_discounts_base
            ),
            BenefitData(
                title = Res.string.subscription_benefit_appointments_count_title,
                description = Res.string.subscription_benefit_appointments_count_base
            ),
            BenefitData(
                title = Res.string.subscription_benefit_chat_title,
                description = Res.string.subscription_benefit_chat_base
            ),
            BenefitData(
                title = Res.string.subscription_benefit_welcome_paper_title,
                description = Res.string.subscription_benefit_welcome_paper_base
            ),
            BenefitData(
                title = Res.string.subscription_benefit_favorite_clinics_title,
                description = Res.string.subscription_benefit_favorite_clinics_base
            ),
            BenefitData(
                title = Res.string.subscription_benefit_decoding_analysis_title,
                description = Res.string.subscription_benefit_decoding_analysis_base
            ),
            BenefitData(
                title = Res.string.subscription_benefit_paid_questions_title,
                description = Res.string.subscription_benefit_paid_questions_base
            )
        )
    )

    data object Standard: SubscriptionType(
        id = 2,
        position = 2,
        name = Res.string.subscription_standard_title,
        icon = Res.drawable.ic_subscription_standard,
        monthlyPrice = Price(
            basePrice = 9.99f,
            discountedPrice = 7.99f
        ),
        yearlyPrice = Price(
            basePrice = 39.99f,
            discountedPrice = 29.99f
        ),
        benefits = listOf(
            BenefitData(
                title = Res.string.subscription_benefit_first_visit_discounts_title,
                description = Res.string.subscription_benefit_first_visit_discounts_standard,
            ),
            BenefitData(
                title = Res.string.subscription_benefit_appointments_count_title,
                description = Res.string.subscription_benefit_appointments_count_standard,
            ),
            BenefitData(
                title = Res.string.subscription_benefit_chat_title,
                description = Res.string.subscription_benefit_chat_standard,
            ),
            BenefitData(
                title = Res.string.subscription_benefit_welcome_paper_title,
                description = Res.string.subscription_benefit_welcome_paper_standard,
            ),
            BenefitData(
                title = Res.string.subscription_benefit_favorite_clinics_title,
                description = Res.string.subscription_benefit_favorite_clinics_standard,
            ),
            BenefitData(
                title = Res.string.subscription_benefit_decoding_analysis_title,
                description = Res.string.subscription_benefit_decoding_analysis_standard,
            ),
            BenefitData(
                title = Res.string.subscription_benefit_paid_questions_title,
                description = Res.string.subscription_benefit_paid_questions_standard,
            ),
        )
    )

    data object Premium: SubscriptionType(
        id = 3,
        position = 3,
        name = Res.string.subscription_premium_title,
        icon = Res.drawable.ic_subscription_premium,
        monthlyPrice = Price(
            basePrice = 16.99f,
            discountedPrice = 9.99f
        ),
        yearlyPrice = Price(
            basePrice = 69.99f,
            discountedPrice = 59.99f
        ),
        benefits = listOf(
            BenefitData(
                title = Res.string.subscription_benefit_first_visit_discounts_title,
                description = Res.string.subscription_benefit_first_visit_discounts_premium,
            ),
            BenefitData(
                title = Res.string.subscription_benefit_appointments_count_title,
                description = Res.string.subscription_benefit_appointments_count_premium,
            ),
            BenefitData(
                title = Res.string.subscription_benefit_chat_title,
                description = Res.string.subscription_benefit_chat_premium,
            ),
            BenefitData(
                title = Res.string.subscription_benefit_welcome_paper_title,
                description = Res.string.subscription_benefit_welcome_paper_premium,
            ),
            BenefitData(
                title = Res.string.subscription_benefit_favorite_clinics_title,
                description = Res.string.subscription_benefit_favorite_clinics_premium,
            ),
            BenefitData(
                title = Res.string.subscription_benefit_decoding_analysis_title,
                description = Res.string.subscription_benefit_decoding_analysis_premium,
            ),
            BenefitData(
                title = Res.string.subscription_benefit_paid_questions_title,
                description = Res.string.subscription_benefit_paid_questions_premium,
            )
        )
    )
}

fun getSubscriptionItems() = listOf(
    Free,
    Base,
    Standard,
    Premium
)

data class Price(
    val basePrice: Float,
    val discountedPrice: Float
)

data class BenefitData(
    val title: StringResource,
    val description: StringResource
)