package com.example.data.model

enum class ProductCategory(val titleBn: String) {
    ALL("সকল পণ্য"),
    CROPS("খাদ্যশস্য ও চাল"),
    VEGETABLES("তাজা সবজি"),
    FRUITS("মৌসুমী ফল"),
    SEEDS("উন্নত বীজ ও চারা"),
    FERTILIZER("জৈব সার ও বালাইনাশক"),
    SPICES("মসলা ও গুড়")
}

data class MarketProduct(
    val id: String,
    val nameBn: String,
    val category: ProductCategory,
    val pricePerUnit: Double, // in BDT (৳)
    val unitBn: String,       // "মণ", "কেজি", "বস্তা", "প্যাকেট"
    val minOrderQuantity: Int = 1,
    val availableStockBn: String,
    val sellerNameBn: String,
    val sellerPhone: String,
    val sellerDistrictBn: String,
    val sellerUpazilaBn: String,
    val isVerifiedFarmer: Boolean = true,
    val isOrganicCertified: Boolean = false,
    val descriptionBn: String,
    val rating: Double = 4.8,
    val imageUrl: String
)

data class CartItem(
    val product: MarketProduct,
    val quantity: Int
)
