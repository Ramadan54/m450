package ch.tbz.werkstatt.repository;

import ch.tbz.werkstatt.model.Auftrag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuftragRepository extends JpaRepository<Auftrag, Long> {
}
