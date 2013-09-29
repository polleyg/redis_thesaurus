package org.shine.dev.redis.thesaurus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Application entry. Using Java simply to show the easy interoperability between it and Groovy.
 *
 * @author Graham Polley | Shine Technologies | http://www.shinetech.com.au/
 */
public class RedisImportMain {
    private static final String APP_CONTEXT = "redis-thesaurus-config.xml";

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(APP_CONTEXT);
        ThesaurusImportEngine engine = context.getBean(ThesaurusImportEngine.class);
        engine.importThesaurus();
    }
}
