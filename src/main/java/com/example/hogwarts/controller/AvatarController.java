package com.example.hogwarts.controller;
import com.example.hogwarts.model.Avatar;
import com.example.hogwarts.service.AvatarService;
import com.example.hogwarts.service.StudentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/avatar")
public class AvatarController {

    private final AvatarService avatarService;
    private final StudentService studentService;

    public AvatarController(AvatarService avatarService, StudentService studentService) {
        this.avatarService = avatarService;
        this.studentService = studentService;
    }

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
        if (avatar.getSize() > 1024 * 300) {
            return ResponseEntity.badRequest().body("File is too dig");
        }
        avatarService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable long id) {
        Avatar avatar = avatarService.findAvatar(id);

        if (avatar != null) {


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
            headers.setContentLength(avatar.getData().length);

            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}/avatar")
    public void downloadAvatar(@PathVariable long id, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatar(id);

        if (avatar != null) {

            Path path = Path.of(avatar.getFilePath());

            try (InputStream is = Files.newInputStream(path);
                 OutputStream os = response.getOutputStream();) {
                response.setStatus(200);
                response.setContentType(avatar.getMediaType());
                response.setContentLength((int) avatar.getFileSize());
                is.transferTo(os);
            }
        }
    }
}
