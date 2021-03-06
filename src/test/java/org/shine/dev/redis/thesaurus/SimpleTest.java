package org.shine.dev.redis.thesaurus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis-thesaurus-config.xml")
public class SimpleTest {
    @Autowired
    private ThesaurusImportEngine engine;

    @Test
    public void testWiredCorrectly() {
        assertNotNull(engine.toString());
    }
}
