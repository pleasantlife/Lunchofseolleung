package com.gandan.lunchofseolleung

class DataModels {

    data class StoreInfo(var storeName : String, var address : String, var priceCash : Int, var priceCard : Int, var openTIme : String, var closeTime : String, var menu : String)
}