package no.system.filebox.fileModel.application.port.out

import no.system.filebox.fileModel.domain.FileModel
import org.springframework.stereotype.Component

@Component
interface UploadFilePort {
    fun uploadFile(FileModel: FileModel): FileModel
}