/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.iqjb.hu.repository;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author oracle
 */
@Transactional(Transactional.TxType.REQUIRED)
public class IqjbLogRepository1 extends IqjbLogRepository {

    @Produces
    @PersistenceContext(unitName = "iqjb2PU")
    @MysqlRepository
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

