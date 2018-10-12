/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.iqjb.hu.event;

import java.io.Serializable;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import oracle.iqjb.hu.pm.dm.IqjbLog;
import oracle.iqjb.hu.service.IqjbLogService;

/**
 *
 * @author oracle
 */
public class IqjbLogEventHandler implements Serializable{
    
    @Inject
    private IqjbLogService iqjbLogService;
    
    public void addLog(@Observes @IqjbLogCreatedEvent1 IqjbLog iqjbLog){
        iqjbLogService.create(iqjbLog.getContent());
    }
    
}
