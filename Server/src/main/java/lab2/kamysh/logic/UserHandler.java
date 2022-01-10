package lab2.kamysh.logic;

import lab2.kamysh.entity.SessionFactoryBuilder;
import lab2.kamysh.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserHandler {

    public static User getUserByLogin(String login){
        Session session = openSession();
        CriteriaBuilder cbCount = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaCount = cbCount.createQuery(User.class);
        Root<User> rootCount = criteriaCount.from(User.class);
        criteriaCount.select(rootCount);
        criteriaCount.where(cbCount.equal(rootCount.get("login"), login));
        Query queryCount = session.createQuery(criteriaCount);
        return  (User) queryCount.getSingleResult();

    }

    private static Session openSession() {
        SessionFactory sessionFactory = SessionFactoryBuilder.getSessionFactory();
        return sessionFactory.openSession();
    }
}
