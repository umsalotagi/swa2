package com.swapasya.model;
import java.lang.reflect.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.util.ReflectionUtils;
public class CascadeCallback implements ReflectionUtils.FieldCallback

{
	Object source;
	MongoOperations mongoOperations;

	public CascadeCallback(Object source, MongoOperations mongoOperations) {
		// TODO Auto-generated constructor stub
		this.source=source;
		this.mongoOperations=mongoOperations;;
	}

	@Override
	public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
		
		ReflectionUtils.makeAccessible(field);
		 
	    if (field.isAnnotationPresent(DBRef.class) && 
	      field.isAnnotationPresent(CascadeSave.class)) {
	     
	        Object fieldValue = field.get(source);
	        if (fieldValue != null) {
	            FieldCallBack callback = new FieldCallBack();
	            ReflectionUtils.doWithFields(fieldValue.getClass(), callback);
	 
	           mongoOperations.save(fieldValue);
	}

}
	}
}
