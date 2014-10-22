package org.anc.processor.conll

import org.junit.Test

/**
 * Created by danmccormack on 10/20/14.
 */
class AnnotationTest
{
    String [] passingArray = ["PENN", "SENTENCES", "BIBER", "C5", "C7", "CB",
                              "CONTENT", "EVENT", "FN", "FNTOK", "HEPPLE", "LOGICAL",
                              "MPQA", "NC", "NE", "PTB", "NONE", "PTBTOK",
                              "SLATE_COREF", "VC"]

    @Test
    void testValidAnnotations()
    {
        //assuming that an empty annotation selection is an error
        //and will return as false accordingly
        assertTrue Annotation.validAnnotations(["PENN", "SENTENCES"])
    }
}
