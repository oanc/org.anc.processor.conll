package org.anc.processor.conll

import org.junit.*

import static org.junit.Assert.*

/**
 * Created by danmccormack on 10/20/14.
 */
class AnnotationTest
{

    /**
     * Cases to test: passing (1, all, a few)
     *                failing (none, one wrong in a list of right, all wrong, capital?)
     */
    @Test
    void testValidAnnotations()
    {
        //assuming that an empty annotation selection is an error
        //and will return as false accordingly

        def testy = new Annotation()

        //PASSING ************
        //ONE
        def pass1 = ["C5"] as ArrayList<String>
        assertTrue(testy.validAnnotations(pass1))

        //FEW
        def pass2 = ["PENN", "SENTENCES"] as ArrayList<String>
        assertTrue(testy.validAnnotations(pass2))

        //ALL
        def pass3 = ["PENN", "SENTENCES", "BIBER", "C5", "C7", "CB",
                     "CONTENT", "EVENT", "FN", "FNTOK", "HEPPLE", "LOGICAL",
                     "MPQA", "NC", "NE", "PTB", "NONE", "PTBTOK",
                     "SLATE_COREF", "VC"] as ArrayList<String>

        assertTrue(testy.validAnnotations(pass3))

        //FAILING ***********
        /* Causes an error when
        //NONE
        def fail1 = [] as ArrayList<String>
        assertFalse(testy.validAnnotations(fail1))
         */

        //ALL
        def fail2 = ["thing1", "thing2", "red", "blue"]
        assertFalse(testy.validAnnotations(fail2))

        //ONE WRONG IN LIST OF RIGHT
        def fail3 = ["PENN", "C5", "BIBER", "HEP", "NONE"]
        assertFalse(testy.validAnnotations(fail3))

        //Will capitals matter?
        def fail4 = ["penn", "c5", "biber"]
        assertFalse(testy.validAnnotations(fail4))

    }
    @Test
    /**
     *  Cases to test: empty string, one word, two words, n-words
     */
    void testParseAnnotations()
    {
        def testy = new Annotation()

        //EMPTY STRING
        assertTrue(testy.parseAnnotations("") == [])

        //ONE WORD
        assertTrue(testy.parseAnnotations("PENN").equals(["PENN"]))

        //TWO WORDS
        assertTrue(testy.parseAnnotations("PENN,C5").equals(["PENN","C5"]))

        //N WORDS
        def listy = []
        for (int i =1; i<10; i++)
        {
            listy.add(i.toString())
        }

        assertTrue(testy.parseAnnotations("1,2,3,4,5,6,7,8,9").equals(listy))
    }

}
