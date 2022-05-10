import java.sql.SQLOutput;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Company company = new Company(100_000_000);

        //создаем 180 операторов
        ArrayList<Employee> operatorList = new ArrayList<>();
        for (int i = 0; i < 180; i++) {
            operatorList.add(new Manager("Operator_" + (i + 1)));
        }
        // принимаем на работу 180 операторов
        company.hireAll(operatorList);

        //создаем 80 менеджеров
        ArrayList<Employee> managerList = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            managerList.add(new Manager("Manager_" + (i + 1)));
        }
        //принимаем 80 менеджеров на работу
        company.hireAll(managerList);

        //создаем 10 топ менеджеров
        ArrayList<Employee> topManagerList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            topManagerList.add(new TopManager("TopManager_" + (i + 1), company));
        }
        //принимаем на работу 10 топ менеджеров
        company.hireAll(topManagerList);

        System.out.println("Сортируем по возрастанию и выводим 30 самых низких зарплат в компании");
        //для вывода результата создаем новый список
        ArrayList<Employee> sorted = new ArrayList<>();
        //выводим 30 самых низких зарплат в компании
        sorted = company.getTopSalaryStaff(30);
        //выводим зарплаты
        for (int i = 0; i < sorted.size(); i++) {
            System.out.println("Salary " + sorted.get(i).getMonthSalary());
        }

        System.out.println();

        System.out.println("Сортируем по убыванию и выводим 15 самых высоких зарплат");
        //выводим 15 самых высоких зарплат в компании
        sorted = company.getLowestSalary(15);
        for (int i = 0; i < sorted.size(); i++) {
            System.out.println("Salary " + sorted
                    .get(i).getMonthSalary());
        }

        //уволняем 50% сотрудников
        System.out.println("Количество сотрудников до увольнения " + company.getCountOfEmployees());
        for (int i = 0; i < 135; i++) {
            company.fire();
        }
        System.out.println("Количество сотрудников После увольнения " + company.getCountOfEmployees());

        System.out.println("Сортируем по возрастанию и выводим 30 самых низких зарплат в компании");
        //для выода результата создаем новый список
        ArrayList<Employee> sortedAfter = new ArrayList<>();
        //выводим 30 самых низких зарплат в компании
        sortedAfter = company.getTopSalaryStaff(30);
        //выводим зарплаты
        for (int i = 0; i < sortedAfter.size(); i++) {
            System.out.println("Salary " + sortedAfter.get(i).getMonthSalary());
        }

        System.out.println();

        System.out.println("Сортируем по убыванию и выводим 15 самых высоких зарплат");
        //выводим 15 самых высоких зарплат в компании
        sortedAfter = company.getLowestSalary(15);
        for (int i = 0; i < sortedAfter.size(); i++) {
            System.out.println("Salary " + sortedAfter
                    .get(i).getMonthSalary());
        }
    }


}