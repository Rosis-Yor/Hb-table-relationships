package coolschool.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import coolschool.hibernate.demo.entity.Course;
import coolschool.hibernate.demo.entity.Instructor;
import coolschool.hibernate.demo.entity.InstructorDetail;
import coolschool.hibernate.demo.entity.Review;
import coolschool.hibernate.demo.entity.Student;


public class DeletePacmanCourseDemo {

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
			
		
			// get course from db by id
			int courseId = 10;
			Course tempCourse = session.get(Course.class, courseId) ;
			 
			//delete the course
		System.out.println(" Deleting course : " + tempCourse);
		
		session.delete(tempCourse);
			
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
