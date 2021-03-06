package com.hufeiya.DAO;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hufeiya.entity.SignInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * SignInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hufeiya.DAO.SignInfo
 * @author MyEclipse Persistence Tools
 */
public class SignInfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SignInfoDAO.class);
	// property constants
	public static final String SIGN_DETAIL = "signDetail";
	public static final String SIGN_TIMES = "signTimes";
	public static final String LAST_SIGN_PHOTO = "lastSignPhoto";

	public void save(SignInfo transientInstance) {
		log.debug("saving SignInfo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void saveSingle(SignInfo transientInstance) {
		log.debug("saving SignInfo instance");
		Session session  = getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(transientInstance);
			tx.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			if(tx != null){
				tx.rollback();
			}
			throw re;
		}
	}
	public boolean updateSingle(int id,String signDetail, int signTimes, String lastSignPhoto){
		Session session = getSession();
		session.beginTransaction();
		String hqlString="update SignInfo  set signDetail=:signDetail , signTimes=:signTimes , lastSignPhoto=:lastSignPhoto where signID=:id";
		Query query = session.createQuery(hqlString);
		query.setString("signDetail", signDetail);
		query.setInteger("signTimes", signTimes);
		query.setString("lastSignPhoto", lastSignPhoto);
		query.setInteger("id", id);
		try {
			query.executeUpdate();
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			log.debug("update signInfo fail!! ");
			e.printStackTrace();
			return false;
		}
		
	}

	public void delete(SignInfo persistentInstance) {
		log.debug("deleting SignInfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SignInfo findById(java.lang.Integer id) {
		log.debug("getting SignInfo instance with id: " + id);
		try {
			SignInfo instance = (SignInfo) getSession().get(
					"com.hufeiya.DAO.SignInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SignInfo instance) {
		log.debug("finding SignInfo instance by example");
		try {
			List results = getSession()
					.createCriteria(SignInfo.class)
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SignInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SignInfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySignDetail(Object signDetail) {
		return findByProperty(SIGN_DETAIL, signDetail);
	}

	public List findBySignTimes(Object signTimes) {
		return findByProperty(SIGN_TIMES, signTimes);
	}

	public List findByLastSignPhoto(Object lastSignPhoto) {
		return findByProperty(LAST_SIGN_PHOTO, lastSignPhoto);
	}

	public List findAll() {
		log.debug("finding all SignInfo instances");
		try {
			String queryString = "from SignInfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SignInfo merge(SignInfo detachedInstance) {
		log.debug("merging SignInfo instance");
		try {
			SignInfo result = (SignInfo) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SignInfo instance) {
		log.debug("attaching dirty SignInfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SignInfo instance) {
		log.debug("attaching clean SignInfo instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}