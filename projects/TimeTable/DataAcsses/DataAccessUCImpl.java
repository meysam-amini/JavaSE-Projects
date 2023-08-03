package TimeTable.DataAcsses;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.management.Query;

import TimeTable.exception.gException;





public class DataAccessUCImpl<T> implements DataAccessUC<T> {



	private Class<T> Classtype;
	private String ClassName;

	@SuppressWarnings("unchecked")
	public DataAccessUCImpl()
	{
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = null;
		if (getClass().toString().indexOf(t.toString()) != 0)
		{
			pt = (ParameterizedType) t;
			Classtype = (Class<T>) pt.getActualTypeArguments()[0];
			ClassName = Classtype.getSimpleName();
		}
	}



	@Override
	public String Add(T entity) throws Exception, gException
	{
	//	em.persist(entity);
		return "";
	}


	@Override
	public T Edit(T entity) throws Exception, gException
	{
		//return em.merge(entity);
		return null;
	}


	@Override
	public void Remove(T entity) throws Exception, gException
	{
		//em.remove(entity);   //for eclipselink
		//em.remove(em.contains(entity) ? entity : em.merge(entity)); // for Hibernate
	}



	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Finders 
	@Override
	public List<T> FindbyFields(String fieldName, String operation, Object fieldValue)
	{
	/*	Query query = em.createQuery("SELECT e FROM " + ClassName + " e WHERE e." + fieldName + operation + ":FieldValue");
		query.setParameter("FieldValue", fieldValue);
		return (List<T>) query.getResultList();*/
		return null;
	}

	


	@Override
	public T FindbyId(long Id)
	{
		/*Query query = em.createQuery("SELECT e FROM " + ClassName + " e WHERE e.id=:id");
		query.setParameter("id", Id);
		return (T) query.getSingleResult();*/
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> FindAll(String orderField, String AscDesc)
	{
		/*Query query = em.createQuery("SELECT e FROM " + ClassName + " e ORDER BY e." + orderField + AscDesc);
		return (List<T>) query.getResultList();*/
		return null;
	}


	

	@SuppressWarnings("unchecked")
	@Override
	public List<T> FindAll()
	{
		/*Query query = em.createQuery("SELECT e FROM " + ClassName + " e");
		return (List<T>) query.getResultList();*/
		return null;
	}










}
