import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {
    //доход компании
    private final double income;
    //список сотрудников
    private final ArrayList<Employee> employees;

    public Company(double income) {
        this.income = income;
        this.employees = new ArrayList<>();
    }

    //получить список сотрудников
    public ArrayList<Employee> getEmployees() {
        return this.employees;
    }

    //найм одного сотрудника
    public void hire(Employee e) {
        employees.add(e);
    }

    //найм списка сотрудников
    public void hireAll(ArrayList es) {
        employees.addAll(es);
    }

    //увольнение сотрудника
    public void fire() {
        int goFor = (int) (Math.random() * getCountOfEmployees());
        employees.remove(goFor);
    }

    //получение значения дохода компании
    public double getIncome() {
        return income;
    }

    //получить количество сотрудников
    int getCountOfEmployees() {
        return employees.size();
    }

    //возвращает список сотрудников отсортированный по возрастанию заработной платы
    ArrayList<Employee> getTopSalaryStaff(int count) {
        ArrayList<Employee> sorted = new ArrayList<>();
        //сортировка по возрастанию
        for (int j = 0; j < employees.size(); j++) {
            for (int i = 0; i < employees.size() - 1; i++) {
                if (employees.get(i).getMonthSalary() > employees.get(i + 1).getMonthSalary()) {
                    employees.add(i, employees.remove(i + 1));
                }
            }
        }

        sorted.addAll(employees);
        if (count < sorted.size()) {
            while (count != sorted.size()) {
                sorted.remove(sorted.size() - 1);
            }
        }
        return sorted;
    }

    //возвращает список сотрудников отсортированный по убыванию заработной платы
    ArrayList<Employee> getLowestSalary(int count) {
        for (int j = 0; j < employees.size(); j++) {
            for (int i = 0; i < this.employees.size() - 1; i++) {
                if (employees.get(i).getMonthSalary() < employees.get(i + 1).getMonthSalary()) {
                    employees.add(i, employees.remove(i + 1));
                }
            }
        }
        ArrayList<Employee> sorted = new ArrayList<>();
        sorted.addAll(employees);
        if (count < sorted.size()) {
            while (count != sorted.size()) {
                sorted.remove(sorted.size() - 1);
            }
        }
        return sorted;
    }

}


