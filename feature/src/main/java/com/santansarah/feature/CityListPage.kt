package com.santansarah.feature

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.santansarah.domainmodels.City
import com.santansarah.viewmodels.CityListViewModel

@Composable
fun CountryListPage(
    listUiState: CityListViewModel.UiState,
) {

    if (listUiState.isLoading) {
        CircularProgressIndicator(
            color = Color.Magenta,
            strokeWidth = 6.dp,
            modifier = Modifier.size(100.dp)
        )
    } else {
        Column(modifier = Modifier.fillMaxHeight()) {
            Column(modifier = Modifier.weight(1f)) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(listUiState.cities) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(16.dp),
                                text = it.city + ", " + it.state + " " + it.zip.toString(),
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(name = "Standard Preview", widthDp = 300, heightDp = 300)
@Composable
fun CountryListPagePreview() {
    CountryListPage(
        listUiState = CityListViewModel.UiState(
            isLoading = true,
            cities = listOf(
                City(
                    zip = 45373,
                    lat = 40.03353,
                    lng = -84.19588,
                    city = "Troy",
                    state = "OH",
                    population = 35913,
                    county = "Miami"
                ), City(
                    zip = 12180,
                    lat = 42.7516,
                    lng = -73.59997,
                    city = "Troy",
                    state = "NY",
                    population = 53181,
                    county = "Rensselaer"
                )
            )
        )
    )
}
