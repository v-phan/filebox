package no.system.filebox.fileModel.application.port.`in`

interface UploadFileValidation {
    fun userIsOwner(hash: String, userID: Int)
}