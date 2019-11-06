package com.example.month6.databean
import com.google.gson.annotations.SerializedName


data class HomeData(
    @SerializedName("imageArr")
    var imageArr: List<ImageArr>,
    @SerializedName("proInfo")
    var proInfo: ProInfo
) {
    data class ImageArr(
        @SerializedName("ID")
        var iD: String, // 5
        @SerializedName("IMAPAURL")
        var iMAPAURL: String, // http://gwop.xtrich.com/xtApp/twcx.html
        @SerializedName("IMAURL")
        var iMAURL: String // http://169.254.44.116:8080/P2PInvest/images/index04.png
    )

    data class ProInfo(
        @SerializedName("id")
        var id: String, // 1
        @SerializedName("memberNum")
        var memberNum: String, // 100
        @SerializedName("minTouMoney")
        var minTouMoney: String, // 100
        @SerializedName("money")
        var money: String, // 10
        @SerializedName("name")
        var name: String, // 纭呰胺褰╄櫣鏂版墜璁″垝
        @SerializedName("progress")
        var progress: String, // 90
        @SerializedName("suodingDays")
        var suodingDays: String, // 30
        @SerializedName("yearRate")
        var yearRate: String // 8.00
    )
}