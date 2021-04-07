package no.system.filebox.fileModel.application.port.`in`

interface UserValidation {
    fun redisValidation(hash: String, userID: Int): Boolean
}