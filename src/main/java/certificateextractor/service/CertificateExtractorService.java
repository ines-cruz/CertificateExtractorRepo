package certificateextractor.service;

import certificateextractor.model.CertificateInfo;
import org.springframework.stereotype.Service;


@Service
public class CertificateExtractorService {
    public CertificateInfo getCertificateInfo(String url){
        return new CertificateInfo("testIssuer", "testSubject", true);
    }
}
