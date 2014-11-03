package org.anc.processor.conll

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam
import javax.xml.ws.Response
import java.lang.reflect.Array

/**
 * Created by danmccormack on 10/31/14.
 */
class Annotation {
    @GET
    Response Process(@QueryParam('annotations') String annotations,
                     @QueryParam('docID') String docID)
    {
        def AnnotationsArray = parseAnnotations(annotations)

        if (validAnnotations(AnnotationsArray))
        {
            //call the processor with the selected annotations
        }
    }

    // conll can handle all combinations of annotations
    def AcceptableAntns = ["PENN", "SENTENCES", "BIBER", "C5", "C7", "CB",
                           "CONTENT", "EVENT", "FN", "FNTOK", "HEPPLE", "LOGICAL",
                           "MPQA", "NC", "NE", "PTB", "NONE", "PTBTOK",
                           "SLATE_COREF", "VC"]

    /**
     * Check if the annotations provided are acceptable for conll processing
     * @param antnArray - ArrayList<String> of annotations
     * @return A boolean response if the annotations passed in are acceptable for
     * conll processing.
     */
    def validAnnotations (antnArray)
    {
        boolean returnval = true
        if (antnArray == [])
        {
            return false
        }
        else {
            for (antn in antnArray)
            {
                // if any of the annotations in the array equal the current annotation
                if (!AcceptableAntns.any { it.equals(antn) })
                {
                    returnval = false
                    break
                }
            }
            return returnval
        }
    }

    /**
     * Split the comma separated string into an ArrayList<String>
     * @param antnString - The comma separated string of selected annotations
     * @return An ArrayList<String> of the selected annotations
     */
    def parseAnnotations(antnString)
    {
        if (antnString == "")
        {
            return []
        } else {
            def retArray = antnString.split(',')
            return retArray
        }
    }
}
