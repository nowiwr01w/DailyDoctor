package nowiwr01p.daily.doctor.new_resources.component_with_resources.screens.auth

import nowiwr01p.daily.doctor.new_resources.component_with_resources.base.ComponentResources
import nowiwr01p.daily.doctor.new_resources.component_with_resources.base.ComponentTranslatedResources

/**
 * DATA
 */
sealed interface AuthScreenResources: ComponentResources {
    val authTitle: String
    val authInputPhoneHint: String
    val authInputPasswordHint: String
    val authInputPasswordRepeatHint: String
    val authButtonSignInTitle: String
    val authButtonSignUpTitle: String
    val authButtonHaveNoAccountYetTitle: String
    val authButtonAlreadyHaveAccountTitle: String
    val authAgreeWithPoliciesTitle: String
}

/**
 * TRANSLATIONS
 */
internal data class AuthScreenResourcesRussian(
    override val authTitle: String = "Авторизация",
    override val authInputPhoneHint: String = "Номер телефона",
    override val authInputPasswordHint: String = "Пароль",
    override val authInputPasswordRepeatHint: String = "Подтверждение пароля",
    override val authButtonSignInTitle: String = "Войти",
    override val authButtonSignUpTitle: String = "Зарегистрироваться",
    override val authButtonHaveNoAccountYetTitle: String = "Ещё нет аккаунта",
    override val authButtonAlreadyHaveAccountTitle: String = "Уже есть аккаунт",
    override val authAgreeWithPoliciesTitle: String = "Продолжая, вы соглашаетесь с нашей <privacy>политикой конфиденциальности</privacy> и <terms>условиями пользования</terms>"
): AuthScreenResources

internal data class AuthScreenResourcesEnglish(
    override val authTitle: String = "Authorization",
    override val authInputPhoneHint: String = "Phone number",
    override val authInputPasswordHint: String = "Password",
    override val authInputPasswordRepeatHint: String = "Confirm password",
    override val authButtonSignInTitle: String = "Sign In",
    override val authButtonSignUpTitle: String = "Sign Up",
    override val authButtonHaveNoAccountYetTitle: String = "Don't have an account yet?",
    override val authButtonAlreadyHaveAccountTitle: String = "Already have an account?",
    override val authAgreeWithPoliciesTitle: String = "By continuing, you agree to our <privacy>Privacy Policy</privacy> and <terms>Terms of Use</terms>"
): AuthScreenResources

internal data class AuthScreenResourcesUkrainian(
    override val authTitle: String = "Авторизація",
    override val authInputPhoneHint: String = "Номер телефону",
    override val authInputPasswordHint: String = "Пароль",
    override val authInputPasswordRepeatHint: String = "Підтвердження паролю",
    override val authButtonSignInTitle: String = "Увійти",
    override val authButtonSignUpTitle: String = "Зареєструватися",
    override val authButtonHaveNoAccountYetTitle: String = "Ще немає аккаунту?",
    override val authButtonAlreadyHaveAccountTitle: String = "Вже є аккаунт?",
    override val authAgreeWithPoliciesTitle: String = "Продовжуючи, ви погоджуєтеся з нашою <privacy>Політикою конфіденційності</privacy> та <terms>Умовами використання</terms>"
): AuthScreenResources

internal data class AuthScreenResourcesGeorgian(
    override val authTitle: String = "ავტორიზაცია",
    override val authInputPhoneHint: String = "ტელეფონის ნომერი",
    override val authInputPasswordHint: String = "პაროლი",
    override val authInputPasswordRepeatHint: String = "პაროლის დადასტურება",
    override val authButtonSignInTitle: String = "შესვლა",
    override val authButtonSignUpTitle: String = "რეგისტრაცია",
    override val authButtonHaveNoAccountYetTitle: String = "ჯერ არ გაქვთ ანგარიში?",
    override val authButtonAlreadyHaveAccountTitle: String = "უკვე გაქვთ ანგარიში?",
    override val authAgreeWithPoliciesTitle: String = "გაგრძელებით, თქვენ ეთანხმებით ჩვენს <privacy>კონფიდენციალობის პოლიტიკას</privacy> და <terms>გამოყენების პირობებს</terms>"
): AuthScreenResources

/**
 * SCREEN RESOURCES
 */
internal class AuthScreenTranslatedResources: ComponentTranslatedResources<AuthScreenResources>() {
    override fun getGeorgianResources() = AuthScreenResourcesGeorgian()
    override fun getEnglishResources() = AuthScreenResourcesEnglish()
    override fun getRussianResources() = AuthScreenResourcesRussian()
    override fun getUkrainianResources() = AuthScreenResourcesUkrainian()
}