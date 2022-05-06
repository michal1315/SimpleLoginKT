class UserInterface {
    fun showTxt(textToShow: Any, newLine: Boolean = true) {
        if (newLine) {
            println(textToShow)
        } else {
            print(textToShow)
        }
    }

    fun dataInput(): String {
        return readln()
    }

    fun clearConsole() {
        if (System.getProperty("os.name") == "Windows") {
            Runtime.getRuntime().exec("cls")
        } else {
            Runtime.getRuntime().exec("clear")

        }

    }
}