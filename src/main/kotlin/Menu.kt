import java.util.Scanner

class Menu(val menuItems: MutableList<MenuItem>) {

    fun displayMenu() {
        for (i in 0 until menuItems.size) {
            println("$i. ${menuItems[i].text}")
        }
    }

    fun readInput(): Int {
        val scanner = Scanner(System.`in`)
        while (true) {
            print("Выберите пункт меню: ")
            if (scanner.hasNextInt()) {
                val input = scanner.nextInt()
                if (input in 0 until menuItems.size) {
                    return input
                }
            }
            println("Неверный ввод. Пожалуйста, введите число от 0 до ${menuItems.size - 1}.")
            scanner.nextLine() // Очистка буфера ввода
        }
    }

    fun executeMenuItem(index: Int) {
        menuItems[index].action.invoke()
    }
}
data class MenuItem(val text: String, val action: () -> Unit)