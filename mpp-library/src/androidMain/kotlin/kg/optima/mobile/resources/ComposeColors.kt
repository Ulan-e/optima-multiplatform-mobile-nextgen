package kg.optima.mobile.resources

import androidx.compose.ui.graphics.Color

object ComposeColors {
	val PrimaryRed: Color get() = Colors.PrimaryRed.toComposeColor()
	val PrimaryDisabledGray: Color get() = Colors.PrimaryDisabledGray.toComposeColor()
	val PrimaryWhite: Color get() = Colors.PrimaryWhite.toComposeColor()
	val PrimaryBlack: Color get() = Colors.PrimaryBlack.toComposeColor()

	val Background: Color get() = Colors.SecondaryBackground.toComposeColor()
	val Green: Color get() = Colors.Green.toComposeColor()
	val SystemGreen: Color get() = Colors.SystemGreen.toComposeColor()
	val DescriptionGray: Color get() = Colors.DescriptionGray.toComposeColor()
	val WhiteF5: Color get() = Colors.WhiteF5.toComposeColor()

	val OpaquedDisabledGray: Color get() = DescriptionGray.copy(alpha = 0.08f)
}