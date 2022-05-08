package certificateextractor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CertificateExtractorController {
    @RequestMapping
    public ResponseEntity getUrlCertificate(@RequestParam String url) {
        return new ResponseEntity("Received:" + url, HttpStatus.OK);
    }
}
