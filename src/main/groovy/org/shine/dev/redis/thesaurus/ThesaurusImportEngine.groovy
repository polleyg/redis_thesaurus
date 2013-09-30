package org.shine.dev.redis.thesaurus

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate

/**
 * A simple groovy engine to import a thesaurus into a Redis instance.
 * @author Graham Polley | Shine Technologies | http://www.shinetech.com.au/
 */
class ThesaurusImportEngine {
    @Autowired
    private StringRedisTemplate redisTemplate
    private String delimiter
    private String thesaurus

    /**
     * Flushes Redis and imports the new thesaurus
     */
    void importThesaurus() {
        println('Flushing Redis for new import of thesaurus..')
        redisTemplate.getConnectionFactory().getConnection().flushDb()

        def rootWord

        println('Processing thesaurus raw file and building Redis db..')
        new File(thesaurus).eachLine { line ->
            def split = line.split(delimiter)
            if (split.last().isNumber()) {//indicates root word
                rootWord = split.first()
            } else {
                redisTemplate.opsForSet().add(rootWord, line)
            }
        }
        println('Finished. Now go learn some new words!')
    }

    void setDelimiter(String delimiter) {
        this.delimiter = delimiter
    }

    void setThesaurus(String thesaurus) {
        this.thesaurus = thesaurus
    }

    @Override
    String toString() {
        return 'Thesaurus Import Engine: $delimiter:$thesaurus'
    }
}