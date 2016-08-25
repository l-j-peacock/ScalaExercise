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
    assert(total.equalsIgnoreCase("£0.60"))
  }

  test("Returns the correct cost of 3 Apples (Value in pounds)") {
    val cart: List[String] = List("Apple", "Apple", "Apple")
    val total = checkout.checkoutCalculator(cart)
    assert(total.equalsIgnoreCase("£1.20"))
  }

  test("Returns the correct cost of 4 Apples (Value in pounds)") {
    val cart: List[String] = List("Apple", "Apple", "Apple", "Apple")
    val total = checkout.checkoutCalculator(cart)
    assert(total.equalsIgnoreCase("£1.20"))
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
    assert(total.equalsIgnoreCase("£0.50"))
  }

  test("Returns the correct cost of 4 Oranges (Value in pounds)") {
    val cart: List[String] = List("Orange", "Orange", "Orange", "Orange")
    val total = checkout.checkoutCalculator(cart)
    assert(total.equalsIgnoreCase("£0.75"))
  }

  test("Returns the correct cost of 4 Apples and 1 Orange (Value in pounds)") {
    val cart: List[String] = List("Apple", "Apple", "Apple", "Apple", "Orange")
    val total = checkout.checkoutCalculator(cart)
    assert(total.equalsIgnoreCase("£1.45"))
  }

  test("Returns the correct cost of 4 Oranges and 1 Apple (Value in pounds)") {
    val cart: List[String] = List("Apple", "Orange", "Orange", "Orange", "Orange")
    val total = checkout.checkoutCalculator(cart)
    assert(total.equalsIgnoreCase("£1.35"))
  }

  test("Returns the correct cost of 3 Oranges and 2 Apples (Value in pounds)") {
    val cart: List[String] = List("Apple", "Apple", "Orange", "Orange", "Orange")
    val total = checkout.checkoutCalculator(cart)
    assert(total.equalsIgnoreCase("£1.10"))
  }

  test("Returns the correct cost of 20 Oranges and 20 Apple (Value in pounds)") {
    val cartA: List[String] = List.fill(20)("Orange")
    val cartB: List[String] = List.fill(20)("Apple")
    val cartC: List[String] = cartA ::: cartB
    val total = checkout.checkoutCalculator(cartC)
    assert(total.equalsIgnoreCase("£9.50"))
  }

  test("Returns the correct cost of 100 Oranges and 2 Apples (Value in pounds)") {
    val cartA: List[String] = List.fill(100)("Orange")
    val cartB: List[String] = List.fill(2)("Apple")
    val cartC: List[String] = cartA ::: cartB
    val total = checkout.checkoutCalculator(cartC)
    assert(total.equalsIgnoreCase("£17.35"))
  }

  test("Returns the correct cost of 100 Apples and 2 Oranges (Value in pounds)") {
    val cartA: List[String] = List.fill(2)("Orange")
    val cartB: List[String] = List.fill(100)("Apple")
    val cartC: List[String] = cartA ::: cartB
    val total = checkout.checkoutCalculator(cartC)
    assert(total.equalsIgnoreCase("£30.50"))
  }

  test("Returns the correct cost of 100 Apples and 100 Oranges (Value in pounds)") {
    val cartA: List[String] = List.fill(100)("Orange")
    val cartB: List[String] = List.fill(100)("Apple")
    val cartC: List[String] = cartA ::: cartB
    val total = checkout.checkoutCalculator(cartC)
    assert(total.equalsIgnoreCase("£46.75"))
  }

  test("Returns the correct cost after entering an invalid item (Value in pounds)") {
    val cart: List[String] = List("Banana", "Apple", "Orange", "Orange", "Orange")
    val total = checkout.checkoutCalculator(cart)
    assert(total.equalsIgnoreCase("£1.10"))
  }

}
