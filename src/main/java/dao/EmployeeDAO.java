package dao;

import main.City;
import main.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> getAllEmployees();
    List<City> getAllCities();

    Employee getEmployee(int employeeId);

    void createEmployee(int id, String name, String surname, int age, int cityId);

    void updateEmployee(int employeeId, String name, String surname, int age, int cityId);

    void deleteEmployee(int employeeId);
}
