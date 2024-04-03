package ui.mobile.verification

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import base.theme.CustomTheme.colors
import base.view_model.rememberViewModel
import ui.common.verification.VerificationContract.Listener
import ui.common.verification.VerificationContract.State
import ui.common.verification.VerificationViewModel
import ui.mobile.auth.TopIcon
import ui.mobile.auth.TopTitle

@Composable
internal fun VerificationMainScreenMobile(
    viewModel: VerificationViewModel = rememberViewModel()
) {
    val listener = object : Listener {

    }
    VerificationMainScreenContent(
        state = viewModel.viewState.value,
        listener = listener
    )
}

/**
 * CONTENT
 */
@Composable
private fun VerificationMainScreenContent(
    state: State,
    listener: Listener
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.backgroundColors.grayBackgroundColor)
    ) {
        val (icon, inputFieldsContainer) = createRefs()

        val isKeyboardOpen = false // TODO: expect function for iOS
        val authContentTransitionDp by animateDpAsState(
            targetValue = if (isKeyboardOpen) 8.dp else 160.dp
        )

        val iconModifier = Modifier
            .size(96.dp)
            .clip(CircleShape)
            .constrainAs(icon) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top, 32.dp)
            }
        TopIcon(modifier = iconModifier)

        val authContentModifier = Modifier
            .fillMaxSize()
            .padding(top = authContentTransitionDp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(colors.backgroundColors.whiteBackgroundColor)
            .constrainAs(inputFieldsContainer) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
            }
        VerificationContent(
            state = state,
            listener = listener,
            modifier = authContentModifier
        )
    }
}

/**
 * VERIFICATION CONTENT
 */
@Composable
private fun VerificationContent(
    state: State,
    listener: Listener,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopTitle("Верификация")
        Subtitle()
    }
}

/**
 * DESCRIPTION
 */
@Composable
private fun Subtitle() = Text(
    text = "Мы отправили код на указанную вами почту. Введите его в поле ниже.",
    color = colors.textColors.blackTextColor,
    style = MaterialTheme.typography.body1,
    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
)