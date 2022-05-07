import kotlin.random.Random
import kotlin.system.exitProcess

class BasicLogic {

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
    fun credentialParser(login:String, password:String): Boolean {
        var checkPass = false
        for(line in sharedData.credentialsArray){
            val loginFormDb = line[0]
            val passwordFromDb = line[2]
            if (login == loginFormDb && password == passwordFromDb){
                checkPass = true
            }
        }
        return checkPass
    }
}