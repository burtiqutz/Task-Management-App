package dataModel;

public class Employee {
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
        final int prime = 31;
        int result = 1;
        result = prime * result + idEmployee;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // If comparing the same object instance
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // If obj is null or not of type Employee
        }
        Employee other = (Employee) obj; // Type casting
        return this.idEmployee == other.idEmployee; // Compare IDs
    }


}
