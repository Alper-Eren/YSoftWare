import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class YSoftTest {
    @Test
    void testAddNewEmployeeAnalyst() {
        YSoft ySoft = new YSoft();

        String name = "Alper";
        String surname = "Eren";
        String empType = "Analyst";
        String workTimeType = "FullTime";

        boolean result = ySoft.addNewEmployee(name, surname, empType, workTimeType);

        assertFalse(result);
    }

    @Test
    void testAddNewEmployeeDesigner() {
        YSoft ySoft = new YSoft();

        String name = "Berkay";
        String surname = "Demirhan";
        String empType = "Designer";
        String workTimeType = "HalfTime";

        boolean result = ySoft.addNewEmployee(name, surname, empType, workTimeType);

        assertFalse(result);
    }

    @Test
    void testAddNewEmployeeProgrammer() {

        YSoft ySoft = new YSoft();
        String name = "Yigit";
        String surname = "Sokel";
        String empType = "Programmer";
        String workTimeType = "FullTime";

        boolean result = ySoft.addNewEmployee(name, surname, empType, workTimeType);

        assertFalse(result);
    }
    
    
    @Test
    void testAddNewEmployeeManager() {
        YSoft ySoft = new YSoft();

        String name = "Yasemin";
        String surname = "Atmaca";
        String empType = "Manager";
        String workTimeType = "HalfTime";

        boolean result = ySoft.addNewEmployee(name, surname, empType, workTimeType);

        assertFalse(result);
    }

}