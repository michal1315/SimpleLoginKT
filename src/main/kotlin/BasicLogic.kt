import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter
import kotlin.random.Random
import kotlin.system.exitProcess

class BasicLogic {

    var attemptNumber: Int = 0
    private var lastAttemptReason: String = ""

    fun randomDataGenerator(lengthOfData: Int = 12): String {
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

    fun credentialParser(login: String, password: String): Boolean {
        var checkPass = false
        for (line in sharedData.credentialsArray) {
            val loginFormDb = line[0]
            val salt = line[1]
            val passwordFromDb = line[2]
            if (login == loginFormDb && hashingData(password + salt) == passwordFromDb) {
                checkPass = true
            }
        }
        return checkPass
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

    private fun hashingData(dataToHash: String): String {
        val bytes = MessageDigest
            .getInstance("SHA-256")
            .digest(dataToHash.toByteArray())
        return DatatypeConverter.printHexBinary(bytes).lowercase()
    }

    fun attemptCounter(attemptReason: String) {
        if (lastAttemptReason.isEmpty() || lastAttemptReason == attemptReason) {
            attemptNumber += 1
            if (attemptNumber > 3) {
                programExit()
            }
        } else {
            attemptNumber = 0
            attemptNumber += 1
        }
        lastAttemptReason = attemptReason
    }
}