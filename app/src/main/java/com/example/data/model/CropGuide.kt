package com.example.data.model

enum class BanglaSeason(val titleBn: String, val monthsBn: String, val tagColorHex: Long) {
    SUMMER("গ্রীষ্মকাল", "বৈশাখ - জ্যৈষ্ঠ", 0xFFE65100),
    MONSOON("বর্ষাকাল", "আষাঢ় - শ্রাবণ", 0xFF0277BD),
    AUTUMN("শরৎকাল", "ভাদ্র - আশ্বিন", 0xFF2E7D32),
    LATE_AUTUMN("হেমন্তকাল", "কার্তিক - অগ্রহায়ণ", 0xFFF57F17),
    WINTER("শীতকাল", "পৌষ - মাঘ", 0xFF00838F),
    SPRING("বসন্তকাল", "ফাল্গুন - চৈত্র", 0xFF689F38)
}

enum class CropCategory(val titleBn: String) {
    ALL("সকল"),
    GRAINS("খাদ্যশস্য (ধান, গম, ভুট্টা)"),
    VEGETABLES("শাকসবজি"),
    PULSES_OIL("ডাল ও তেলবীজ"),
    FRUITS("ফলমূল"),
    SPICES("মসলা")
}

data class FertilizerDosage(
    val fertilizerNameBn: String,
    val amountPerBighaBn: String, // e.g., "২৫ কেজি / বিঘা"
    val applicationTimingBn: String
)

data class PestControlTip(
    val pestOrDiseaseNameBn: String,
    val symptomsBn: String,
    val remedyChemicalBn: String,
    val remedyOrganicBn: String
)

data class CropGuide(
    val id: String,
    val nameBn: String,
    val englishName: String,
    val category: CropCategory,
    val season: BanglaSeason,
    val sowingPeriodBn: String,
    val harvestingPeriodBn: String,
    val idealTemperatureBn: String,
    val soilPreparationBn: String,
    val seedRateBn: String,
    val fertilizerList: List<FertilizerDosage>,
    val pestControlList: List<PestControlTip>,
    val irrigationTipsBn: String,
    val expectedYieldBn: String,
    val imageUrl: String
)
