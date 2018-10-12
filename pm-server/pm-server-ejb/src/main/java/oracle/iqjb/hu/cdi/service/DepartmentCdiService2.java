package oracle.iqjb.hu.cdi.service;

import java.util.List;
import javax.inject.Inject;
import oracle.iqjb.hu.cdi.repository.IqjbRepository2;
import oracle.iqjb.hu.pm.dm.Department;


public class DepartmentCdiService2 {

    @Inject
    private IqjbRepository2 iqjbRepository2;  

//    @Interceptors(LoggerInterceptor.class)
    public Department add(Department department) {
        return iqjbRepository2.add(department);
    }
  
    public List<Department> findAllDepartment() {
        return iqjbRepository2.findAll();
    }
    
    public void delete(long id) {
        iqjbRepository2.delete(id);
    }
    
    public Department find(long id) {
        return iqjbRepository2.findDepartmentById(id);
    }
    
}
