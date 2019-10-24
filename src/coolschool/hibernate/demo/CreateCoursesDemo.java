package coolschool.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import coolschool.hibernate.demo.entity.Course;
import coolschool.hibernate.demo.entity.Instructor;
import coolschool.hibernate.demo.entity.InstructorDetail;
import coolschool.hibernate.demo.entity.Review;

public class CreateCoursesDemo {

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
			
			
			// create the objects
			Instructor tempInstructor =
					new Instructor("Susan", "Public", "susan@luv2code.com") ;
			
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("http://www.youtube.com/luv2code",
						" Video Games !!! ");
			
			// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			
			// start a transaction
			session.beginTransaction() ;
			
						
			// save the instructor
			//
			// Note : this will ALSO save the details object
			// because of CascadeType.ALL
			// 
			System.out.println(" Saving instructor : " + tempInstructor);
			session.save(tempInstructor) ;
			
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
