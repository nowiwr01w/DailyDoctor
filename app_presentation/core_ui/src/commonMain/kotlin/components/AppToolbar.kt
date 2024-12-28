package components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import theme.CustomTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import components.image.AppImage
import nowiwr01p.daily.doctor.resources.Res
import nowiwr01p.daily.doctor.resources.ic_back
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import theme.CustomTheme.colors

@Composable
fun AppToolbar(
    title: StringResource,
    onBackClick: (() -> Unit)? = null,
    actionButtons: List<AppToolbarActionButtonData> = listOf()
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(colors.backgroundColors.whiteBackgroundColor)
    ) {
        val (back, text, actions) = createRefs()

        val backButtonModifier = Modifier
            .padding(start = 8.dp)
            .constrainAs(back) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        if (onBackClick != null) {
            ToolbarButton(
                icon = Res.drawable.ic_back,
                onClick = onBackClick,
                modifier = backButtonModifier
            )
        }

        val titleModifier = Modifier
            .padding(start = if (onBackClick != null) 8.dp else 16.dp)
            .constrainAs(text) {
                start.linkTo(if (onBackClick != null) back.end else parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        Text(
            text = stringResource(title),
            color = colors.textColors.blackTextColor,
            style = CustomTheme.typography.headlineLarge,
            modifier = titleModifier
        )

        if (actionButtons.isNotEmpty()) {
            val actionButtonsModifier = Modifier
                .padding(end = 8.dp)
                .constrainAs(actions) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            Row(
                modifier = actionButtonsModifier
            ) {
                actionButtons.forEachIndexed { index, actionButton ->
                    when (actionButton) {
                        is AppToolbarActionButtonData.CustomIcon -> ToolbarButtonWrap(
                            content = actionButton.icon,
                            onClick =  actionButton.onClick
                        )
                        is AppToolbarActionButtonData.DefaultIcon -> ToolbarButton(
                            icon = actionButton.icon,
                            onClick = actionButton.onClick
                        )
                    }
                    if (index != actionButtons.lastIndex) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ToolbarButtonWrap(
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(28.dp)
            .clip(CircleShape)
            .clickable(enabled = onClick != null) {
                onClick?.let { it() }
            }
    ) {
        content()
    }
}

@Composable
fun ToolbarButton(
    icon: DrawableResource,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ToolbarButtonWrap(
        onClick = onClick,
        modifier = modifier
    ) {
        AppImage(
            image = icon,
            color = colors.textColors.blackTextColor,
            modifier = Modifier.size(16.dp)
        )
    }
}

sealed class AppToolbarActionButtonData {

    data class CustomIcon(
        val icon: @Composable () -> Unit,
        val onClick: () -> Unit
    ): AppToolbarActionButtonData()

    data class DefaultIcon(
        val icon: DrawableResource,
        val onClick: () -> Unit
    ): AppToolbarActionButtonData()
}
