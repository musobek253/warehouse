package uz.pdp.warehouse.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.tamplet.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}