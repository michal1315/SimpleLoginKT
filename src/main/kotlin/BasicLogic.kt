import kotlin.random.Random
import kotlin.system.exitProcess

class BasicLogic {

    var attemptsNumber: Int = 0
    private var lastAttemptReason: String = ""

    fun genRandomData(lengthOfData: Int = 12): String {
        var iteration = 0
        var randomString = ""
        val charset = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        while (iteration < lengthOfData) {
            val randomNumber = Random.nextInt(0, 61)
            randomString += charset[randomNumber]
            iteration++
        }
        return randomString
    }

    fun programExit(exitReason: Int = 0) {
        exitProcess(exitReason)
    }

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

    fun heartbeat(timeout: Int = 3) {
        var counter = 0
        val dot = "."
        while (counter < timeout) {
            showTxt(dot, false)
            dot + dot
            counter += 1
            Thread.sleep(1000)
        }
    }

    fun attemptCounter(attemptReason: String) {
        if (lastAttemptReason.isEmpty() || lastAttemptReason == attemptReason) {
            attemptsNumber += 1
        } else {
            attemptsNumber = 0
            attemptsNumber += 1
        }
        lastAttemptReason = attemptReason
    }
}