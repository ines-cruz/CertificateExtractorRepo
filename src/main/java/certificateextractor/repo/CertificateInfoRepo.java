package certificateextractor.repo;

import certificateextractor.model.CertificateInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateInfoRepo extends CrudRepository<CertificateInfo, Long> {

    List<CertificateInfo> findAll();
}
