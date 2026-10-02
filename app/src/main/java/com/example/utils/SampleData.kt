package com.example.utils

import com.example.data.model.BanglaSeason
import com.example.data.model.CommunityPost
import com.example.data.model.CropCategory
import com.example.data.model.CropGuide
import com.example.data.model.ExpertAdvice
import com.example.data.model.FertilizerDosage
import com.example.data.model.MarketProduct
import com.example.data.model.PestControlTip
import com.example.data.model.PostComment
import com.example.data.model.ProductCategory

object SampleData {

    val seasonalCropList = listOf(
        CropGuide(
            id = "crop_aman_rice",
            nameBn = "রোপা আমন ধান (ব্রি ধান-৪৯, ব্রি ধান-৭৫)",
            englishName = "Transplanted Aman Rice",
            category = CropCategory.GRAINS,
            season = BanglaSeason.AUTUMN,
            sowingPeriodBn = "শ্রাবণ - ভাদ্র (জুলাই - আগস্ট)",
            harvestingPeriodBn = "কার্তিক - অগ্রহায়ণ (অক্টোবর - নভেম্বর)",
            idealTemperatureBn = "২৫° - ৩৫° সেলসিয়াস",
            soilPreparationBn = "জমি ৩-৪ বার আড়াআড়ি চাষ ও মই দিয়ে থকথকে কাদা তৈরি করতে হবে। শেষ চাষের সময় জৈব সার ও টিএসপি মিশিয়ে দিতে হবে।",
            seedRateBn = "বিঘা প্রতি ৩ - ৩.৫ কেজি সুস্থ ও পুষ্ট বীজ",
            fertilizerList = listOf(
                FertilizerDosage("ইউরিয়া (Urea)", "২৬ কেজি / বিঘা", "চারা রোপণের ১৫, ৩০ এবং ৪৫ দিন পর ৩ কিস্তিতে সমানভাগে প্রয়োগ করুন।"),
                FertilizerDosage("টিএসপি (TSP)", "১৩ কেজি / বিঘা", "জমি তৈরির শেষ চাষে পুরোটা সমানভাবে ছিটিয়ে দিন।"),
                FertilizerDosage("এমওপি (MoP/Potash)", "১৭ কেজি / বিঘা", "অর্ধেক শেষ চাষে এবং বাকি অর্ধেক কাইচথোড় আসার সময়।"),
                FertilizerDosage("জিপসাম (Gypsum)", "৯ কেজি / বিঘা", "শেষ চাষের সময় মাটির সাথে মিশিয়ে দিন।"),
                FertilizerDosage("দস্তা (Zinc Sulphate)", "১.৫ কেজি / বিঘা", "জমিতে দস্তার ঘাটতি থাকলে শেষ চাষে ব্যবহার্য।")
            ),
            pestControlList = listOf(
                PestControlTip(
                    pestOrDiseaseNameBn = "মাজরা পোকা (Stem Borer)",
                    symptomsBn = "কুশি অবস্থায় মাঝের পাতা শুকিয়ে 'মৃত ডিগ' এবং থোড় অবস্থায় শীষ শুকিয়ে সাদা হয়ে যায়।",
                    remedyChemicalBn = "কার্বোফিউরান (যেমন ফুরাডান ৫জি) অথবা ভিরতাকো প্রতি বিঘায় ১ কেজি হারে প্রয়োগ।",
                    remedyOrganicBn = "জমিতে বাঁশের কঞ্চি বা ডাল পুঁতে পাখি বসার পার্চিং (Perching) ব্যবস্থা করুন।"
                ),
                PestControlTip(
                    pestOrDiseaseNameBn = "ধানের ব্লাস্ট রোগ (Rice Blast)",
                    symptomsBn = "পাতায় চোখের মতো দু'প্রান্তে সূচালো বাদামি দাগ হয়, শীষের গোড়া পচে শীষ ভেঙে পড়ে।",
                    remedyChemicalBn = "ট্রাইসাইক্লাজল গ্রুপের ট্রুপার বা নেটিভো ০.৬ গ্রাম প্রতি লিটার পানিতে মিশিয়ে স্প্রে।",
                    remedyOrganicBn = "জমিতে অতিরিক্ত নাইট্রোজেন (ইউরিয়া) পরিহার করুন এবং সিলিকন সমৃদ্ধ ছাই ব্যবহার করুন।"
                )
            ),
            irrigationTipsBn = "কাইচথোড় থেকে দুধ হওয়া পর্যন্ত জমিতে ২-৩ ইঞ্চি পানি ধরে রাখা আবশ্যক। কাটার ১০-১২ দিন আগে পানি নিষ্কাশন করুন।",
            expectedYieldBn = "বিঘা প্রতি ২০ - ২৫ মণ",
            imageUrl = "https://images.unsplash.com/photo-1536657464919-892534f60d6e?w=800&auto=format&fit=crop"
        ),
        CropGuide(
            id = "crop_potato",
            nameBn = "গোল আলু (ডায়মন্ড ও কার্ডিনাল)",
            englishName = "High Yielding Potato",
            category = CropCategory.VEGETABLES,
            season = BanglaSeason.LATE_AUTUMN,
            sowingPeriodBn = "কার্তিক - অগ্রহায়ণ (নভেম্বর)",
            harvestingPeriodBn = "মাঘ - ফাল্গুন (জানুয়ারি - ফেব্রুয়ারি)",
            idealTemperatureBn = "১৫° - ২২° সেলসিয়াস",
            soilPreparationBn = "দোআঁশ বা বেলে দোআঁশ মাটিতে ৫-৬ বার গভীর চাষ দিয়ে মাটি ঝুরঝুরে ও আগাছামুক্ত করতে হবে।",
            seedRateBn = "বিঘা প্রতি ২০০ - ২৫০ কেজি অঙ্কুরিত বীজ আলু",
            fertilizerList = listOf(
                FertilizerDosage("গোবর / কম্পোস্ট সার", "১০০০ কেজি / বিঘা", "জমি তৈরির প্রথম চাষেই মিশিয়ে দিতে হবে।"),
                FertilizerDosage("ইউরিয়া", "৩৫ কেজি / বিঘা", "অর্ধেক জমি তৈরির সময়, বাকি অর্ধেক চারা গজানোর ৩০ দিন পর গোড়ায় মাটি তোলার সময়।"),
                FertilizerDosage("টিএসপি", "২২ কেজি / বিঘা", "সম্পূর্ণ পরিমাণ শেষ চাষে প্রয়োগ করুন।"),
                FertilizerDosage("এমওপি (পটাশ)", "৩৫ কেজি / বিঘা", "অর্ধেক শেষ চাষে এবং অর্ধেক চারা গজানোর ৩০ দিন পর।"),
                FertilizerDosage("বোরন (Boron)", "১.৫ কেজি / বিঘা", "আলু ফাটা ও কালচে দাগ রোধে শেষ চাষে দিন।")
            ),
            pestControlList = listOf(
                PestControlTip(
                    pestOrDiseaseNameBn = "আলুর নাবিধসা রোগ (Late Blight)",
                    symptomsBn = "ঘন কুয়াশায় পাতার কিনারে পানিভেজা কালো দাগ এবং পাতার উল্টো পিঠে সাদা তুলোর মতো ছত্রাক দেখা যায়।",
                    remedyChemicalBn = "কুয়াশা পড়ার সাথে সাথে রিডোমিল গোল্ড অথবা ডায়থেন এম-৪৫ প্রতি লিটার পানিতে ২ গ্রাম হারে স্প্রে।",
                    remedyOrganicBn = "আক্রান্ত গাছ তুলে পুড়িয়ে ফেলুন এবং ট্রাইকোডার্মা জৈব ছত্রাকনাশক ব্যবহার করুন।"
                )
            ),
            irrigationTipsBn = "রোপণের ২০-২৫ দিন পর ১ম সেচ এবং কন্দ বৃদ্ধির সময় ১০-১২ দিন পর পর হালকা সেচ দিন। পানি জমতে দেওয়া যাবে না।",
            expectedYieldBn = "বিঘা প্রতি ১০০ - ১২০ মণ",
            imageUrl = "https://images.unsplash.com/photo-1518977676601-b53f82aba655?w=800&auto=format&fit=crop"
        ),
        CropGuide(
            id = "crop_mustard",
            nameBn = "উন্নত জাতের সরিষা (বারি সরিষা-১৪ ও ১৭)",
            englishName = "Mustard Seed",
            category = CropCategory.PULSES_OIL,
            season = BanglaSeason.WINTER,
            sowingPeriodBn = "কার্তিক - অগ্রহায়ণ (মধ্য অক্টোবর - নভেম্বর)",
            harvestingPeriodBn = "মাঘের শেষ (জানুয়ারি - ফেব্রুয়ারি)",
            idealTemperatureBn = "১২° - ২৫° সেলসিয়াস",
            soilPreparationBn = "৪-৫ বার চাষ ও মই দিয়ে মাটি বেশ মিহি ও ঝুরঝুরে করে নিতে হবে। জমিতে রস থাকা জরুরি।",
            seedRateBn = "বিঘা প্রতি ১ - ১.২ কেজি বীজ",
            fertilizerList = listOf(
                FertilizerDosage("ইউরিয়া", "৩৫ কেজি / বিঘা", "অর্ধেক শেষ চাষে ও বাকি অর্ধেক গাছে ফুল আসার পূর্বে উপরিপ্রয়োগ।"),
                FertilizerDosage("টিএসপি", "২০ কেজি / বিঘা", "সম্পূর্ণ শেষ চাষে।"),
                FertilizerDosage("এমওপি", "১২ কেজি / বিঘা", "সম্পূর্ণ শেষ চাষে।"),
                FertilizerDosage("জিপসাম ও বোরিক এসিড", "২০ কেজি ও ১ কেজি", "দানা পুষ্ট ও তেলের পরিমাণ বাড়াতে শেষ চাষে।")
            ),
            pestControlList = listOf(
                PestControlTip(
                    pestOrDiseaseNameBn = "জাব পোকা (Aphids)",
                    symptomsBn = "কচি ডগা ও ফুলের কুঁড়ি থেকে রস চুষে নেয়, গাছ কুঁকড়ে যায়।",
                    remedyChemicalBn = "ইমিডাক্লোপ্রিড গ্রুপের এডমায়ার বা টিডো প্রতি লিটার পানিতে ০.৫ মিলি হারে স্প্রে।",
                    remedyOrganicBn = "সাবান পানি স্প্রে করুন অথবা নিম তেলের নির্যাস ব্যবহার করুন।"
                )
            ),
            irrigationTipsBn = "ফুল আসার আগে একবার এবং ফল ধরার সময় একবার—মোট দুটি হালকা সেচ দিলে ফলন অনেক বেড়ে যায়।",
            expectedYieldBn = "বিঘা প্রতি ৫ - ৬ মণ",
            imageUrl = "https://images.unsplash.com/photo-1508873696983-2df5293cb32f?w=800&auto=format&fit=crop"
        ),
        CropGuide(
            id = "crop_maize",
            nameBn = "হাইব্রিড ভুট্টা (পায়োনিয়ার ও এনকে-৪০)",
            englishName = "Hybrid Maize",
            category = CropCategory.GRAINS,
            season = BanglaSeason.WINTER,
            sowingPeriodBn = "কার্তিক - পৌষ (নভেম্বর - ডিসেম্বর)",
            harvestingPeriodBn = "চৈত্র - বৈশাখ (মার্চ - এপ্রিল)",
            idealTemperatureBn = "১৮° - ৩০° সেলসিয়াস",
            soilPreparationBn = "গভীরভাবে ৪-৫ বার চাষ দিয়ে মাটি নরম করতে হবে। সারি থেকে সারির দূরত্ব ৬০ সেমি এবং গাছ থেকে গাছ ২৫ সেমি।",
            seedRateBn = "বিঘা প্রতি ৩ - ৩.৫ কেজি",
            fertilizerList = listOf(
                FertilizerDosage("ইউরিয়া", "৪৫ কেজি / বিঘা", "৩ কিস্তিতে (গাছের ৮ পাতা অবস্থায়, হাঁটু সমান হলে ও মোচা বের হওয়ার সময়)।"),
                FertilizerDosage("ডিএপি / টিএসপি", "২৫ কেজি / বিঘা", "শেষ চাষে প্রয়োগ।"),
                FertilizerDosage("পটাশ (MoP)", "২৫ কেজি / বিঘা", "শেষ চাষে অর্ধেক এবং মোচা আসার পূর্বে বাকি অর্ধেক।")
            ),
            pestControlList = listOf(
                PestControlTip(
                    pestOrDiseaseNameBn = "ফল আর্মিওয়ার্ম (Fall Armyworm)",
                    symptomsBn = "ভুট্টার পাতার ভেতরে কচি পাতা কুরে কুরে খায় এবং বড় বড় ছিদ্র তৈরি করে।",
                    remedyChemicalBn = "স্পাইনোসেড অথবা এমামেকটিন বেনজোয়েট গ্রুপের কীটনাশক বিকেলে স্প্রে করতে হবে।",
                    remedyOrganicBn = "ফেরোমোন ফাঁদ পাতুন এবং আক্রান্ত গাছের মাথায় ছাই বা বালি দিন।"
                )
            ),
            irrigationTipsBn = "ভুট্টা গাছের বৃদ্ধির প্রাথমিক পর্যায়, সিল্কিং ও দানা বাঁধার সময় মোট ৩-৪ বার সেচ দেওয়া জরুরি।",
            expectedYieldBn = "বিঘা প্রতি ৩৫ - ৪০ মণ",
            imageUrl = "https://images.unsplash.com/photo-1551754655-cd27e38d2076?w=800&auto=format&fit=crop"
        ),
        CropGuide(
            id = "crop_cabbage",
            nameBn = "শীতকালীন বাঁধাকপি ও ফুলকপি",
            englishName = "Cabbage & Cauliflower",
            category = CropCategory.VEGETABLES,
            season = BanglaSeason.WINTER,
            sowingPeriodBn = "ভাদ্র - কার্তিক (সেপ্টেম্বর - অক্টোবর)",
            harvestingPeriodBn = "পৌষ - মাঘ (ডিসেম্বর - জানুয়ারি)",
            idealTemperatureBn = "১৫° - ২৫° সেলসিয়াস",
            soilPreparationBn = "উঁচু ও পানি নিষ্কাশনযুক্ত জমিতে বেড তৈরি করে চাষ দিন। পর্যাপ্ত গোবর সার মিশিয়ে ঝুরঝুরে করুন।",
            seedRateBn = "বিঘা প্রতি ৫০ - ৬০ গ্রাম হাইব্রিড বীজ",
            fertilizerList = listOf(
                FertilizerDosage("ভার্মিকম্পোস্ট / গোবর সার", "৫০০ কেজি / বিঘা", "বেড তৈরির সময় মাটির সাথে ভালোভাবে মেশান।"),
                FertilizerDosage("ইউরিয়া", "৩০ কেজি / বিঘা", "চারা লাগানোর ১০, ২৫ ও ৪০ দিন পর উপরিপ্রয়োগ।"),
                FertilizerDosage("টিএসপি ও পটাশ", "২০ কেজি ও ২০ কেজি", "বেড তৈরির শেষ ধাপে।")
            ),
            pestControlList = listOf(
                PestControlTip(
                    pestOrDiseaseNameBn = "লেদা পোকা ও ডায়মন্ড ব্যাক মথ",
                    symptomsBn = "কপির কচি পাতা ফুটো করে খেয়ে পাতা ঝাঁঝরা করে ফেলে।",
                    remedyChemicalBn = "ক্লোরান্ট্রানিলিপ্রোল বা কার্টাপ প্রতি লিটার পানিতে ১ মিলি হারে স্প্রে।",
                    remedyOrganicBn = "ফেরোমোন ফাঁদ ও সেক্স ফেরোমোন লিউর ব্যবহার করুন।"
                )
            ),
            irrigationTipsBn = "মাটি শুকিয়ে গেলে হালকা সেচ দিতে হবে। ফুল বা হেড গঠনের সময় পর্যাপ্ত আর্দ্রতা রাখা জরুরি।",
            expectedYieldBn = "বিঘা প্রতি ২৫০০ - ৩০০০ টি কপি",
            imageUrl = "https://images.unsplash.com/photo-1598170845058-32b9d6a5da37?w=800&auto=format&fit=crop"
        )
    )

    val expertAdviceList = listOf(
        ExpertAdvice(
            id = "adv_1",
            titleBn = "চলতি মৌসুমে ধানের ব্লাস্ট ও মাজরা পোকা দমনে জরুরি করণীয়",
            expertNameBn = "ড. মো. রফিকুল ইসলাম",
            expertTitleBn = "প্রধান বৈজ্ঞানিক কর্মকর্তা (কীটতত্ত্ব বিভাগ)",
            institutionBn = "বাংলাদেশ ধান গবেষণা ইনস্টিটিউট (BRRI)",
            summaryBn = "আমন ও বোরো ধানের থোড় আসার সময়ে জমিতে রোগবালাইয়ের আক্রমণ প্রতিরোধে সঠিক সময়ে সুষম সার এবং জৈব প্রতিকার ব্যবস্থার নির্দেশিকা।",
            fullArticleBn = "ধান চাষে সঠিক সময়ে রোগ দমন না করলে ৩০-৪০% পর্যন্ত ফলন হ্রাস পেতে পারে। ব্লাস্ট রোগ প্রতিরোধের সবচেয়ে কার্যকর উপায় হলো কাইচথোড় আসার সঙ্গে সঙ্গে আগাম ছত্রাকনাশক যেমন ট্রুপার বা নেটিভো বিকেলে স্প্রে করা। জমিতে ইউরিয়া সারের মাত্রাতিরিক্ত ব্যবহার ব্লাস্ট রোগের তীব্রতা বহুগুণ বাড়িয়ে দেয়। পটাশ সারের সঠিক ব্যবহার ফসলের রোগ প্রতিরোধ ক্ষমতা বৃদ্ধি করে। জমিতে পার্চিং (প্রতি বিঘায় ৫-৬টি ডাল পোঁতা) করলে ফিঙে ও শালিক পাখি বসে মাজরা পোকার মথ ও কীড়া খেয়ে সাবাড় করে, যা সম্পূর্ণ পরিবেশবান্ধব।",
            videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            isVideo = true,
            videoDuration = "০৪:২৫ মিনিট",
            categoryBn = "ধান গবেষণা ও রোগ দমন",
            publishDateBn = "১ অক্টোবর ২০২৬",
            readTimeMinutes = 4,
            likesCount = 384
        ),
        ExpertAdvice(
            id = "adv_2",
            titleBn = "মাটির স্বাস্থ্য রক্ষা ও কম খরচে দ্বিগুণ ফলনে ভার্মিকম্পোস্টের ব্যবহার",
            expertNameBn = "কৃষিবিদ সালমা খাতুন",
            expertTitleBn = "উপজেলা কৃষি কর্মকর্তা",
            institutionBn = "কৃষি সম্প্রসারণ অধিদপ্তর (DAE), রংপুর",
            summaryBn = "জমির উর্বরতা ধরে রাখতে কেঁচো সার বা ভার্মিকম্পোস্ট তৈরির সহজ পদ্ধতি এবং ফসলে এর সঠিক প্রয়োগ কৌশল।",
            fullArticleBn = "ভার্মিকম্পোস্ট মাটিতে অনুজীবের কার্যকারিতা বহুগুণ বৃদ্ধি করে। এটি মাটিতে পানি ধারণ ক্ষমতা বাড়ায় যার ফলে সেচের খরচ ২০% পর্যন্ত সাশ্রয় হয়। প্রতি বিঘায় ১০০-১৫০ কেজি ভার্মিকম্পোস্ট ব্যবহারে রাসায়নিক সারের ব্যবহার প্রায় ৩০% কমিয়ে আনা সম্ভব। শাকসবজি এবং ফলমূল চাষে ভার্মিকম্পোস্টের ব্যবহার পণ্যের স্বাদ ও পুষ্টিগুণ অতুলনীয় করে তোলে। কৃষকরা নিজেদের গোবর ও সবজির উচ্ছিষ্ট দিয়ে সহজেই বাড়িতে সিমেন্টের রিং বা চাড়িতে এই সার উৎপাদন করতে পারেন।",
            videoUrl = null,
            isVideo = false,
            videoDuration = null,
            categoryBn = "জৈব কৃষি ও মৃত্তিকা",
            publishDateBn = "২৮ সেপ্টেম্বর ২০২৬",
            readTimeMinutes = 3,
            likesCount = 215
        ),
        ExpertAdvice(
            id = "adv_3",
            titleBn = "আগাম রবি ফসল চাষে জলবায়ু সহনশীল জাত নির্বাচন ও সেচ ব্যবস্থাপনা",
            expertNameBn = "প্রফেসর ড. লুৎফর রহমান",
            expertTitleBn = "সিনিয়র গবেষক ও উদ্ভিদ প্রজননবিদ",
            institutionBn = "বাংলাদেশ কৃষি বিশ্ববিদ্যালয় (BAU), ময়মনসিংহ",
            summaryBn = "অসময়ের খরা ও অতিরিক্ত বৃষ্টিপাতের ঝুঁকি মোকাবিলায় আগাম জাতের গম, ভুট্টা ও সরিষার ফলন দ্বিগুণ করার আধুনিক কৌশল।",
            fullArticleBn = "জলবায়ু পরিবর্তনের কারণে আবহাওয়ার যে অনিশ্চয়তা তৈরি হচ্ছে, তাতে অল্প সময়ে ঘরে তোলা যায় এমন জাত নির্বাচন করা অত্যন্ত বুদ্ধিমত্তার কাজ। যেমন বারি সরিষা-১৪ মাত্র ৭৫-৮০ দিনে পেকে যায়, ফলে এর পর অনায়াসে বোরো ধান চাষ করা সম্ভব। জমিতে আধুনিক ড্রিপ ইরিগেশন বা পর্যায়ক্রমিক ভেজানো ও শুকানো (AWD) পদ্ধতি ব্যবহার করলে বিদ্যুৎ ও ডিজেলের ব্যাপক সাশ্রয় হয়।",
            videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            isVideo = true,
            videoDuration = "০৫:১০ মিনিট",
            categoryBn = "জলবায়ু ও শস্য ব্যবস্থাপনা",
            publishDateBn = "২৫ সেপ্টেম্বর ২০২৬",
            readTimeMinutes = 5,
            likesCount = 429
        )
    )

    val communityFeedList = listOf(
        CommunityPost(
            id = "post_1",
            authorNameBn = "মোঃ রফিকুল আলম",
            authorRoleBn = "কৃষক উদ্যোক্তা",
            authorDistrictBn = "দিনাজপুর সদর",
            timeAgoBn = "২ ঘণ্টা আগে",
            textContentBn = "আলহামদুলিল্লাহ! ব্রি ধান-৭৫ এর ফলন এবার বেশ চমৎকার হয়েছে। বিঘায় প্রায় ২২ মণ করে পেয়েছি। কোনো প্রকার অতিরিক্ত রাসায়নিক কীটনাশক ছাড়াই শুধু পার্চিং ও নিমপাতা স্প্রে করে মাজরা পোকা দমন করেছি। ভাইরা, কারও পরামর্শ লাগলে কমেন্টে জানাতে পারেন।",
            imageUrl = "https://images.unsplash.com/photo-1500382017468-9049fed747ef?w=800&auto=format&fit=crop",
            videoUrl = null,
            likesCount = 68,
            commentsCount = 14,
            sharesCount = 5,
            isLikedByUser = false,
            comments = mutableListOf(
                PostComment("c1", "সালাম মোল্লা", "বগুড়া", "মাশাআল্লাহ রফিক ভাই! পার্চিং কি চারা লাগানোর কতদিন পর করেছেন?", "১ ঘণ্টা আগে"),
                PostComment("c2", "কৃষিবিদ তানভীর", "রংপুর", "অসাধারণ উদ্যোগ! পরিবেশবান্ধব চাষাবাদের উজ্জ্বল উদাহরণ।", "৪৫ মিনিট আগে")
            )
        ),
        CommunityPost(
            id = "post_2",
            authorNameBn = "আনোয়ার হোসেন",
            authorRoleBn = "সবজি চাষী",
            authorDistrictBn = "যশোর, মনিরামপুর",
            timeAgoBn = "৫ ঘণ্টা আগে",
            textContentBn = "আমার বেগুনের ক্ষেতে ডগা ও ফল ছিদ্রকারী পোকার ব্যাপক উপদ্রব দেখা দিয়েছে। কিছু ডগা ঢলে পড়ছে। সেক্স ফেরোমোন ফাঁদ লাগিয়েছি কিন্তু কাজ কম হচ্ছে। অভিজ্ঞ ভাইয়েরা কী ঔষধ বা স্প্রে দেওয়া যেতে পারে জানাবেন কি?",
            imageUrl = "https://images.unsplash.com/photo-1598170845058-32b9d6a5da37?w=800&auto=format&fit=crop",
            videoUrl = null,
            likesCount = 29,
            commentsCount = 8,
            sharesCount = 2,
            isLikedByUser = true,
            comments = mutableListOf(
                PostComment("c3", "ডা. কামরুল হাসান", "যশোর", "আনোয়ার ভাই, আক্রান্ত ডগাগুলো হাত দিয়ে ভেঙে মাটির নিচে পুঁতে ফেলুন এবং সাথে ট্রেসার (স্পাইনোসেড) ০.৪ মিলি প্রতি লিটার পানিতে স্প্রে করুন।", "৩ ঘণ্টা আগে")
            )
        ),
        CommunityPost(
            id = "post_3",
            authorNameBn = "কৃষি সম্প্রসারণ বার্তা",
            authorRoleBn = "কৃষি তথ্য সার্ভিস (AIS)",
            authorDistrictBn = "ঢাকা",
            timeAgoBn = "৮ ঘণ্টা আগে",
            textContentBn = "ভিডিও প্রতিবেদন: আধুনিক পদ্ধতিতে সাইলেজ তৈরি ও গবাদিপশুর পুষ্টিকর খাদ্য সংরক্ষণ কৌশল। বর্ষা বা বন্যায় ঘাসের সংকট কাটাতে এখনই শিখে নিন সাইলেজ তৈরির সহজ ধাপগুলো।",
            imageUrl = null,
            videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
            videoTitleBn = "সহজ উপায়ে পুষ্টিকর সাইলেজ প্রস্তুতকরণ",
            isVideo = true,
            likesCount = 184,
            commentsCount = 32,
            sharesCount = 47,
            isLikedByUser = false,
            comments = mutableListOf(
                PostComment("c4", "মতিউর রহমান", "পাবনা", "খুবই তথ্যবহুল ভিডিও। ভুট্টার কাণ্ড দিয়ে বানিয়ে চমৎকার ফলাফল পেয়েছি।", "৬ ঘণ্টা আগে")
            )
        ),
        CommunityPost(
            id = "post_4",
            authorNameBn = "আবদুর রহিম মিয়া",
            authorRoleBn = "বীজ উৎপাদক",
            authorDistrictBn = "রাজশাহী, গোদাগাড়ী",
            timeAgoBn = "১ দিন আগে",
            textContentBn = "এ বছর খাঁটি সরিষার আবাদের জন্য বারি-১৪ এর বীজ সংগ্রহ শুরু করেছি। প্রতি বিঘায় ফুল আসলেই যেন হলুদ চাদর বিছানো রূপ! মৌমাছির বক্স বসালে পরাগায়ন ভালো হয় এবং খাঁটি মধুও পাওয়া যায়।",
            imageUrl = "https://images.unsplash.com/photo-1508873696983-2df5293cb32f?w=800&auto=format&fit=crop",
            videoUrl = null,
            likesCount = 95,
            commentsCount = 19,
            sharesCount = 9,
            isLikedByUser = false,
            comments = mutableListOf(
                PostComment("c5", "শাহেদ আলী", "নাটোর", "রহিম ভাই, ৫ কেজি বীজ কি পাঠানো যাবে সুন্দরবন কুরিয়ারে?", "১৮ ঘণ্টা আগে")
            )
        )
    )

    val marketplaceProducts = listOf(
        MarketProduct(
            id = "prod_1",
            nameBn = "খাঁটি মিনিকেট সুগন্ধি চাল (নতুন ধান)",
            category = ProductCategory.CROPS,
            pricePerUnit = 2450.0,
            unitBn = "মণ (৪০ কেজি)",
            availableStockBn = "১৫০ মণ মজুদ",
            sellerNameBn = "মোঃ আবদুর রশিদ",
            sellerPhone = "01712345678",
            sellerDistrictBn = "দিনাজপুর",
            sellerUpazilaBn = "বীরগঞ্জ",
            isVerifiedFarmer = true,
            isOrganicCertified = false,
            descriptionBn = "দিনাজপুরের বিখ্যাত সুগন্ধি ধান থেকে সরাসরি চালিত। কোনো কৃত্রিম পলিশ বা রাসায়নিক মেশানো হয়নি। চকচকে ও চমৎকার সুঘ্রাণযুক্ত। পাইকারি ও খুচরা বিক্রয়যোগ্য।",
            rating = 4.9,
            imageUrl = "https://images.unsplash.com/photo-1586201375761-83865001e31c?w=800&auto=format&fit=crop"
        ),
        MarketProduct(
            id = "prod_2",
            nameBn = "তাজা দেশি গোল আলু (হিমায়িত মুক্ত)",
            category = ProductCategory.VEGETABLES,
            pricePerUnit = 32.0,
            unitBn = "কেজি",
            availableStockBn = "৫০০ কেজি মজুদ",
            sellerNameBn = "সালাম মন্ডল",
            sellerPhone = "01819876543",
            sellerDistrictBn = "বগুড়া",
            sellerUpazilaBn = "শিবগঞ্জ",
            isVerifiedFarmer = true,
            isOrganicCertified = true,
            descriptionBn = "বগুড়ার মাঠ থেকে সরাসরি তোলা তাজা দেশি লাল ও গোল আলু। কোল্ড স্টোরেজের আলু নয়, রান্নায় স্বাদ অতুলনীয় এবং দ্রুত সেদ্ধ হয়।",
            rating = 4.7,
            imageUrl = "https://images.unsplash.com/photo-1518977676601-b53f82aba655?w=800&auto=format&fit=crop"
        ),
        MarketProduct(
            id = "prod_3",
            nameBn = "খাঁটি কাঠের ঘানিতে ভাঙা সরিষার তেল",
            category = ProductCategory.SPICES,
            pricePerUnit = 280.0,
            unitBn = "লিটার",
            availableStockBn = "৮০ লিটার মজুদ",
            sellerNameBn = "মোঃ শরিফুল ইসলাম",
            sellerPhone = "01911223344",
            sellerDistrictBn = "যশোর",
            sellerUpazilaBn = "ঝিকরগাছা",
            isVerifiedFarmer = true,
            isOrganicCertified = true,
            descriptionBn = "নিজেদের জমির মাঘি সরিষা কাঠের ঘানিতে ধীর গতিতে ভাঙানো। কোনো ঝাঁঝালো কেমিক্যাল মুক্ত, ঝাঁঝালো ঘ্রাণ ও আসল হলুদ রঙ। বোতলজাত করে পাঠানো হয়।",
            rating = 5.0,
            imageUrl = "https://images.unsplash.com/photo-1474979266404-7eaacbcd87c5?w=800&auto=format&fit=crop"
        ),
        MarketProduct(
            id = "prod_4",
            nameBn = "বারি সরিষা-১৪ অনুমোদিত প্রত্যায়িত বীজ",
            category = ProductCategory.SEEDS,
            pricePerUnit = 160.0,
            unitBn = "কেজি প্যাকেট",
            availableStockBn = "২০০ প্যাকেট",
            sellerNameBn = "কৃষি বীজ ভাণ্ডার (কৃষক সমবায়)",
            sellerPhone = "01755667788",
            sellerDistrictBn = "পাবনা",
            sellerUpazilaBn = "ঈশ্বরদী",
            isVerifiedFarmer = true,
            isOrganicCertified = false,
            descriptionBn = "গজানোর হার ৯৫%+। মাত্র ৭৫-৮০ দিনে ফসল আসে। বিঘা প্রতি ৫-৬ মণ ফলন নিশ্চিত। রোগ প্রতিরোধ ক্ষমতা সম্পন্ন খাঁটি বীজ।",
            rating = 4.8,
            imageUrl = "https://images.unsplash.com/photo-1508873696983-2df5293cb32f?w=800&auto=format&fit=crop"
        ),
        MarketProduct(
            id = "prod_5",
            nameBn = "১০০% খাঁটি কেঁচো সার (ভার্মিকম্পোস্ট)",
            category = ProductCategory.FERTILIZER,
            pricePerUnit = 18.0,
            unitBn = "কেজি (৫০ কেজির বস্তা)",
            availableStockBn = "১০০০ কেজি",
            sellerNameBn = "সবুজ বাংলা জৈব খামার",
            sellerPhone = "01622334455",
            sellerDistrictBn = "ময়মনসিংহ",
            sellerUpazilaBn = "ত্রিশাল",
            isVerifiedFarmer = true,
            isOrganicCertified = true,
            descriptionBn = "সম্পূর্ণ গন্ধহীন, কালো চা-পাতার মতো ঝুরঝুরে উন্নত কেঁচো সার। যেকোনো শাকসবজি, ছাদবাগান ও ফসলি জমিতে মাটির উর্বরতা বৃদ্ধিতে চমৎকার কার্যকরী।",
            rating = 4.9,
            imageUrl = "https://images.unsplash.com/photo-1615811361523-6bd03d7748e7?w=800&auto=format&fit=crop"
        ),
        MarketProduct(
            id = "prod_6",
            nameBn = "রাজশাহীর মিষ্টি আম্রপালি ও ফজলি আম",
            category = ProductCategory.FRUITS,
            pricePerUnit = 95.0,
            unitBn = "কেজি",
            availableStockBn = "৩০০ কেজি",
            sellerNameBn = "মোঃ কামরুজ্জামান",
            sellerPhone = "01733445566",
            sellerDistrictBn = "রাজশাহী",
            sellerUpazilaBn = "বাঘা",
            isVerifiedFarmer = true,
            isOrganicCertified = true,
            descriptionBn = "বাগান থেকে তরতাজা ছেঁড়া ফর্মালিনমুক্ত পরিপক্ক আম। মিষ্টি স্বাদের নিশ্চয়তা। ক্যারেট ভর্তি করে কুরিয়ারে দেশের যেকোনো প্রান্তে পাঠানো হয়।",
            rating = 4.9,
            imageUrl = "https://images.unsplash.com/photo-1553279768-865429fa0078?w=800&auto=format&fit=crop"
        )
    )
}
