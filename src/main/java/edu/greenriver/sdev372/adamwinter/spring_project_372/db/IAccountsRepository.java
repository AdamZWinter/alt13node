package edu.greenriver.sdev372.adamwinter.spring_project_372.db;

import edu.greenriver.sdev372.adamwinter.spring_project_372.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  Repository bean extends JpaRepository
 */
@Repository
public interface IAccountsRepository extends JpaRepository<Account, Integer> {
}
