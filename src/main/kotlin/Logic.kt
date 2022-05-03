import java.io.File
import kotlin.random.Random
import kotlin.system.exitProcess


val sharedData = SharedData()
val textMessage = Texts()
val dbOperations = DataBaseOperations()

class Logic {

    fun program() {
        val base = BaseLogic()

        val userInterface = UserInterface()
        userInterface.showTxt(textMessage.helloTxt)
        dbOperations.dbRead()
        for (line in sharedData.credentialsArray) {
            println(line)
        }

    }


}


class BaseLogic {
    fun programStart() {
        dbOperations.dbRead()
        dbCorrectnessValidator()
    }

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

    fun dbCorrectnessValidator() {
        var elementsAmount = 0
        for (line in sharedData.credentialsArray) {
            elementsAmount += line.size
        }
        if (elementsAmount % 3 != 0 || dbOperations.dbLenCount() == 0) {
            println("db hujnia")
        }
    }
    fun programExit(exitReason:Int = 0){
        exitProcess(exitReason)
    }
}


class DataBaseOperations {
    private val dbFile = File(sharedData.dbFile)

    fun dbLenCount(): Int {
        var lineCount = 0
        dbFile.forEachLine { lineCount++ }
        return lineCount
    }

    fun dbRead() {
        dbFile.forEachLine { line ->
            val arrayElement = line.split(", ")
            sharedData.credentialsArray.add(arrayElement)
        }
    }

    fun writeToDb(dataToWrite: String) {
        dbFile.appendText(dataToWrite + System.getProperty("line.separator"))
    }

    fun createFile(nameCreatedFile:String){
        val fileToCreate = File(nameCreatedFile)
        fileToCreate.createNewFile()
    }
}


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

