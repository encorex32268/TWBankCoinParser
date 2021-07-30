import java.math.BigDecimal

class Coin(
    private val dollar : String ,
    private val cashBuyIn : String,
    private val cashSoldOut : String,
    private val sightBuyIn : String,
    private val sightSoldOut : String

) {
    override fun toString(): String {
        return "Coin(dollar='$dollar', cashBuyIn=${cashBuyIn}, cashSoldOut=$cashSoldOut, sightBuyIn=$sightBuyIn, sightSoldOut=$sightSoldOut)"
    }
}