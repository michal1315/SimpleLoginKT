fun main(args: Array<String>) {

    val debug: Boolean = false
    val basicLogic = BasicLogic()
    val dataBase = DataBase()
    val credential = CredentialHandler()
    val lang = Language()
    var userChoice: String = ""


    fun login() {
        while (true) {
            basicLogic.clearConsole()
            when (basicLogic.attemptsNumber) {
                0 -> basicLogic.showTxt(lang.signInHint)
                else -> basicLogic.showTxt(lang.signInHintTry)
            }
            basicLogic.showTxt(lang.loginTxt, false)
            val login = basicLogic.dataInput()
            basicLogic.showTxt(lang.passTxt, false)
            val pass = basicLogic.dataInput()
            if (credential.credentialPassCheck(login, pass)) {
                basicLogic.showTxt(lang.goodCredential + "\n" + lang.goodBay)
                basicLogic.programExit()
            } else {
                if (basicLogic.attemptsNumber == 3) {
                    basicLogic.showTxt(lang.badCredential + "\n" + lang.goodBay)
                    basicLogic.programExit()
                }
                basicLogic.attemptCounter("loginTry")
            }
        }
    }

    fun createAccount() {
        basicLogic.showTxt(lang.createAccount)
        basicLogic.showTxt(lang.loginTxt, false)
        val newLogin = basicLogic.dataInput()
        basicLogic.showTxt(lang.passTxt, false)
        val newPass = basicLogic.dataInput()
        val salt = basicLogic.genRandomData(8)
        dataBase.write(dataBase.recordFormatBuild(newLogin, salt, credential.hashData(newPass + salt)))
        basicLogic.programExit()
    }

    fun welcomeMenu() {
        while (true) {
            basicLogic.showTxt(lang.welcomeTxt, false)
            userChoice = basicLogic.dataInput()
            when (userChoice) {
                "1" -> login()
                "2" -> createAccount()
                "3" -> basicLogic.programExit()
                else -> {
                    basicLogic.attemptCounter("welcomeMenu")
                }
            }
            if (basicLogic.attemptsNumber == 3) {
                basicLogic.programExit()
            }
        }
    }

    fun program() {
        //lang choice
        if (dataBase.connection()) {
            if (dataBase.conditionCheck()) {
                //println("db good")
                dataBase.read()
            } else {
                //println("db ungood!")
            }
        } else {
            dataBase.create()
        }
        // https://kiasmos.bandcamp.com/track/lit
        welcomeMenu()
    }
    program()
}