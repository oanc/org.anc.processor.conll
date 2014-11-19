package org.anc.processor.conll

import org.anc.index.api.Index
import org.anc.index.core.IndexImpl
import org.anc.processor.conll.i18n.Messages
import org.junit.*

import javax.ws.rs.core.Response

import static org.junit.Assert.*

/**
 * Created by danmccormack on 10/20/14.
 */
class ProcessorTest
{
    private static final Messages MESSAGES = new Messages()

    ConllProcessor processor

    @Before
    void setup() {
        processor = new ConllProcessor(["f.penn", "f.s", "f.cb", "f.event", "f.fn", "f.fntok",
                                        "f.logical", "f.mpqa", "f.ns", "f.ne", "f.ptb", "f.ptbtok",
                                        "f.vc"] as HashSet<String>)
    }

    @After
    void cleanup() {
        processor = null
    }


    /**
     * Cases to test: passing (1, all, a few)
     *                failing (none, one wrong in a list of right, all wrong, capital)
     */
    @Test
    void testValidAnnotations()
    {
        //PASSING ************
        //ONE
        def pass1 = ["f.penn"] as ArrayList<String>
        assertTrue(processor.validAnnotations(pass1, processor.ACCEPTABLE))

        //FEW
        def pass2 = ["f.penn", "f.s"] as ArrayList<String>
        assertTrue(processor.validAnnotations(pass2, processor.ACCEPTABLE))

        //ALL
        assertTrue(processor.validAnnotations(processor.ACCEPTABLE as ArrayList<String>, processor.ACCEPTABLE))

        //FAILING ***********
        /* Causes an error when
        //NONE
        def fail1 = [] as ArrayList<String>
        assertFalse(testy.validAnnotations(fail1))
         */

        //ALL
        def fail2 = ["thing1", "thing2", "red", "blue"]
        assertFalse(processor.validAnnotations(fail2, processor.ACCEPTABLE))

        //ONE WRONG IN LIST OF RIGHT
        def fail3 = ["penn", "f.cb", "f.penn", "f.ne"]
        assertFalse(processor.validAnnotations(fail3, processor.ACCEPTABLE))

        //Capitals
        def fail4 = ["F.PENN"]
        assertFalse(processor.validAnnotations(fail4, processor.ACCEPTABLE))

    }
    @Test
    /**
     *  Cases to test: empty string, one word, two words, n-words
     */
    void testParseAnnotations()
    {
        //EMPTY STRING
        // The empty string should result in ALL annotation types being selected
        assertTrue("Empty string should return all acceptable annotations.", processor.parseAnnotations("") == ConllProcessor.ACCEPTABLE.toList())

        //ONE WORD
        List expected = ["f.penn"]
        List actual = processor.parseAnnotations("penn")
        assertTrue "Actual is " + actual, actual == expected

        //TWO WORDS
        expected = ["f.penn", "f.cb"]
        assertTrue(processor.parseAnnotations("penn,cb") == expected)

        //N WORDS
        // Use Groovy range operator to populate the collection, then add the 'f.' prefix.
        expected = (1..9).collect { "f." + it }
        actual = processor.parseAnnotations("1,2,3,4,5,6,7,8,9")
        assertTrue("Expected is " + expected + " Actual is " + actual, actual == expected)
    }

    @Test
    void testProcess() {
        Response response = processor.process("penn, s", "MASC3-0202")
        assertTrue response.entity, response.status == 200
    }

    @Test
    void testInvalidDocId() {
        Response response = processor.process("penn, s", "Invalid ID")
        assertTrue response.entity, response.status == 500
        assertTrue "Wrong error message returned", response.entity == MESSAGES.INVALID_ID
    }

    @Test
    void testInvalidAnnotationType() {
        Response response = processor.process("invalid,annotations", "MASC3-0202")
        assertTrue response.entity, response.status == 500
        assertTrue "Wrong error message returned.", response.entity == MESSAGES.INVALID_TYPE
    }

}
