class UserInterface {
    private val basicLogic = BasicLogic()
    private val message = Texts()
    fun welcomeSrc(){
        basicLogic.showTxt(message.helloTxt + message.userHint)
        basicLogic.showTxt("1: " + message.signInTxt)
        basicLogic.showTxt("2: " + message.exitProgram)

    }
}