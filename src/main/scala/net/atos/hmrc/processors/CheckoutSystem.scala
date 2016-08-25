package net.atos.hmrc.processors

import java.util.{Currency, Locale}

import com.typesafe.config.{ ConfigFactory }

class CheckoutSystem {

  private val config = ConfigFactory.load("settings.conf")

  /**
    *
    * @param cartCost - Takes the total cost of the cart / 100
    * @return - Returns a formatted cost in £'s
    */
  def formatCostToString(cartCost: Double): String = {
    val gbp = Currency.getInstance(Locale.UK)
    val formatter = java.text.NumberFormat.getCurrencyInstance
    formatter.setCurrency(gbp)
    formatter.format(cartCost)
  }

  /**
    *
    * @param ShoppingCart - Takes a List of Strings
    * @return - Returns the total formatted cost of the cart in £'s
    */
  def checkoutCalculator(ShoppingCart: List[String]): String = {
    var totalCost: Double = 0
    var uniqueItemsInCart: List[String] = ShoppingCart.distinct
    val mapOfItemsAndQuantity: Map[String, Int] = uniqueItemsInCart.map(item => item -> ShoppingCart.count(_ == item)).toMap


    mapOfItemsAndQuantity foreach {item => totalCost += (config.getInt(item._1 + ".value") * item._2)}

    formatCostToString(totalCost / 100)
  }

}
