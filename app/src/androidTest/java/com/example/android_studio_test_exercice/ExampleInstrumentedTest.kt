package com.example.android_studio_test_exercice

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android_studio_test_exercice.ui.theme.AndroidstudiotestexerciceTheme
import com.example.android_studio_test_exercice.view.MainView
import com.example.android_studio_test_exercice.viewmodel.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun mainViewShowsInitialComposableContent() {
        setMainViewContent()

        composeTestRule.onNodeWithText("Activar Wi-Fi: ").assertExists()
        composeTestRule.onNodeWithText("Opcions de menú:").assertExists()
        composeTestRule.onNodeWithText("Carnívor/a").assertExists()
        composeTestRule.onNodeWithText("Vegetarià/na").assertExists()
        composeTestRule.onNodeWithText("Vegà/na").assertExists()
        composeTestRule.onNodeWithText("TriState").assertExists()
        composeTestRule.onNodeWithText("Pilota d'Or:").assertExists()
        composeTestRule.onNodeWithText("Vinicius").assertExists()
        composeTestRule.onNodeWithText("Lamine Yamal").assertExists()
        composeTestRule.onNodeWithText("Raphina").assertExists()
        composeTestRule.onNodeWithText("Volum: 0%").assertExists()
        composeTestRule.onNodeWithText("Opció A").assertExists()
        composeTestRule.onNodeWithText("Buscar...").assertExists()
        composeTestRule.onNodeWithText("Buscar").assertExists()
        composeTestRule.onNodeWithText("Desactivat").assertExists()
    }

    @Test
    fun switchCheckboxesAndTriStateReactToClicks() {
        val viewModel = setMainViewContent()

        composeTestRule.onNodeWithTag("wifi_switch").assertIsOn().performClick()
        composeTestRule.onNodeWithTag("wifi_switch").assertIsOff()

        composeTestRule.onNodeWithTag("carnivor_checkbox").assertIsOn().assertIsNotEnabled()
        composeTestRule.onNodeWithTag("vegetaria_checkbox").assertIsOff().assertIsEnabled().performClick()
        composeTestRule.onNodeWithTag("vegetaria_checkbox").assertIsOn()
        composeTestRule.onNodeWithTag("vega_checkbox").assertIsOff().assertIsEnabled().performClick()
        composeTestRule.onNodeWithTag("vega_checkbox").assertIsOn()

        composeTestRule.onNodeWithTag("tristate_checkbox").assertIsOff().performClick()
        assertEquals(ToggleableState.Indeterminate, viewModel.triStateStatus.value)
        composeTestRule.onNodeWithTag("tristate_checkbox").performClick()
        composeTestRule.onNodeWithTag("tristate_checkbox").assertIsOn()
    }

    @Test
    fun radioButtonsSliderDropdownSearchAndButtonUpdateUi() {
        setMainViewContent()

        composeTestRule.onNodeWithTag("radio_Vinicius").assertIsNotEnabled()
        composeTestRule.onNodeWithTag("radio_Lamine Yamal").performClick().assertIsSelected()

        composeTestRule.onNodeWithTag("volume_slider")
            .performSemanticsAction(SemanticsActions.SetProgress) { it(40f) }
        composeTestRule.onNodeWithText("Volum: 40%").assertExists()

        composeTestRule.onNodeWithTag("dropdown_anchor").assertTextContains("Opció A").performClick()
        composeTestRule.onNodeWithText("Opció C").performClick()
        composeTestRule.onNodeWithTag("dropdown_anchor").assertTextContains("Opció C")

        composeTestRule.onNodeWithTag("search_text_field").performTextInput("menu")
        composeTestRule.onNodeWithText("menu").assertExists()
        composeTestRule.onNodeWithTag("search_button").performClick()
        composeTestRule.onNodeWithText("Acció completada!").assertExists()

        composeTestRule.onNodeWithTag("toggle_button").assertTextContains("Desactivat").performClick()
        composeTestRule.onNodeWithTag("toggle_button").assertTextContains("Activat")
    }

    private fun setMainViewContent(): MainViewModel {
        val viewModel = MainViewModel()

        composeTestRule.setContent {
            AndroidstudiotestexerciceTheme {
                MainView(myViewModel = viewModel)
            }
        }

        return viewModel
    }
}