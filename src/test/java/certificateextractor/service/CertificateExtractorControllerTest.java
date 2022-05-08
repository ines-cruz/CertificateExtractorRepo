package certificateextractor.service;


import certificateextractor.controller.CertificateExtractorController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CertificateExtractorControllerTest {
    @Autowired
    private CertificateExtractorController testController;

    @Test
    public void getValidUrlCertificateTest() {
        ResponseEntity responseEntity = this.testController.getUrlCertificate("https://www.google.pt");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
    }

    @Test
    public void getInvalidUrlCertificateTest() {
        ResponseEntity responseEntity = this.testController.getUrlCertificate("https://not_valid_url");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
