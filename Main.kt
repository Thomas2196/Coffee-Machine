package machine

import java.util.Scanner

class CoffeeMachine(var water: Int, var milk: Int, var beans: Int, var cups: Int, var money: Int) {

    private data class Receipe(val water: Int, val milk: Int, val beans: Int, val cups: Int, val money: Int)
    private val scanner = Scanner(System.`in`)
    private val espresso = Receipe(250, 0, 16, 1, 4)
    private val latte = Receipe(350, 75, 20, 1, 7)
    private val cappuccino = Receipe(200, 100, 12, 1, 6)


    fun run() {

        loop@ do {
            print("Write action (buy, fill, take, remaining, exit):")
            when (scanner.next()) {
                "buy" -> printBuy()
                "fill" -> fill()
                "take" -> take()
                "remaining" -> remaining()
                "exit" -> break@loop
                else -> println("Command unknown")
            }
        } while (true)

    }


    private fun remaining() {
        println("\nThe coffee machine has:\n" +
                "$water of water\n" +
                "$milk of milk\n" +
                "$beans of coffee beans\n" +
                "$cups of disposable cups\n" +
                "$money of money\n")
    }

    private fun printBuy() {
        print("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
        when (scanner.next()) {
            "1" -> buy(espresso)
            "2" -> buy(latte)
            "3" -> buy(cappuccino)
        }
    }

    private fun buy (coffeeReceipe: Receipe) {
        when {
            water < coffeeReceipe.water -> return println("Sorry, not enough water!\n")
            milk < coffeeReceipe.milk -> return println("Sorry, not enough milk!\n")
            beans < coffeeReceipe.beans -> return println("Sorry, not enough beans!\n")
            cups < coffeeReceipe.cups -> return println("Sorry, not enough cups!\n")
        }
        println("I have enough resources, making you a coffee!\n")
        water -= coffeeReceipe.water
        milk -= coffeeReceipe.milk
        beans -= coffeeReceipe.beans
        cups -= coffeeReceipe.cups
        money += coffeeReceipe.money
    }

    private fun fill() {
        print("Write how many ml of water do you want to add: ")
        water += scanner.nextInt()
        print("Write how many ml of milk do you want to add: ")
        milk += scanner.nextInt()
        print("Write how many grams of coffee beans do you want to add: ")
        beans += scanner.nextInt()
        print("Write how many cups of coffee do you want to add: ")
        cups += scanner.nextInt()
        println()
    }

    private fun take() {
        println("I gave you $$money")
        money = 0
    }

}

fun main() {
    val coffeeMachine = CoffeeMachine(400, 540, 120, 9, 550)
    coffeeMachine.run()
}