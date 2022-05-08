package certificateextractor.model;



public class CertificateInfo {
private String subject;
    private String issuer;

    private Boolean isValid;

    public CertificateInfo(){

    }


    public CertificateInfo(String subject, String issuer, Boolean isValid) {
        this.subject = subject;
        this.issuer = issuer;
        this.isValid = isValid;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}