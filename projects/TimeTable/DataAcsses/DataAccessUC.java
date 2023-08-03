package TimeTable.DataAcsses;

import java.util.List;

import TimeTable.exception.gException;

public interface DataAccessUC<T> {
	public String Add(T entity) throws Exception, gException;

	public T Edit(T entity) throws Exception, gException;

	public void Remove(T entity) throws Exception, gException;

	public List<T> FindAll();

	public List<T> FindAll(String oderField, String AscDesc);


	public T FindbyId(long Id);

	public List<T> FindbyFields(String fieldName, String operation, Object fieldValue);

	

	

}
