package lab2.kamysh.logic;

import java.rmi.RemoteException;

import lab2.kamysh.entity.SessionFactoryBuilder;
import lab2.kamysh.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;

public class LoginHandlerImpl implements LoginHandler {

    @Override
    public boolean registry(String[] params) throws RemoteException {
        User user = User.builder()
                .login(params[0])
                .password(getHashPass(params[1]))
                .build();
        try {
            save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean login(String[] params) throws RemoteException {
        User user = UserHandler.getUserByLogin(params[0]);
        return user.getPassword().equals(getHashPass(params[1]));
    }

    @Override
    public boolean isLoginExist(String login) throws RemoteException {
        Session session = openSession();
        CriteriaBuilder cbCount = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaCount = cbCount.createQuery(Long.class);
        Root<User> rootCount = criteriaCount.from(User.class);
        criteriaCount.select(cbCount.count(rootCount));
        criteriaCount.where(cbCount.equal(rootCount.get("login"), login));
        Query queryCount = session.createQuery(criteriaCount);
        Long total = (Long) queryCount.getSingleResult();
        session.close();
        return total.equals(0L);
    }

    private Session openSession() {
        SessionFactory sessionFactory = SessionFactoryBuilder.getSessionFactory();
        return sessionFactory.openSession();
    }

    public EntityManager createEntityManager() {
        SessionFactory sessionFactory = SessionFactoryBuilder.getSessionFactory();
        return sessionFactory.createEntityManager();
    }

    private String getHashPass(String pass){
        return DigestUtils.md5Hex(pass);
    }

    private void save(User user){
        EntityManager em = createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
}
