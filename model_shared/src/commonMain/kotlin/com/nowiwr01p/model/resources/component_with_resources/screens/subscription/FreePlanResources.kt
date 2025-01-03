package com.nowiwr01p.model.resources.component_with_resources.screens.subscription

import com.nowiwr01p.model.model.subscription.benefits.SubscriptionBenefit
import com.nowiwr01p.model.model.subscription.benefits.SubscriptionBenefits.FreeSubscriptionPlanBenefits
import com.nowiwr01p.model.resources.language.Language

internal data class FreeSubscriptionPlanBenefitsGeorgian(
    override val freePlanTitleDescription: SubscriptionBenefit = SubscriptionBenefit(
        order = 1,
        title = "ეს პროექტი შექმნილია ერთი ადამიანის მიერ",
        description = "ამ მასშტაბის პროექტები ჩვეულებრივ მთლიანი დეველოპერების გუნდის მიერ იქმნება. თუმცა, მე იგი დამოუკიდებლად შევქმენი, დიდი დროისა და ძალისხმევის ჩადებით. თუ მოგწონთ ის, რასაც ვაკეთებ, შესანიშნავი იქნება, თუ ფინანსურად დაუჭერთ მხარს პროექტს. ეს დაეხმარება მის შემდგომ განვითარებას და გახდის მას თქვენთვის კიდევ უკეთესს"
    )
): FreeSubscriptionPlanBenefits(language = Language.Georgian)

internal data class FreeSubscriptionPlanBenefitsEnglish(
    override val freePlanTitleDescription: SubscriptionBenefit = SubscriptionBenefit(
        order = 1,
        title = "This project was created by one person",
        description = "Projects of this scale are usually created by entire teams of developers. However, I created it myself, investing a lot of time and effort. If you like what I do, it would be great if you could support the project financially. This will help further develop it and make it even better for you"
    )
): FreeSubscriptionPlanBenefits(language = Language.English)

internal data class FreeSubscriptionPlanBenefitsRussian(
    override val freePlanTitleDescription: SubscriptionBenefit = SubscriptionBenefit(
        order = 1,
        title = "Этот проект был создан одним человеком",
        description = "Обычно проекты такого масштаба создаются целыми командами разработчиков. Я же создал его самостоятельно, вкладывая много времени и усилий. Если вам нравится то, что я делаю, было бы здорово, если бы вы поддержали проект финансово. Это поможет развивать его дальше и делать его ещё лучше для вас"
    )
): FreeSubscriptionPlanBenefits(language = Language.Russian)

internal data class FreeSubscriptionPlanBenefitsUkrainian(
    override val freePlanTitleDescription: SubscriptionBenefit = SubscriptionBenefit(
        order = 1,
        title = "Цей проєкт створений однією людиною",
        description = "Зазвичай проєкти такого масштабу створюються цілими командами розробників. Я ж створив його самостійно, вкладаючи багато часу та зусиль. Якщо вам подобається те, що я роблю, було б чудово, якби ви підтримали проєкт фінансово. Це допоможе розвивати його далі та зробити його ще кращим для вас"
    )
): FreeSubscriptionPlanBenefits(language = Language.Ukrainian)
