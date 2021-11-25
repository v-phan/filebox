package no.system.filebox.fileModel.application.port.`in`

interface UserValidation {
    fun redisValidation(password: String, userID: Int): Boolean
}