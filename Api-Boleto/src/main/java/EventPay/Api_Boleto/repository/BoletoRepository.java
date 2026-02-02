package EventPay.Api_Boleto.repository;

import EventPay.Api_Boleto.entity.BoletoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoletoRepository extends CrudRepository<BoletoEntity, Long> {
    Optional<BoletoEntity> findByBarcode(String barcode);
}