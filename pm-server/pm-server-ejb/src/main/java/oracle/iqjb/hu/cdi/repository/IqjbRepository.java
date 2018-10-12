/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.iqjb.hu.cdi.repository;

import java.util.List;
import javax.persistence.EntityManager;
import oracle.iqjb.hu.pm.dm.Department;

/**
 *
 * @author oracle
 */
public abstract class IqjbRepository {
    
    protected abstract EntityManager getEntityManager();
  
    public Department add(Department department){
        
        if(department.getId() == null) {
            getEntityManager().persist(department);
        } else {
            getEntityManager().merge(department);
        }
        
        getEntityManager().flush();
        return department;
    }
    
    public List<Department> findAll() {
        return getEntityManager().createQuery("select d from Department d", Department.class).getResultList();
    }
    
    public Department findDepartmentById(long id){  
        return getEntityManager().find(Department.class, id);
    } 
    
    public void delete(long id) {
        getEntityManager().remove(findDepartmentById(id));
    }
    
}
