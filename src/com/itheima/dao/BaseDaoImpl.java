package com.itheima.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.domain.PageBean;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	private Class clazz;
	
	public BaseDaoImpl() {
		// 拿到子类的class对象
		Class c = this.getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType){
			ParameterizedType pType = (ParameterizedType) type;
			Type[] types = pType.getActualTypeArguments();
			this.clazz = (Class) types[0];
		}
	}

	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public T findById(Long id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from " + clazz.getSimpleName());
	}

	@Override
	public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		PageBean<T> pageBean = new PageBean<T>();
		pageBean.setPageSize(pageSize);
		pageBean.setPageCode(pageCode);
		
		// 保存总记录数
		criteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if (list != null && list.size() > 0){
			int totalCount = list.get(0).intValue();
			pageBean.setTotalCount(totalCount);
		}
		// 保存记录
		criteria.setProjection(null);
		List<T> list2 = (List<T>) this.getHibernateTemplate().findByCriteria(criteria, (pageBean.getPageCode() - 1) * pageBean.getPageSize(), pageBean.getPageSize());
		pageBean.setBeanList(list2);
		return pageBean;
	}

}
