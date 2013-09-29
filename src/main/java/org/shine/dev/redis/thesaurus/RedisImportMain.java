package org.shine.dev.redis.thesaurus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RedisImportMain {
    //Begin the import of the Thesaurus
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("redis-thesaurus-config.xml");
        ThesaurusImportEngine engine = context.getBean(ThesaurusImportEngine.class);
        engine.importThesaurus();
    }
}
