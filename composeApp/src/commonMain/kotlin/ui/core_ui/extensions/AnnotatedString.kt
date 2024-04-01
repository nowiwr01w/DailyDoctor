package ui.core_ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle
import base.theme.CustomTheme

fun AnnotatedString.onTextClick(
    text: String,
    offset: Int,
    onClick: () -> Unit
) {
    getStringAnnotations(text, offset, offset).firstOrNull()?.let {
        onClick.invoke()
    }
}

@Composable
fun AnnotatedString.Builder.appendLink(name: String) {
    pushStringAnnotation(
        tag = name,
        annotation = name
    )
    withStyle(
        style = SpanStyle(color = CustomTheme.colors.backgroundColors.grayBackgroundColor)
    ) {
        append(name)
    }
    pop()
}