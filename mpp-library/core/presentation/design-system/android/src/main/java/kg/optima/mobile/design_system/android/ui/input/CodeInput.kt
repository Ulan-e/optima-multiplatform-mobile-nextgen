package kg.optima.mobile.design_system.android.ui.input

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kg.optima.mobile.base.utils.emptyString
import kg.optima.mobile.common.Constants
import kg.optima.mobile.design_system.android.values.Deps
import kg.optima.mobile.design_system.android.utils.resources.ComposeColors
import kg.optima.mobile.design_system.android.utils.resources.resId
import kg.optima.mobile.resources.images.MainImages
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CodeInput(
	modifier: Modifier = Modifier,
	length: Int = Constants.PIN_LENGTH,
	value: String = "",
	withKeyboard: Boolean = false,
	onValueChanged: (String) -> Unit = {},
	onInputCompleted: (String) -> Unit = {},
) {
	val focusRequester = remember { FocusRequester() }
	val keyboard = LocalSoftwareKeyboardController.current

	if (!withKeyboard) {
		if (value.length <= length) {
			if (value.all { c -> c in numberRange }) {
				onValueChanged(value)
			}
			if (value.length >= length) {
				onInputCompleted(value)
			}
		}
	}

	TextField(
		modifier = Modifier
			.size(Deps.Size.invisible)
			.focusRequester(focusRequester),
		value = value,
		onValueChange = {
			if (it.length <= length) {
				if (it.all { c -> c in numberRange }) {
					onValueChanged(it)
				}
				if (it.length >= length) {
					keyboard?.hide()
					onInputCompleted(it)
				}
			}
		},
		keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
	)

	Row(
		modifier = Modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.Center,
	) {
		repeat(length) {
			OtpCell(
				modifier = modifier.clickable {
					if (withKeyboard) {
						focusRequester.requestFocus()
						keyboard?.show()
					}
				},
				cellStatus = when {
					value.length == it -> CellStatus.Focused
					value.length > it -> CellStatus.Filled
					else -> CellStatus.Empty
				},
			)
		}
	}
}

@Composable
private fun OtpCell(
	modifier: Modifier = Modifier,
	cellStatus: CellStatus,
) {
	val scope = rememberCoroutineScope()
	val (cursorSymbol, setCursorSymbol) = remember { mutableStateOf(emptyString) }

	LaunchedEffect(key1 = cursorSymbol, cellStatus) {
		if (cellStatus == CellStatus.Focused) {
			scope.launch {
				delay(500)
				setCursorSymbol(if (cursorSymbol.isEmpty()) "|" else emptyString)
			}
		}
	}

	Surface(
		modifier = modifier
			.padding(horizontal = Deps.Spacing.pinCellXMargin)
			.size(
				width = Deps.Size.pinCellSize.first,
				height = Deps.Size.pinCellSize.second
			),
		border = BorderStroke(
			width = Deps.borderStroke,
			color = if (cellStatus == CellStatus.Focused) {
				ComposeColors.PrimaryBlack
			} else {
				Color.Transparent
			},
		),
		shape = RoundedCornerShape(size = Deps.cornerRadius),
	) {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.background(ComposeColors.WhiteF5)
		) {
			when (cellStatus) {
				CellStatus.Empty -> Unit
				CellStatus.Focused -> Text(
					modifier = Modifier
						.align(Alignment.Center)
						.offset(y = (-4).dp),
					text = cursorSymbol,
					color = ComposeColors.PrimaryBlack,
					fontSize = Deps.TextSize.codeInputSymbol,
					style = TextStyle(fontWeight = FontWeight.W200),
				)
				CellStatus.Filled -> Icon(
					modifier = Modifier
						.size(Deps.Size.pinDotSize)
						.align(Alignment.Center),
					painter = painterResource(id = MainImages.dot.resId()),
					contentDescription = emptyString,
					tint = ComposeColors.PrimaryBlack,
				)
			}
		}
	}
}

private enum class CellStatus {
	Empty, Focused, Filled;
}

private val numberRange = '0'..'9'