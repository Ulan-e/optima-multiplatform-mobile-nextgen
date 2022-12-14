package kg.optima.mobile.design_system.android.ui.screens.pin.headers

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import kg.optima.mobile.design_system.android.utils.resources.ComposeColors
import kg.optima.mobile.design_system.android.utils.resources.sp
import kg.optima.mobile.design_system.android.values.Deps
import kg.optima.mobile.resources.Headings

fun pinSetScreenHeader(
	header: String,
	subheader: String,
): @Composable ColumnScope.(Modifier) -> Unit = {
	Spacer(modifier = it)
	Text(
		text = header,
		fontSize = Headings.H1.sp,
		fontWeight = FontWeight.Bold,
		color = ComposeColors.PrimaryDisabledGray,
	)
	Text(
		text = subheader,
		modifier = Modifier.padding(top = Deps.Spacing.subheaderMargin),
		fontSize = Headings.H5.sp,
		color = ComposeColors.DescriptionGray,
	)
	Spacer(modifier = Modifier.weight(1f))
}
