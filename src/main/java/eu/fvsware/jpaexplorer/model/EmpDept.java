/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fvsware.jpaexplorer.model;

/**
 *
 * @author fvasi
 */
public class EmpDept {
    private String empName;
    private String deptName;

    public EmpDept(String empName, String deptName) {
        this.empName = empName;
        this.deptName = deptName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "EmpDept{" + "empName=" + empName + ", deptName=" + deptName + '}';
    }
    
    
            
}
