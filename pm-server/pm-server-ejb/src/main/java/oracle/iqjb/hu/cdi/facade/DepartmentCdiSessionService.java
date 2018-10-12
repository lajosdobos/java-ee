package oracle.iqjb.hu.cdi.facade;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.transaction.Transactional;
import oracle.iqjb.hu.cdi.service.DepartmentCdiService1;
import oracle.iqjb.hu.cdi.service.DepartmentCdiService2;
import oracle.iqjb.hu.event.IqjbLogCreatedEvent1;
import oracle.iqjb.hu.exception.MyException;
import oracle.iqjb.hu.pm.dm.Department;
import oracle.iqjb.hu.pm.dm.IqjbLog;
import oracle.iqjb.hu.pm.intf.message.AddDepartmentRequest;
import oracle.iqjb.hu.pm.intf.message.AddDepartmentResponse;

@Transactional(Transactional.TxType.REQUIRED)
public class DepartmentCdiSessionService {

    @Inject
    private DepartmentCdiService1 departmentCdiService1;
    
    @Inject
    private DepartmentCdiService2 departmentCdiService2;
    
    @Inject
    @IqjbLogCreatedEvent1
    private Event<IqjbLog> logEvent1;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Department add(Department department) {
        try {
            departmentCdiService1.add(department);
        } catch(MyException e) {
            //
        }            
        return departmentCdiService2.add(department);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public AddDepartmentResponse addDepartment(AddDepartmentRequest request)  {
        AddDepartmentResponse response = new AddDepartmentResponse();
        Department department = new Department(request.getName());
        try {
            departmentCdiService1.add(department);
        } catch (MyException ex) {
            Logger.getLogger(DepartmentCdiSessionService.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        logEvent1.fire(new IqjbLog("event is created", new Date()));
        departmentCdiService2.add(department);
        //context.setRollbackOnly();
        
        return response;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Department findDepartmentById(long id) {
        return departmentCdiService1.find(id);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(long id) {
        departmentCdiService1.delete(id);
        //departmentCdiService2.delete(id);
    }
   
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Department> findAll() {
        return departmentCdiService1.findAllDepartment();
    }

}
