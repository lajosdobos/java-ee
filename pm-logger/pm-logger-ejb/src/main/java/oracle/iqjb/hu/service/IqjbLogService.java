/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.iqjb.hu.service;

import java.util.Date;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.transaction.Transactional;
import oracle.iqjb.hu.pm.dm.IqjbLog;
import oracle.iqjb.hu.repository.IqjbLogRepository1;
import oracle.iqjb.hu.repository.IqjbLogRepository2;

/**
 *
 * @author oracle
 */
@Transactional(Transactional.TxType.REQUIRED)
public class IqjbLogService {

    @Inject
    private IqjbLogRepository2 iqjbLogRepository2;

    @Inject
    private IqjbLogRepository1 iqjbLogRepository1;    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void create(String content){
        IqjbLog iqjbLog = new IqjbLog(content, new Date());
        iqjbLogRepository1.add(iqjbLog);
        iqjbLogRepository2.add(iqjbLog);
    }

}
