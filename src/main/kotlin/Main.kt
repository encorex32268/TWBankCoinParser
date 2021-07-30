import org.jsoup.Jsoup
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter
import java.net.URL
import java.net.URLConnection
import javax.swing.JPanel

fun main() {
    crawlerCoinData()
//    downloadRateCoinData()


}

private fun downloadRateCoinData() {
    val file = File("CoinRate.txt")
    val downLoadURL = "https://rate.bot.com.tw/xrt/fltxt/0/day"
    val connection = URL(downLoadURL).openConnection()
    connection.addRequestProperty(
        "User-Agent",
        "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0"
    )
    val bis = BufferedInputStream(connection.getInputStream())
    val fis = FileOutputStream(file)
    val buffer = ByteArray(1024)
    var count = 0
    while (bis.read(buffer, 0, 1024).also { count = it } != -1) {
        fis.write(buffer, 0, count)
    }
    fis.close()
    bis.close()
}

private fun crawlerCoinData() {
    val url = "https://rate.bot.com.tw/xrt?Lang=zh-TW"
    val jsoup = Jsoup.connect(url).get()
    var coinArray = arrayListOf<Coin>()

    val coin = jsoup.getElementsByClass("hidden-phone print_show").text()
    val step1coin = coin.replace(") ", ")/")
    val step2coin = step1coin.split("/")

    // data-table , class
    val cashBuyIn =
        jsoup.select("[data-table=\"本行現金買入\"]").select("[class=\"text-right display_none_print_show print_width\"]").toString()
    val step1CashBuyIn =
        cashBuyIn.replace("<td data-table=\"本行現金買入\" class=\"text-right display_none_print_show print_width\">", "")
    val step2CashBuyIn = step1CashBuyIn.replace("</td>", "/")
    val step3CashBuyIn = step2CashBuyIn.split("/")

    val cashSoldOut =
        jsoup.select("[data-table=\"本行現金賣出\"]").select("[class=\"rate-content-cash text-right print_hide\"]").toString()
    val step1CashSoldOut =
        cashSoldOut.replace("<td data-table=\"本行現金賣出\" class=\"rate-content-cash text-right print_hide\">", "")
    val step2CashSoldOut = step1CashSoldOut.replace("</td>", "/")
    val step3CashSoldOut = step2CashSoldOut.split("/")

    val sightBuyIn =
        jsoup.select("[data-table=\"本行即期買入\"]").select("[class=\"text-right display_none_print_show print_width\"]")
            .toString()
    val step1SightBuyIn =
        sightBuyIn.replace("<td data-table=\"本行即期買入\" class=\"text-right display_none_print_show print_width\">", "")
    val step2SightBuyIn = step1SightBuyIn.replace("</td>", "/")
    val step3SightBuyIn = step2SightBuyIn.split("/")


    val sightSoldOut =
        jsoup.select("[data-table=\"本行即期賣出\"]").select("[class=\"text-right display_none_print_show print_width\"]")
            .toString()
    val step1SightSoldOut =
        sightSoldOut.replace("<td data-table=\"本行即期賣出\" class=\"text-right display_none_print_show print_width\">", "")
    val step2SightSoldOut = step1SightSoldOut.replace("</td>", "/")
    val step3SightSoldOut = step2SightSoldOut.split("/")

    for (i in step2coin.indices) {
        val coin = Coin(
            step2coin[i].trim(),
            step3CashBuyIn[i].trim(),
            step3CashSoldOut[i].trim(),
            step3SightBuyIn[i].trim(),
            step3SightSoldOut[i].trim()
        )
        coinArray.add(coin)
    }
    coinArray.forEach {
        println(it.toString())
    }
}

fun getCoin(){
    val url = "https://rate.bot.com.tw/xrt?Lang=zh-TW"
    val jsoup = Jsoup.connect(url).get()
    val coin = jsoup.getElementsByClass("hidden-phone print_show").text()
    var replaceCoins = coin.replace(") ",") /")
    replaceCoins.split("/").forEach {
        println(it)
    }
}
fun getRateCashBuyIn(){
    //<td data-table="本行現金買入" class="rate-content-cash text-right print_hide">27.53</td>
    val url = "https://rate.bot.com.tw/xrt?Lang=zh-TW"
    val jsoup = Jsoup.connect(url).get()
    val cashBuyIn = jsoup.select("[data-table=\"本行現金買入\"]").select("[class=\"rate-content-cash text-right print_hide\"]").toString()
    val step1 = cashBuyIn.replace("<td data-table=\"本行現金買入\" class=\"rate-content-cash text-right print_hide\">","")
    val step2 = step1.replace("</td>","/")
    step2.split("/").forEach {
        println(it)
    }

}

fun getRateCashSoldOut(){
    //<td data-table="本行現金賣出" class="rate-content-cash text-right print_hide">28.2</td>
    val url = "https://rate.bot.com.tw/xrt?Lang=zh-TW"
    val jsoup = Jsoup.connect(url).get()
    val cashSoldOut = jsoup.select("[data-table=\"本行現金賣出\"]").select("[class=\"rate-content-cash text-right print_hide\"]").toString()
    val step1 = cashSoldOut.replace("<td data-table=\"本行現金賣出\" class=\"rate-content-cash text-right print_hide\">","")
    val step2 = step1.replace("</td>","/")
    step2.split("/").forEach {
        println(it)
    }
}
fun getRateSightBuyIn(){
    //<td data-table="本行即期買入" class="rate-content-sight text-right print_hide hidden-phone" data-hide="phone">27.88</td>
    val url = "https://rate.bot.com.tw/xrt?Lang=zh-TW"
    val jsoup = Jsoup.connect(url).get()
    val sightBuyIn = jsoup.select("[data-table=\"本行即期買入\"]").select("[class=\"text-right display_none_print_show print_width\"]").toString()
    val step1 = sightBuyIn.replace("<td data-table=\"本行即期買入\" class=\"text-right display_none_print_show print_width\">","")
    val step2 = step1.replace("</td>","/")
    step2.split("/").forEach {
        println(it)
    }
}

fun getRateSightSoldOut(){
    //<td data-table="本行即期賣出" class="rate-content-sight text-right print_hide hidden-phone" data-hide="phone">27.88</td>
    val url = "https://rate.bot.com.tw/xrt?Lang=zh-TW"
    val jsoup = Jsoup.connect(url).get()
    val sightSoldOut = jsoup.select("[data-table=\"本行即期賣出\"]").select("[class=\"text-right display_none_print_show print_width\"]").toString()
    val step1SightSoldOut = sightSoldOut.replace("<td data-table=\"本行即期賣出\" class=\"text-right display_none_print_show print_width\">","")
    val step2SightSoldOut = step1SightSoldOut.replace("</td>","/")
    step2SightSoldOut.split("/").forEach {
        println(it)
    }
}
