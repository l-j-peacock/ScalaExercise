package net.atos.hmrc.processors

import org.scalatest._

class CheckoutSystemTest extends FunSuite {

  val checkout = new CheckoutSystem

  test("Returns the correct cost of 1 Apple (Value in pounds)") {
    val cart: List[String] = List("Apple")
    val total = checkout.checkoutCalculator(cart)
    assert(total.equalsIgnoreCase("£0.60"))
  }

  test("Returns the correct cost of 2 Apples (Value in pounds)") {
    val cart: List[String] = List("Apple", "Apple")
    val total = checkout.checkoutCalculator(cart)
    assert(total.equalsIgnoreCase("£1.20"))
  }

  test("Returns the correct cost of 3 Apples (Value in pounds)") {
    val cart: List[String] = List("Apple", "Apple", "Apple")
    val total = checkout.checkoutCalculator(cart)
    assert(total.equalsIgnoreCase("£1.80"))
  }

  test("Returns the correct cost of 1 Orange (Value in pounds)") {
    val cart: List[String] = List("Orange")
    val total = checkout.checkoutCalculator(cart)
    assert(total.equalsIgnoreCase("£0.25"))
  }

  test("Returns the correct cost of 2 Oranges (Value in pounds)") {
    val cart: List[String] = List("Orange", "Orange")
    val total = checkout.checkoutCalculator(cart)
    assert(total.equalsIgnoreCase("£0.50"))
  }

  test("Returns the correct cost of 3 Oranges (Value in pounds)") {
    val cart: List[String] = List("Orange", "Orange", "Orange")
    val total = checkout.checkoutCalculator(cart)
    assert(total.equalsIgnoreCase("£0.75"))
  }

}
