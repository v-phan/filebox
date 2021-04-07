package no.system.filebox.fileModel.adapter.out.persistence

import no.system.filebox.fileModel.application.port.out.DownloadFilePort
import no.system.filebox.fileModel.application.port.out.GetFileListForUserPort
import no.system.filebox.fileModel.application.port.out.UploadFilePort
import no.system.filebox.fileModel.domain.FileModel
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class FilePersistenceAdapter(private val fileRepository: FileRepository): UploadFilePort, DownloadFilePort, GetFileListForUserPort {

    override fun uploadFile(model: FileModel): FileModel {
        return fileRepository.save(model)
    }

    override fun downloadFile(fileID: Int): FileModel? {
        return fileRepository.findByIdOrNull(fileID)
    }

    override fun getFileListForUser(userID: Int): List<FileModel> {
        return fileRepository.getUsersFiles(userID)
    }
}