package com.iems.security.web.authentication.rememberme;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.util.Assert;

public class EhCacheTokenRepositoryImpl implements PersistentTokenRepository, InitializingBean {
	//~ Static fields/initializers =====================================================================================

    private static final Log logger = LogFactory.getLog(EhCacheTokenRepositoryImpl.class);

    //~ Instance fields ================================================================================================

    private Ehcache cache;

    //~ Methods ========================================================================================================

    public Ehcache getCache() {
        return cache;
    }
    
	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		Element element = cache.get(token.getSeries());
		
		if (logger.isDebugEnabled()) {
            logger.debug("Cache hit: " + (element != null) + "; series: " + token.getSeries());
        }

        if (element != null) {
            SerializablePersistentRememberMeToken current = (SerializablePersistentRememberMeToken) element.getValue();

            if (current != null) {
                throw new DataIntegrityViolationException("Series Id '"+ token.getSeries() +"' already exists!");
            }
            
            cache.remove(token.getUsername());
            cache.remove(token.getSeries());
        }
        
        SerializablePersistentRememberMeToken serialToken = 
        		new SerializablePersistentRememberMeToken(token.getUsername(), token.getSeries(), token.getTokenValue(), token.getDate());
        
        cache.put(new Element(serialToken.getSeries(), serialToken));
        System.out.println("put serialToken["+serialToken.toPlainString()+"] into cache");
        
        Set<String> usernameSeries = new HashSet<String>();
        usernameSeries.add(serialToken.getSeries());
        cache.put(new Element(serialToken.getUsername(), usernameSeries));
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		PersistentRememberMeToken token = getTokenForSeries(series);
		
		/*
		Set<String> usernameSeries = new HashSet<String>();
        Element eleUsernameSeries = cache.get(token.getUsername());
        if (eleUsernameSeries != null) {
        	usernameSeries = (Set<String>) eleUsernameSeries.getValue();
        }
		 */
		
		SerializablePersistentRememberMeToken newSerialToken = 
				new SerializablePersistentRememberMeToken(token.getUsername(), series, tokenValue, new Date());

        // Store it, overwriting the existing one.
        cache.put(new Element(series, newSerialToken));
        System.out.println("put newSerialToken["+newSerialToken.toPlainString()+"] into cache");
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		System.out.println("will get token["+seriesId+"] from cache");
		
		Element element = cache.get(seriesId);
		
		if (element == null) {
            return null;
        }
		
		SerializablePersistentRememberMeToken serialToken = 
				(SerializablePersistentRememberMeToken) element.getValue();
		
		System.out.println("get serialToken["+serialToken.toPlainString()+"] from cache");
		
		return serialToken.toPersistentRememberMeToken();
	}

	@Override
	public void removeUserTokens(String username) {
		Set<String> usernameSeries = new HashSet<String>();
        Element eleUsernameSeries = cache.get(username);
        if (eleUsernameSeries != null) {
        	usernameSeries = (Set<String>) eleUsernameSeries.getValue();
        }

		Iterator<String> series = usernameSeries.iterator();

        while (series.hasNext()) {
            String seriesId = series.next();

            PersistentRememberMeToken token = getTokenForSeries(seriesId);

            if (username.equals(token.getUsername())) {
            	cache.remove(token.getSeries());
            }
        }
        
        cache.remove(username);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cache, "cache mandatory");
	}

	public void setCache(Ehcache cache) {
        this.cache = cache;
    }
}
