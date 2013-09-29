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
     * Imports the configured thesaurus, flushing the old one before doing so
     */
    void importThesaurus() {
        redisTemplate.getConnectionFactory().getConnection().flushDb()

        def rootWord, synonyms

        new File(thesaurus).splitEachLine(delimiter) { fields ->
            rootWord = fields.first()
            synonyms = fields.subList(1, fields.size() - 1)
            redisTemplate.opsForSet().add(rootWord, synonyms.toArray())
        }
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