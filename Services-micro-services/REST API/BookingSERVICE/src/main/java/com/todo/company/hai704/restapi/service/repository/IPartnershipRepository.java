package com.todo.company.hai704.restapi.service.repository;

import com.todo.company.hai704.restapi.service.entity.Partnership;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IPartnershipRepository extends JpaRepository<Partnership, Long> {
    public Partnership findPartnershipByIdAgency(String idAgency);
    @Modifying
    @Transactional
    @Query("UPDATE Partnership p SET p.agencyName = :agencyName, p.password = :password WHERE p.idAgency = :idAgency")
    int updatePartnershipByIdAgency(@Param("idAgency") String idAgency, @Param("agencyName") String agencyName,
                                    @Param("password") String password);

}
