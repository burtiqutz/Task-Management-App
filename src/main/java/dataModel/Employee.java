package dataModel;

import java.io.Serializable;

public class Employee implements Serializable {
    private int idEmployee;
    private String name;

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee(int idEmployee, String name) {
        this.idEmployee = idEmployee;
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idEmployee);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // If comparing the same object instance
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // If obj is null or not of type Employee
        }
        Employee other = (Employee) obj;
        return this.idEmployee == other.idEmployee; // Compare IDs
    }

    @Override
    public String toString() {
        return name;
    }

}
