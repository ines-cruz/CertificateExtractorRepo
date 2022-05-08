package certificateextractor.service;

import certificateextractor.model.CertificateInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;


@Service
public class CertificateExtractorService {
    private static final Logger LOG = LoggerFactory.getLogger(CertificateExtractorService.class);


    public CertificateInfo getCertificateInfo(URL url) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        try {
            connection.connect();
        } catch (UnknownHostException e) {
            throw new IOException("Unknown Host Exception");
        }

        Certificate certificate = getCertificate(connection);

        if (certificate instanceof X509Certificate) {
            X509Certificate x509cert = (X509Certificate) certificate;

            String subject = getSubject(x509cert);
            String issuer = getIssuer(x509cert);
            Date validityDate = getValidityDate(x509cert);

            Boolean isValid = isDateValid(validityDate);

            return new CertificateInfo(subject, issuer, isValid);

        } else {
            throw new IOException();
        }
    }
    protected Certificate getCertificate(HttpsURLConnection connection) throws IOException {

        Certificate[] certs;

        try {
            certs = connection.getServerCertificates();

        } catch (SSLPeerUnverifiedException e) {
            throw new IOException("Error extracting certificate");
        }

        if (certs.length == 0) {
            throw new IOException("No certificates found");
        } else if (certs.length > 1) {
            LOG.warn("Multiple certs received");
        }
        return certs[0];
    }

    protected Date getValidityDate(X509Certificate x509cert) {
        return x509cert.getNotAfter();
    }

    protected String getIssuer(X509Certificate x509cert) {
        return x509cert.getIssuerX500Principal().toString();
    }

    protected String getSubject(X509Certificate x509cert) {
        return x509cert.getSubjectX500Principal().toString();
    }
    protected boolean isDateValid(Date certificateDate) {
        return certificateDate.getTime() > System.currentTimeMillis();
    }
}
