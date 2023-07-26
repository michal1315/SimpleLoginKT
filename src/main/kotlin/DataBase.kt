import java.io.File

val basicLogic = BasicLogic()
const val fileName = "credentials.txt"
var credentialsArray: ArrayList<List<String>> = arrayListOf()
val file = File(fileName)

class DataBase {


    fun connection(): Boolean {
        return file.exists()
    }

    fun create() {
        val db = File(fileName)
        val login = basicLogic.genRandomData()
        val salt = basicLogic.genRandomData(8)
        val pass = basicLogic.genRandomData(64).lowercase()
        if (file.exists()) {
            file.delete()
        } else {
            db.createNewFile()
        }
        write(recordFormatBuild(login, salt, pass))
    }

    fun read() {
        file.forEachLine { line: String ->
            val arrayElement = line.split(", ")
            credentialsArray.add(arrayElement)
        }
    }

    fun write(dataToWrite: String) {
        file.appendText(dataToWrite + System.getProperty("line.separator"))
    }

    private fun lenCount(): Int {
        var lineCount = 0
        file.forEachLine { lineCount++ }
        return lineCount
    }

    fun recordFormatBuild(login: String, salt: String, password: String): String {
        return "$login, $salt, $password"
    }

    fun conditionCheck(): Boolean {
        var dbCondition = true
        var elementsAmount = 0
        for (line in credentialsArray) {
            elementsAmount += line.size
        }
        if (elementsAmount % 3 != 0 || lenCount() == 0) {
            dbCondition = false
        }
        return dbCondition
    }
}