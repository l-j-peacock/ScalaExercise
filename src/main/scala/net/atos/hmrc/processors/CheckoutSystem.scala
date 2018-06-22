package net.atos.hmrc.processors

import java.util.{Currency, Locale}

import com.typesafe.config.ConfigFactory

class CheckoutSystem {

  private val config = ConfigFactory.load("settings.conf")

  def getItemValue(item: String): Double = {
    try {
      config.getDouble(item + ".value")
    } catch {
      case iae: IllegalArgumentException => 0.0
      case e: Exception => 0.0
    }
  }

  def isItemBogof(item: String): Boolean = {
    try {
      config.getBoolean(item + ".bogof")
    } catch {
      case iae: IllegalArgumentException => false
      case e: Exception => false
    }
  }

  def isItemThreeForTwo(item: String): Boolean = {
    try {
      config.getBoolean(item + ".threeForTwo")
    } catch {
      case iae: IllegalArgumentException => false
      case e: Exception => false
    }
  }

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
    * @param numberOfItemInCart - Takes the total number of said item in the cart
    * @return - Returns the total cost of the items with offers factored in as a double
    */
  def calculateBogofOffers(item: String, numberOfItemInCart: Int): Double = {
    val itemsWithOffer = numberOfItemInCart / 2
    val itemsWithoutOffer = numberOfItemInCart % 2
    val itemValue = getItemValue(item)

    (itemValue * itemsWithOffer) + (itemValue * itemsWithoutOffer)
  }

  /**
    *
    * @param numberOfItemInCart - Takes the total number of said item in the cart
    * @return - Returns the total cost of the items with offers factored in as a double
    */
  def calculateThreeForTwoOffers(item: String, numberOfItemInCart: Int): Double = {
    val itemsWithOffer: Int = numberOfItemInCart / 3
    val itemsWithoutOffer: Int = numberOfItemInCart % 3
    val itemValue = getItemValue(item)

    ((itemsWithOffer * 2) * itemValue) + (itemValue * itemsWithoutOffer)
  }

  /**
    *
    * @param ShoppingCart - Takes a List of Strings
    * @return - Returns the total formatted cost of the cart in £'s
    */
  def checkoutCalculator(ShoppingCart: List[String]): String = {
    var totalCost: Double = 0
    val uniqueItemsInCart: List[String] = ShoppingCart.distinct
    val mapOfItemsAndQuantity: Map[String, Int] = uniqueItemsInCart.map(item => item -> ShoppingCart.count(_ == item)).toMap

    for ((item, itemQuantity) <- mapOfItemsAndQuantity) {
      if (isItemBogof(item).equals(true)) {
        totalCost += calculateBogofOffers(item, itemQuantity)
      } else if (isItemThreeForTwo(item).equals(true)) {
        totalCost += calculateThreeForTwoOffers(item, itemQuantity)
      } else {
        totalCost += getItemValue(item) * itemQuantity
      }
    }

    formatCostToString(totalCost / 100)
  }

}
