import java.util.List;

public class Application {
    public static void main(String[] args) {
        DBManager manager = new DBManager();

        List<Employee> employeeData = manager.getEmployeeData();
        for (Employee e : employeeData) {
            System.out.println(String.format(
                    "last name : %8s |" +
                            "first name : %8s |" +
                            "birth bate : %8s | " +
                            "department : %15s |" +
                            "position : %20s |" +
                            "salary : %8s ",
                    e.getLastName(), e.getFirstName(), e.getBirthDate(),
                    e.getDepartment(), e.getPosition(), e.getSalary()));
        }

        //      manager.updateSalary(10000);
    }
}
