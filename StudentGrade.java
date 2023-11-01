import java.util.Scanner;
public class StudentGrade{
		public static void main (String[] args)
	{
		 Scanner sc=new Scanner(System.in);
		 System.out.println("Enter the number of subjects that you have:");
		 int numOfSubjects=sc.nextInt();
		 int totalMarks=0;
		 for(int i=1;i<=numOfSubjects ;i++)
		 {
			  System.out.println("Enter the number of marks that you obtain in subjects "+ i+":");
			  int  marks=sc.nextInt();
			  totalMarks+=marks;
		 }
		 double average =(double) totalMarks/numOfSubjects;
		 String grade;
		 if (average>=90)
		 {
			 grade="O";
		 }
		 else if(average>=80)
		 {
			 grade="A";
			 
		 }
		 else if(average>=70)
		 {
			 grade="B";
			 
		 }
		 else if(average>=60)
		 {
			 grade="C";
			 
		 }
		 else if(average>=50)
		 {
			 grade="D";
			 
		 }
		 else
		 {
			 grade="F";
			 
		 }
		 System.out.println("TOTAL MARKS:" +totalMarks);
		 System.out.println("AVERAGE PERCENTAGE:"+average);
		 System.out.println("GRADE:" +grade);
		 
		 sc.close();
		 
	}
	
}