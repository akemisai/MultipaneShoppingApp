package com.example.multipaneshoppingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.multipaneshoppingapp.ui.theme.MultipaneShoppingAppTheme
import com.example.multipaneshoppingapp.ui.theme.Purple40
import com.example.multipaneshoppingapp.ui.theme.PurpleGrey40

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MultipaneShoppingAppTheme {
                MultipaneShoppingApp()
            }
        }
    }
}

data class Product(val name: String, val price: String, val description: String)


@Composable
fun MultipaneShoppingApp() {
    // Detect the current orientation using LocalConfiguration
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT
    val products = listOf(
        Product("Wireless Headphones", "$99.99", "Experience exceptional sound quality with these wireless headphones."),
        Product("Smartwatch Series 6", "$299.99", "Stay connected with this stylish smartwatch that tracks your health."),
        Product("Portable Bluetooth Speaker", "$49.99", "Enjoy music on the go with this compact and powerful speaker."),
        Product("Gaming Laptop", "$1,199.99", "High-performance laptop designed for gaming and productivity."),
        Product("4K Ultra HD TV", "$799.99", "Immerse yourself in stunning visuals with this 4K Ultra HD TV."),
        Product("Instant Pot Duo", "$89.99", "Cook delicious meals quickly with this versatile multi-cooker."),
        Product("Fitness Tracker", "$59.99", "Monitor your activity and health with this sleek fitness tracker."),
        Product("E-reader with Wi-Fi", "$129.99", "Carry your entire library with this lightweight e-reader."),
        Product("Wireless Charger", "$29.99", "Charge your devices effortlessly with this fast wireless charger."),
        Product("Robot Vacuum Cleaner", "$249.99", "Keep your floors clean with this smart robot vacuum cleaner.")
    )
    val selectedProduct = remember { mutableStateOf<Product?>(null) }
    if (isPortrait) {
        if (selectedProduct.value == null) {
            // Show product list
            Column {
                Text(
                    text = "Select a product to view details",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                    lineHeight = MaterialTheme.typography.titleLarge.lineHeight,
                    modifier = Modifier.padding(16.dp))
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(products) { product ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(2.dp)
                                .border(1.dp, Purple40, shape = RoundedCornerShape(8.dp))
                        ) {
                            Text(
                                text = product.name,
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
                                color = Purple40,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .clickable {
                                        selectedProduct.value = product
                                    }
                            )
                        }
                    }
                }
            }
        } else {
            // Show product details
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = "Product Details", modifier = Modifier.padding(bottom = 8.dp))
                Text(text = "Name: ${selectedProduct.value?.name}", modifier = Modifier.padding(bottom = 4.dp))
                Text(text = "Price: ${selectedProduct.value?.price}", modifier = Modifier.padding(bottom = 4.dp))
                Text(text = "Description: ${selectedProduct.value?.description}")

                // Back button to return to product list
                ElevatedButton(modifier = Modifier.padding(top = 16.dp), onClick = {selectedProduct.value = null}) {
                    Text(text = "Back",
                        color = PurpleGrey40,
                        style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    } else {
        // In landscape, show both product list and product details side by side
        Row(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.weight(1f).padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Select a product to view details",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                    lineHeight = MaterialTheme.typography.titleLarge.lineHeight,
                    modifier = Modifier.padding(16.dp))
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(products) { product ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                            .border(1.dp, Purple40, shape = RoundedCornerShape(8.dp))
                    ) {
                        Text(
                            text = product.name,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
                            color = Purple40,
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable {
                                    selectedProduct.value = product
                                }
                        )
                    }
                }
            }
            }

            selectedProduct.value?.let { product ->
                Column(modifier = Modifier.weight(1f).padding(16.dp)) {
                    Text(text = "Product Details", modifier = Modifier.padding(bottom = 8.dp))
                    Text(text = "Name: ${product.name}", modifier = Modifier.padding(bottom = 4.dp))
                    Text(text = "Price: ${product.price}", modifier = Modifier.padding(bottom = 4.dp))
                    Text(text = "Description: ${product.description}")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MultipaneShoppingAppPreview() {
    MultipaneShoppingAppTheme {
        MultipaneShoppingApp()
    }
}