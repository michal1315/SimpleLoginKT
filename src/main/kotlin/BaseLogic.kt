import kotlin.random.Random
import kotlin.system.exitProcess

class BaseLogic {
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
}

