package kg.optima.mobile.design_system.android.ui.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import kg.optima.mobile.design_system.android.values.Deps
import kg.optima.mobile.resources.ComposeColors
import kg.optima.mobile.resources.Headings

@Composable
fun PrimaryButton(
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	text: String,
	fontSize: Headings = Headings.H4,
	onClick: () -> Unit = {}
) = Button(
	modifier = modifier
		.height(Deps.buttonHeight)
		.background(color = Color.Companion.Transparent),
	shape = RoundedCornerShape(Deps.buttonCornerRadius),
	colors = ButtonDefaults.buttonColors(
		backgroundColor = ComposeColors.primaryRed,
		disabledBackgroundColor = ComposeColors.primaryDisabledGray,
	),
	onClick = onClick,
	enabled = enabled,
) {
	Text(
		text = text,
		fontSize = fontSize.px.sp,
		color = ComposeColors.primaryWhite,
	)
}