package oracle.iqjb.hu.cdi.repository;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional(Transactional.TxType.REQUIRED)
public class IqjbRepository1 extends IqjbRepository {

    @Produces
    @PersistenceContext(unitName = "iqjb1PU")
    @MysqlRepository
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
