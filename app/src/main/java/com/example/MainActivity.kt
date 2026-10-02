package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.api.BangladeshDistrict
import com.example.data.api.WeatherRepository
import com.example.data.model.CartItem
import com.example.data.model.CommunityPost
import com.example.data.model.KrishiWeather
import com.example.data.model.MarketProduct
import com.example.data.model.PostComment
import com.example.ui.components.AddPostDialog
import com.example.ui.components.AddProductDialog
import com.example.ui.components.CartBottomSheet
import com.example.ui.components.CropDoctorDialog
import com.example.ui.components.FarmerProfileDialog
import com.example.ui.screens.FeedScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MarketScreen
import com.example.ui.theme.KrishiGoldHarvest
import com.example.ui.theme.KrishiGreenContainer
import com.example.ui.theme.KrishiGreenPrimary
import com.example.ui.theme.MyApplicationTheme
import com.example.utils.BanglaDateHelper
import com.example.utils.SampleData
import kotlinx.coroutines.launch

enum class KrishiTab(val titleBn: String) {
    HOME("ড্যাশবোর্ড"),
    FEED("কৃষক আড্ডা"),
    MARKET("কৃষি বাজার")
}

class MainActivity : ComponentActivity() {

    private val weatherRepository = WeatherRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                KrishiApp(weatherRepository = weatherRepository)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KrishiApp(
    weatherRepository: WeatherRepository
) {
    val coroutineScope = rememberCoroutineScope()

    // Navigation Tab state
    var selectedTab by remember { mutableStateOf(KrishiTab.HOME) }

    // Weather state
    var selectedDistrict by remember { mutableStateOf(WeatherRepository.POPULAR_DISTRICTS.first()) }
    var weatherData by remember { mutableStateOf<KrishiWeather?>(null) }
    var isWeatherLoading by remember { mutableStateOf(false) }

    fun refreshWeather(district: BangladeshDistrict = selectedDistrict) {
        coroutineScope.launch {
            isWeatherLoading = true
            val result = weatherRepository.fetchWeather(district)
            weatherData = result.getOrNull()
            isWeatherLoading = false
        }
    }

    LaunchedEffect(selectedDistrict) {
        refreshWeather(selectedDistrict)
    }

    // Community Feed Posts state
    val postsList = remember {
        mutableStateListOf<CommunityPost>().apply {
            addAll(SampleData.communityFeedList)
        }
    }

    // Marketplace Products state
    val productsList = remember {
        mutableStateListOf<MarketProduct>().apply {
            addAll(SampleData.marketplaceProducts)
        }
    }

    // Cart state
    val cartItems = remember { mutableStateListOf<CartItem>() }

    // Dialog & Sheet states
    var showAddPostDialog by remember { mutableStateOf(false) }
    var showAddProductDialog by remember { mutableStateOf(false) }
    var showCartSheet by remember { mutableStateOf(false) }
    var showCropDoctorDialog by remember { mutableStateOf(false) }
    var showProfileDialog by remember { mutableStateOf(false) }

    // Farmer Profile state
    var farmerName by remember { mutableStateOf("মোঃ আবদুর রহমান") }
    var farmerPhone by remember { mutableStateOf("01712-345678") }
    var farmerDistrict by remember { mutableStateOf("দিনাজপুর") }
    var isLoggedIn by remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = KrishiGreenPrimary,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Image(
                                    painter = painterResource(id = R.drawable.img_krishi_logo),
                                    contentDescription = "Logo",
                                    modifier = Modifier.size(32.dp).clip(CircleShape)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "KRISHI • কৃষি",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = KrishiGreenPrimary
                            )
                            Text(
                                text = "কৃষি ও কৃষকের ডিজিটাল সাথী",
                                fontSize = 10.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                actions = {
                    // Crop Doctor Icon button
                    IconButton(
                        onClick = { showCropDoctorDialog = true },
                        modifier = Modifier.testTag("top_crop_doctor_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = "ফসল ডাক্তার",
                            tint = KrishiGoldHarvest
                        )
                    }

                    // Profile Icon
                    IconButton(
                        onClick = { showProfileDialog = true },
                        modifier = Modifier.testTag("top_profile_btn")
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = KrishiGreenContainer,
                            modifier = Modifier.size(32.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = farmerName.take(1),
                                    color = KrishiGreenPrimary,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .navigationBarsPadding()
                    .testTag("bottom_nav_bar"),
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                // Tab 1: Home / Dashboard
                NavigationBarItem(
                    selected = selectedTab == KrishiTab.HOME,
                    onClick = { selectedTab = KrishiTab.HOME },
                    icon = {
                        Icon(
                            imageVector = if (selectedTab == KrishiTab.HOME) Icons.Filled.Home else Icons.Outlined.Home,
                            contentDescription = "হোম"
                        )
                    },
                    label = {
                        Text(
                            text = KrishiTab.HOME.titleBn,
                            fontSize = 11.sp,
                            fontWeight = if (selectedTab == KrishiTab.HOME) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = KrishiGreenPrimary,
                        selectedTextColor = KrishiGreenPrimary,
                        indicatorColor = KrishiGreenContainer
                    ),
                    modifier = Modifier.testTag("nav_tab_home")
                )

                // Tab 2: Community / News Feed
                NavigationBarItem(
                    selected = selectedTab == KrishiTab.FEED,
                    onClick = { selectedTab = KrishiTab.FEED },
                    icon = {
                        Icon(
                            imageVector = if (selectedTab == KrishiTab.FEED) Icons.Filled.ChatBubble else Icons.Filled.ChatBubbleOutline,
                            contentDescription = "আড্ডা"
                        )
                    },
                    label = {
                        Text(
                            text = KrishiTab.FEED.titleBn,
                            fontSize = 11.sp,
                            fontWeight = if (selectedTab == KrishiTab.FEED) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = KrishiGreenPrimary,
                        selectedTextColor = KrishiGreenPrimary,
                        indicatorColor = KrishiGreenContainer
                    ),
                    modifier = Modifier.testTag("nav_tab_feed")
                )

                // Tab 3: Marketplace / E-Commerce
                NavigationBarItem(
                    selected = selectedTab == KrishiTab.MARKET,
                    onClick = { selectedTab = KrishiTab.MARKET },
                    icon = {
                        BadgedBox(
                            badge = {
                                if (cartItems.isNotEmpty()) {
                                    Badge(
                                        containerColor = KrishiGoldHarvest,
                                        contentColor = Color.White
                                    ) {
                                        Text(
                                            text = BanglaDateHelper.toBanglaDigits(cartItems.sumOf { it.quantity }),
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (selectedTab == KrishiTab.MARKET) Icons.Filled.Storefront else Icons.Outlined.Storefront,
                                contentDescription = "বাজার"
                            )
                        }
                    },
                    label = {
                        Text(
                            text = KrishiTab.MARKET.titleBn,
                            fontSize = 11.sp,
                            fontWeight = if (selectedTab == KrishiTab.MARKET) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = KrishiGreenPrimary,
                        selectedTextColor = KrishiGreenPrimary,
                        indicatorColor = KrishiGreenContainer
                    ),
                    modifier = Modifier.testTag("nav_tab_market")
                )
            }
        },
        floatingActionButton = {
            when (selectedTab) {
                KrishiTab.FEED -> {
                    FloatingActionButton(
                        onClick = { showAddPostDialog = true },
                        containerColor = KrishiGreenPrimary,
                        contentColor = Color.White,
                        shape = RoundedCornerShape(16.dp),
                        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp),
                        modifier = Modifier.testTag("fab_add_post")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Default.PostAdd, contentDescription = "পোস্ট লিখুন")
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("পোস্ট লিখুন", fontWeight = FontWeight.Bold)
                        }
                    }
                }
                KrishiTab.MARKET -> {
                    FloatingActionButton(
                        onClick = { showAddProductDialog = true },
                        containerColor = KrishiGoldHarvest,
                        contentColor = Color.White,
                        shape = RoundedCornerShape(16.dp),
                        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp),
                        modifier = Modifier.testTag("fab_sell_product")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Default.AddShoppingCart, contentDescription = "পণ্য বিক্রি")
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("পণ্য বিক্রি", fontWeight = FontWeight.Bold)
                        }
                    }
                }
                KrishiTab.HOME -> {
                    FloatingActionButton(
                        onClick = { showCropDoctorDialog = true },
                        containerColor = KrishiGreenPrimary,
                        contentColor = Color.White,
                        shape = CircleShape,
                        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp),
                        modifier = Modifier.testTag("fab_crop_doctor")
                    ) {
                        Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = "ফসল ডাক্তার")
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedTab) {
                KrishiTab.HOME -> {
                    HomeScreen(
                        weather = weatherData,
                        isWeatherLoading = isWeatherLoading,
                        selectedDistrict = selectedDistrict,
                        onDistrictSelected = { district ->
                            selectedDistrict = district
                        },
                        onRefreshWeather = { refreshWeather() },
                        onOpenCropDoctor = { showCropDoctorDialog = true }
                    )
                }

                KrishiTab.FEED -> {
                    FeedScreen(
                        posts = postsList,
                        onToggleLike = { postId ->
                            val index = postsList.indexOfFirst { it.id == postId }
                            if (index != -1) {
                                val post = postsList[index]
                                val newLiked = !post.isLikedByUser
                                val newCount = if (newLiked) post.likesCount + 1 else (post.likesCount - 1).coerceAtLeast(0)
                                postsList[index] = post.copy(
                                    isLikedByUser = newLiked,
                                    likesCount = newCount
                                )
                            }
                        },
                        onAddComment = { postId, commentText ->
                            val index = postsList.indexOfFirst { it.id == postId }
                            if (index != -1) {
                                val post = postsList[index]
                                val newComment = PostComment(
                                    id = "comm_${System.currentTimeMillis()}",
                                    authorNameBn = farmerName,
                                    authorLocationBn = farmerDistrict,
                                    commentTextBn = commentText,
                                    timeAgoBn = "এইমাত্র"
                                )
                                val updatedComments = post.comments.toMutableList().apply { add(newComment) }
                                postsList[index] = post.copy(
                                    comments = updatedComments,
                                    commentsCount = post.commentsCount + 1
                                )
                            }
                        }
                    )
                }

                KrishiTab.MARKET -> {
                    MarketScreen(
                        products = productsList,
                        cartItemCount = cartItems.sumOf { it.quantity },
                        onOpenCart = { showCartSheet = true },
                        onAddToCart = { product ->
                            val existingIndex = cartItems.indexOfFirst { it.product.id == product.id }
                            if (existingIndex != -1) {
                                val current = cartItems[existingIndex]
                                cartItems[existingIndex] = current.copy(quantity = current.quantity + 1)
                            } else {
                                cartItems.add(CartItem(product = product, quantity = 1))
                            }
                        },
                        onOpenSellProduct = { showAddProductDialog = true }
                    )
                }
            }
        }
    }

    // Add Post Dialog
    if (showAddPostDialog) {
        AddPostDialog(
            onDismiss = { showAddPostDialog = false },
            onPostCreated = { newPost ->
                postsList.add(0, newPost)
            }
        )
    }

    // Add Product Dialog
    if (showAddProductDialog) {
        AddProductDialog(
            onDismiss = { showAddProductDialog = false },
            onProductAdded = { newProduct ->
                productsList.add(0, newProduct)
            }
        )
    }

    // Cart Bottom Sheet
    if (showCartSheet) {
        CartBottomSheet(
            cartItems = cartItems,
            onQuantityChange = { prodId, newQty ->
                val index = cartItems.indexOfFirst { it.product.id == prodId }
                if (index != -1) {
                    cartItems[index] = cartItems[index].copy(quantity = newQty)
                }
            },
            onRemoveItem = { prodId ->
                cartItems.removeAll { it.product.id == prodId }
            },
            onClearCart = {
                cartItems.clear()
            },
            onDismiss = { showCartSheet = false }
        )
    }

    // AI Crop Doctor Dialog
    if (showCropDoctorDialog) {
        CropDoctorDialog(
            onDismiss = { showCropDoctorDialog = false }
        )
    }

    // Farmer Profile Dialog
    if (showProfileDialog) {
        FarmerProfileDialog(
            farmerName = farmerName,
            phoneNumber = farmerPhone,
            district = farmerDistrict,
            isLoggedIn = isLoggedIn,
            onLoginSuccess = { name, phone, dist ->
                farmerName = name
                farmerPhone = phone
                farmerDistrict = dist
                isLoggedIn = true
            },
            onLogout = {
                isLoggedIn = false
                farmerName = "অতিথি কৃষক"
            },
            onDismiss = { showProfileDialog = false }
        )
    }
}
