/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interceptor.LoggerInterceptor;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import oracle.iqjb.hu.exception.MyException;
import oracle.iqjb.hu.pm.dm.Department;
import oracle.iqjb.hu.repository.DepartmentRepository1;

/**
 *
 * @author oracle
 */
@Stateless
@LocalBean
public class DepartmentService1 {

    @EJB
    private DepartmentRepository1 departmentRepository1;
    
    @Interceptors(LoggerInterceptor.class)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Department add(Department department) throws MyException {
        //iqjbLogService.create("DepartmentService1.add is called33");
        Department dep = departmentRepository1.add(department);
//        if (1==1){
            //iqjbLogService.create("error occured in DepartmentService1.add");
            //throw new MyException("hiba");
//        }
        return dep;
    }

}
