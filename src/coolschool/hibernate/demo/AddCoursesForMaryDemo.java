package coolschool.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import coolschool.hibernate.demo.entity.Course;
import coolschool.hibernate.demo.entity.Instructor;
import coolschool.hibernate.demo.entity.InstructorDetail;
import coolschool.hibernate.demo.entity.Review;
import coolschool.hibernate.demo.entity.Student;


public class AddCoursesForMaryDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory() ;				
		
		// create session
		Session session = factory.getCurrentSession() ;
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
		
			// get student ary from database
			int studentId = 2;
			Student tempStudent = session.get(Student.class, studentId) ;
			
			
			System.out.println(" \n Loaded student : " + tempStudent);
			System.out.println(" Course : " + tempStudent.getCourses());
			
			// create more courses
			Course tempCourse1 = new Course(" How to caress the cat correctly");
			Course tempCourse2 = new Course(" How much sleeps a cat");
			
			// add student to courses
			tempCourse1.addStudent(tempStudent);
			tempCourse2.addStudent(tempStudent);
			
			// save the courses
			System.out.println("\n Saving the courses ... ");
			
			session.save(tempCourse1) ;
			session.save(tempCourse2) ;
			
			// commit transaction
			session.getTransaction().commit();
			
			//close session
			System.out.println(" Done ! ");
			
		session.close();
		}
		finally {
			
			//add clean up code
			session.close();
			factory.close();
		}
		
		
	}

}
