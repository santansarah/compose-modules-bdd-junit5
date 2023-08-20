package com.santansarah.feature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava3.subscribeAsState
import com.santansarah.viewmodels.CityListViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CityListAdapter(
    viewModel: CityListViewModel = getViewModel(),
) {
    val state by viewModel.state.subscribeAsState(CityListViewModel.UiState())

    CountryListPage(
        listUiState = state,
    )

}
