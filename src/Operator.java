public class Operator extends CompareSalary {
    private final String name;
    private final double salary;

    Operator(String name) {
        this.name = name;
        //генерируем зарплату оператора
        this.salary = (int) (Math.random() * 30_000) + 40;
    }

    public double getMonthSalary() {
        return salary;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(Employee o) {
        return (int)(this.getMonthSalary() - o.getMonthSalary());
    }
}