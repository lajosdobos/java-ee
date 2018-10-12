/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.iqjb.hu.cdi.service;

import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import oracle.iqjb.hu.cdi.repository.IqjbRepository1;
import oracle.iqjb.hu.exception.MyException;
import oracle.iqjb.hu.pm.dm.Department;

public class DepartmentCdiService1 {

    @Inject
    private IqjbRepository1 iqjbRepository1;
    
//    @Interceptors(LoggerInterceptor.class)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Department add(Department department) throws MyException {
        //iqjbLogService.create("DepartmentService1.add is called33");
        Department dep = iqjbRepository1.add(department);
//        if (1==1){
            //iqjbLogService.create("error occured in DepartmentService1.add");
            //throw new MyException("hiba");
//        }
        return dep;
    }
    
    public List<Department> findAllDepartment() {
        return iqjbRepository1.findAll();
    }
    
    public void delete(long id) {
        iqjbRepository1.delete(id);
    }
    
    public Department find(long id) {
        return iqjbRepository1.findDepartmentById(id);
    }
    
    

}
