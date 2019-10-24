package coolschool.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import coolschool.hibernate.demo.entity.Course;
import coolschool.hibernate.demo.entity.Instructor;
import coolschool.hibernate.demo.entity.InstructorDetail;
import coolschool.hibernate.demo.entity.Review;

public class GetCourseAndReviewsDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory() ;				
		
		// create session
		Session session = factory.getCurrentSession() ;
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
		// get the course
			int theId = 10;
			Course tempCourse = session.get(Course.class, theId) ;
			
		// print the course
			System.out.println(tempCourse);
			
		// print the course reviews
		System.out.println(tempCourse.getReviews());
			
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
