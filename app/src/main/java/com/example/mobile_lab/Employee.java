package com.example.mobile_lab;


import java.text.DecimalFormat;

public class Employee {

    protected String Name;
    protected double Salary;
    protected double NetSalary;


    public Employee(String a, double b) {
        this.Name = a;
        this.Salary = b;
    }

    public String getName() {
        return Name;
    }

    public void setName(){
        this.Name = Name;
    }

    public double getSalary(){
        return Salary;
    }

    public void setSalary(){
        this.Salary = Salary;
    }

    public double getNetSalary(){
        return NetSalary;
    }

    public void setNetSalary(){
        this.NetSalary = NetSalary;
    }

    public String calculationNetSalary(){
        double a;
        DecimalFormat df = new DecimalFormat("#,###.#");

        a = this.Salary - this.Salary * 0.105;


        if (a <= 11000000){
            NetSalary = a;
        } else {
            double tax = (this.Salary - 11000000) * 0.05;
            NetSalary = a - tax;
        }

        return df.format(NetSalary);
    }
}