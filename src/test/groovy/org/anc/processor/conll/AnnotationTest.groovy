package org.anc.processor.conll

import org.junit.Test
import static org.junit.Assert.*

/**
 * Created by danmccormack on 10/20/14.
 */
class AnnotationTest {
    @Test
    /**
     * Cases to test: passing (1, all, a few)
     *                failing (none, one wrong in a list of right, all wrong, spaces in words?)
     */
    void testValidAnnotations() {
        //assuming that an empty annotation selection is an error
        //and will return as false accordingly
        def pass1 = ["PENN", "SENTENCES"]
        println(pass1)
        assertTrue new Annotation().validAnnotations(pass1)
    }
}
