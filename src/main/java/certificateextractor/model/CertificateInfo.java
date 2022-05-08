package certificateextractor.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CertificateInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
private String subject;
    private String issuer;

    private Boolean isValid;

    public CertificateInfo(){

    }


    public CertificateInfo(Long id, String subject, String issuer, Boolean isValid) {
        this.id = id;
        this.subject = subject;
        this.issuer = issuer;
        this.isValid = isValid;
    }

    public CertificateInfo(String subject, String issuer, Boolean isValid) {
        this.subject = subject;
        this.issuer = issuer;
        this.isValid = isValid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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