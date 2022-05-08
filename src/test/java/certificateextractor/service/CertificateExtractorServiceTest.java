package certificateextractor.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CertificateExtractorServiceTest {

    @Autowired
    CertificateExtractorService certificateExtractorService;

    private static X509Certificate certificate = null;

    @BeforeAll
    public static void setUp() throws Exception {

        String cert = "-----BEGIN CERTIFICATE-----\n"
                + "MIIEQTCCAymgAwIBAgIBATANBgkqhkiG9w0BAQUFADCBkzEaMBgGA1UEAxMRTW9u\n"
                + "a2V5IE1hY2hpbmUgQ0ExCzAJBgNVBAYTAlVLMREwDwYDVQQIEwhTY290bGFuZDEQ\n"
                + "MA4GA1UEBxMHR2xhc2dvdzEcMBoGA1UEChMTbW9ua2V5bWFjaGluZS5jby51azEl\n"
                + "MCMGCSqGSIb3DQEJARYWY2FAbW9ua2V5bWFjaGluZS5jby51azAeFw0wNTAzMDYy\n"
                + "MzI4MjJaFw0wNjAzMDYyMzI4MjJaMIGvMQswCQYDVQQGEwJVSzERMA8GA1UECBMI\n"
                + "U2NvdGxhbmQxEDAOBgNVBAcTB0dsYXNnb3cxGzAZBgNVBAoTEk1vbmtleSBNYWNo\n"
                + "aW5lIEx0ZDElMCMGA1UECxMcT3BlbiBTb3VyY2UgRGV2ZWxvcG1lbnQgTGFiLjEU\n"
                + "MBIGA1UEAxMLTHVrZSBUYXlsb3IxITAfBgkqhkiG9w0BCQEWEmx1a2VAbW9ua2V5\n"
                + "bWFjaGluZTBcMA0GCSqGSIb3DQEBAQUAA0sAMEgCQQDItxZr07mm65ttYH7RMaVo\n"
                + "VeMCq4ptfn+GFFEk4+54OkDuh1CHlk87gEc1jx3ZpQPJRTJx31z3YkiAcP+RDzxr\n"
                + "AgMBAAGjggFIMIIBRDAJBgNVHRMEAjAAMBEGCWCGSAGG+EIBAQQEAwIHgDALBgNV\n"
                + "HQ8EBAMCBeAwHQYDVR0OBBYEFG7mW1czzw4vFcL03+wUvvvPVFY8MIHABgNVHSME\n"
                + "gbgwgbWAFKt47K8QG4qbH8exJY8WKPIXmq02oYGZpIGWMIGTMRowGAYDVQQDExFN\n"
                + "b25rZXkgTWFjaGluZSBDQTELMAkGA1UEBhMCVUsxETAPBgNVBAgTCFNjb3RsYW5k\n"
                + "MRAwDgYDVQQHEwdHbGFzZ293MRwwGgYDVQQKExNtb25rZXltYWNoaW5lLmNvLnVr\n"
                + "MSUwIwYJKoZIhvcNAQkBFhZjYUBtb25rZXltYWNoaW5lLmNvLnVrggEAMDUGCWCG\n"
                + "SAGG+EIBBAQoFiZodHRwczovL21vbmtleW1hY2hpbmUuY28udWsvY2EtY3JsLnBl\n"
                + "bTANBgkqhkiG9w0BAQUFAAOCAQEAZ961bEgm2rOq6QajRLeoljwXDnt0S9BGEWL4\n"
                + "PMU2FXDog9aaPwfmZ5fwKaSebwH4HckTp11xwe/D9uBZJQ74Uf80UL9z2eo0GaSR\n"
                + "nRB3QPZfRvop0I4oPvwViKt3puLsi9XSSJ1w9yswnIf89iONT7ZyssPg48Bojo8q\n"
                + "lcKwXuDRBWciODK/xWhvQbaegGJ1BtXcEHtvNjrUJLwSMDSr+U5oUYdMohG0h1iJ\n"
                + "R+JQc49I33o2cTc77wfEWLtVdXAyYY4GSJR6VfgvV40x85ItaNS3HHfT/aXU1x4m\n"
                + "W9YQkWlA6t0blGlC+ghTOY1JbgWnEfXMmVgg9a9cWaYQ+NQwqA==\n" + "-----END CERTIFICATE-----";
        ByteArrayInputStream in = new ByteArrayInputStream(cert.getBytes());
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        certificate = (X509Certificate) cf.generateCertificate(in);
    }

    @Test
    public void getIssuerTest() {
        assertThat(certificateExtractorService.getIssuer(certificate))
                .isEqualTo("EMAILADDRESS=ca@monkeymachine.co.uk, O=monkeymachine.co.uk, L=Glasgow, ST=Scotland, C=UK, CN=Monkey Machine CA");
    }

    @Test
    public void getSubjectTest() {
        assertThat(certificateExtractorService.getSubject(certificate))
                .isEqualTo("EMAILADDRESS=luke@monkeymachine, CN=Luke Taylor, OU=Open Source Development Lab., O=Monkey Machine Ltd, L=Glasgow, ST=Scotland, C=UK");
    }

    @Test
    public void isDateValidTest(){
        assertThat(certificateExtractorService.isDateValid(new Date(System.currentTimeMillis() + 1000))).isTrue();
    }

}