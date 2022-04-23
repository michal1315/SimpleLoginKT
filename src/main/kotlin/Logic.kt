import java.io.File
import kotlin.random.Random


val sharedData = SharedData()
val textMessage = Texts()
val dbOperations = DataBaseOperations()

class Logic {

    fun program() {

        val userInterface = UserInterface()
        userInterface.showTxt(textMessage.helloTxt)
        dbOperations.dbRead()
        val prog = ProgramInit()
        println(prog.randomDataGenerator())
    }


}


class ProgramInit() {
    fun randomDataGenerator(length: Int = 12): String {
        var iteration = 0
        var randomString = ""
        val charset = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        while (iteration < length) {
            val randomNumber = Random.nextInt(0, 61)
            randomString += charset[randomNumber]
            iteration++
        }
        return randomString
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
        dbFile.forEachLine { line: String -> dbLineSplitter(line) }
    }

    fun dbLineSplitter(lineToSplit: String) {
        val splittedLine = lineToSplit.split(", ")
        dbArrayBuilder(splittedLine)
    }

    fun dbArrayBuilder(arrayElement: List<String>) {
        sharedData.credentialsArray.add(arrayElement)
    }

    fun writeToDb(dataToWrite: String) {
        dbFile.appendText(dataToWrite + System.getProperty("line.separator"))
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
