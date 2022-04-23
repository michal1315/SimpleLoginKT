import java.io.File

val sharedData = SharedData()
val textMessage = Texts()
val dbOperations = DataBaseOperations()

class Logic {

    fun program() {

        val userInterface = UserInterface()
        userInterface.showTxt(textMessage.helloTxt)
        dbOperations.dbRead()
        for (line in sharedData.credentialsArray) {
            println(line[1])
        }
    }


}


class ProgramInit() {


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
