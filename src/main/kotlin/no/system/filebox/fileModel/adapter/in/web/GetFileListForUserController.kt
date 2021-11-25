package no.system.filebox.fileModel.adapter.`in`.web

import no.system.filebox.fileModel.application.port.`in`.GetFileListForUserUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/file")
class GetFileListForUserController(val getFileListForUserUseCase: GetFileListForUserUseCase) {

    @GetMapping("/user/{userID}")
    @CrossOrigin(origins = ["http://localhost:3000"])
    fun getUsersFiles(@PathVariable userID: Int, @RequestHeader ("password") password: String): ResponseEntity<Any>{
        return ResponseEntity
            .status(200)
            .body(getFileListForUserUseCase.getFiles(password, userID))
    }
}