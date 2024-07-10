package dao;

import main.City;
import main.Employee;
import utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (final Connection connection = ConnectionManager.open()) {

            String sql = """
                    SELECT * FROM employees;
                    """;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("age"),
                        resultSet.getInt("city_id")
                ));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employees;
    }

    @Override
    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();

        try (final Connection connection = ConnectionManager.open()) {
            String sql = """
                    SELECT * FROM cities;
                    """;

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cities.add(new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cities;
    }

    @Override
    public Employee getEmployee(int employeeId) {
        Employee employee = null;

        try (final Connection connection = ConnectionManager.open()) {

            String sql = """
                    SELECT * FROM employees WHERE id = ?;
                    """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, employeeId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employee = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("age"),
                        resultSet.getInt("city_id")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public void createEmployee(int id, String name, String surname, int age, int cityId) {
        try (final Connection connection = ConnectionManager.open()) {
            String sql = """
                    INSERT INTO employees 
                    (id, name, surname, age, city_id)
                    VALUES 
                    (?, ?, ?, ?, ?); 
                    """;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, surname);
            statement.setInt(4, age);
            statement.setInt(5, cityId);

            int resultSet = statement.executeUpdate();
            if (resultSet == 1) {
                System.out.println("Новый сотрудник успешно добавлен.");
            } else {
                System.out.println("Ошибка добавления нового сотрудника.");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEmployee(int employeeId, String name, String surname, int age, int cityId) {
        try (final Connection connection = ConnectionManager.open()) {

            String sql = """
                    UPDATE employees 
                    SET 
                    name = ?,
                    surname = ?,
                    age = ?,
                    city_id = ?
                    WHERE id = ?
                     """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setInt(3, age);
            statement.setInt(4, cityId);
            statement.setInt(5, employeeId);

            int resultSet = statement.executeUpdate();
            if (resultSet == 1) {
                Employee newEmployee = new Employee(employeeId, name, surname, age, cityId);
                System.out.println("Данные сотрудника под номером: " + employeeId
                        + " - успешно обновлены!");
                System.out.println(newEmployee);
            } else {
                System.out.println("Ошибка обновления данных.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void deleteEmployee(int employeeId) {
        try(final Connection connection = ConnectionManager.open()) {
            String sql = """
                        DELETE FROM employees WHERE id = ?;
                    """;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, employeeId);
            int resultSet = statement.executeUpdate();
            if (resultSet == 1) {
                System.out.println("Сотрудник под номером 6 - удалён.");
            } else {
                System.out.println("Ошибка удаления.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
