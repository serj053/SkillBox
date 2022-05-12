public class TopManager extends CompareSalary {
    private final String name;
    private final double salary;

    TopManager(String name, Company2 company) {
        this.name = name;
        //формирование зарплаты сотоящей из фиксированной части
        // и бонуса в 150% от зп если доход компании превысил 10 млн.
        double salary = (int) (Math.random() * 50_000) + 70_000;
        if (company.getIncome() > 10_000_000) {
            this.salary = salary + salary * 1.5;
        } else {
            this.salary = salary;
        }
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
