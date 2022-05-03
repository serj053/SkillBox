import java.util.ArrayList;
import java.util.List;

public class Company {
    public static void main(String[] args) {
        System.out.println(Math.random());
    }

    double income;

    //список сотрудников
    ArrayList<Employee> list = new ArrayList<>();


    //найм одного сотрудника
    public void hire(Employee e) {
        list.add(e);
    }

    //найм списка сотрудников
    public void hireAll(List es) {
        list.addAll(es);
    }

    //увольнение сотрудника
    public void fire(int re) {
        list.remove(re);
    }

    //получение значения дохода компании
    public double getIncome() {
        return income;
    }
//
//    //возвращает список отсортированный по возрастанию заработной платы
//    List<Employee> getTopSalaryStaff(int count) {
//
//        return
//    }
//
//    //возвращает список отсортированный по убыванию заработной платы.
//    List<Employee> getLowestSalaryStaff(int count) {
//        return
//    }
}


