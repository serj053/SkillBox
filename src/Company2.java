import java.util.ArrayList;
import java.util.Collections;

public class Company2  {
    //доход компании
    private final double income;
    //список сотрудников
    private final ArrayList<CompareSalary> employees;

    public Company2(double income) {
        this.income = income;
        this.employees = new ArrayList<>();
    }

    //получить список сотрудников
    public ArrayList<CompareSalary> getEmployees() {
        return this.employees;
    }

    //найм одного сотрудника
    public void hire(CompareSalary e) {
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

    public ArrayList<CompareSalary> getTopSalaryStaff(int count) {
        Collections.sort(employees);
        ArrayList<CompareSalary> sorted = new ArrayList<>();
        sorted.addAll(employees);
        if (count < sorted.size()) {
            while (count != sorted.size()) {
                sorted.remove(sorted.size() - 1);
            }
        }
        return sorted;
    }

    public ArrayList<CompareSalary> getLowestSalary(int count) {
        Collections.sort(employees, Collections.reverseOrder());
        ArrayList<CompareSalary> sorted = new ArrayList<>();
        sorted.addAll(employees);
        if (count < sorted.size()) {
            while (count != sorted.size()) {
                sorted.remove(sorted.size() - 1);
            }
        }
        return sorted;
    }
}
