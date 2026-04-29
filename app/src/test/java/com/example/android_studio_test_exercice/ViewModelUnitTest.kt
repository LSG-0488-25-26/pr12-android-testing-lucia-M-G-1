package com.example.android_studio_test_exercice

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.state.ToggleableState
import com.example.android_studio_test_exercice.viewmodel.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun initialState() {
        val viewModel = MainViewModel()

        assertTrue(viewModel.estatSwitch.value == true)
        assertFalse(viewModel.esVegetaria.value == true)
        assertFalse(viewModel.esVega.value == true)
        assertTrue(viewModel.esCarnivor.value == true)
        assertEquals(ToggleableState.Off, viewModel.triStateStatus.value)
        assertEquals("Messi", viewModel.selectedOption.value)
        assertEquals(0f, viewModel.sliderValue.value ?: -1f, 0f)
        assertFalse(viewModel.expanded.value == true)
        assertEquals("Opció A", viewModel.selectedItem.value)
        assertEquals("", viewModel.searchText.value)
        assertFalse(viewModel.showSnackbar.value == true)
        assertFalse(viewModel.toggleState.value == true)
    }

    @Test
    fun toggleEstatSwitchChangesWifiState() {
        val viewModel = MainViewModel()

        viewModel.toggleEstatSwitch()
        assertFalse(viewModel.estatSwitch.value == true)

        viewModel.toggleEstatSwitch()
        assertTrue(viewModel.estatSwitch.value == true)
    }

    @Test
    fun toggleEsCarnivorChangesCarnivoreState() {
        val viewModel = MainViewModel()

        viewModel.toggleEsCarnivor()

        assertFalse(viewModel.esCarnivor.value == true)
    }

    @Test
    fun toggleEsVegetariaChangesVegetarianState() {
        val viewModel = MainViewModel()

        viewModel.toggleEsVegetaria()

        assertTrue(viewModel.esVegetaria.value == true)
    }

    @Test
    fun toggleEsVegaChangesVeganState() {
        val viewModel = MainViewModel()

        viewModel.toggleEsVega()

        assertTrue(viewModel.esVega.value == true)
    }

    @Test
    fun toggleTriStateStatusCyclesThroughAllStates() {
        val viewModel = MainViewModel()

        viewModel.toggleTriStateStatus()
        assertEquals(ToggleableState.Indeterminate, viewModel.triStateStatus.value)

        viewModel.toggleTriStateStatus()
        assertEquals(ToggleableState.On, viewModel.triStateStatus.value)

        viewModel.toggleTriStateStatus()
        assertEquals(ToggleableState.Off, viewModel.triStateStatus.value)
    }

    @Test
    fun setSelectedOptionChangesRadioSelection() {
        val viewModel = MainViewModel()

        viewModel.setSelectedOption("Raphina")

        assertEquals("Raphina", viewModel.selectedOption.value)
    }

    @Test
    fun setSliderValueChangesVolume() {
        val viewModel = MainViewModel()

        viewModel.setSliderValue(75f)

        assertEquals(75f, viewModel.sliderValue.value ?: -1f, 0f)
    }

    @Test
    fun setExpandedChangesDropdownVisibility() {
        val viewModel = MainViewModel()

        viewModel.setExpanded(true)

        assertTrue(viewModel.expanded.value == true)
    }

    @Test
    fun setSelectedItemChangesDropdownSelection() {
        val viewModel = MainViewModel()

        viewModel.setSelectedItem("Opció C")

        assertEquals("Opció C", viewModel.selectedItem.value)
    }

    @Test
    fun setSearchTextChangesSearchInput() {
        val viewModel = MainViewModel()

        viewModel.setSearchText("pizza")

        assertEquals("pizza", viewModel.searchText.value)
    }

    @Test
    fun performSearchShowsSnackbarMessage() {
        val viewModel = MainViewModel()

        viewModel.performSearch()

        assertTrue(viewModel.showSnackbar.value == true)
    }

    @Test
    fun toggleChangesFinalButtonState() {
        val viewModel = MainViewModel()

        viewModel.toggle()
        assertTrue(viewModel.toggleState.value == true)

        viewModel.toggle()
        assertFalse(viewModel.toggleState.value == true)
    }
}