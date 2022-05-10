class Manager implements Employee {
    private final String name;
    private final double salary;

    Manager(String name) {
        this.name = name;
        //формирование бонуса в виде 5% от заработанных для компании денег
        //от 115000 до 140000
        int bonus = 5 * ((int) (Math.random() * 25_000) + 115_000) / 100;
        this.salary = (int) (Math.random() * 50_000) + 50_000 + bonus;
    }

    public String getName() {
        return this.name;
    }

    public double getMonthSalary() {
        return salary;
    }

}

