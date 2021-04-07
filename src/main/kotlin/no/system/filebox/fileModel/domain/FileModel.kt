package no.system.filebox.fileModel.domain

import java.text.SimpleDateFormat
import java.util.*
import javax.persistence.*

@Entity
class FileModel(val fileName: String,
                val fileType: String,
                val filedata: ByteArray,
                val userID: Int) {

    @Id
    @GeneratedValue(generator = "file_id_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "file_id_seq",
        sequenceName = "file_id_seq",
        allocationSize = 50)
    var fileID: Int? = null
    val uploadedDate: String = SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date())

}