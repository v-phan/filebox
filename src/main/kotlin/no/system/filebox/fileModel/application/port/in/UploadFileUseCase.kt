package no.system.filebox.fileModel.application.port.`in`

import no.system.filebox.fileModel.domain.FileModel
import org.springframework.web.multipart.MultipartFile

interface UploadFileUseCase {
    fun uploadFile(file: MultipartFile, userID: Int): FileModel
}