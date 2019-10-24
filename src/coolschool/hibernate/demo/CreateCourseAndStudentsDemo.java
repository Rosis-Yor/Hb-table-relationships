package coolschool.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import coolschool.hibernate.demo.entity.Course;
import coolschool.hibernate.demo.entity.Instructor;
import coolschool.hibernate.demo.entity.InstructorDetail;
import coolschool.hibernate.demo.entity.Review;
import coolschool.hibernate.demo.entity.Student;


public class CreateCourseAndStudentsDemo {

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
			
		// create a course
			Course tempCourse = new Course(" How to organize memorable lunch") ;
			
		
		
		// save the course .. and leverage the cascade all :-)
		System.out.println(" \n Saving the course");
		
		//System.out.println(tempCourse.getReviews());
		session.save(tempCourse);
		System.out.println(" Saved the course : " + tempCourse);
		
		// create the students
		Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com") ;
		Student tempStudent2 = new Student("Mary", "Coding", "mary@luv2code.com") ;
		
		// add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);
		
		// save the students
		System.out.println("\n Saving students");
		session.save(tempStudent1) ;
		session.save(tempStudent2) ;
		
		System.out.println(" Saved students : " + tempCourse.getStudents());
		
			
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
