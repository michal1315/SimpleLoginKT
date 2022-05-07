fun main(args: Array<String>) {

    val basicLogic = BasicLogic()
    val userInterface = UserInterface()
    val dbOperation = DataBaseOperations()
    val message = Texts()
    var userChoice: Int


    fun init(){
        if (!dbOperation.dbCorruptionCheck()){
            dbOperation.dbRead()
        }
        else{
            //db rebuild
        }
    }

    fun program() {
        dbOperation.dbRead()
        var attempt = 0
        while (true) {
            userInterface.welcomeSrc()
            userChoice = basicLogic.dataInput().toInt()
            if (userChoice == 1) {
                userInterface.loginSrc()
                basicLogic.showTxt(message.loginTxt, false)
                val login = basicLogic.dataInput()
                basicLogic.showTxt(message.passwordTxt, false)
                val password = basicLogic.dataInput()
                if(basicLogic.credentialParser(login,password)){
                    basicLogic.showTxt(message.goodCredential)
                }
                else{
                    basicLogic.showTxt(message.badCredential)
                }
            }
            if (userChoice == 0) {
                basicLogic.programExit()
            } else {
                attempt += 1
                if (attempt == 3){
                    basicLogic.programExit()
                }
            }
        }
    }
    init()
    program()
}