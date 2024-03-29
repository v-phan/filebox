package no.system.filebox.fileModel.adapter.`in`.web

import no.system.filebox.fileModel.application.port.`in`.DownloadFileUseCase
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*

@Component
@RestController
@RequestMapping("/file")
class DownloadFileController(private val downloadFileUseCase: DownloadFileUseCase) {

    @GetMapping("/download/{fileID}")
    @CrossOrigin(origins = ["http://localhost:3000"])
    fun downloadFile(@PathVariable fileID : Int, @RequestHeader ("password") password: String)  : ResponseEntity<Any> {
        val file = downloadFileUseCase.downloadFile(password, fileID)!!
        return ResponseEntity
            .status(HttpStatus.OK)
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file?.fileName + "\"")
            .header(HttpHeaders.CONTENT_TYPE, file?.fileType)
            .body(ByteArrayResource(file.filedata))
    }
}