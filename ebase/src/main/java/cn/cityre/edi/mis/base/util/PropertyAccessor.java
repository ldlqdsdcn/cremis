package cn.cityre.edi.mis.base.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoaderListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



/**
 * This class acts as a utility class to fetch the property from the class path
 * property files referred in applicaiton context
 * 
 * Also, this class would fetch the property based on the
 * fallbackPropertyConfigurer and propertyConfigurer and based on the order
 * definition for each definition
 * 
 * @author AnandhKumar_J
 * 
 */
@Component
public class PropertyAccessor {

	@Autowired
	private AbstractBeanFactory beanFactory;

	/**
	 * Property cache
	 */
	private static Map<String, String> cache = null;

	/**
	 * Instance creation for Cache
	 */
	public PropertyAccessor() {
		cache = new ConcurrentHashMap<String, String>();
	}

	/**
	 * This function would check whether the property is available in the cache.
	 * If it is already available, then the value is returned form the cahce, if
	 * not,then it would be fetched from the property file using abstract bean
	 * factory implementation
	 * 
	 * @param key
	 * @return
	 * @throws
	 */
	public static String getProperty(String key) throws Exception {
		if (cache.containsKey(key)) {
			return cache.get(key);
		}

		PropertyAccessor propertyAccessor = ContextLoaderListener
				.getCurrentWebApplicationContext().getBean(
						PropertyAccessor.class);
		return propertyAccessor.getPropertyFromBean(key);

	}

	/**
	 * Retrieve property value from bean factory
	 * @param key
	 * @return
	 * @throws
	 */
	public String getPropertyFromBean(String key)
			throws Exception {
		String foundProp = null;
		try {
			foundProp = beanFactory.resolveEmbeddedValue("${" + key.trim()
					+ "}");
			cache.put(key, foundProp);
		} catch (IllegalArgumentException ex) {
			throw new Exception(ex);
		}
		return foundProp;
	}

}
