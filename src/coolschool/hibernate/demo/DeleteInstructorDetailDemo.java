                                                               package coolschool.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import coolschool.hibernate.demo.entity.Instructor;
import coolschool.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory() ;				
		
		// create session
		Session session = factory.getCurrentSession() ;
		
		try {
			
			// start a transaction
			session.beginTransaction() ;
			
			// get the InstructorDetail object
			int theId = 3;
			InstructorDetail tempInstructorDetail = 
					session.get(InstructorDetail.class, theId) ;
			
			// print instructor detail
			System.out.println("tempInstructorDetail : " + tempInstructorDetail);
	
			
			// print the associated instructor
			System.out.println(" The associated instructor : " + tempInstructorDetail.getInstructor());

			
			// now let's delete instructor detail
			System.out.println(" Deleting tempInstructorDetail : " 
			+ tempInstructorDetail);
			
			// remove the associated object reference
			// break bi-directional link
			
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			session.delete(tempInstructorDetail);
			
			//	commit transaction
			session.getTransaction().commit();
			
			//close session
			System.out.println(" Done ! ");
			
		session.close();
		}
		catch (Exception exc) {
			
			exc.printStackTrace();
		}
		finally {
			// handle connection leak issue
			session.close();
			
			factory.close();
		}
		
		
	}

}
