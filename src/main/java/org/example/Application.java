package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Application {


    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();

        DBHelper dbHelper = new DBHelper();
        DBManager manager = new DBManager(factory);
        View view = new View();

        dbHelper.initDb();

        view.printEmployeeData(manager.getAllEmployees());
        System.out.println("====================================== ^ ВСЕ СОТРУДНИКИ ^ ======================================");
        manager.deleteEmployeeData(3);
        view.printEmployeeData(manager.getAllEmployees());
        System.out.println("====================================== ^ -1 СОТРУДНИК ^ ======================================");
        manager.addNewEmployee();
        view.printEmployeeData(manager.getAllEmployees());
        System.out.println("====================================== ^ +1 СОТРУДНИК ^ ======================================");
        manager.changeEmployee();
        view.printEmployeeData(manager.getAllEmployees());
        System.out.println("====================================== ^ ИЗМЕНИТЬ СОТРУДНИКА ^ ======================================");

        dbHelper.dropDb();
    }
}