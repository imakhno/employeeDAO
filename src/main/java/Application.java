import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import main.City;
import main.Employee;

import java.util.List;

public class Application {
    public static void main(String[] args) {

        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

        List<City> cities = employeeDAO.getAllCities();
        for (City city : cities) {
            System.out.println("Идентификатор города: " + city.getId());
            System.out.println("Название города: " + city.getName());
            System.out.println("-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•");
        }


        List<Employee> employeesList = employeeDAO.getAllEmployees();
        for (Employee employee : employeesList) {
            System.out.println("Идентификатор сотрудника: " + employee.getId());
            System.out.println("Имя сотрудника: " + employee.getName());
            System.out.println("Фамилия сотрудника: " + employee.getSurname());
            System.out.println("Возраст сотрудника: " + employee.getAge());
            System.out.println("Идентификатор города сотрудника: " + employee.getCity_id());
            System.out.println("-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•-•");
        }

        System.out.println(employeeDAO.getEmployee(2));

        employeeDAO.createEmployee(6,"Ivan","Sidorov",22,1);

        employeeDAO.updateEmployee(6, "Иван", "Сидоров", 22, 1);
        employeeDAO.updateEmployee(5, "Пётр", "Иванов", 22, 1);

        employeeDAO.deleteEmployee(6);
    }
}
