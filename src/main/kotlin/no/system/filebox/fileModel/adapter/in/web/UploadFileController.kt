package no.system.filebox.fileModel.adapter.`in`.web

import no.system.filebox.fileModel.application.port.`in`.UploadFileUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@Component
@RestController
@RequestMapping("/file")
class UploadFileController(private val uploadFileUseCase: UploadFileUseCase) {

    @PostMapping("/save")
    @CrossOrigin(origins = ["http://localhost:3000"])
    fun uploadFile(@RequestParam("file") file: MultipartFile,
                   @RequestHeader("userID") userID: Int,
                   @RequestHeader("hash") hash: String): ResponseEntity<Any>{
        val model = uploadFileUseCase.uploadFile(file, userID)
        val downloadUrl =  ServletUriComponentsBuilder.fromCurrentContextPath().path("file/download/").path(model.fileID.toString()).toUriString()
        return ResponseEntity
            .status(HttpStatus.OK)
            .header("download url", downloadUrl)
            .body(model)
    }
}