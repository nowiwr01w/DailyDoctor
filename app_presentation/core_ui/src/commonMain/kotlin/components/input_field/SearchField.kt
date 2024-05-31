package components.input_field

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import theme.CustomTheme.colors

@Composable
fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    textStyle: TextStyle = MaterialTheme.typography.body1,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    val customTextSelectionColors = TextSelectionColors(
        handleColor = colors.textColors.blackTextColor,
        backgroundColor = colors.textColors.blackTextColor.copy(alpha = 0.4f)
    )
    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        CustomTextField(
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                textColor = colors.textColors.whiteTextColor,
                cursorColor = colors.textColors.blackTextColor,
                backgroundColor = colors.backgroundColors.blueBackgroundColor.copy(alpha = 0.5f),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = placeholderText,
                        color = colors.textColors.blackTextColor.copy(alpha = 0.5f),
                        style = textStyle,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            },
            leadingIcon = leadingIcon,
            trailingIcon = if (trailingIcon == null) null else {{
                AnimatedVisibility(
                    visible = value.isNotEmpty(),
                    enter = fadeIn(
                        animationSpec = tween(200)
                    ),
                    exit = fadeOut(
                        animationSpec = tween(200)
                    )
                ) {
                    trailingIcon.invoke()
                }
            }},
            maxLines = 1,
            textStyle = textStyle,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            modifier = modifier
        )
    }
}