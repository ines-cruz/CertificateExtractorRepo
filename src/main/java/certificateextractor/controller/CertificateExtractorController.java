package certificateextractor.controller;

import certificateextractor.model.ErrorResponse;
import certificateextractor.service.CertificateExtractorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;

@RestController
public class CertificateExtractorController {

    private static final Logger LOG = LoggerFactory.getLogger(CertificateExtractorController.class);

    @Autowired
    private CertificateExtractorService service;

    @RequestMapping
    public ResponseEntity getUrlCertificate(@RequestParam String url) {
        try {
            URL httpsURL = new URL(url);
            return new ResponseEntity(service.getCertificateInfo(httpsURL), HttpStatus.ACCEPTED);

        } catch (IOException e) {
            LOG.error("Exception caught", e);
            return new ResponseEntity( new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}

