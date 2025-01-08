package bottom_sheets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import getScreenHeight
import nowiwr01p.daily.doctor.app_presentation.navigation.mobile.navigation.config.child.MobileBottomSheetChild.TestChild

@Composable
internal fun TestChild.TestBottomSheet() {
    val height = getScreenHeight() / 2
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
    ) {
        Text("text text")
    }
}
