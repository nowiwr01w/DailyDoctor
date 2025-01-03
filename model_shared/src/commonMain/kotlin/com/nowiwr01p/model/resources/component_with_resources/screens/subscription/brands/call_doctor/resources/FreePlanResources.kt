package com.nowiwr01p.model.resources.component_with_resources.screens.subscription.brands.call_doctor.resources

import com.nowiwr01p.model.model.subscription.type.SubscriptionType
import com.nowiwr01p.model.model.subscription.type.SubscriptionType.Free
import com.nowiwr01p.model.resources.component_with_resources.screens.subscription.base.SubscriptionTypeHolder

abstract class FreePlanResources: SubscriptionTypeHolder {
    override val type: SubscriptionType = Free
    abstract val placeholderTitle: String
    abstract val placeholderDescription: String
}

internal data class FreePlanResourcesRussian(
    override val placeholderTitle: String = "Этот проект был создан одним человеком",
    override val placeholderDescription: String = "Обычно проекты такого масштаба создаются целыми командами разработчиков. Я же создал его самостоятельно, вкладывая много времени и усилий. Если вам нравится то, что я делаю, было бы здорово, если бы вы поддержали проект финансово. Это поможет развивать его дальше и делать его ещё лучше для вас"
): FreePlanResources()

internal data class FreePlanResourcesEnglish(
    override val placeholderTitle: String = "This project was created by one person",
    override val placeholderDescription: String = "Projects of this scale are usually created by entire teams of developers. However, I created it myself, investing a lot of time and effort. If you like what I do, it would be great if you could support the project financially. This will help further develop it and make it even better for you"
): FreePlanResources()

internal data class FreePlanResourcesUkrainian(
    override val placeholderTitle: String = "Цей проєкт створений однією людиною",
    override val placeholderDescription: String = "Зазвичай проєкти такого масштабу створюються цілими командами розробників. Я ж створив його самостійно, вкладаючи багато часу та зусиль. Якщо вам подобається те, що я роблю, було б чудово, якби ви підтримали проєкт фінансово. Це допоможе розвивати його далі та зробити його ще кращим для вас"
): FreePlanResources()

internal data class FreePlanResourcesGeorgian(
    override val placeholderTitle: String = "ეს პროექტი შექმნილია ერთი ადამიანის მიერ",
    override val placeholderDescription: String = "ამ მასშტაბის პროექტები ჩვეულებრივ მთლიანი დეველოპერების გუნდის მიერ იქმნება. თუმცა, მე იგი დამოუკიდებლად შევქმენი, დიდი დროისა და ძალისხმევის ჩადებით. თუ მოგწონთ ის, რასაც ვაკეთებ, შესანიშნავი იქნება, თუ ფინანსურად დაუჭერთ მხარს პროექტს. ეს დაეხმარება მის შემდგომ განვითარებას და გახდის მას თქვენთვის კიდევ უკეთესს"
): FreePlanResources()
