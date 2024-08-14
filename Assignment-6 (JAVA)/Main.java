//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import com.javaTraining.Employee;
public class Main {
    public static void main(String[] args) {
        Employee emp = new Employee();
        emp.setName("Employee1");
        emp.setAge(22);
        emp.setHeight(5.5f);
        emp.setEmpId(1234567890);
        emp.setChild((byte)2);
        emp.setMarried(true);
        emp.setGender('m');
        emp.setSiblings((short) 2);
        emp.setSalary((double)123456789);
        emp.setFriends(new String[]{"harish","ganesh","dinesh"});
       System.out.println(emp.toString());

    }
}
