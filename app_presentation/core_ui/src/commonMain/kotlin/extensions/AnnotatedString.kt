package extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import theme.CustomTheme.colors

private val XML_TAG_REGEX = "<(\\w+)>(.*?)</\\1>".toRegex()

fun AnnotatedString.onTextClick(
    offset: Int,
    onClick: (tag: String) -> Unit
) {
    getStringAnnotations(offset, offset).firstOrNull()?.let { annotation ->
        onClick.invoke(annotation.tag)
    }
}

@Composable
fun buildAnnotatedStringFromStringWithTags(
    fullText: String,
    linkStyle: SpanStyle = SpanStyle(color = colors.backgroundColors.grayBackgroundColor)
): AnnotatedString {
    return remember(fullText) {
        buildAnnotatedString {
            var lastIndex = 0
            val matches = XML_TAG_REGEX.findAll(fullText).toList()
            matches.forEach { match ->
                val (tag, linkText) = match.destructured
                val preTagText = fullText.substring(
                    startIndex = lastIndex,
                    endIndex = match.range.first
                )
                append(preTagText)
                appendLink(
                    tag = tag,
                    name = linkText,
                    linkStyle = linkStyle
                )
                lastIndex = match.range.last + 1
            }
            if (lastIndex < fullText.length) {
                val remainingText = fullText.substring(lastIndex)
                append(remainingText)
            }
        }
    }
}

fun AnnotatedString.Builder.appendLink(
    tag: String,
    name: String,
    linkStyle: SpanStyle
) {
    pushStringAnnotation(
        tag = tag,
        annotation = name
    )
    withStyle(style = linkStyle) {
        append(name)
    }
    pop()
}

data class LinkData(
    val tag: LinkTag,
    val url: String,
    val onClick: () -> Unit
)

sealed class LinkTag(val tag: String) {
    data object PrivacyPolicy: LinkTag("privacy")
    data object TermsAndConditions: LinkTag("terms")
}