class UserInterface {
    private val baseLogic = BaseLogic()
    private val message = Texts()
    fun welcomeSrc(){
        baseLogic.showTxt(message.helloTxt + message.userHint)
        baseLogic.showTxt("1: " + message.signInTxt)
        baseLogic.showTxt("2: " + message.exitProgram)

    }
}