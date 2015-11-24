package com.hufeiya.DAO;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hufeiya.entity.StudentSheet;

/**
 * A data access object (DAO) providing persistence and search support for
 * StudentSheet entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.hufeiya.DAO.StudentSheet
 * @author MyEclipse Persistence Tools
 */
public class StudentSheetDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(StudentSheetDAO.class);
	// property constants
	public static final String SHEET_NAME = "sheetName";

	public void save(StudentSheet transientInstance) {
		log.debug("saving StudentSheet instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(StudentSheet persistentInstance) {
		log.debug("deleting StudentSheet instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StudentSheet findById(java.lang.Integer id) {
		log.debug("getting StudentSheet instance with id: " + id);
		try {
			StudentSheet instance = (StudentSheet) getSession().get(
					"com.hufeiya.DAO.StudentSheet", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(StudentSheet instance) {
		log.debug("finding StudentSheet instance by example");
		try {
			List results = getSession()
					.createCriteria("com.hufeiya.DAO.StudentSheet")
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
		log.debug("finding StudentSheet instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from StudentSheet as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySheetName(Object sheetName) {
		return findByProperty(SHEET_NAME, sheetName);
	}

	public List findAll() {
		log.debug("finding all StudentSheet instances");
		try {
			String queryString = "from StudentSheet";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public StudentSheet merge(StudentSheet detachedInstance) {
		log.debug("merging StudentSheet instance");
		try {
			StudentSheet result = (StudentSheet) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(StudentSheet instance) {
		log.debug("attaching dirty StudentSheet instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StudentSheet instance) {
		log.debug("attaching clean StudentSheet instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}