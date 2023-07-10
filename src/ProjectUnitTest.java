import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectUnitTest {

    private Project project;

    @BeforeEach
    public void setUp() {
        project = new Project("Project", 1, 1, 1, 2, 2, 2);
    }

    @Test


	   public void testCanAddEmployeeWithManagerTrue() {
   
        Manager manager = new Manager();
        assertTrue(project.canAddEmployee(manager));
    }

    public void testCanAddEmployeeWithManagerFalse() {
        Manager manager = new Manager();
        project.addEmployeetoProject(manager);

        Manager anotherManager = new Manager();
        assertFalse(project.canAddEmployee(anotherManager));
    }

    @Test
    public void testCanAddEmployeeWithAnalystTrue() {

        Analyst analyst = new Analyst();
        assertTrue(project.canAddEmployee(analyst));

        project.addEmployeetoProject(analyst);
        Analyst anotherAnalyst = new Analyst();
        assertTrue(project.canAddEmployee(anotherAnalyst));
    }
    
    public void testCanAddEmployeeWithAnalystFalse() {

        Analyst analyst = new Analyst();
        assertTrue(project.canAddEmployee(analyst));

        project.addEmployeetoProject(analyst);
        Analyst anotherAnalyst = new Analyst();
        assertTrue(project.canAddEmployee(anotherAnalyst));

        project.addEmployeetoProject(analyst);
        anotherAnalyst = new Analyst();
        assertFalse(project.canAddEmployee(anotherAnalyst));
    }

    @Test
    public void testCanAddEmployeeWithDesignerFalse() {
        Designer designer = new Designer();
        assertTrue(project.canAddEmployee(designer));

        project.addEmployeetoProject(designer);
        Designer anotherDesigner = new Designer();
        assertTrue(project.canAddEmployee(anotherDesigner));

        project.addEmployeetoProject(designer);
        anotherDesigner = new Designer();
        assertFalse(project.canAddEmployee(anotherDesigner));
    }

        @Test
    public void testCanAddEmployeeWithDesignerTrue() {
        Designer designer = new Designer();
        assertTrue(project.canAddEmployee(designer));

        project.addEmployeetoProject(designer);
        Designer anotherDesigner = new Designer();
        assertTrue(project.canAddEmployee(anotherDesigner));

     
    }

    @Test
    public void testCanAddEmployeeWithProgrammerTrue() {
       
        Programmer programmer = new Programmer();
        assertTrue(project.canAddEmployee(programmer));

        project.addEmployeetoProject(programmer);
        Programmer anotherProgrammer = new Programmer();
        assertTrue(project.canAddEmployee(anotherProgrammer));
    }



}