package uz.pdp.warehouse.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uz.pdp.warehouse.entity.tamplet.AttachmentContent;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer>, JpaSpecificationExecutor<AttachmentContent> {
    Optional<AttachmentContent> findByAttachmentId(Integer id);

}