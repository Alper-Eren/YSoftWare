import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class YSoft {
    public static void main(String[] args) throws Exception {
        
        Accounting accounting = new Accounting();
        Company company=new Company();

    }
    public boolean makeProjectActive(String id) {
    	Company company = new Company();
    	Project project = company.findProjectbyId(id);

    	if(project.canProjectActiveFunc()) {
    	
    		project.activeProject();
    		return true;
    		
    	}
    	return false;
    }

   public  boolean addNewEmployee(String name,String surname,String empType,String workTimeType){
    	Company company= new Company();
        double salary;
        Accounting accounting=new Accounting();
        if(workTimeType.equals("HalfTime")){
            salary=accounting.calculateSalaryForHalfTimeWorker();
        }
        else{
            salary=accounting.calculateSalaryForFullTimeWorker();
        }
        if(empType.equals("Analyst")){
            Analyst newA = new Analyst(name, surname, salary);
           return  company.hireEmployee(newA);

        }
        else if(empType.equals("Designer")){
            Designer newD = new Designer(name, surname, salary);
            return company.hireEmployee(newD);

        }
        else if(empType.equals("Programmer")){
            Programmer newP = new Programmer(name, surname, salary);
           return  company.hireEmployee(newP);
        }
        else{
            //manager
            Manager newM = new Manager(name, surname, salary);
           return company.hireEmployee(newM);
        }
    }
    void addNewProject(String name,int minAnalyst,int minDesigner,int minProgrammer,int maxAnalyst,int maxDesigner,int maxProgrammer){
        Company com = new Company();
    	Project newP = new Project(name, minAnalyst, minDesigner, minProgrammer, maxAnalyst, maxDesigner, maxProgrammer);
        com.addProject(newP);

    }
    void finishProject(String id){
    	Company com = new Company();
    	com.finishProject(com.findProjectbyId(id));
    	
    	
    	
    }
}
    



