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
        var newLogin = ""
        var newPass = ""
        basicLogic.showTxt(lang.createAccount)
        while (true) {
            if (basicLogic.attemptsNumber != 0) {
                basicLogic.showTxt(lang.loginOccupiedTxt)
            }
            basicLogic.showTxt(lang.loginTxt, false)
            newLogin = basicLogic.dataInput()
            if (credential.loginFreeCheck(newLogin)) {
                break
            } else basicLogic.attemptCounter("loginNotFree")
            if (basicLogic.attemptsNumber == 3) {
                basicLogic.showTxt(lang.createAccountFail + "\n" + lang.goodBay)
                basicLogic.programExit()
            }
        }
        while (true) {
            if (basicLogic.attemptsNumber == 0) {
                basicLogic.showTxt(lang.passHint)
            }
            basicLogic.showTxt(lang.passTxt, false)
            newPass = basicLogic.dataInput()
            val salt = basicLogic.genRandomData(8)
            if (credential.passwordValidator(newPass)) {
                dataBase.write(dataBase.recordFormatBuild(newLogin, salt, credential.hashData(newPass + salt)))
                basicLogic.showTxt(lang.createAccountSuccess)
                basicLogic.programExit()
            } else basicLogic.attemptCounter("passNotValid")
            if (basicLogic.attemptsNumber == 3) {
                basicLogic.showTxt(lang.createAccountFail + "\n" + lang.goodBay)
                basicLogic.programExit()
            }
        }
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
            dataBase.read()
            if (dataBase.conditionCheck()) {
                //println("db good")
                // https://kiasmos.bandcamp.com/track/lit
                welcomeMenu()
            } else {
                //println("db ungood!")
                basicLogic.showTxt(lang.newDataFileQuestion, false)
                val dbRebuildQuestion = basicLogic.dataInput()
                if (dbRebuildQuestion.lowercase() == "y") {
                    dataBase.create()
                } else {
                    basicLogic.programExit()
                }
            }
        } else {
            dataBase.create()
        }
    }
    program()
}