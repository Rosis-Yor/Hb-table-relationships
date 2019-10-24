package coolschool.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import coolschool.hibernate.demo.entity.Course;
import coolschool.hibernate.demo.entity.Instructor;
import coolschool.hibernate.demo.entity.InstructorDetail;
import coolschool.hibernate.demo.entity.Review;


public class CreateCourseAndReviewsDemo {

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
			
		// create a course
			Course tempCourse = new Course("Pacman - How to Score One Million Points") ;
			
		// add some reviews
		tempCourse.addReview(new Review(" Great course ... I loved it !!!"));
		tempCourse.addReview(new Review("Cool course, job well done !!!"));
		tempCourse.addReview(new Review(" What a dumb course, you are an idiot !!!"));
		
		
		// save the course .. and leverage the cascade all :-)
		System.out.println(" Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());
		session.save(tempCourse);
		
		
			
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
